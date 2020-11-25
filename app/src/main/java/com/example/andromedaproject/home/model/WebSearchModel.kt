package com.example.andromedaproject.home.model

data class WebSearchModel(
    val meta: MetaData,
    val documents: List<DocumentsData>
)

data class MetaData(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean
)

data class DocumentsData(
    val datetime: String,
    val contents: String,
    val title: String,
    val url: String
)