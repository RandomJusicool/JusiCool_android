package com.jusiCool.domain.model.news.response

data class ApiResponseModel(
    val detail: Detail,
    val totalItems: Int,
    val totalPages: Int,
    val page: Int,
    val pageSize: Int,
    val data: List<Article>
) {
    data class Detail(
        val message: String,
        val code: String,
        val ok: Boolean
    )

    data class Article(
        val sections: List<String>,
        val title: String,
        val titleKo: String,
        val summary: String,
        val summaryKo: String,
        val body: String,
        val bodyKo: String,
        val imageUrl: String,
        val contentUrl: String,
        val companies: List<Company>,
        val publishedAt: String
    )

    data class Company(
        val name: String,
        val symbol: String,
        val exchange: String,
        val importance: String,
        val sentiment: String,
        val reason: String
    )
}
