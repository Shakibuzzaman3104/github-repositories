package com.shakibuzzaman.githubrepos.repository

import androidx.room.withTransaction
import com.shakibuzzaman.githubrepos.api.RepositoryApi
import com.shakibuzzaman.githubrepos.db.GithubRepositoryDb
import com.shakibuzzaman.githubrepos.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject


class GithubReposRepository @Inject constructor(
    private val api: RepositoryApi,
    private val db: GithubRepositoryDb
) {

    private val restaurantDao = db.repositoryDao()

    fun getRepositories() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurants()
        },
        fetch = {
            delay(2000)
            api.getTopRepositories()
        },
        saveFetchResult = { repo ->
            db.withTransaction {
                restaurantDao.deleteAllRepositories()
                restaurantDao.insertRepositories(repo.items!!)
            }
        }
    )
}