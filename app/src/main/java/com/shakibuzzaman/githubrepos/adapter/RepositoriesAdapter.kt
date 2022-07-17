package com.shakibuzzaman.githubrepos.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shakibuzzaman.githubrepos.model.Item


class RepositoriesAdapter : ListAdapter<Item, RepositoriesAdapter.ViewModel>(RepositoryComparator()){

    class RepositoryComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem
    }

    class ViewModel(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        //val binding =
        return ViewModel(null)
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {

    }

}