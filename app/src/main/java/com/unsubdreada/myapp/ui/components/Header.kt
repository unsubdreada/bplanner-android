package com.unsubdreada.myapp.ui.components

import TablerArrowsSort
import TablerDots
import TablerInfinity
import TablerSearch
import TablerTrash
import TablerX
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.ui.theme.AccentBlue
import com.unsubdreada.myapp.ui.theme.InputFieldBackground
import com.unsubdreada.myapp.ui.theme.InputFieldStrokeFocused
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    isSelectedMode: Boolean,
    onDeleteClick: () -> Unit,
    searchText: String,
    onSearchTextExchange: (String) -> Unit,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit,
    availableFilters: List<String>,
    onSortClick: () -> Unit,
    isSearchVisible: Boolean,
    scope: CoroutineScope,
    scrollState: LazyListState
) {
    val focusManager = LocalFocusManager.current
    val carouselState = rememberLazyListState()
    val filteredCategory = remember(availableFilters) {
        listOf("Все") + availableFilters
    }
    val searchFocus = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryDark.copy(0.9f))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "BPlanner",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = TextPrimary
                )

                if (isSelectedMode) {
                    IconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier.minimumInteractiveComponentSize()
                    ) {
                        Icon(
                            imageVector = TablerTrash,
                            contentDescription = "Удалить выбранное",
                            tint = TextPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    AnimatedVisibility(
                        visible = !isSearchVisible,
                        enter = scaleIn() + expandHorizontally(),
                        exit = scaleOut() + shrinkHorizontally()
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    scrollState.animateScrollToItem(0)
                                    delay(50.milliseconds)
                                    searchFocus.requestFocus()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = TablerSearch,
                                contentDescription = null,
                                tint = TextPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = isSearchVisible,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                BasicTextField(
                    singleLine = true,
                    value = searchText,
                    onValueChange = onSearchTextExchange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .focusRequester(searchFocus),
                    cursorBrush = SolidColor(TextSecondary),
                    textStyle = TextStyle(color = TextSecondary, fontSize = 14.sp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus()
                        }
                    ),
                    decorationBox = { innerTextField ->
                        TextFieldDefaults.DecorationBox(
                            value = searchText,
                            innerTextField = innerTextField,
                            enabled = true,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            interactionSource = remember { MutableInteractionSource() },
                            placeholder = {
                                Text(
                                    text = "Поиск по примечанию",
                                    fontSize = 12.sp
                                )
                            },
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = InputFieldBackground,
                                unfocusedPlaceholderColor = TextSecondary,
                                focusedContainerColor = InputFieldBackground,
                                cursorColor = TextSecondary,
                                focusedTextColor = TextSecondary,
                                unfocusedTextColor = TextSecondary,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            shape = CircleShape,
                            leadingIcon = {
                                Icon(
                                    imageVector = TablerSearch,
                                    contentDescription = null,
                                    tint = TextSecondary
                                )
                            },
                            trailingIcon = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy((-15).dp)
                                ) {
                                    if (searchText.isNotEmpty()) {
                                        IconButton(
                                            onClick = {
                                                onSearchTextExchange("")
                                                focusManager.clearFocus()
                                            }
                                        ) {
                                            Icon(
                                                imageVector = TablerX,
                                                contentDescription = null,
                                                tint = TextSecondary
                                            )
                                        }
                                    }
                                    IconButton(
                                        onClick = {
                                            onSortClick()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = TablerArrowsSort,
                                            contentDescription = null,
                                            tint = TextSecondary
                                        )
                                    }
                                }
                            }
                        )
                    }
                )
            }
        }

        Spacer(Modifier.height(5.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(horizontal = 10.dp),
            shape = CircleShape,
            color = InputFieldBackground.copy(0.9f)
        ) {
            LazyRow(
                state = carouselState,
                modifier = Modifier.padding(5.dp)
            ) {
                items(filteredCategory) { categoryTitle ->
                    val isSelected = if (categoryTitle == "Все") {
                        selectedCategory.isEmpty()
                    } else {
                        selectedCategory == categoryTitle
                    }

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (categoryTitle == "Все") {
                                onCategorySelect("")
                            } else {
                                onCategorySelect(if (isSelected) "" else categoryTitle)
                            }
                        },
                        label = {
                            Text(
                                text = categoryTitle,
                                fontSize = 12.sp
                            )
                        },
                        shape = CircleShape,
                        leadingIcon = {
                            val iconVector = if (categoryTitle == "Все") {
                                TablerInfinity
                            } else {
                                FinanceCategory.entries.find { it.title == categoryTitle }?.icon
                                    ?: TablerDots
                            }
                            Icon(
                                imageVector = iconVector,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            labelColor = TextSecondary,
                            selectedLabelColor = AccentBlue,
                            selectedContainerColor = InputFieldStrokeFocused.copy(alpha = 0.15f),
                            disabledLeadingIconColor = TextSecondary,
                            selectedLeadingIconColor = AccentBlue,
                            iconColor = TextSecondary
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = Color.Transparent,
                            selectedBorderWidth = 0.dp,
                            borderWidth = 2.dp
                        )
                    )
                }
            }
        }
    }
}