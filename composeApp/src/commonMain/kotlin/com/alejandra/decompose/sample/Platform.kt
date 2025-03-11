package com.alejandra.decompose.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform