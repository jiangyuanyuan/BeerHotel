package com.beer.baselibrary.view.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beer.baselibrary.R

import com.beer.baselibrary.common.BaseFragment
import uk.co.jamiecruwys.StatefulView
import uk.co.jamiecruwys.ViewState
import uk.co.jamiecruwys.contracts.*

/**
 * https://github.com/JamieCruwys/StatefulView
 *
 * A fragment that contains a [StatefulView]
 */
abstract class StatefulFragment<ITEM_TYPE> : BaseFragment(), ViewStateLayouts, ViewStateRootLayout, ViewStateChange, InitialViewState, ListingData<ITEM_TYPE> {
    protected lateinit var statefulView: StateCocoView
    protected lateinit var viewRoot: View

    /**
     * Sets up the stateful view inside this fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(provideLayout(), container, false)
        return viewRoot
    }

    override fun getLayoutId(): Int {
        return provideLayout()
    }

    /**
     * Provides a default layout for this fragment
     *
     * @return default layout resource for this fragment
     */
    override fun provideLayout(): Int {
        return R.layout.stateful_view
    }

    /**
     * Provides the id of the [StatefulView] in the default layout for this fragment
     *
     * @return layout id of the [StatefulView] in the default layout for this fragment
     */
    override fun provideStatefulViewId(): Int {
        return R.id.statefulView
    }

    /**
     * Set the initial view state
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        statefulView = viewRoot.findViewById<View>(provideStatefulViewId()) as StateCocoView

        statefulView.setStateLayout(context!!, provideLoadingLayout(), ViewState.LOADING)
        statefulView.setStateLayout(context!!, provideEmptyLayout(), ViewState.EMPTY)
        statefulView.setStateLayout(context!!, provideLoadedLayout(), ViewState.LOADED)
        statefulView.setStateLayout(context!!, provideErrorLayout(), ViewState.ERROR)

        viewState = provideInitialViewState()
    }

    /**
     * Sets the new view state to transition to
     *
     * @param state which it is transitioning to
     */
    override fun setViewState(state: ViewState) {
        statefulView.viewState = state
    }

    override fun getViewState(): ViewState {
        return statefulView.viewState
    }

    override fun provideInitialViewState(): ViewState {
        return ViewState.EMPTY
    }

    override fun onResume() {
        super.onResume()

        if (shouldReloadOnResume()) {
            viewState = ViewState.LOADING
            getListData(this)
        }
    }

    /**
     * Whether or not content should be reloaded when the view is resumed
     *
     * @return true to reload content on resume, false to not reload content
     */
    protected open fun shouldReloadOnResume(): Boolean {
        return false
    }

    /**
     * Get the data that will be displayed in the list
     */
    protected abstract fun getListData(callback: ListingData<*>)

    override fun onListingDataRetrieved(items: List<ITEM_TYPE>) {
        // Do not continue if the fragment is detached from an activity
        if (!isAdded || activity == null) {
            return
        }

        if (items.isEmpty()) {
            setViewState(ViewState.EMPTY)
        } else {
            setViewState(ViewState.LOADED)
        }
    }

    override fun onListingDataError(throwable: Throwable?) {
        // Do not continue if the fragment is detached from an activity
        if (!isAdded || activity == null) {
            return
        }

        viewState = ViewState.ERROR
    }
}