package me.rhari.pastoidlib;

import java.util.List;

import me.rhari.pastoidlib.model.NewPasteParameters;
import me.rhari.pastoidlib.model.Paste;
import me.rhari.pastoidlib.model.User;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface PastebinRxApiService {

    @FormUrlEncoded
    @POST(Constants.Urls.PATH_API_LOGIN)
    Observable<String> tryLogin(@Field(Constants.Fields.API_USER_NAME) String username,
                          @Field(Constants.Fields.API_USER_PASSWORD) String password);

    @POST(Constants.Urls.PATH_URL_API_POST)
    @Headers(Constants.Headers.API_OPTION_PASTE)
    Observable<String> createNewPaste(@Body NewPasteParameters newPasteParameters);

    @FormUrlEncoded
    @POST(Constants.Urls.PATH_URL_API_POST)
    @Headers({Constants.Headers.AUTHENTICATION_REQUIRED_TRUE,
            Constants.Headers.API_OPTION_LIST})
    Observable<List<Paste>> getUserPastes(@Field(Constants.Fields.API_RESULTS_LIMIT)
                                                  int resultsLimit);

    @POST(Constants.Urls.PATH_URL_API_POST)
    @Headers(Constants.Headers.API_OPTION_TRENDS)
    Observable<List<Paste>> getTrendingPastes();

    @FormUrlEncoded
    @POST(Constants.Urls.PATH_URL_API_POST)
    @Headers({Constants.Headers.AUTHENTICATION_REQUIRED_TRUE,
            Constants.Headers.API_OPTION_DELETE})
    Observable<Void> deleteUserPaste(@Field(Constants.Fields.API_PASTE_KEY) String pasteKey);

    @POST(Constants.Urls.PATH_URL_API_POST)
    @Headers({Constants.Headers.AUTHENTICATION_REQUIRED_TRUE,
            Constants.Headers.API_OPTION_USER_DETAILS})
    Observable<User> getUserDetails();

    @FormUrlEncoded
    @POST(Constants.Urls.PATH_URL_API_RAW)
    @Headers({Constants.Headers.AUTHENTICATION_REQUIRED_TRUE,
            Constants.Headers.API_OPTION_SHOW_PASTE})
    Observable<String> getRawUserPasteOutput(@Field(Constants.Fields.API_PASTE_KEY)
                                                     String pasteKey);

    @GET(Constants.Urls.PATH_URL_RAW_PASTE)
    Observable<String> getRawPasteOutput(@Path(Constants.Fields.RAW_OUTPUT_PASTE_KEY)
                                                 String pasteKey);
}
