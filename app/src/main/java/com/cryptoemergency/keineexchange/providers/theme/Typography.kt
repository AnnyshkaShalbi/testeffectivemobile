package com.cryptoemergency.keineexchange.providers.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cryptoemergency.keineexchange.R

val sfProDisplayFontFamily = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
)

val typography = Typography(
    title1 = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 27.sp,
    ),
    title2 = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    title3 = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    title4 = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 17.sp,
    ),
    text1 = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 17.sp,
    ),
    buttonText1 = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    buttonText2 = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 19.sp,
    ),
    tabText = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 11.sp,
    ),
    number = TextStyle(
        fontFamily = sfProDisplayFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 7.sp,
        lineHeight = 8.sp,
    ),
    fontText = TextStyle(
        fontFamily = sfProDisplayFontFamily
    )
)
