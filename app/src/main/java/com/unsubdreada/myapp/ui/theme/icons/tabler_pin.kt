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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TablerPin: ImageVector
    get() {
        if (_TablerPin != null) return _TablerPin!!
        
        _TablerPin = ImageVector.Builder(
            name = "pin",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(15.113f, 3.21f)
                lineToRelative(0.094f, 0.083f)
                lineToRelative(5.5f, 5.5f)
                arcToRelative(1f, 1f, 0f, false, true, -1.175f, 1.59f)
                lineToRelative(-3.172f, 3.171f)
                lineToRelative(-1.424f, 3.797f)
                arcToRelative(1f, 1f, 0f, false, true, -0.158f, 0.277f)
                lineToRelative(-0.07f, 0.08f)
                lineToRelative(-1.5f, 1.5f)
                arcToRelative(1f, 1f, 0f, false, true, -1.32f, 0.082f)
                lineToRelative(-0.095f, -0.083f)
                lineToRelative(-2.793f, -2.792f)
                lineToRelative(-3.793f, 3.792f)
                arcToRelative(1f, 1f, 0f, false, true, -1.497f, -1.32f)
                lineToRelative(0.083f, -0.094f)
                lineToRelative(3.792f, -3.793f)
                lineToRelative(-2.792f, -2.793f)
                arcToRelative(1f, 1f, 0f, false, true, -0.083f, -1.32f)
                lineToRelative(0.083f, -0.094f)
                lineToRelative(1.5f, -1.5f)
                arcToRelative(1f, 1f, 0f, false, true, 0.258f, -0.187f)
                lineToRelative(0.098f, -0.042f)
                lineToRelative(3.796f, -1.425f)
                lineToRelative(3.171f, -3.17f)
                arcToRelative(1f, 1f, 0f, false, true, 1.497f, -1.26f)
                close()
            }
        }.build()
        
        return _TablerPin!!
    }

private var _TablerPin: ImageVector? = null