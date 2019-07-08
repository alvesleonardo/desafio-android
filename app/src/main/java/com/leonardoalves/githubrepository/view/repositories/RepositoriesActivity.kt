package com.leonardoalves.githubrepository.view.repositories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leonardoalves.githubrepository.R
import com.leonardoalves.githubrepository.view.custom.*
import com.leonardoalves.githubrepository.view.pullrequests.PULL_REQUEST_CREATOR_EXTRA
import com.leonardoalves.githubrepository.view.pullrequests.PULL_REQUEST_REPOSITORY_EXTRA
import com.leonardoalves.githubrepository.view.pullrequests.PullRequestsActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RepositoriesActivity : AppCompatActivity(), RepositoriesView {

    private val presenter: RepositoriesPresenter by inject { parametersOf(this) }

    private lateinit var repositoryAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
        presenter.getRepositoryList()
    }

    private fun setupList() {
        repositoryAdapter = RecyclerViewAdapter(object : ViewHolderFactory {
            override fun getType(viewModel: ViewModel): Int = when (viewModel) {
                is RepositoryViewModel -> REPOSITORY_LIST_ITEM_HOLDER_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View) = when (viewType) {
                REPOSITORY_LIST_ITEM_HOLDER_ID -> RepostoryViewHolder(view, onRepositoryClicked)
                else -> throw IllegalArgumentException()
            }
        })
        rvRepositories.apply {
            adapter = repositoryAdapter
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            addItemDecoration(ListDividerItemDecoration(context))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (linearLayoutManager.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2){
                        presenter.onScrollBeyond()
                    }
                }
            })
        }
        srlRepositories.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    private val onRepositoryClicked = object : ViewHolder.Listener<RepositoryViewModel>{
        override fun onClick(viewModel: RepositoryViewModel) {
            val intent = Intent(this@RepositoriesActivity, PullRequestsActivity::class.java).apply {
                putExtra(PULL_REQUEST_CREATOR_EXTRA, viewModel.ownerUserName)
                putExtra(PULL_REQUEST_REPOSITORY_EXTRA, viewModel.repositoryName)
            }
            startActivity(intent)
        }
    }

    override fun setItems(items: List<RepositoryViewModel>, resetList: Boolean) {
        if (resetList){
            repositoryAdapter.setItems(items)
        } else {
            repositoryAdapter.addItems(items)
        }
    }
}
