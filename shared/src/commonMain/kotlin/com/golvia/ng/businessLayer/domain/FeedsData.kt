package com.golvia.ng.businessLayer.domain

import kotlinx.serialization.Serializable


/**
 * davidsunday
 */

@Serializable
data class FeedsData(
    val  id: Int?,
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

@Serializable
data class Likes(
    val id: Int?,
    val likes: Int?
)

@Serializable
data class Comments(
    val id: Int?,
    val comment: String?,
)

@Serializable
data class Shares(
    val id: Int?,
    val share: String?
)



//dummy data to be removed

fun createDummyFeedsData(): List<FeedsData> {
    val dummyLikes = listOf(
        Likes(id = 1, likes = 10),
        Likes(id = 2, likes = 5),
        Likes(id = 3, likes = 15)
    )

    val dummyComments = listOf(
        Comments(id = 1, comment = "Great post!"),
        Comments(id = 2, comment = "Thanks for sharing!"),
        Comments(id = 3, comment = "Very informative."),
    )

    val dummyShares = listOf(
        Shares(id = 1, share = "Shared on Facebook"),
        Shares(id = 2, share = "Shared on Twitter"),
        Shares(id = 3, share = "Shared on Instagram"),
    )

    return List(10) { index ->
        FeedsData(
            id = index,
            profileImageUrl = "https://example.com/profile_$index.jpg",
            profileName = "User $index",
            agent = "Agent - top scout",
            type = if (index % 2 == 0) "Post" else "Story",
            description = "Delight to get a young talented football joining\n" +
                    "wolves FC for 2024/2025 season on-going trials $index.",
            descImage = "https://example.com/image_$index.jpg",
            likes = dummyLikes.take(index % 3 + 1),
            comments = dummyComments.take(index % 3 + 1),
            share = dummyShares.take(index % 3 + 1)
        )
    }
}
