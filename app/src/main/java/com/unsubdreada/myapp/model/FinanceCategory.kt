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
    PRODUCTS("Продукты", TablerPizza, Color(0xFFF2877B), false),
    CAFE("Кафе и рестораны", TablerCoffee, Color(0xFFC9A07C), false),
    TRANSPORT("Транспорт", TablerCar, Color(0xFF57D2E3), false),
    HOUSING("ЖКХ и Дом", TablerHome2, Color(0xFFFFB259), false),
    HEALTH("Здоровье", TablerRibbonHealth, Color(0xFF8FD193), false),
    CLOTHES("Одежда", TablerShirt, Color(0xFFCE8FDA), false),
    ENTERTAINMENT("Развлечения", TablerRollercoaster, Color(0xFFF584AC), false),
    EDUCATION("Образование", TablerBrain, Color(0xFF97A4E6), false),
    GIFTS_EXP("Подарки", TablerGift, Color(0xFFFFD95E), false),
    OTHER_EXP("Другие расходы", TablerDots, Color(0xFFAEBDC6), false),

    SALARY("Зарплата", TablerCreditCardPay, Color(0xFF58C98A), true),
    BONUS("Премия", TablerStars, Color(0xFFE8B84B), true),
    INVESTMENTS("Инвестиции", TablerTrendingUp, Color(0xFF29B4D6), true),
    FREELANCE("Подработка", TablerDeviceLaptop, Color(0xFF3FC4A6), true),
    GIFTS_INC("Подарки", TablerGift, Color(0xFFFFA94D), true),
    RENT("Сдача в аренду", TablerKey, Color(0xFFABD96F), true),
    CASHBACK("Кэшбэк", TablerCashMoveBack, Color(0xFF34E08F), true),
    OTHER_INC("Другие доходы", TablerDots, Color(0xFF8FA3B8), true)
}