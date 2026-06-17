package com.unsubdreada.myapp.ui.components

import TablerChartBar
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.ui.theme.AccentBlue
import com.unsubdreada.myapp.ui.theme.BlurredPanelBackground
import com.unsubdreada.myapp.ui.theme.TextSecondary

@Composable
fun Footer() {
    val items = listOf(
        "История" to TablerHistory,
        "График" to TablerChartBar,
        "Настройки" to TablerSettings
    )
    var selectedItem by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .height(54.dp),
        color = Color.Transparent,
        shape = CircleShape,
    ) {
        NavigationBar(
            containerColor = BlurredPanelBackground,
            tonalElevation = 0.dp
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedItem == index
                val (title, iconVector) = item

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { selectedItem = index },
                    label = {
                        Text(
                            text = title,
                            fontSize = 12.sp,
                            modifier = Modifier.offset(y = (-5).dp)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = iconVector,
                            contentDescription = title,
                            modifier = Modifier
                                .size(20.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AccentBlue,
                        selectedTextColor = AccentBlue,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

