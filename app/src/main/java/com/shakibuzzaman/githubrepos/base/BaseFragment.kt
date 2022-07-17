package com.shakibuzzaman.githubrepos.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<E : ViewBinding?> : Fragment() {
    val TAG = "Q#_${javaClass.simpleName}"

    private var _binding: E? = null
    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): E
    protected abstract fun init()
    protected abstract fun initListeners()

    protected lateinit var mContext: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    val binding: E
        get() = _binding!!

}