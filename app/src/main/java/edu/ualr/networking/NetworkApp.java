package edu.ualr.networking;

import android.app.Application;
import android.content.Context;

/**
 * Created by irconde on 2019-10-26.
 */
public class NetworkApp extends Application {

    private static NetworkApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static NetworkApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

}
