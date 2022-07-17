package com.shakibuzzaman.githubrepos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shakibuzzaman.githubrepos.base.BaseFragment
import com.shakibuzzaman.githubrepos.databinding.FragmentGithubRepositoryDetailsBinding


class GithubRepositoryDetailsFragment : BaseFragment<FragmentGithubRepositoryDetailsBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGithubRepositoryDetailsBinding =
        FragmentGithubRepositoryDetailsBinding.inflate(inflater, container, false)

    override fun init() {

    }

    override fun initListeners() {

    }

}