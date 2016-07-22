package kr.pm10.networksample.library.utils;

import retrofit2.adapter.rxjava.HttpException;

public class Utils {
    
    public static int getNetworkErrorCode(Throwable throwable) {
        if (throwable == null)
            return 0;
        return ((HttpException) throwable).code();
    }
}
