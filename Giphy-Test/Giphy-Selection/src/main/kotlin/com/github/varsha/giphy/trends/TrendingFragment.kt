package com.github.varsha.giphy.trends

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.github.varsha.giphy.R
import com.github.varsha.giphy.common.arch.state.Result
import com.github.varsha.giphy.common.arch.state.UiState
import com.github.varsha.giphy.common.extensions.gone
import com.github.varsha.giphy.common.extensions.visible
import com.github.varsha.giphy.trends.model.TrendingResponse
import com.google.android.material.snackbar.Snackbar
import xyz.klinker.giphy.GifSelectedCallback
import xyz.klinker.giphy.Giphy
import xyz.klinker.giphy.GiphyView

class TrendingFragment : Fragment() {

    private val trendingAdapter by lazy { TrendingAdapter() }
    private lateinit var progressBar: ProgressBar
    private lateinit var trendsRecyclerView: RecyclerView
    private lateinit var parentContainer: View
    private lateinit var viewModel: TrendingViewModel

    companion object {
        fun newInstance() = TrendingFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.trending_fragment, container, false)
        parentContainer = view.findViewById(R.id.parent_container)
        progressBar = view.findViewById(R.id.progress_bar)
        trendsRecyclerView = view.findViewById(R.id.trends_recycler_view)
         setupRecyclerView()
     //  giphy.initializeView("1gGutWcmvuVxANQsV3aMlmlhjQwyobSg", (5 * 1024 * 1024))

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        observeUiState()
        observeTrendingData()
        viewModel.loadTrending()

    }

    private fun observeTrendingData() {
        viewModel.trendsDataEvent.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is Result.Success -> {
                    showTrending(state.value)
                }
                is Result.Error.ApiKeyNotSetError -> {
                    showApiKeyNotSetDialog()
                }

                is Result.Error.FeatureError.TrendingError -> {
                    showTrendingError()
                }
            }
        })
    }

    private fun observeUiState() {
        viewModel.uiStateEvent.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is UiState.Loading -> {
                    showProgress()
                }
                is UiState.Loaded -> {
                    hideProgress()
                }
                is UiState.Error -> {
                    hideProgress()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        trendsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_item_decoration_offset)))
            adapter = trendingAdapter
        }
    }

    private fun showProgress() {
        progressBar.visible()
        trendsRecyclerView.gone()
    }

    private fun hideProgress() {
        progressBar.gone()
        trendsRecyclerView.visible()
    }

    private fun showTrending(trending: TrendingResponse) {
        hideProgress()
        trendingAdapter.addTrends(trending.data)
    }

    private fun showTrendingError() {
        hideProgress()
        Snackbar.make(parentContainer, getString(R.string.trending_error), Snackbar.LENGTH_SHORT).show()
    }

    private fun showApiKeyNotSetDialog() {
        activity?.let { context ->
            val alertDialogError = AlertDialog.Builder(context)
                    .setTitle(getString(R.string.dialog_title))
                    .setPositiveButton(getString(R.string.dialog_close_button), null)
                    .setMessage(getString(R.string.api_key_error))
                    .create()
            alertDialogError.show()
        }
    }
}
