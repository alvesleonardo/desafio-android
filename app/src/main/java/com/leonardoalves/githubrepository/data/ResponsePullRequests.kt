package com.leonardoalves.githubrepository.data

import com.google.gson.annotations.SerializedName
import com.leonardoalves.githubrepository.data.entity.Head
import com.leonardoalves.githubrepository.data.entity.LabelsItem
import com.leonardoalves.githubrepository.data.entity.User

data class ResponsePullRequests(
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("diff_url")
	val diffUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("head")
	val head: Head? = null,

	@field:SerializedName("author_association")
	val authorAssociation: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("draft")
	val draft: Boolean? = null,

	@field:SerializedName("comments_url")
	val commentsUrl: String? = null,

	@field:SerializedName("active_lock_reason")
	val activeLockReason: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("locked")
	val locked: Boolean? = null,

	@field:SerializedName("commits_url")
	val commitsUrl: String? = null,

	@field:SerializedName("closed_at")
	val closedAt: String? = null,

	@field:SerializedName("labels")
	val labels: List<LabelsItem?>? = null,

	@field:SerializedName("user")
	val user: User? = null
)