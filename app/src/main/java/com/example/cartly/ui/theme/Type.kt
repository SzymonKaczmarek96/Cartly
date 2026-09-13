package com.example.cartly.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cartly.R

// Set of Material typography styles to start with
val PlusJakartaSans = FontFamily(
    Font(
        resId = R.font.plus_jakarta_sans_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.plus_jakarta_sans_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.plus_jakarta_sans_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.plus_jakarta_sans_bold,
        weight = FontWeight.Bold
    )
)

val Typography = Typography(

    // Display
    displayLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 57.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),

    // Headline
    headlineLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    titleSmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    bodySmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // Labels
    labelLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    labelMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)