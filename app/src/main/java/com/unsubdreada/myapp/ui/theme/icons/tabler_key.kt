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

val TablerKey: ImageVector
    get() {
        if (_TablerKey != null) return _TablerKey!!
        
        _TablerKey = ImageVector.Builder(
            name = "key",
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
                moveTo(16.555f, 3.843f)
                lineToRelative(3.602f, 3.602f)
                arcToRelative(2.877f, 2.877f, 0f, false, true, 0f, 4.069f)
                lineToRelative(-2.643f, 2.643f)
                arcToRelative(2.877f, 2.877f, 0f, false, true, -4.069f, 0f)
                lineToRelative(-0.301f, -0.301f)
                lineToRelative(-6.558f, 6.558f)
                arcToRelative(2f, 2f, 0f, false, true, -1.239f, 0.578f)
                lineToRelative(-0.175f, 0.008f)
                horizontalLineToRelative(-1.172f)
                arcToRelative(1f, 1f, 0f, false, true, -0.993f, -0.883f)
                lineToRelative(-0.007f, -0.117f)
                verticalLineToRelative(-1.172f)
                arcToRelative(2f, 2f, 0f, false, true, 0.467f, -1.284f)
                lineToRelative(0.119f, -0.13f)
                lineToRelative(0.414f, -0.414f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(-2f)
                lineToRelative(2.144f, -2.144f)
                lineToRelative(-0.301f, -0.301f)
                arcToRelative(2.877f, 2.877f, 0f, false, true, 0f, -4.069f)
                lineToRelative(2.643f, -2.643f)
                arcToRelative(2.877f, 2.877f, 0f, false, true, 4.069f, 0f)
            }
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15f, 9f)
                horizontalLineToRelative(0.01f)
            }
        }.build()
        
        return _TablerKey!!
    }

private var _TablerKey: ImageVector? = null