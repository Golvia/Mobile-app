package com.golvia.ng.businessLayer.domain

/**
 * davidsunday
 */

data class FeedsData(
    val profileImageUrl: String?,
    val profileName: String?,
    val agent: String?,
    val type: String?,
    val description: String?,
    val descImage: String?,
    val likes: List<Likes>?,
    val comments: List<Comments>?,
    val share: List<Shares>?
)

data class Likes(
    val id: Int?,
    val likes: Int?
)

data class Comments(
    val id: Int?,
    val comment: String?,
)

data class Shares(
    val id: Int?,
    val share: String?
)
