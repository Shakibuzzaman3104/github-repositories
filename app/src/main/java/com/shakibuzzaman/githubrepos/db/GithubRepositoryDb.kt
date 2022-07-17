package com.shakibuzzaman.githubrepos.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shakibuzzaman.githubrepos.model.Item

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
abstract class GithubRepositoryDb : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

    companion object {

        @Volatile
        private var INSTANCE: GithubRepositoryDb? = null
        fun getInstance(application: Application): GithubRepositoryDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(application).also { INSTANCE = it }
            }

        private fun buildDatabase(application: Application) = Room.databaseBuilder(
            application,
            GithubRepositoryDb::class.java, "github.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}

