package edu.ualr.networking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ualr.networking.R;
import edu.ualr.networking.model.Book;

/**
 * Created by irconde on 2019-10-25.
 */
public class BookListAdapter extends RecyclerView.Adapter {

    private List<Book> mItems;

    public BookListAdapter(List<Book> items) {
        this.mItems = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book = this.mItems.get(position);
        RecipeViewHolder recipeViewHolder = (RecipeViewHolder) holder;
        // TODO 04. Uncomment the following lines
        // recipeViewHolder.author.setText(book.getAuthor());
        // recipeViewHolder.title.setText(book.getTitle());
        // recipeViewHolder.summary.setText(book.getSummary());
        // recipeViewHolder.isbn.setText(book.getIsbn());
        // recipeViewHolder.publisher.setText(book.getPublisher());
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public void setItems (List<Book> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    private class RecipeViewHolder extends RecyclerView.ViewHolder {

        public TextView title, author, summary, isbn, publisher;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.author);
            summary = itemView.findViewById(R.id.summary);
            isbn = itemView.findViewById(R.id.isbn);
            publisher = itemView.findViewById(R.id.publisher);
        }
    }
}
