package me.rhari.pastoidlib;


import java.io.IOException;

import me.rhari.pastoidlib.model.Visibility;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


class PastebinApiInterceptor implements Interceptor {

    private final String mApiDevKey;
    private String mApiUserKey;

    PastebinApiInterceptor(String apiDevKey, String apiUserKey) {
        mApiDevKey = apiDevKey;
        mApiUserKey = apiUserKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (originalRequest.method().equals("GET")) {
            return chain.proceed(originalRequest);
        }

        String url = originalRequest.url().toString();
        boolean loginAttempt = url.endsWith(Constants.Urls.PATH_API_LOGIN);
        boolean authenticationRequired = originalRequest.header(
                Constants.Headers.FIELD_NAME_AUTHENTICATION_REQUIRED) != null;
        String apiOption = originalRequest.header(Constants.Headers.FIELD_NAME_API_OPTION);
        RequestBody newRequestBody = createNewRequestBody(originalRequest.body(),
                authenticationRequired, apiOption);
        Request newRequest = originalRequest.newBuilder()
                .post(newRequestBody)
                .removeHeader(Constants.Headers.FIELD_NAME_AUTHENTICATION_REQUIRED)
                .removeHeader(Constants.Headers.FIELD_NAME_API_OPTION)
                .build();

        Response originalResponse = chain.proceed(newRequest);
        return createCorrectedResponse(originalResponse, loginAttempt);
    }

    private RequestBody createNewRequestBody(RequestBody originalRequestBody,
                                             boolean authenticationRequired, String apiOption) {
        FormBody.Builder newRequestBodyBuilder = new FormBody.Builder();

        if (originalRequestBody instanceof FormBody) {
            FormBody originalFormBody = (FormBody) originalRequestBody;
            for (int i = 0;i < originalFormBody.size();i++) {
                String fieldName = originalFormBody.encodedName(i);
                String fieldValue = originalFormBody.encodedValue(i);
                if (fieldName.equals(Constants.Fields.API_PASTE_PRIVATE) &&
                        Visibility.parse(fieldValue) == Visibility.PRIVATE) {
                    authenticationRequired = true;
                }
                newRequestBodyBuilder.addEncoded(originalFormBody.encodedName(i),
                        originalFormBody.encodedValue(i));
            }
        }
        newRequestBodyBuilder.add(Constants.Fields.API_DEV_KEY, mApiDevKey);
        if (authenticationRequired) {
            newRequestBodyBuilder.add(Constants.Fields.API_USER_KEY, mApiUserKey);
        }
        if (apiOption != null) {
            newRequestBodyBuilder.add(Constants.Fields.API_OPTION, apiOption);
        }
        return newRequestBodyBuilder.build();
    }

    private Response createCorrectedResponse(Response originalResponse, boolean loginAttept)
            throws IOException {
        if (!originalResponse.isSuccessful()) {
            return originalResponse;
        }

        ResponseBody originalResponseBody = originalResponse.body();
        int newResponseCode;
        ResponseBody newResponseBody;

        if (originalResponseBody != null) {
            String originalResponseContent = originalResponseBody.string();
            if (!isResponseGood(originalResponseContent)) {
                newResponseCode = 400;
            } else {
                newResponseCode = originalResponse.code();
                if (loginAttept) {
                    mApiUserKey = originalResponseContent;
                }
            }
            newResponseBody = ResponseBody.create(originalResponseBody.contentType(),
                    originalResponseContent);
        } else {
            newResponseCode = originalResponse.code();
            newResponseBody = null;
        }

        return originalResponse.newBuilder()
                .body(newResponseBody)
                .code(newResponseCode)
                .build();
    }

    private boolean isResponseGood(String rawResponse) {
        return rawResponse != null && !rawResponse.startsWith(
                Constants.RESPONSE_PREFIX_BAD_API_REQUEST);
    }
}
