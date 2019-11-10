package edu.ualr.networking.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.ualr.networking.model.Book;

/**
 * Created by irconde on 2019-11-09.
 */
// TODO 04. Create a database class that represents the book database and exposes the defined DAO
@Database(entities = {Book.class}, version = BooksDB.VERSION)
public abstract class BooksDB extends RoomDatabase {

    static final int VERSION = 1;
    public static final String DB_NAME = "books.db";
    private static BooksDB instance;

    public abstract BooksDAO getBooksDAO();

    public static BooksDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BooksDB.class, DB_NAME).build();
        }
        return instance;
    }

}
