package edu.ualr.networking.network.session;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by irconde on 2019-10-26.
 */
// TODO 08. We create a simple POJO class to define the data we have to provide in the body of the refresh token query
public class SessionDataQuery {
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
