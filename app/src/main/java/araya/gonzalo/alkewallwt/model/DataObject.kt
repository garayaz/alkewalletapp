package araya.gonzalo.alkewallwt.model

data class DataObject(

    val previousPage: Any?,
    val nextPage: Any?,
    val data: MutableList<TransactionAW>
)
