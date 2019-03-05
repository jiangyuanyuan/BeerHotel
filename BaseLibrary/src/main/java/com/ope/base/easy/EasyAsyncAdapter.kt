package com.ope.base.easy

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ben.
 *
 * 简单异步计算列表差异的RecyclerView适配器
 *
 * 注意：如数据量较小请使用EasyAdapter
 *
 * @see AsyncListDiffer
 * @see EasyAdapter
 */
class EasyAsyncAdapter<T>(private val layoutResId: Int, private val bindView: (View, Int, T) -> Unit) :
        RecyclerView.Adapter<EasyAsyncAdapter.ViewHolder<T>>() {

    private val differ: AsyncListDiffer<T> by lazy(LazyThreadSafetyMode.NONE) {
        AsyncListDiffer<T>(this, object : DiffUtil.ItemCallback<T>() {

            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem == newItem
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return ViewHolder(view, bindView)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(getItem(position), position)
    }

    class ViewHolder<in T>(view: View, private val bindView: (View, Int, T) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: T, position: Int) {
            with(item) {
                bindView(itemView, position, item)
            }
        }
    }

    /**
     * Submits a new list to be diffed, and displayed.
     *
     *
     * If a list is already being displayed, a diff will be computed on a background thread, which
     * will dispatch Adapter.notifyItem events on the main thread.
     *
     * @param list The new list to be displayed.
     */
    fun submitList(list: List<T>) {
        if (list.isEmpty()) {
            differ.submitList(null)
        } else {
            differ.submitList(list)
        }
    }

    fun getItem(position: Int): T {
        return differ.currentList[position]
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}