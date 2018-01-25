package me.rhari.pastoidlib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;

//TODO: Finish writing tests and JavaDoc
public class PastebinApiServiceTest {

    private static final String API_DEV_KEY = "apiDevKey";
    private static final String API_USER_KEY = "apiUserKey";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private Map<String, String> mRequiredFields;
    private MockWebServer mServer;

    private PastebinApiService mApiService;

    @Before
    public void setup() {
        mRequiredFields = new HashMap<>();
        mRequiredFields.put(Constants.Fields.API_DEV_KEY, API_DEV_KEY);
        mServer = new MockWebServer();
        PastebinApiServiceFactory factory = PastebinApiServiceFactory.create(API_DEV_KEY);
        mApiService = factory.createApiServiceForTesting(mServer.url("").toString());
    }

    @Test
    public void tryLogin_withSuccessfulResponse() throws IOException, InterruptedException {
        enqueueResponse(API_USER_KEY, 200);

        String apiUserKey = mApiService.tryLogin(USERNAME, PASSWORD).execute().body();

        RecordedRequest request = mServer.takeRequest();
        assertRequestPath(request, Constants.Urls.PATH_API_LOGIN);
        mRequiredFields.put(Constants.Fields.API_USER_NAME, USERNAME);
        mRequiredFields.put(Constants.Fields.API_USER_PASSWORD, PASSWORD);
        assertEquals(getFields(request), mRequiredFields);
        assertEquals(apiUserKey, API_USER_KEY);
    }

    @Test
    public void getUserPastes_withSuccessfulResponse() throws IOException, InterruptedException {

    }

    @After
    public void tearDown() throws IOException {
        mServer.shutdown();
    }

    private void enqueueResponse(String body, int code) {
        MockResponse response = new MockResponse()
                .setBody(body)
                .setResponseCode(code);
        mServer.enqueue(response);
    }

    private void assertRequestPath(RecordedRequest request, String path) {
        assertEquals(request.getPath(), "/" + path);
    }

    private Map<String, String> getFields(RecordedRequest request)
            throws UnsupportedEncodingException{
        Map<String, String> fields = new HashMap<>();
        String body = request.getBody().readUtf8();
        for (String param : body.split("&")) {
            String[] pair = param.split("=");
            String key = URLDecoder.decode(pair[0], "UTF-8");
            String value = URLDecoder.decode(pair[1], "UTF-8");
            fields.put(key, value);
        }
        return fields;
    }
}
