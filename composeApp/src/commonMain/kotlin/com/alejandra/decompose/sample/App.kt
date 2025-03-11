package com.alejandra.decompose.sample

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.alejandra.decompose.sample.navigation.RootComponent
import com.alejandra.decompose.sample.screens.ScreenA
import com.alejandra.decompose.sample.screens.ScreenB
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.ScreenA -> ScreenA(instance.component)
                is RootComponent.Child.ScreenB -> ScreenB(instance.component.text, instance.component)
            }
        }
    }
}