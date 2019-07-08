package com.leonardoalves.githubrepository.view.pullrequests

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leonardoalves.githubrepository.R
import com.leonardoalves.githubrepository.view.custom.*
import kotlinx.android.synthetic.main.activity_pull_requests.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PullRequestsActivity : AppCompatActivity(), PullRequestsView {

    private lateinit var pullRequestsAdapter: RecyclerViewAdapter
    private val presenter: PullRequestsPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)
        setupList()
        presenter.onCreate(intent)
    }

    private fun setupList() {
        pullRequestsAdapter = RecyclerViewAdapter(object : ViewHolderFactory {
            override fun getType(viewModel: ViewModel): Int = when (viewModel) {
                is PullRequestViewModel -> PULL_REQUEST_LIST_ITEM_HOLDER_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View) = when (viewType) {
                PULL_REQUEST_LIST_ITEM_HOLDER_ID -> PullRequestViewHolder(view, onPullRequestClicked)
                else -> throw IllegalArgumentException()
            }
        })
        rvPullRequests.apply {
            adapter = pullRequestsAdapter
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            addItemDecoration(ListDividerItemDecoration(context))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (linearLayoutManager.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2) {
                        presenter.onScrollBeyond()
                    }
                }
            })
            setHasFixedSize(true)
        }
        srlPullRequests.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    val onPullRequestClicked = object : ViewHolder.Listener<PullRequestViewModel>{
        override fun onClick(viewModel: PullRequestViewModel) {
            val intent = Intent(Intent.ACTION_VIEW).apply{
                data = Uri.parse(viewModel.url)
            }
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setupToolbar(repositoryName: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = repositoryName
    }

    override fun loading(loading: Boolean) {
        srlPullRequests.isRefreshing = loading
    }

    override fun setItems(items: List<ViewModel>, resetList: Boolean) {
        if (resetList){
            pullRequestsAdapter.setItems(items)
        } else {
            pullRequestsAdapter.addItems(items)
        }
    }

}