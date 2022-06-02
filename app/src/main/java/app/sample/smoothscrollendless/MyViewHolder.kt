package app.sample.smoothscrollendless

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val tvName: TextView = view.findViewById(R.id.tv_name)
    private val tvDesignation: TextView = view.findViewById(R.id.tv_desgination)

    fun setData(models: Models){
        tvName.text = models.name
        tvDesignation.text = models.designation
    }

}