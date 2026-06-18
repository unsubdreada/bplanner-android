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
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FluentuiSystemIconsPersonStarburst: ImageVector
    get() {
        if (_FluentuiSystemIconsPersonStarburst != null) return _FluentuiSystemIconsPersonStarburst!!

        _FluentuiSystemIconsPersonStarburst = ImageVector.Builder(
            name = "person-starburst",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF1EC8B0), Color(0xFF2764E7)),
                    start = Offset(5.26165f, 0.763044f),
                    end = Offset(15.3747f, 23.2365f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(9.83976f, 2.03398f)
                curveTo(9.95228f, 2.07226f, 10.0622f, 2.11779f, 10.1688f, 2.17028f)
                lineTo(11.4516f, 2.80183f)
                curveTo(11.7997f, 2.97323f, 12.2077f, 2.97323f, 12.5558f, 2.80183f)
                lineTo(13.8386f, 2.17028f)
                curveTo(15.2012f, 1.49942f, 16.8496f, 2.06018f, 17.5205f, 3.42276f)
                lineTo(17.5939f, 3.58509f)
                lineTo(17.6568f, 3.75183f)
                lineTo(18.1172f, 5.10543f)
                curveTo(18.2422f, 5.4728f, 18.5307f, 5.7613f, 18.8981f, 5.88627f)
                lineTo(20.2517f, 6.34673f)
                curveTo(21.6895f, 6.83585f, 22.4586f, 8.39799f, 21.9695f, 9.83585f)
                curveTo(21.9312f, 9.94837f, 21.8857f, 10.0583f, 21.8332f, 10.1649f)
                lineTo(21.2017f, 11.4477f)
                curveTo(21.0303f, 11.7958f, 21.0303f, 12.2038f, 21.2017f, 12.5519f)
                lineTo(21.8332f, 13.8347f)
                curveTo(22.5041f, 15.1972f, 21.9433f, 16.8457f, 20.5807f, 17.5165f)
                curveTo(20.4741f, 17.569f, 20.3642f, 17.6146f, 20.2517f, 17.6528f)
                lineTo(18.8981f, 18.1133f)
                curveTo(18.5307f, 18.2383f, 18.2422f, 18.5268f, 18.1172f, 18.8941f)
                lineTo(17.6568f, 20.2477f)
                curveTo(17.1676f, 21.6856f, 15.6055f, 22.4547f, 14.1676f, 21.9656f)
                curveTo(14.0551f, 21.9273f, 13.9452f, 21.8818f, 13.8386f, 21.8293f)
                lineTo(12.5558f, 21.1977f)
                curveTo(12.2077f, 21.0263f, 11.7997f, 21.0263f, 11.4516f, 21.1977f)
                lineTo(10.1688f, 21.8293f)
                curveTo(8.80623f, 22.5002f, 7.1578f, 21.9394f, 6.48694f, 20.5768f)
                curveTo(6.43444f, 20.4702f, 6.38891f, 20.3603f, 6.35063f, 20.2477f)
                lineTo(5.89017f, 18.8941f)
                curveTo(5.7652f, 18.5268f, 5.47671f, 18.2383f, 5.10933f, 18.1133f)
                lineTo(3.75573f, 17.6528f)
                curveTo(2.31787f, 17.1637f, 1.54876f, 15.6016f, 2.03789f, 14.1637f)
                curveTo(2.07616f, 14.0512f, 2.12169f, 13.9413f, 2.17419f, 13.8347f)
                lineTo(2.80573f, 12.5519f)
                curveTo(2.97714f, 12.2038f, 2.97714f, 11.7958f, 2.80573f, 11.4477f)
                lineTo(2.17419f, 10.1649f)
                curveTo(1.50333f, 8.80233f, 2.06408f, 7.15389f, 3.42667f, 6.48303f)
                curveTo(3.5333f, 6.43053f, 3.64322f, 6.385f, 3.75573f, 6.34673f)
                lineTo(5.10933f, 5.88627f)
                curveTo(5.47671f, 5.7613f, 5.7652f, 5.4728f, 5.89017f, 5.10543f)
                lineTo(6.35063f, 3.75183f)
                curveTo(6.83976f, 2.31396f, 8.40189f, 1.54486f, 9.83976f, 2.03398f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFFFF6CE8), Color(0xFFFF6CE8)),
                    start = Offset(15.1339f, 4.37063f),
                    end = Offset(19.1424f, 24.2882f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(9.83976f, 2.03398f)
                curveTo(9.95228f, 2.07226f, 10.0622f, 2.11779f, 10.1688f, 2.17028f)
                lineTo(11.4516f, 2.80183f)
                curveTo(11.7997f, 2.97323f, 12.2077f, 2.97323f, 12.5558f, 2.80183f)
                lineTo(13.8386f, 2.17028f)
                curveTo(15.2012f, 1.49942f, 16.8496f, 2.06018f, 17.5205f, 3.42276f)
                lineTo(17.5939f, 3.58509f)
                lineTo(17.6568f, 3.75183f)
                lineTo(18.1172f, 5.10543f)
                curveTo(18.2422f, 5.4728f, 18.5307f, 5.7613f, 18.8981f, 5.88627f)
                lineTo(20.2517f, 6.34673f)
                curveTo(21.6895f, 6.83585f, 22.4586f, 8.39799f, 21.9695f, 9.83585f)
                curveTo(21.9312f, 9.94837f, 21.8857f, 10.0583f, 21.8332f, 10.1649f)
                lineTo(21.2017f, 11.4477f)
                curveTo(21.0303f, 11.7958f, 21.0303f, 12.2038f, 21.2017f, 12.5519f)
                lineTo(21.8332f, 13.8347f)
                curveTo(22.5041f, 15.1972f, 21.9433f, 16.8457f, 20.5807f, 17.5165f)
                curveTo(20.4741f, 17.569f, 20.3642f, 17.6146f, 20.2517f, 17.6528f)
                lineTo(18.8981f, 18.1133f)
                curveTo(18.5307f, 18.2383f, 18.2422f, 18.5268f, 18.1172f, 18.8941f)
                lineTo(17.6568f, 20.2477f)
                curveTo(17.1676f, 21.6856f, 15.6055f, 22.4547f, 14.1676f, 21.9656f)
                curveTo(14.0551f, 21.9273f, 13.9452f, 21.8818f, 13.8386f, 21.8293f)
                lineTo(12.5558f, 21.1977f)
                curveTo(12.2077f, 21.0263f, 11.7997f, 21.0263f, 11.4516f, 21.1977f)
                lineTo(10.1688f, 21.8293f)
                curveTo(8.80623f, 22.5002f, 7.1578f, 21.9394f, 6.48694f, 20.5768f)
                curveTo(6.43444f, 20.4702f, 6.38891f, 20.3603f, 6.35063f, 20.2477f)
                lineTo(5.89017f, 18.8941f)
                curveTo(5.7652f, 18.5268f, 5.47671f, 18.2383f, 5.10933f, 18.1133f)
                lineTo(3.75573f, 17.6528f)
                curveTo(2.31787f, 17.1637f, 1.54876f, 15.6016f, 2.03789f, 14.1637f)
                curveTo(2.07616f, 14.0512f, 2.12169f, 13.9413f, 2.17419f, 13.8347f)
                lineTo(2.80573f, 12.5519f)
                curveTo(2.97714f, 12.2038f, 2.97714f, 11.7958f, 2.80573f, 11.4477f)
                lineTo(2.17419f, 10.1649f)
                curveTo(1.50333f, 8.80233f, 2.06408f, 7.15389f, 3.42667f, 6.48303f)
                curveTo(3.5333f, 6.43053f, 3.64322f, 6.385f, 3.75573f, 6.34673f)
                lineTo(5.10933f, 5.88627f)
                curveTo(5.47671f, 5.7613f, 5.7652f, 5.4728f, 5.89017f, 5.10543f)
                lineTo(6.35063f, 3.75183f)
                curveTo(6.83976f, 2.31396f, 8.40189f, 1.54486f, 9.83976f, 2.03398f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colors = listOf(Color(0xFF9DEAFF), Color(0xFFffffff)),
                    start = Offset(19.2462f, 28.3806f),
                    end = Offset(-1.9464f, -2.71174f),
                    tileMode = TileMode.Clamp
                )
            ) {
                moveTo(11.9999f, 5.96484f)
                curveTo(10.6667f, 5.96484f, 9.58587f, 7.04563f, 9.58587f, 8.37884f)
                curveTo(9.58587f, 9.71206f, 10.6667f, 10.7928f, 11.9999f, 10.7928f)
                curveTo(13.3331f, 10.7928f, 14.4139f, 9.71206f, 14.4139f, 8.37884f)
                curveTo(14.4139f, 7.04563f, 13.3331f, 5.96484f, 11.9999f, 5.96484f)
                close()
                moveTo(15.0174f, 11.9998f)
                lineTo(8.98235f, 11.9998f)
                curveTo(7.98245f, 11.9998f, 7.17188f, 12.8105f, 7.17188f, 13.8103f)
                curveTo(7.17188f, 15.1574f, 7.72585f, 16.2367f, 8.63494f, 16.9664f)
                curveTo(9.52965f, 17.6846f, 10.7288f, 18.0349f, 11.9999f, 18.0349f)
                curveTo(13.2709f, 18.0349f, 14.4701f, 17.6846f, 15.3648f, 16.9664f)
                curveTo(16.2739f, 16.2367f, 16.8279f, 15.1575f, 16.8279f, 13.8103f)
                curveTo(16.8279f, 12.8104f, 16.0173f, 11.9998f, 15.0174f, 11.9998f)
                close()
            }
        }.build()

        return _FluentuiSystemIconsPersonStarburst!!
    }

private var _FluentuiSystemIconsPersonStarburst: ImageVector? = null