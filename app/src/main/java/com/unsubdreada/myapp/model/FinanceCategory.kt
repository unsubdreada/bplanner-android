package com.unsubdreada.myapp.model

import TablerBrain
import TablerCar
import TablerCashMoveBack
import TablerCoffee
import TablerCreditCardPay
import TablerDeviceLaptop
import TablerDots
import TablerGift
import TablerHome2
import TablerKey
import TablerPizza
import TablerRibbonHealth
import TablerRollercoaster
import TablerShirt
import TablerStars
import TablerTrendingUp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class FinanceCategory(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val isIncome: Boolean
) {
    PRODUCTS("Продукты", TablerPizza, Color(0xFFE57373), false),
    CAFE("Кафе и рестораны", TablerCoffee, Color(0xFFA1887F), false),
    TRANSPORT("Транспорт", TablerCar, Color(0xFF4DD0E1), false),
    HOUSING("ЖКХ и Дом", TablerHome2, Color(0xFFFFB74D), false),
    HEALTH("Здоровье", TablerRibbonHealth, Color(0xFF81C784), false),
    CLOTHES("Одежда", TablerShirt, Color(0xFFBA68C8), false),
    ENTERTAINMENT("Развлечения", TablerRollercoaster, Color(0xFFF06292), false),
    EDUCATION("Образование", TablerBrain, Color(0xFF7986CB), false),
    GIFTS_EXP("Подарки", TablerGift, Color(0xFFFFD54F), false),
    OTHER_EXP("Другие расходы", TablerDots, Color(0xFF90A4AE), false),

    SALARY("Зарплата", TablerCreditCardPay, Color(0xFF4CAF50), true),
    BONUS("Премия", TablerStars, Color(0xFF81C784), true),
    INVESTMENTS("Инвестиции", TablerTrendingUp, Color(0xFF00ACC1), true),
    FREELANCE("Подработка", TablerDeviceLaptop, Color(0xFF26A69A), true),
    GIFTS_INC("Подарки", TablerGift, Color(0xFFFFB300), true),
    RENT("Сдача в аренду", TablerKey, Color(0xFF9CCC65), true),
    CASHBACK("Кэшбэк", TablerCashMoveBack, Color(0xFF00E676), true),
    OTHER_INC("Другие доходы", TablerDots, Color(0xFF78909C), true)
}