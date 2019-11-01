package edu.ualr.networking.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.ualr.networking.model.Book;
import edu.ualr.networking.network.WebServiceManager;
import edu.ualr.networking.network.WebAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by irconde on 2019-10-25.
 */

// TODO 10. We create a Repository class to provide just an abstraction for data access.
public class Repository {

    private static final String TAG = Repository.class.getSimpleName();
    // TODO 12. We add a LiveData member. It's used to inform the ViewModel when we receive data
    //  from the server
    private MutableLiveData<List<Book>> bookListObservable = new MutableLiveData<>();
    // TODO 11. Reference to the Web API client
    private WebAPI bookAPI;

    // TODO 14. We define the constructor of the class. We get a reference to the Web API client
    public Repository() {
        bookAPI = WebServiceManager.getService();
    }

    // TODO 15. We define a method to allow other components of the app fetch data from the repository
    public void fetchData() {
        // TODO 16. We define a method to fetch books data from web service
        getBooksFromWeb();
    }

    // TODO 13. We define a get method for the bookListObservable member
    public MutableLiveData<List<Book>> getBookListObservable() {
        return bookListObservable;
    }

    // TODO 16. We define a method to fetch books data from web service. We update the
    private void getBooksFromWeb(){
        bookAPI.getBooksFromWeb().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    // TODO 17. We update the list of books in case we receive a successful response
                    setBooksListObservableData(response.body());
                } else {
                    switch (response.code()) {
                        case 404:
                            Log.d(TAG, "not found");
                            break;
                        case 500:
                            Log.d(TAG, "not logged in or server broken");
                            break;
                        default:
                            Log.d(TAG, "unknown error");
                            break;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    /**
     * This method changes the observable's LiveData data without changing the status
     * @param bookList the data that need to be updated
     */
    // TODO 17. We update the list of books in case we receive a successful response
    private void setBooksListObservableData(List<Book> bookList) {
        bookListObservable.setValue(bookList);
    }

    // TODO 19. We update the list of books once we make sure the transaction has
    //  been successful and we receive the resulting list of books.
    private void addBooksToListObservableData(List<Book> bookList) {
        List<Book> books = bookListObservable.getValue();
        books.addAll(bookList);
        bookListObservable.setValue(books);
    }

    // TODO 18. We define a method to send the web server a request to remotely add/save a new book.
    public void saveData(Book book) {
        bookAPI.saveBookInWeb(book).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    // TODO 19. We update the list of books once we make sure the transaction has
                    //  been successful and we receive the resulting list of books.
                    addBooksToListObservableData(response.body());
                } else {
                    switch (response.code()) {
                        case 404:
                            Log.d(TAG, "not found");
                            break;
                        case 500:
                            Log.d(TAG, "not logged in or server broken");
                            break;
                        default:
                            Log.d(TAG, "unknown error");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) { }
        });
    }
}
