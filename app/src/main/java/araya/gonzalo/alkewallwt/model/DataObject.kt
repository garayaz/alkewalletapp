package araya.gonzalo.alkewallwt.model

import araya.gonzalo.alkewallwt.model.local.entity.Transaction

data class DataObject(

    val previousPage: String?,
    val nextPage: String?,
    val data: MutableList<Transaction>
)
