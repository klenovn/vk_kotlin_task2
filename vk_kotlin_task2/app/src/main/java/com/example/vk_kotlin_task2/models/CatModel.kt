package com.example.vk_kotlin_task2.models

import com.google.gson.annotations.SerializedName

data class CatModel(
    @SerializedName("_id") val id: String,
    @SerializedName("mimetype") val mimeType: String,
    @SerializedName("size") val size: Long,
    @SerializedName("tags") val tags: List<String>
)