package com.unsubdreada.myapp.model

import TablerCurrencyBitcoin
import TablerCurrencyDollar
import TablerCurrencyEthereum
import TablerCurrencyEuro
import TablerCurrencyRubel
import TablerCurrencyYuan
import androidx.compose.ui.graphics.vector.ImageVector

enum class CurrencyType(
    val title: String,
    val symbol: ImageVector,
    val stringSymbol: String
) {
    RUB("Российский рубль", TablerCurrencyRubel, "₽"),
    USD("Доллар США", TablerCurrencyDollar, "$"),
    EUR("Евро", TablerCurrencyEuro, "€"),
    CNY("Китайский юань", TablerCurrencyYuan, "¥"),

    //Крипта
    BTC("Bitcoin", TablerCurrencyBitcoin, "₿"),
    ETH("Ethereum", TablerCurrencyEthereum, "Ξ")
}