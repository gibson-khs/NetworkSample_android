package kr.pm10.networksample.api.models;

import com.google.gson.annotations.SerializedName;

public class User {
    //    github user
    @SerializedName("login")
    public String loginId;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    public String created_at;

    public String getUserName() {
        if (name == null || name.isEmpty())
            name = "no name";

        return name;
    }

}
