package edu.ualr.networking.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.ualr.networking.R;
import edu.ualr.networking.adapter.BookListAdapter;
import edu.ualr.networking.model.Book;
import edu.ualr.networking.viewmodel.BookViewModel;

/**
 * Created by irconde on 2019-10-26.
 */
public class ListFragment extends Fragment {

    private RecyclerView mRecipesListView;
    private BookListAdapter mAdapter;
    private BookViewModel bookViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.book_list, container, false);
        mRecipesListView = view.findViewById(R.id.recipes_list_rv);
        mAdapter = new BookListAdapter(new ArrayList<Book>());
        mRecipesListView.setAdapter(mAdapter);
        mRecipesListView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        bookViewModel = ViewModelProviders.of(this.getActivity()).get(BookViewModel.class);
        // TODO 31. Try to retrieve data from the viewmodel
        // TODO 32. Populate the view with the list of books when this gets updated
        return view;
    }
}
