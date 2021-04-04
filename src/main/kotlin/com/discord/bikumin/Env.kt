package com.discord.bikumin

import java.awt.Color
import kotlin.properties.ReadOnlyProperty

object Env {
    val BOT_TOKEN by string { "" }
    val HOST by string { "0.0.0.0" }
    val PORT by int { 80 }
    val LOG by string { "INFO" }
    val EMBED_COLOR by color { Color(0, 255, 0) }
}

private val stringOrNull: ReadOnlyProperty<Env, String?>
    get() = ReadOnlyProperty { _, property ->
        System.getenv(property.name)
    }

private fun string(default: () -> String): ReadOnlyProperty<Env, String> = ReadOnlyProperty { _, property ->
    System.getenv(property.name) ?: default()
}

private fun color(default: () -> Color): ReadOnlyProperty<Env, Color> = ReadOnlyProperty { _, property ->
    val data = System.getenv(property.name).split(",").map { it.trim() }
    Color(data[0].toInt(), data[1].toInt(), data[2].toInt()) ?: default()
}

private fun int(default: () -> Int): ReadOnlyProperty<Env, Int> = ReadOnlyProperty { _, property ->
    System.getenv(property.name)?.toIntOrNull() ?: default()
}