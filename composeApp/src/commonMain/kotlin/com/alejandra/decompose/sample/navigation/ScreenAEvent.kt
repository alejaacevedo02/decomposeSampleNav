package com.alejandra.decompose.sample.navigation

sealed interface ScreenAEvent {

    data object ClickButtonA : ScreenAEvent
    data class UpdateText(val text: String) : ScreenAEvent

}