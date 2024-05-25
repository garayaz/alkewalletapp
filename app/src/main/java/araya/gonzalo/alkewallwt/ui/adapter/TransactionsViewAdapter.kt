package araya.gonzalo.alkewallwt.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import araya.gonzalo.alkewallwt.R
import araya.gonzalo.alkewallwt.databinding.TransactionsItemBinding
import araya.gonzalo.alkewallwt.model.Transaction
import android.content.res.Resources

// El adapter se usa para cargar los datos en la vista, en este caso a la TransactionsItemBinding,
// que es el reciclerView
class TransactionsViewAdapter :
    RecyclerView.Adapter<TransactionsViewAdapter.TransactionViewHolder>() {
    lateinit var onItemClickistener: (Transaction) -> Unit
    var transactions = mutableListOf<Transaction>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }


    // El onCreate, crea la vista y se la pasa al viewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {

        var bindingItem =
            TransactionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(bindingItem)

    }

    // el bind permite cargar los datos en la vista
    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {
        val transaction: Transaction = transactions[position]
        // se llama la funcion bind del viewHolder y se le pasa la transaccion(datos)
        holder.bind(transaction)

    }

    override fun getItemCount(): Int {
        return transactions.size
    }


   // Esta clase se usa para parar los datos a la vista
    inner class TransactionViewHolder(
        private val bindingItem:
        TransactionsItemBinding
    ) : RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(transaction: Transaction) {
            bindingItem.nameItem.text = transaction.userName
            bindingItem.dateItem.text = transaction.date
            val qtt = transaction.amount.toString()
            val formattedString = qtt.reversed().chunked(3).joinToString(".").reversed()
            var raya = " $"
            if (transaction.type == 0) {
                bindingItem.arrowItem.setImageResource(R.drawable.deposit2_icon)
            } else {
                bindingItem.arrowItem.setImageResource(R.drawable.payment_icon)
                raya = "-$"
            }
            bindingItem.amountItem.text = buildString {
                append(raya)
                append(formattedString)
            }
            // Load and display profile image
            // ...
            val packageName = bindingItem.root.context.packageName
            val photox = transaction.photo.toString()
            val resourceId =
                Resources.getSystem().getIdentifier(photox, "drawable", packageName)
            bindingItem.imageItem.setImageResource(resourceId)
            //Picasso.get().load(transaction.imgUrl).into(bindingItem.imageItem)
            // binding.imageItem.setImageDrawable(resourceId)
//            bindingItem.root.setOnClickListener() {
//                onItemClickistener(transaction)
//            }
        }

    }
    // Load and display profile image
    // ...

}
//   binding.txtId.text = transaction.id.toString()

//   binding.txtNombre.text = transaction.nombre
// se indica que toda la fila sera clickeable, si se ubiese deseado que la flecha fuera clickeable
// se hubiera definido binding.arrow.setOnClickListener.  Con esto el adaptador escucha el click,
// luego se debe ir a la actividad para que escuche el click (MainActiviry)
//   binding.root.setOnClickListener(){
//  binding.arrow.setOnClickListener() {
//    onItemClickistener(transaction)
//}









