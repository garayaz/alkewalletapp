package araya.gonzalo.alkewallwt.model

import androidx.annotation.DrawableRes

data class Transaction(
    val photo: Int,
    val userName: String,
    val date: String,
    val type: Int, // 0 - income, 1 - expense
    val amount: Long
)
