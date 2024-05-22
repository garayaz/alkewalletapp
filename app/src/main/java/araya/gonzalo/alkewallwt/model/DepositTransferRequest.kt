package araya.gonzalo.alkewallwt.model

data class DepositTransferRequest (
    val type : String,
    val concept : String,
    val amount : Double
)
