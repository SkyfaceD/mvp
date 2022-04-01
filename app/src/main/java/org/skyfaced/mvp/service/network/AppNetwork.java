package org.skyfaced.mvp.service.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

public final class AppNetwork {
    private static AppNetwork instance;

    public final WaifuService waifuService = retrofit("https://api.waifu.pics/").create(WaifuService.class);
    public final JikanService jikanService = retrofit("https://api.jikan.moe/v4/").create(JikanService.class);

    private AppNetwork() {

    }

    public static AppNetwork getInstance() {
        if (instance == null) instance = new AppNetwork();
        return instance;
    }

    private OkHttpClient client() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(Timber::d).setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private Retrofit retrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client())
                .build();
    }
}
