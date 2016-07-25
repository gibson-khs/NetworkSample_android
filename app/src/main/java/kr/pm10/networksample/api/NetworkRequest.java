package kr.pm10.networksample.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import kr.pm10.networksample.R;
import kr.pm10.networksample.ui.base.BaseApplication;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class NetworkRequest {


    // Default error handling
    private static Action1<Throwable> mOnError = error -> {
        Log.e("NetworkRequest ", error.toString());
        error.printStackTrace();
        Context context = BaseApplication.baseContext;
        Toast.makeText(context, context.getResources().getString(R.string.network_error), Toast.LENGTH_LONG).show();
    };

    public static <T> Subscription request(final Observable<T> observable, Action1<? super T> onAction) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onAction, mOnError);
    }

    public static <T> Subscription request(Observable<T> observable, Action1<? super T> onAction, Action1<Throwable> onError) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onAction, onError);
    }

}