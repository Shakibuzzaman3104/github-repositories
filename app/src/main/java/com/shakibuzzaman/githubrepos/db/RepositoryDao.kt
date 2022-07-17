package com.shakibuzzaman.githubrepos.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shakibuzzaman.githubrepos.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repos: List<Item>)

    @Query("DELETE FROM repository")
    suspend fun deleteAllRepositories()

    @Query("SELECT * FROM repository")
    fun getAllRestaurants(): Flow<List<Item>>

    @Query("SELECT * FROM repository ORDER BY :sort DESC")
    fun getAllRestaurants(sort:String): Flow<List<Item>>

}