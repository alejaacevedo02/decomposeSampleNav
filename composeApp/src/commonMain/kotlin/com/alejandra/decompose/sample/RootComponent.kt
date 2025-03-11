package com.alejandra.decompose.sample

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

     val childStack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.ScreenA,
        handleBackButton = true,
        childFactory = ::createChild

    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            Config.ScreenA -> Child.ScreenA(
                ScreenAComponent(
                    componentContext,
                    ::onNavigateToScreenB
                )
            )

            is Config.ScreenB -> Child.ScreenB(
                ScreenBComponent(
                    text = config.text,
                    componentContext = componentContext,
                    onGoBack = { navigation.pop() }
                )
            )
        }
    }

    fun onNavigateToScreenB(text: String) {
        navigation.pushNew(Config.ScreenB(text))
    }

    // For navigation
    sealed class Child {
        class ScreenA(val component: ScreenAComponent) : Child()
        class ScreenB(val component: ScreenBComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object ScreenA : Config()

        @Serializable
        data class ScreenB(val text: String) : Config()
    }


}