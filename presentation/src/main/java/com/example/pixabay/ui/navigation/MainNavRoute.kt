package com.example.pixabay.ui.navigation

const val ARG_IMAGE_ID = "IMAGE_ID"

sealed class MainNavRoute(val path: String) {

    data object Splash : MainNavRoute("splash")

    data object Search : MainNavRoute("search")

    data object Detail : MainNavRoute("detail") {
        const val imageId = ARG_IMAGE_ID
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}