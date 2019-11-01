package edu.ualr.networking.network;

import java.io.IOException;

import edu.ualr.networking.network.session.Session;
import edu.ualr.networking.network.session.SessionDataQuery;
import edu.ualr.networking.network.session.SessionDataResponse;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by irconde on 2019-10-26.
 */
public class TokenRefreshAuthenticator implements Authenticator {
    private ApiServiceHolder myServiceHolder;
    private Session session;

    public TokenRefreshAuthenticator(ApiServiceHolder myServiceHolder, Session session) {
        this.myServiceHolder = myServiceHolder;
        this.session = session;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (myServiceHolder == null) {
            return null;
        }
        WebAPI service = myServiceHolder.get();
        SessionDataQuery sessionQuery = new SessionDataQuery();
        sessionQuery.setRefreshToken(session.getRefreshToken());
        retrofit2.Response retrofitResponse = service.refreshAccessToken(sessionQuery).execute();
        if (retrofitResponse != null && retrofitResponse.isSuccessful()) {
            SessionDataResponse refreshTokenResponse = (SessionDataResponse) retrofitResponse.body();
            session.setAuthToken(refreshTokenResponse.getAccessToken());
            session.setRefreshToken(refreshTokenResponse.getRefreshToken());
            return response.request().newBuilder()
                    .header("Authorization", "Bearer " + session.getAuthToken())
                    .build();
        }
        return null;
    }
}
