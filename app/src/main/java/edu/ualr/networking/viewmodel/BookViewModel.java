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

public class BookViewModel extends androidx.lifecycle.ViewModel {
    private Repository mRepository;
    private MediatorLiveData<List<Book>> bookListObservable = new MediatorLiveData<>();

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

    public void retrieveData(){
        mRepository.fetchData();
    }

    public void saveData(Book book) {
        mRepository.saveData(book);
    }

    public LiveData<List<Book>> getBookListObservable() {
        return bookListObservable;
    }
}
