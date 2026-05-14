package com.example.roadmapaperolesson3exercise.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFF41C40),
    secondary = Color(0xff2D2D2D),
    tertiary = Pink80,
    background = Color(0xFF212121),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF888888),
    onSurface = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF41C40),
    secondary = Color(0xffFFFFFF),
    tertiary = Pink40,
    background = Color(0xFFF2F2F2),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF000000),
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color(0xFF888888),
    onSurface = Color(0xFF000000),
)
//primary:
//- Màu chính của app
//- Dùng cho Button chính, FAB, thành phần nổi bật
//
//secondary:
//- Màu phụ của app
//- Dùng cho UI phụ, chip, filter, accent phụ
//
//tertiary:
//- Màu nhấn thứ 3
//- Dùng cho highlight, success, warning, biểu đồ
//
//background:
//- Màu nền toàn màn hình app
//- Thường dùng cho Scaffold hoặc screen root
//
//surface:
//- Màu nền của component nổi trên background
//- Dùng cho Card, Dialog, BottomSheet, Menu
//
//onPrimary:
//- Màu text/icon nằm trên primary
//- Ví dụ text trong Button primary
//
//onSecondary:
//- Màu text/icon nằm trên secondary
//
//onTertiary:
//- Màu text/icon nằm trên tertiary
//
//onBackground:
//- Màu text/icon nằm trên background
//- Thường là màu chữ chính của màn hình
//
//onSurface:
//- Màu text/icon nằm trên surface
//- Thường là text trong Card/Dialog
//
//primaryContainer:
//- Phiên bản nhạt hơn của primary
//- Dùng cho container/background phụ liên quan primary
//
//onPrimaryContainer:
//- Màu text/icon nằm trên primaryContainer
//
//secondaryContainer:
//- Phiên bản nhạt hơn của secondary
//
//onSecondaryContainer:
//- Màu text/icon nằm trên secondaryContainer
//
//tertiaryContainer:
//- Phiên bản nhạt hơn của tertiary
//
//onTertiaryContainer:
//- Màu text/icon nằm trên tertiaryContainer
//
//error:
//- Màu báo lỗi
//- Ví dụ validation error
//
//onError:
//- Màu text/icon nằm trên error
//
//errorContainer:
//- Background lỗi nhẹ hơn error
//
//onErrorContainer:
//- Text/icon nằm trên errorContainer
//
//surfaceVariant:
//- Biến thể khác của surface
//- Dùng cho card phụ, item phụ
//
//onSurfaceVariant:
//- Text/icon nằm trên surfaceVariant
//
//outline:
//- Màu border/viền
//- Divider, TextField border
//
//outlineVariant:
//- Border nhẹ hơn outline
//
//inverseSurface:
//- Màu surface đảo ngược
//- Thường dùng cho Snackbar
//
//inverseOnSurface:
//- Màu content nằm trên inverseSurface
//
//inversePrimary:
//- Phiên bản đảo/ngược của primary
//- Dùng trong dark/light contrast
//
//scrim:
//- Màu lớp phủ tối
//- Ví dụ background phía sau Dialog/BottomSheet
//
//surfaceTint:
//- Màu tint áp dụng cho surface elevation
//- Material 3 dùng để tạo hiệu ứng nổi

@Composable
fun RoadMapAperoLesson3ExerciseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}