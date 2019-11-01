package edu.ualr.networking.network;

import androidx.annotation.Nullable;

/**
 * Created by irconde on 2019-10-26.
 */
public class ApiServiceHolder {
    WebAPI myService = null;

    @Nullable
    public WebAPI get() {
        return myService;
    }

    public void set(WebAPI myService) {
        this.myService = myService;
    }
}