package com.shakibuzzaman.githubrepos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shakibuzzaman.githubrepos.repository.GithubReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(repository: GithubReposRepository) : ViewModel() {

    val repositories = repository.getRepositories().asLiveData()

}