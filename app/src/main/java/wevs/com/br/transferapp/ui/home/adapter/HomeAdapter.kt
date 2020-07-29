package wevs.com.br.transferapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.Statement
import wevs.com.br.transferapp.utils.*

class HomeAdapter(
    private val items: MutableList<Statement>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_payment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Statement) {
            itemView.findViewById<TextView>(R.id.item_payment_title).text = item.title

            itemView.findViewById<TextView>(R.id.item_payment_description).text = item.desc

            itemView.findViewById<TextView>(R.id.item_payment_value).apply {
                setTextColor(ContextCompat.getColor(context, item.value.returnsColors()))
                text = item.value.formatToBrazilianCurrency()
            }

            itemView.findViewById<TextView>(R.id.item_payment_date).text =
                item.date.formatBrazilianDate(AMERICAN_FORMAT, BRAZILIAN_FORMAT)
        }
    }

}