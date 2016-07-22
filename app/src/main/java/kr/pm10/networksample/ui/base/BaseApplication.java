package kr.pm10.networksample.ui.base;

import android.app.Application;
import android.content.Context;

import kr.pm10.networksample.api.GitHubApi;

public class BaseApplication extends Application {

    public static Context baseContext;

    @Override
    public void onCreate() {
        super.onCreate();
        GitHubApi.getInstance().setApiService();
        baseContext = this;
    }
}
