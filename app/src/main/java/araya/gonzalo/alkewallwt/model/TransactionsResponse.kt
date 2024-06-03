package araya.gonzalo.alkewallwt.model

data class TransactionsResponse(

    val previousPage: Any?,
    val nextPage: Any?,
    val data: MutableList<TransactionAW>
)
