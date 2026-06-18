package com.unsubdreada.myapp.ui.theme.icons/*
MIT License

Copyright (c) 2020 Microsoft Corporation

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FluentuiSystemIconsBookDatabase: ImageVector
    get() {
        if (_FluentuiSystemIconsBookDatabase != null) return _FluentuiSystemIconsBookDatabase!!

        _FluentuiSystemIconsBookDatabase = ImageVector.Builder(
            name = "book-database",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF9DEAFF), Color(0xFF58AAFE)),
                    start = Offset(12.1739f, 20.4f),
                    end = Offset(12.1739f, 18f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(5f, 19f)
                horizontalLineTo(20.2812f)
                curveTo(20.2812f, 19f, 20f, 19.5f, 20f, 20f)
                curveTo(20f, 20.5f, 20.2812f, 21f, 20.2812f, 21f)
                horizontalLineTo(6f)
                curveTo(5.44772f, 21f, 5f, 20.5523f, 5f, 20f)
                verticalLineTo(19f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF20AC9D), Color(0xFF2052CB)),
                    start = Offset(9.69295f, 5.74211f),
                    end = Offset(12.6806f, 27.308f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(4f, 4.5f)
                curveTo(4f, 3.11929f, 5.11929f, 2f, 6.5f, 2f)
                horizontalLineTo(18f)
                curveTo(19.3807f, 2f, 20.5f, 3.11929f, 20.5f, 4.5f)
                verticalLineTo(18.75f)
                curveTo(20.5f, 19.1642f, 20.1642f, 19.5f, 19.75f, 19.5f)
                horizontalLineTo(5.5f)
                curveTo(5.5f, 20.0523f, 5.94772f, 20.5f, 6.5f, 20.5f)
                horizontalLineTo(19.75f)
                curveTo(20.1642f, 20.5f, 20.5f, 20.8358f, 20.5f, 21.25f)
                curveTo(20.5f, 21.6642f, 20.1642f, 22f, 19.75f, 22f)
                horizontalLineTo(6.5f)
                curveTo(5.11929f, 22f, 4f, 20.8807f, 4f, 19.5f)
                verticalLineTo(4.5f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF9FF0F9), Color(0xFF6CE0FF)),
                    start = Offset(10.4487f, 4.31375f),
                    end = Offset(15.0534f, 11.4776f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(7f, 6f)
                curveTo(7f, 5.44772f, 7.44772f, 5f, 8f, 5f)
                horizontalLineTo(16f)
                curveTo(16.5523f, 5f, 17f, 5.44772f, 17f, 6f)
                verticalLineTo(8f)
                curveTo(17f, 8.55228f, 16.5523f, 9f, 16f, 9f)
                horizontalLineTo(8f)
                curveTo(7.44772f, 9f, 7f, 8.55228f, 7f, 8f)
                verticalLineTo(6f)
                close()
            }
            path(
                fill = SolidColor(Color.Transparent)
            ) {
                moveTo(4f, 4.5f)
                curveTo(4f, 3.11929f, 5.11929f, 2f, 6.5f, 2f)
                horizontalLineTo(18f)
                curveTo(19.3807f, 2f, 20.5f, 3.11929f, 20.5f, 4.5f)
                curveTo(20.5f, 10.0352f, 20.5f, 16.0253f, 20.5f, 21.25f)
                curveTo(20.5f, 21.6642f, 20.1642f, 22f, 19.75f, 22f)
                horizontalLineTo(6.5f)
                curveTo(5.11929f, 22f, 4f, 20.8807f, 4f, 19.5f)
                verticalLineTo(4.5f)
                close()
            }
            path(
                fill = SolidColor(Color.Transparent)
            ) {
                moveTo(4f, 4.5f)
                curveTo(4f, 3.11929f, 5.11929f, 2f, 6.5f, 2f)
                horizontalLineTo(18f)
                curveTo(19.3807f, 2f, 20.5f, 3.11929f, 20.5f, 4.5f)
                curveTo(20.5f, 10.0352f, 20.5f, 16.0253f, 20.5f, 21.25f)
                curveTo(20.5f, 21.6642f, 20.1642f, 22f, 19.75f, 22f)
                horizontalLineTo(6.5f)
                curveTo(5.11929f, 22f, 4f, 20.8807f, 4f, 19.5f)
                verticalLineTo(4.5f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF3BD5FF), Color(0xFF4894FE)),
                    start = Offset(15.3608f, 12.5749f),
                    end = Offset(21.1933f, 22.0723f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(19.5f, 15.8965f)
                curveTo(20.0293f, 15.8219f, 20.5336f, 15.708f, 21f, 15.5585f)
                curveTo(21.3509f, 15.446f, 21.6803f, 15.3134f, 21.9827f, 15.1622f)
                curveTo(22.3368f, 14.9852f, 22.6902f, 14.7618f, 23f, 14.4917f)
                verticalLineTo(20.5f)
                curveTo(23f, 21.8807f, 20.7614f, 23f, 18f, 23f)
                curveTo(15.9497f, 23f, 14.1876f, 22.383f, 13.416f, 21.5f)
                curveTo(13.1484f, 21.1938f, 13f, 20.8556f, 13f, 20.5f)
                verticalLineTo(14.4917f)
                curveTo(13.3098f, 14.7618f, 13.6632f, 14.9852f, 14.0173f, 15.1622f)
                curveTo(15.0882f, 15.6976f, 16.4977f, 16f, 18f, 16f)
                curveTo(18.5141f, 16f, 19.0174f, 15.9646f, 19.5f, 15.8965f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF66C0FF), Color(0xFFC8F3FF)),
                    start = Offset(21.3333f, 17.5f),
                    end = Offset(15.2222f, 12f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(19.5f, 12.1144f)
                curveTo(20.0464f, 12.2003f, 20.5523f, 12.3317f, 21f, 12.4998f)
                curveTo(22.2144f, 12.9559f, 23f, 13.6821f, 23f, 14.5f)
                curveTo(23f, 15.3179f, 22.2144f, 16.0441f, 21f, 16.5002f)
                curveTo(20.5523f, 16.6683f, 20.0464f, 16.7997f, 19.5f, 16.8855f)
                curveTo(19.0265f, 16.9599f, 18.5226f, 17f, 18f, 17f)
                curveTo(15.2386f, 17f, 13f, 15.8807f, 13f, 14.5f)
                curveTo(13f, 13.1193f, 15.2386f, 12f, 18f, 12f)
                curveTo(18.5226f, 12f, 19.0265f, 12.0401f, 19.5f, 12.1144f)
                close()
            }
        }.build()

        return _FluentuiSystemIconsBookDatabase!!
    }

private var _FluentuiSystemIconsBookDatabase: ImageVector? = null