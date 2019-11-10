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
// TODO 02. The "Token refresh" class has to implement the Authenticator interface so ...
public class TokenRefreshAuthenticator implements Authenticator {
    private ApiServiceHolder myServiceHolder;
    private Session session;

    public TokenRefreshAuthenticator(ApiServiceHolder myServiceHolder, Session session) {
        this.myServiceHolder = myServiceHolder;
        this.session = session;
    }

    // TODO 03. ... it has to override the authenticate method. It's automatically code when the Okhttp client
    // receives an error from the server related to authentication and retries the petition to the server
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (myServiceHolder == null) {
            return null;
        }
        // TODO 03.01. Get a reference to the WebAPI interface we use to send petitions to the server
        // NOTE. To avoid a circular reference problem we'll define a class to wrap the web API: ApiServiceHolder
        WebAPI service = myServiceHolder.get();
        // TODO 10. Create and execute a new refresh token petition. We must provide the refresh token in the body
        SessionDataQuery sessionQuery = new SessionDataQuery();
        sessionQuery.setRefreshToken(session.getRefreshToken());
        retrofit2.Response retrofitResponse = service.refreshAccessToken(sessionQuery).execute();
        // TODO 11. Once we have received a successful response, we persist the new tokens
        if (retrofitResponse != null && retrofitResponse.isSuccessful()) {
            SessionDataResponse refreshTokenResponse = (SessionDataResponse) retrofitResponse.body();
            session.setAuthToken(refreshTokenResponse.getAccessToken());
            session.setRefreshToken(refreshTokenResponse.getRefreshToken());
            // TODO 12. We retry the petition provided as input parameter, using the new authorization token
            return response.request().newBuilder()
                    .header("Authorization", "Bearer " + session.getAuthToken())
                    .build();
        }
        return null;
    }
}
