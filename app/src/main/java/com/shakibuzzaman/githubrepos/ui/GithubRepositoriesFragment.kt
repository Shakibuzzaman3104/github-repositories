package com.shakibuzzaman.githubrepos.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.shakibuzzaman.githubrepos.base.BaseFragment
import com.shakibuzzaman.githubrepos.databinding.FragmentGithubRepositoriesBinding
import com.shakibuzzaman.githubrepos.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubRepositoriesFragment : BaseFragment<FragmentGithubRepositoriesBinding>() {

    private val viewModel: RepositoriesViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGithubRepositoriesBinding =
        FragmentGithubRepositoriesBinding.inflate(inflater, container, false)

    override fun init() {
        initObservers()
    }

    private fun initObservers() {
        viewModel.repositories.observe(viewLifecycleOwner) { result ->

            Log.d(TAG, "initObservers: ${result.data?.size}")

            binding.progressBar.isVisible =
                result is Resource.Loading && result.data.isNullOrEmpty()
            binding.textViewError.isVisible =
                result is Resource.Error && result.data.isNullOrEmpty()
            binding.textViewError.text = result.error?.localizedMessage
        }
    }

    override fun initListeners() {

    }

}