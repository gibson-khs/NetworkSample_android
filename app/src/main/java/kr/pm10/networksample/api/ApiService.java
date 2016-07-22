package kr.pm10.networksample.api;

import kr.pm10.networksample.api.models.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiService {

    @GET("users/{login}")
    Observable<User> getUserInfomation(@Path("login") String loginId);
}
