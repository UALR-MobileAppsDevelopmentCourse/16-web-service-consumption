package edu.ualr.networking.network;

import androidx.annotation.Nullable;

/**
 * Created by irconde on 2019-10-26.
 */
// TODO 04. Define a Holder class that wraps the API in order to avoid a circular reference problem
//  between the WebServiceManager and TokenRefreshAuthenticator classes
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