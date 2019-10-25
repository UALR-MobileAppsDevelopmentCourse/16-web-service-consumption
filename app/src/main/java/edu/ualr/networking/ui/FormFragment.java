package edu.ualr.networking.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import edu.ualr.networking.R;
import edu.ualr.networking.viewmodel.BookViewModel;

/**
 * Created by irconde on 2019-10-26.
 */
public class FormFragment extends Fragment implements View.OnClickListener {

    private EditText titleET, authorET, summaryET, isbnET, publisherET;
    private Button saveBtn;
    private BookViewModel bookViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.book_form, container, false);
        titleET = view.findViewById(R.id.title_et);
        authorET = view.findViewById(R.id.author_et);
        summaryET = view.findViewById(R.id.summary_et);
        isbnET = view.findViewById(R.id.isbn_et);
        publisherET = view.findViewById(R.id.publisher_et);
        saveBtn = view.findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);
        bookViewModel = ViewModelProviders.of(this.getActivity()).get(BookViewModel.class);
        // TODO 29. Show a Toast message when a new book has been successfully saved in the server
        return view;
    }

    @Override
    public void onClick(View view) {
        // TODO 30. Save data regarding a new book in the repository
    }
}
