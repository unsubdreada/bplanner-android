/*
MIT License

Copyright (c) 2020-2026 Paweł Kuna

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TablerCoins: ImageVector
    get() {
        if (_TablerCoins != null) return _TablerCoins!!
        
        _TablerCoins = ImageVector.Builder(
            name = "coins",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 14f)
                curveToRelative(0f, 1.657f, 2.686f, 3f, 6f, 3f)
                reflectiveCurveToRelative(6f, -1.343f, 6f, -3f)
                reflectiveCurveToRelative(-2.686f, -3f, -6f, -3f)
                reflectiveCurveToRelative(-6f, 1.343f, -6f, 3f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 14f)
                verticalLineToRelative(4f)
                curveToRelative(0f, 1.656f, 2.686f, 3f, 6f, 3f)
                reflectiveCurveToRelative(6f, -1.344f, 6f, -3f)
                verticalLineToRelative(-4f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 6f)
                curveToRelative(0f, 1.072f, 1.144f, 2.062f, 3f, 2.598f)
                reflectiveCurveToRelative(4.144f, 0.536f, 6f, 0f)
                curveToRelative(1.856f, -0.536f, 3f, -1.526f, 3f, -2.598f)
                curveToRelative(0f, -1.072f, -1.144f, -2.062f, -3f, -2.598f)
                reflectiveCurveToRelative(-4.144f, -0.536f, -6f, 0f)
                curveToRelative(-1.856f, 0.536f, -3f, 1.526f, -3f, 2.598f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 6f)
                verticalLineToRelative(10f)
                curveToRelative(0f, 0.888f, 0.772f, 1.45f, 2f, 2f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 11f)
                curveToRelative(0f, 0.888f, 0.772f, 1.45f, 2f, 2f)
            }
        }.build()
        
        return _TablerCoins!!
    }

private var _TablerCoins: ImageVector? = null