package com.mdgroup.nasawallpapers.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            background = Color.Black,
            onBackground = Color.White,

            primary = Color(0xFF1C1C1F),
            onPrimary = Color.White,
            primaryVariant = Color(0xFF007AFF),

            secondary = Color(0xFF1C1C1F),
            onSecondary = Color(0xFF8D8D93),
            secondaryVariant = Color(0xFF5B5B60),

            surface = Color(0xFF323236),
            onSurface = Color(0xFF8D8D93),

            error = Color(0xFFB00020),
            onError = Color.White
        )
    } else {
        lightColors(
            background = Color(0xFFF2F2F7),
            onBackground = Color.Black,

            primary = Color(0xFFFFFFFF),
            onPrimary = Color.Black,
            primaryVariant = Color(0xFF007AFF),

            secondary = Color(0xFFEEEEEF),
            onSecondary = Color(0xFF8A8A8E),
            secondaryVariant = Color(0xFFFFFFFF),

            surface = Color(0xFFEFEFF0),
            onSurface = Color(0xFF8A8A8E),

            error = Color(0xFFB00020),
            onError = Color.White
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(6.dp),
        medium = RoundedCornerShape(10.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
