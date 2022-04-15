package com.example.myapplication.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Notes;

import java.util.List;

@Dao
public interface MainDAO {

    @Insert(onConflict = REPLACE)
    void insert(Notes note);

    @Query("SELECT * FROM notes ORDER BY ID DESC")
    List<Notes> getAll();

    @Query("UPDATE notes SET title = :title, note = :note WHERE ID = :ID")
    void update(int ID, String title, String note);

    @Delete
    void delete(Notes notes);

    @Query("UPDATE notes SET pinned = :pin WHERE ID = :id")
    void pin(int id,boolean pin);
}
