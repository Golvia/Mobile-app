package com.golvia.ng.businessLayer.service.common


import business.datasource.network.common.JAlertResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MainGenericResponse<T>(
    @SerialName("result") var result: T?,
    @SerialName("status") var status: Boolean?,
    @SerialName("alert") var alert: JAlertResponse? = JAlertResponse(),
)