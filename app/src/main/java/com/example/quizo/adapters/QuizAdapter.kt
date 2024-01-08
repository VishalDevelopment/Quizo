package com.example.quizo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizo.R
import com.example.quizo.activities.QuestionActivity
import com.example.quizo.models.Quiz
import com.example.quizo.utils.colorpicker
import com.example.quizo.utils.iconPicker

class QuizAdapter (val context: Context, val Quizzes:List<Quiz>):RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)

    }

    override fun onBindViewHolder(holder: QuizAdapter.QuizViewHolder, position: Int) {
        holder.textViewTitle.text = Quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorpicker.getColor()))
        holder.iconView.setImageResource(iconPicker.getColor())

        holder.itemView.setOnClickListener {
//            Toast.makeText(context, Quizzes[position].title, Toast.LENGTH_SHORT).show()

            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",Quizzes[position].title)
            context.startActivity(intent)


        }

    }

    override fun getItemCount(): Int {
    return Quizzes.size
    }


    inner class QuizViewHolder (itemView: View ):RecyclerView.ViewHolder(itemView) {
        var textViewTitle:TextView = itemView.findViewById<TextView>(R.id.quizTitle)
        var iconView:ImageView = itemView.findViewById<ImageView>(R.id.quizIcon)
        var cardContainer:CardView = itemView.findViewById<CardView>(R.id.cardContainer)
    }


}


