package com.leonardoalves.githubrepository.data

import com.google.gson.annotations.SerializedName
import com.leonardoalves.githubrepository.data.entity.Repository

data class ResponseRespository(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<Repository?>? = null
)