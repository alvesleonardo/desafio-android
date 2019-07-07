package com.leonardoalves.githubrepository.view.repositories

import android.view.View
import com.bumptech.glide.Glide
import com.leonardoalves.githubrepository.R
import com.leonardoalves.githubrepository.view.custom.ViewHolder
import com.leonardoalves.githubrepository.view.utils.circleCropBuilder
import com.leonardoalves.githubrepository.view.utils.crossFade
import kotlinx.android.synthetic.main.repository_item_list.view.*

const val REPOSITORY_LIST_ITEM_HOLDER_ID = R.layout.repository_item_list

class RepostoryViewHolder(itemView: View,  val onClickListener:Listener<RepositoryViewModel>) : ViewHolder<RepositoryViewModel>(itemView) {
    override fun bind(viewModel: RepositoryViewModel) {
        with(itemView){
            tvRepositoryName.text = viewModel.repositoryName
            tvRepositoryDesc.text = viewModel.repositoryDescription
            tvForks.text = viewModel.repositoryForks.toString()
            tvStars.text = viewModel.repositoryStars.toString()
            tvUserFullName.text = viewModel.ownerFullName
            tvUsername.text = viewModel.ownerUserName
            Glide.with(this)
                .load(viewModel.ownerAvatar)
                .circleCropBuilder()
                .crossFade()
                .into(ivOwnerAvatar)
        }
    }

    override fun recycle() {
        Glide.with(itemView)
            .clear(itemView.ivOwnerAvatar)
    }
}