package com.golvia.ng.businessLayer.core

sealed class NetworkState{

   data object Good: NetworkState()

   data object Failed: NetworkState()

}
