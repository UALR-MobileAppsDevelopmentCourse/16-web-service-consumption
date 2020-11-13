package edu.ualr.networking.network;

/**
 * Created by irconde on 2020-11-12.
 */
// TODO 02. The "Token refresh" class has to implement the Authenticator interface so ...
public class TokenRefreshAuthenticator {

    // TODO 03. ... it has to override the authenticate method. It's automatically invoked when the Okhttp client
    // receives an error from the server related to authentication and retries the petition to the server

    // TODO 03.01. Get a reference to the WebAPI interface we use to send petitions to the server
    // NOTE. To avoid a circular reference problem we'll define a class to wrap the web API: ApiServiceHolder

    // TODO 10. Create and execute a new refresh token petition. We must provide the refresh token in the body

    // TODO 11. Once we have received a successful response, we persist the new tokens

    // TODO 12. We retry the petition provided as input parameter, using the new authorization token

}
