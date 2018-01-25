# Pasted

Pasted is a Java wrapper for the [Pastebin API](https://pastebin.com/api).

## Usage

The library uses [Retrofit 2](https://github.com/square/retrofit) for making network requests. The same threading model is used for executing the requests synchronously / asynchronously.

```java
PastebinApiServiceFactory apiServiceFactory = PastebinApiServiceFactory.create("YOUR_API_DEV_KEY");
PastebinApiService apiService = apiServiceFactory.createApiService();

//The user API key is automatically cached and reused for future requests.
String apiUserKey = apiService.tryLogin("USERNAME", "PASSWORD").execute();

Call<List<Paste>> pastesCall = apiService.getUserPastes(10);
List<Paste> pastes = pastesCall.execute().body();
```

A full list of all the API calls available can be found in the [PastebinApiService.java file](src/main/java/me/rhari/pastoidlib/PastebinApiService.java). RxJava and RxJava 2 interfaces are available at [PastebinRxApiService.java](src/main/java/me/rhari/pastoidlib/PastebinRxApiService.java) and [PastebinRx2ApiService.java](src/main/java/me/rhari/pastoidlib/PastebinRx2ApiService.java) respectively.

