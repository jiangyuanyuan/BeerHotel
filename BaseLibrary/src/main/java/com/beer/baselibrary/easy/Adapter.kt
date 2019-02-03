package com.beer.baselibrary.easy

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer

class Adapter<ITEM>(
        private val createView: (parent: ViewGroup, viewType: Int) -> View,
        private val bindViewHolder: (viewHolder: ViewHolder, item: ITEM, position: Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    fun submitList(list: List<ITEM>) {
        if (list.isEmpty()) {
            differ.submitList(null)
        } else {
            differ.submitList(list)
        }
    }

    fun getItem(position: Int): ITEM {
        return differ.currentList[position]
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(createView.invoke(parent, viewType))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            bindViewHolder.invoke(holder, getItem(position), position)


    private val differ: AsyncListDiffer<ITEM> by lazy(LazyThreadSafetyMode.NONE) {
        AsyncListDiffer<ITEM>(this, object : DiffUtil.ItemCallback<ITEM>() {

            override fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
                return oldItem == newItem
            }
        })
    }

}

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer