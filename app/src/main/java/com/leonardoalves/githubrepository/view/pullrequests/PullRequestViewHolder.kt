package com.leonardoalves.githubrepository.view.pullrequests

import android.view.View
import com.bumptech.glide.Glide
import com.leonardoalves.githubrepository.R
import com.leonardoalves.githubrepository.view.custom.ViewHolder
import com.leonardoalves.githubrepository.view.utils.circleCropBuilder
import com.leonardoalves.githubrepository.view.utils.crossFade
import kotlinx.android.synthetic.main.pullrequest_item_list.view.*

const val PULL_REQUEST_LIST_ITEM_HOLDER_ID = R.layout.pullrequest_item_list
class PullRequestViewHolder(itemView: View, private val clickListener: Listener<PullRequestViewModel>) : ViewHolder<PullRequestViewModel>(itemView) {
    override fun bind(viewModel: PullRequestViewModel) {
        with(itemView){
            setOnClickListener { clickListener.onClick(viewModel) }
            tvUsername.text = viewModel.userName
            tvFullName.text = viewModel.fullName
            tvPullRequestTitle.text = viewModel.name
            tvPullRequestDesc.text = viewModel.description
            Glide.with(this)
                .load(viewModel.userAvatar)
                .crossFade()
                .circleCropBuilder()
                .into(ivUserAvatar)
        }
    }

    override fun recycle() {
        with(itemView){
            Glide.with(this)
                .clear(ivUserAvatar)
        }
    }
}