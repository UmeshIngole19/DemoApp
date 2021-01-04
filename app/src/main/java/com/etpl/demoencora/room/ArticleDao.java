package com.etpl.demoencora.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.etpl.demoencora.model.Docs;
import com.etpl.demoencora.model.RoomRes;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Docs doc);

    @Query("SELECT * from Docs ORDER BY id ASC")
    LiveData<List<Docs>> getAllPosts();

    @Query("DELETE FROM Docs")
    void deleteAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPosts(List<Docs> docs);

}