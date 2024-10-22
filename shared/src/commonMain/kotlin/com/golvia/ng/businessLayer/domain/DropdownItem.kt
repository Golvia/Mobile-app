package com.golvia.ng.businessLayer.domain

/**
 * davidsunday
 */

class DropdownItem(
    val drawable: Int?,
    val text: String,
    val additionalInfo: String = ""
)

fun convertStringListToDropdownItem(list: List<String>): List<DropdownItem> {
    return list.map { string ->
        DropdownItem(
            drawable = null,
            text = string,
            additionalInfo = ""
        )
    }
}