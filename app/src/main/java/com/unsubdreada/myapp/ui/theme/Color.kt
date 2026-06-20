package com.unsubdreada.myapp.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//
// Главные системные цвета (База)
val ScreenBackground =
    Color(0xFF181818)     // Общий фон приложения (глубокий темно-серый, почти черный)
val PrimaryDark =
    Color(0xFF212121)         // Фон хэдера, футера и карточек (чуть светлее общего фона)
val BlurredPanelBackground = Color(0xD9212121)

val RedBackground = Color(0XFF640d14)

// Текст
val TextPrimary = Color(0xFFFFFFFF)          // Основной текст (Имена, сообщения)
val TextSecondary = Color(0xFF757575)        // Второстепенный текст и иконки (Время, плейсхолдеры)

val TextAdditional = Color(0xFF6EB1F3)

// Фирменный Акцент (Кнопки, Фокус, Системные элементы)
val AccentBlue = Color(0xFF2EA6FF)           // Цвет круглой плавающей кнопки "плюс" и галочек
val AccentBlueOnDark =
    Color(0xFF52B6FF)     // Чуть более светлый голубой (для текста/ссылок на темном фоне)
val TextIncome = Color(0xFF45CD6F)   // Мягкий зеленый для доходов (как в Telegram)
val TextExpense = Color(0xFFF05252)  // Мягкий красный для расходов
val TextNeutral =
    Color(0xFFFFFFFF)  // Для нулевого баланса (0.0 ₽) лучше оставить белый или серый TextSecondary


// Элементы ввода (Инпуты / Текстовые поля)
val InputFieldBackground = Color(0xFF242424) // Фон инпута (слегка выделяется на фоне экрана)
val InputFieldStroke = Color(0xFF2F2F2F)     // Тонкая нейтральная граница в обычном состоянии
val InputFieldStrokeFocused = Color(0xFF2EA6FF) // Граница при фокусе (ваш AccentBlue)
val InputTextPlaceholder = Color(0xFF686868)    // Цвет подсказки (placeholder)


// Семантические кнопки (Доход / Расход / Принять / Отмена)
val ButtonIncomeBackground =
    Color(0xFF34C759).copy(alpha = 0.55f) // Приглушенный зеленый (в стиле Telegram для доходов)
val ButtonIncomeText = Color(0xFFFFFFFF)

val ButtonExpenseBackground =
    Color(0xFFFF3B30).copy(alpha = 0.55f) // Приглушенный красный (в стиле Telegram для расходов)
val ButtonExpenseText = Color(0xFFFFFFFF)

val ButtonAcceptBackground = Color(0xFF2EA6FF) // Кнопка "Принять" — акцентная голубая
val ButtonAcceptText = Color(0xFFFFFFFF)       // Текст на ней белый

val ButtonCancelBackground = Color(0x00000000) // Кнопка "Отмена" — прозрачная
val ButtonCancelStroke = Color(0xFF3B3B3B)     // Тонкая серая граница

// Универсальные цвета для ЛЮБОЙ заблокированной кнопки
val ButtonDisabledBackground =
    Color(0xFF2EA6FF).copy(alpha = 0.12f) // На основе вашего AccentBlue, но блеклый
val ButtonDisabledText =
    Color(0xFFFFFFFF).copy(alpha = 0.38f)       // Белый текст, затонированный в серый

val ButtonCancelText = Color(0xFF757575)       // Серый нейтральный текст
