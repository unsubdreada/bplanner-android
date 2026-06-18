/*
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
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FluentuiSystemIconsPerson: ImageVector
    get() {
        if (_FluentuiSystemIconsPerson != null) return _FluentuiSystemIconsPerson!!
        
        _FluentuiSystemIconsPerson = ImageVector.Builder(
            name = "person",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF9C6CFE), Color(0xFF7A41DC)),
                    start = Offset(7.80816f, 15.0636f),
                    end = Offset(10.3936f, 23.3188f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(17.7541f, 14f)
                curveTo(18.9961f, 14f, 20.0029f, 15.0069f, 20.0029f, 16.2489f)
                verticalLineTo(17.1673f)
                curveTo(20.0029f, 17.7407f, 19.8237f, 18.2997f, 19.4903f, 18.7662f)
                curveTo(17.9445f, 20.9295f, 15.4202f, 22.0012f, 11.9999f, 22.0012f)
                curveTo(8.57891f, 22.0012f, 6.05595f, 20.929f, 4.51379f, 18.7646f)
                curveTo(4.18182f, 18.2987f, 4.00342f, 17.7409f, 4.00342f, 17.1689f)
                verticalLineTo(16.2489f)
                curveTo(4.00342f, 15.0069f, 5.01027f, 14f, 6.25229f, 14f)
                horizontalLineTo(17.7541f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF885EDB), Color(0xFFE362F8)),
                    start = Offset(12.0032f, 13.0475f),
                    end = Offset(15.6233f, 26.5729f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(17.7541f, 14f)
                curveTo(18.9961f, 14f, 20.0029f, 15.0069f, 20.0029f, 16.2489f)
                verticalLineTo(17.1673f)
                curveTo(20.0029f, 17.7407f, 19.8237f, 18.2997f, 19.4903f, 18.7662f)
                curveTo(17.9445f, 20.9295f, 15.4202f, 22.0012f, 11.9999f, 22.0012f)
                curveTo(8.57891f, 22.0012f, 6.05595f, 20.929f, 4.51379f, 18.7646f)
                curveTo(4.18182f, 18.2987f, 4.00342f, 17.7409f, 4.00342f, 17.1689f)
                verticalLineTo(16.2489f)
                curveTo(4.00342f, 15.0069f, 5.01027f, 14f, 6.25229f, 14f)
                horizontalLineTo(17.7541f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF9C6CFE), Color(0xFF7A41DC)),
                    start = Offset(9.37853f, 3.334f),
                    end = Offset(14.4749f, 11.4718f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(12.0005f, 2.00464f)
                curveTo(14.7619f, 2.00464f, 17.0005f, 4.24321f, 17.0005f, 7.00464f)
                curveTo(17.0005f, 9.76606f, 14.7619f, 12.0046f, 12.0005f, 12.0046f)
                curveTo(9.23906f, 12.0046f, 7.00049f, 9.76606f, 7.00049f, 7.00464f)
                curveTo(7.00049f, 4.24321f, 9.23906f, 2.00464f, 12.0005f, 2.00464f)
                close()
            }
        }.build()
        
        return _FluentuiSystemIconsPerson!!
    }

private var _FluentuiSystemIconsPerson: ImageVector? = null