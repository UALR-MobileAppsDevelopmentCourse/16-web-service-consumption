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
public interface WebAPI {

    @GET("ds/books")
    Call<List<Book>> getBooksFromWeb();

    @POST("auth/refresh")
    Call<SessionDataResponse> refreshAccessToken(@Body SessionDataQuery refreshToken);

    @POST("ds/books")
    Call<List<Book>> saveBookInWeb(@Body Book book);

}
