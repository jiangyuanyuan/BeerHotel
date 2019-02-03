package com.beer.baselibrary.easy

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beer.baselibrary.R



/**
 * Created by ben.
 *
 * 简单的RecyclerView适配器
 *
 * 注意：如数据量较大且常变化，请使用BaseAsyncAdapter
 *
 * @see DiffUtil
 * @see EasyAsyncAdapter
 */
open class EasyAdapter<T>(private val layoutResId: Int, private val bindView: (View, Int, T) -> Unit, private var list: List<T> = emptyList()) :
        RecyclerView.Adapter<EasyAdapter.ViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return ViewHolder(view, bindView)
    }

    /**
     * 更新数据集
     */
    fun submitList(newList: List<T>) {
        val oldList = this.list
        this.list = newList

        if (oldList.isEmpty()) {
            notifyDataSetChanged()
        } else {
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return oldList.size
                }

                override fun getNewListSize(): Int {
                    return newList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItem = oldList[oldItemPosition]
                    val newItem = newList[newItemPosition]

                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItem = oldList[oldItemPosition]
                    val newItem = newList[newItemPosition]

                    return oldItem == newItem
                }
            }, true).dispatchUpdatesTo(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount() = list.size

    fun getData() = list

    class ViewHolder<in T>(view: View, private val bindView: (View, Int, T) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: T, position: Int) {
            with(item) {
                itemView.setTag(R.id.easyadapter_position, position)
                bindView(itemView, position, item)
            }
        }
    }
}