package com.example.quizo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizo.R
import com.example.quizo.models.Question

class OptionAdapter(val context: Context, val question: Question?):RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options:List<String> = listOf(question!!.option1 , question.option2, question.option3 , question.option4)

    inner class OptionViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        var optionView = itemview.findViewById<TextView>(R.id.quizoption)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return OptionViewHolder(view)
    }

    override fun getItemCount():Int {
       return options.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener {
//            Toast.makeText(context, options[position], Toast.LENGTH_SHORT).show()
            question!!.userAnswer = options[position]
            notifyDataSetChanged()
        }
            if(question!!.userAnswer==options[position]){
                holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
            }
            else{
                holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
            }


    }
}