package kr.pm10.networksample.api;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import kr.pm10.networksample.library.Constant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubApi {

    private static GitHubApi apiAdapter;
    private ApiService apiService;

    private final int TIME_OUT = 40;


    public static GitHubApi getInstance() {
        if (apiAdapter == null)
            apiAdapter = new GitHubApi();

        return apiAdapter;
    }

    public ApiService service() {
        return apiService;
    }


    public void setApiService() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.GIT_HUB_API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //rx사용
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

}