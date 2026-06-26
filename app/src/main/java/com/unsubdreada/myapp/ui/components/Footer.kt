package com.unsubdreada.myapp.ui.components

import TablerChartBar
import TablerCoins
import TablerHistory
import TablerSettings
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.ui.theme.AccentBlue
import com.unsubdreada.myapp.ui.theme.ScreenBackground
import com.unsubdreada.myapp.ui.theme.TextPrimary
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.blur.blurEffect
import dev.chrisbanes.haze.blur.materials.HazeMaterials
import dev.chrisbanes.haze.hazeEffect

@Composable
fun Footer(
    selectedItem: Int,
    onTabSelected: (Int) -> Unit,
    onTabReselected: (Int) -> Unit,
    hazeState: HazeState
) {
    val items = listOf(
        "История" to TablerHistory,
        "График" to TablerChartBar,
        "Цели" to TablerCoins,
        "Настройки" to TablerSettings
    )
    val hazeStyle = HazeMaterials.ultraThin(ScreenBackground)

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(30.dp))
            .height(64.dp)
            .hazeEffect(state = hazeState) {
                blurEffect {
                    style = hazeStyle
                    blurredEdgeTreatment
                }
            },
        color = Color.Transparent,
        shape = CircleShape,
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedItem == index
                val (title, iconVector) = item

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (index == selectedItem) onTabReselected(index)
                        else onTabSelected(index)
                    },
                    modifier = Modifier.weight(1f),
                    label = {
                        Text(
                            text = title,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.offset(y = (-3).dp)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = iconVector,
                            contentDescription = title,
                            modifier = Modifier
                                .size(24.dp)
                                .offset(y = 3.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AccentBlue,
                        selectedTextColor = AccentBlue,
                        unselectedIconColor = TextPrimary,
                        unselectedTextColor = TextPrimary,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

