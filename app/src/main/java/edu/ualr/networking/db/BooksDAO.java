package edu.ualr.networking.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.ualr.networking.model.Book;

/**
 * Created by irconde on 2019-11-09.
 */

// TODO 02. Create dao class to provide query method to manage the database
@Dao
public interface BooksDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertEntry(Book book);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(Book book);

    // Removes an entry from the database
    @Delete
    void delete(Book book);

    // Gets all entries in the database
    @Query("SELECT * FROM Book")
    List<Book> getAllEntries();

    // ...
}
