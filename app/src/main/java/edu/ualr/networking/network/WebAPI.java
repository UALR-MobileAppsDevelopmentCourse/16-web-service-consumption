package edu.ualr.networking.network;

import java.util.List;

import edu.ualr.networking.model.Book;
import edu.ualr.networking.network.session.SessionDataQuery;
import edu.ualr.networking.network.session.SessionDataResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by irconde on 2019-10-25.
 */
// TODO 03. We declare an API interface
public interface WebAPI {

    // TODO 03.01. Request with GET Http method. Get list of available books. Url: ds/books
    @GET("ds/books")
    Call<List<Book>> getBooksFromWeb();

    @POST("auth/refresh")
    Call<SessionDataResponse> refreshAccessToken(@Body SessionDataQuery refreshToken);

    // TODO 03.02. Request with POST Http method. Save new book in the server. Url: ds/books
    @POST("ds/books")
    Call<List<Book>> saveBookInWeb(@Body Book book);

}
