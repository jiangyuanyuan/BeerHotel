package com.ope.base.view.state

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ope.base.R


import com.ope.base.easy.EasyAdapter
import java.util.*

/**
 * https://github.com/JamieCruwys/StatefulView
 * 只对布局是list的
 * Created by Jamie Cruwys of 3 SIDED CUBE on 10/04/2017.
 */
abstract class StatefulListingFragment<ITEM_TYPE> : StatefulFragment<ITEM_TYPE>() {

    protected var recycler: RecyclerView? = null
    protected var adapter: RecyclerView.Adapter<*>? = null
    private var savedLayoutManager: Parcelable? = null
    private var restoredState = false

    private var initView: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler = view!!.findViewById<View>(R.id.recycler) as RecyclerView

        if (savedInstanceState == null) {
            refresh(Collections.EMPTY_LIST as List<ITEM_TYPE>)
        } else {
            restoredState = false
            savedLayoutManager = savedInstanceState.getParcelable(SAVED_LAYOUT_MANAGER)
        }
    }

    private fun refresh(items: List<ITEM_TYPE>) {
        if (!initView || recycler?.adapter == null) {
            recycler?.layoutManager = provideLayoutManager()

            adapter = provideAdapter(items)
            recycler?.adapter = adapter

            val itemDecoration = provideItemDecoration()
            if (itemDecoration != null) {
                recycler?.addItemDecoration(itemDecoration)
            }

            if (!restoredState) {
                recycler?.layoutManager?.onRestoreInstanceState(savedLayoutManager)
                restoredState = true
            }
            initView = true
        } else {
            recycler?.adapter?.apply {
                if (this is EasyAdapter<*>) {
                    (this as EasyAdapter<ITEM_TYPE>).submitList(items)
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun provideLoadedLayout(): Int {
        return uk.co.jamiecruwys.statefulview.R.layout.stateful_listing
    }

    /**
     * Provide the layout manager to use for the listing
     *
     * @return [android.support.v7.widget.RecyclerView.LayoutManager] to use for the listing
     */
    protected open fun provideLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    /**
     * Provide the adapter to use for the listing
     *
     * @param items to seed the adapter with
     *
     * @return [RecyclerView.Adapter] to use for the listing
     */
    protected abstract fun provideAdapter(items: List<ITEM_TYPE>): RecyclerView.Adapter<*>

    /**
     * Provide any item decoration you want applied to the listing
     *
     * @return [android.support.v7.widget.RecyclerView.ItemDecoration] you want applied to the listing
     */
    protected fun provideItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun onListingDataRetrieved(items: List<ITEM_TYPE>) {
        // Do not continue if the fragment is detached from an activity
        if (!isAdded || activity == null) {
            return
        }
        super.onListingDataRetrieved(items)
        refresh(items)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (recycler != null) {
            val layoutManager = recycler?.layoutManager

            if (layoutManager != null) {
                val layoutManagerState = layoutManager.onSaveInstanceState()

                if (layoutManagerState != null) {
                    outState.putParcelable(SAVED_LAYOUT_MANAGER, layoutManagerState)
                }
            }
        }

        super.onSaveInstanceState(outState)
    }

    companion object {
        private val SAVED_LAYOUT_MANAGER = "saved_layout_manager"
    }
}