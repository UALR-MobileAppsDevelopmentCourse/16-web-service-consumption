package edu.ualr.networking.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import edu.ualr.networking.model.Book;
import edu.ualr.networking.repository.Repository;

/**
 * Created by irconde on 2019-10-25.
 */

// TODO 20. We create the ViewModel used as data source for the book-related fragments in the app
public class BookViewModel extends androidx.lifecycle.ViewModel {
    // TODO 21. Repository member
    private Repository mRepository;
    // TODO 22. MediatorLiveData member. Proxy that observes several LiveData objects and react on
    //  OnChanged events from them.
    private MediatorLiveData<List<Book>> bookListObservable = new MediatorLiveData<>();

    // TODO 23. ViewModel constructor.
    // TODO 23.01 We initialize the Repository member
    // TODO 23.02. We subscribe the MediatorLiveData member to LiveData member in the repository to
    //  receive data updates from the web service and we pass those data along the view (fragment/activity)
    public BookViewModel() {
        super();
        mRepository = new Repository();
        bookListObservable.addSource(mRepository.getBookListObservable(),
                new Observer<List<Book>>() {
                    @Override
                    public void onChanged(@Nullable List<Book> recipes) {
                        bookListObservable.setValue(recipes);
                    }
                });
    }

    // TODO 25. We define method to retrieve/save data from/to the repository
    public void retrieveData(){
        mRepository.fetchData();
    }

    public void saveData(Book book) {
        mRepository.saveData(book);
    }

    // TODO 24. Get method for the MediatorLiveData member
    public LiveData<List<Book>> getBookListObservable() {
        return bookListObservable;
    }
}
