package bakareritesh1729atgmail.com.a7minutesworkoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import bakareritesh1729atgmail.com.a7minutesworkoutapp.databinding.ItemHistoryRowBinding


class HistoryAdapter(private val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val llHistoryMain = binding.llHistoryMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date: String = items.get(position)

        holder.tvItem.text = date
        holder.tvPosition.text = (position + 1).toString()

        if (position % 2 == 0) {
            holder.llHistoryMain.setBackgroundColor(
                ContextCompat.getColor(holder.llHistoryMain.context, R.color.color_light_grey)
            )
        } else {
            holder.llHistoryMain.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

    }

}