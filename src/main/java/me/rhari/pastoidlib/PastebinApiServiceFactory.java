package me.rhari.pastoidlib;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class PastebinApiServiceFactory {

    private static ScalarsConverterFactory mScalarsConverterFactory;
    private static Serializer mSerializer;
    private static SimpleXmlConverterFactory mXmlConverterFactory;
    private static PastebinConverterFactory mPastebinConverterFactory;

    private final String mApiDevKey;
    private final String mApiUserKey;

    private PastebinApiServiceFactory(String apiDevKey, String apiUserKey) {
        if (apiDevKey == null || apiDevKey.trim().isEmpty()) {
            throw new IllegalArgumentException("apiDevKey cannot be null or empty");
        }
        mApiDevKey = apiDevKey;
        mApiUserKey = apiUserKey;
    }

    public static PastebinApiServiceFactory create(String apiDevKey) {
        return new PastebinApiServiceFactory(apiDevKey, null);
    }

    public static PastebinApiServiceFactory createWithUserApiKey(String apiDevKey,
                                                                 String apiUserKey) {
        if (apiUserKey == null || apiUserKey.trim().isEmpty()) {
            throw new IllegalArgumentException("apiUserKey cannot be null or empty");
        }
        return new PastebinApiServiceFactory(apiDevKey, apiUserKey);
    }

    public PastebinApiService createApiService() {
        Retrofit retrofit = getRetrofitBuilder().build();
        return retrofit.create(PastebinApiService.class);
    }

    public PastebinRxApiService createRxApiService() {
        Retrofit retrofit = getRetrofitBuilder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(PastebinRxApiService.class);
    }

    public PastebinRx2ApiService createRx2ApiService() {
        Retrofit retrofit = getRetrofitBuilder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(PastebinRx2ApiService.class);
    }

    PastebinApiService createApiServiceForTesting(String baseUrl) {
        Retrofit retrofit = getRetrofitBuilder(baseUrl).build();
        return retrofit.create(PastebinApiService.class);
    }

    private Retrofit.Builder getRetrofitBuilder() {
        return getRetrofitBuilder(Constants.Urls.BASE_URL);
    }

    private Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        initialize();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient(mApiDevKey, mApiUserKey))
                .addConverterFactory(mScalarsConverterFactory)
                .addConverterFactory(mPastebinConverterFactory)
                .addConverterFactory(mXmlConverterFactory);
    }

    private static void initialize() {
        if (mScalarsConverterFactory == null) {
            mScalarsConverterFactory = ScalarsConverterFactory.create();
        }
        if (mSerializer == null) {
            mSerializer = new Persister(new AnnotationStrategy());
        }
        if (mXmlConverterFactory == null) {
            mXmlConverterFactory = SimpleXmlConverterFactory.createNonStrict(mSerializer);
        }
        if (mPastebinConverterFactory == null) {
            mPastebinConverterFactory = PastebinConverterFactory.create(mSerializer);
        }
    }

    private OkHttpClient getOkHttpClient(String apiDevKey, String apiUserKey) {
        //TODO: find a way to reuse the OkHttpClient while keeping different interceptors
        Interceptor interceptor = new PastebinApiInterceptor(apiDevKey, apiUserKey);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
