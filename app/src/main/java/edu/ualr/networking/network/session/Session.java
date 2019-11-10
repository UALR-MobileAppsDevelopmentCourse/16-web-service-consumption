package edu.ualr.networking.network.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import edu.ualr.networking.NetworkApp;

/**
 * Created by irconde on 2019-10-26.
 */

// TODO 05. We define a class in charge of managing the persistence of both the authorization and refresh tokens.
// The class is based on the SharedPreferences persistence mechanism.
public class Session {

    private static final String TAG = Session.class.getSimpleName();

    private static final String PREFS_KEY = "PREFS_KEY";
    private static final String AUTH_TOKEN_KEY = "AUTHTOKEN_KEY";
    private static final String REFRESH_TOKEN_KEY = "REFRESHTOKEN_KEY";
    private String authToken;
    private String refreshToken;
    private SharedPreferences prefs;

    public Session() {
        // TODO 06. To provide an easiest and more straightforward access to the application context
        //  we define a new class to represent the whole application
        // We avoid passing the context as parameter through a never-ending invocation chain
        this.prefs = NetworkApp.getContext().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        this.authToken = prefs.getString(AUTH_TOKEN_KEY, null);
        this.refreshToken = prefs.getString(REFRESH_TOKEN_KEY, null);
    }

    public String getAuthToken() {
        if (authToken == null) {
            this.authToken = this.prefs.getString(AUTH_TOKEN_KEY, null);
        }
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        this.prefs.edit().putString(AUTH_TOKEN_KEY, authToken).commit();
        Log.d(TAG, String.format("New authorization token: %s", authToken));
    }

    public String getRefreshToken() {
        if (refreshToken == null) {
            this.refreshToken = this.prefs.getString(REFRESH_TOKEN_KEY, null);
        }
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        this.prefs.edit().putString(REFRESH_TOKEN_KEY, refreshToken).commit();
    }
}
