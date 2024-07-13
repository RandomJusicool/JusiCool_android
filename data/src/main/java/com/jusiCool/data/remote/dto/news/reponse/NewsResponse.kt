package com.jusiCool.data.remote.dto.news.reponse

import com.jusiCool.domain.model.news.response.ApiResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "detail") val detail: Detail,
    @Json(name = "total_items") val totalItems: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "page") val page: Int,
    @Json(name = "page_size") val pageSize: Int,
    @Json(name = "data") val data: List<Article>
) {

    @JsonClass(generateAdapter = true)
    data class Detail(
        @Json(name = "message") val message: String,
        @Json(name = "code") val code: String,
        @Json(name = "ok") val ok: Boolean
    )

    @JsonClass(generateAdapter = true)
    data class Article(
        @Json(name = "sections") val sections: List<String>,
        @Json(name = "title") val title: String,
        @Json(name = "title_ko") val titleKo: String,
        @Json(name = "summary") val summary: String,
        @Json(name = "summary_ko") val summaryKo: String,
        @Json(name = "body") val body: String,
        @Json(name = "body_ko") val bodyKo: String,
        @Json(name = "image_url") val imageUrl: String,
        @Json(name = "content_url") val contentUrl: String,
        @Json(name = "companies") val companies: List<Company>,
        @Json(name = "published_at") val publishedAt: String
    )

    @JsonClass(generateAdapter = true)
    data class Company(
        @Json(name = "name") val name: String,
        @Json(name = "symbol") val symbol: String,
        @Json(name = "exchange") val exchange: String,
        @Json(name = "importance") val importance: String,
        @Json(name = "sentiment") val sentiment: String,
        @Json(name = "reason") val reason: String
    )
}

fun ApiResponse.toModel() = ApiResponseModel(
    detail = detail.toModel(),
    totalItems = totalItems,
    totalPages = totalPages,
    page = page,
    pageSize = pageSize,
    data = data.map { it.toModel() }
)

fun ApiResponse.Detail.toModel() = ApiResponseModel.Detail(
    message = message,
    code = code,
    ok = ok
)

fun ApiResponse.Article.toModel() = ApiResponseModel.Article(
    sections = sections,
    title = title,
    titleKo = titleKo,
    summary = summary,
    summaryKo = summaryKo,
    body = body,
    bodyKo = bodyKo,
    imageUrl = imageUrl,
    contentUrl = contentUrl,
    companies = companies.map { it.toModel() },
    publishedAt = publishedAt
)

fun ApiResponse.Company.toModel() = ApiResponseModel.Company(
    name = name,
    symbol = symbol,
    exchange = exchange,
    importance = importance,
    sentiment = sentiment,
    reason = reason
)