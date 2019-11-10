package edu.ualr.networking.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by irconde on 2019-10-25.
 */
// TODO 03. Modify the Book model to define a new database entity. Set the isbn field as the primary key
@Entity
public class Book {
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("title")
    @Expose
    private String title;
    @PrimaryKey
    @NonNull
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("publisher")
    @Expose
    private String publisher;

    public Book(String author, String title, String isbn, String summary, String publisher) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.summary = summary;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
