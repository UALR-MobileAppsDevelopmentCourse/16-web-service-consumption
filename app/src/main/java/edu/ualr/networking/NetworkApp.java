package edu.ualr.networking;

import android.app.Application;
import android.content.Context;

/**
 * Created by irconde on 2019-10-26.
 */
// TODO 06. To provide an easiest and more straightforward access to the application context
//  we define a new class to represent the whole application
public class NetworkApp extends Application {

    private static NetworkApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext(){
        return instance;
    }

}
