package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.models.Quiz
import com.example.quizapp.utils.ColorPicker
import com.example.quizapp.utils.IconPicker

class QuizAdapter(val context: Context,val quizzers:List<Quiz>):RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){
    inner class QuizViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textViewTitle:TextView=itemView.findViewById(R.id.quizTitle)
        var iconView:ImageView=itemView.findViewById(R.id.quizIcon)
        var cardContainer:CardView=itemView.findViewById(R.id.cardContainer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        var viewHolder= QuizViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text=quizzers[position].title

        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getcolor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
         //   Toast.makeText(context,quizzers[position].title,Toast.LENGTH_SHORT).show()
            val intent= Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzers[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        Log.d("hello","getItemcount boll tha ha ${quizzers.size}")
       return  quizzers.size
    }

}



