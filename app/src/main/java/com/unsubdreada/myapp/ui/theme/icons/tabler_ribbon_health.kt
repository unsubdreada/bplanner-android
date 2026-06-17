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

val TablerRibbonHealth: ImageVector
    get() {
        if (_TablerRibbonHealth != null) return _TablerRibbonHealth!!
        
        _TablerRibbonHealth = ImageVector.Builder(
            name = "ribbon-health",
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
                moveTo(7f, 21f)
                reflectiveCurveToRelative(9.286f, -9.841f, 9.286f, -13.841f)
                arcToRelative(3.864f, 3.864f, 0f, false, false, -1.182f, -3.008f)
                arcToRelative(4.13f, 4.13f, 0f, false, false, -3.104f, -1.144f)
                arcToRelative(4.13f, 4.13f, 0f, false, false, -3.104f, 1.143f)
                arcToRelative(3.864f, 3.864f, 0f, false, false, -1.182f, 3.01f)
                curveToRelative(0f, 4f, 9.286f, 13.84f, 9.286f, 13.84f)
            }
        }.build()
        
        return _TablerRibbonHealth!!
    }

private var _TablerRibbonHealth: ImageVector? = null