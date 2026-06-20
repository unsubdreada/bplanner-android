package com.unsubdreada.myapp.ui.screens

import TablerChevronLeft
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.R
import com.unsubdreada.myapp.data.AppDatabase
import com.unsubdreada.myapp.model.BackupManager
import com.unsubdreada.myapp.ui.components.SettingRow
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.RedBackground
import com.unsubdreada.myapp.ui.theme.ScreenBackground
import com.unsubdreada.myapp.ui.theme.TextExpense
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataSettingsScreen(
    innerPadding: PaddingValues,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val db = remember { AppDatabase.getDatabase(context) }
    val dao = remember { db.transactionDao() }

    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(ScreenBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = TablerChevronLeft,
                    contentDescription = null,
                    tint = TextPrimary
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = "Данные и память",
                color = TextPrimary,
                fontSize = 18.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = PrimaryDark,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            SettingRow(
                title = "Экспорт данных",
                subTitle = "Сохранить резервную копию",
                icon = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_document),
                onClick = {
                    val isSuccess = BackupManager.exportDatabase(context)

                    if (isSuccess) {
                        Toast.makeText(
                            context, "Резервная копия сохранена в папку 'Загрузки'",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(context, "Ошибка при экспорте данных", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = TextSecondary.copy(0.1f)
            )

            SettingRow(
                title = "Импорт данных",
                subTitle = "Восстановление из резервной копии",
                icon = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_document_add),
                onClick = {
                    val isSuccess = BackupManager.importDatabase(context)

                    if (isSuccess) {
                        Toast.makeText(
                            context, "Данные успешно восстановлены из резервной копии",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Ошибка при восстановлении данных",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            )

        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = RedBackground.copy(0.2f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            SettingRow(
                title = "Очистить данные",
                subTitle = "Безвозвратное удаление всех записей",
                icon = ImageVector.vectorResource(id = R.drawable.dismiss_circle_16),
                onClick = { showDeleteDialog = true }
            )
        }
    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Удаление всех записей",

                    )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Вы уверены что хотите безвозвратно удалить все записи?"
                    )

                    Text(
                        text = "Рекомендуем перед этим сделать резервную копию!",
                        fontStyle = FontStyle.Italic
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        scope.launch { dao.clearAllTransactions() }
                        showDeleteDialog = false
                    }
                ) {
                    Text(
                        text = "Удалить",
                        color = TextExpense
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text(
                        text = "Отмена",
                        color = TextSecondary
                    )
                }
            }
        )
    }
}