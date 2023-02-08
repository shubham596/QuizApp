package com.example.quizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.models.Question

class OptionAdapter(val context: Context,val question:Question): RecyclerView.Adapter<OptionAdapter.viewHolder>() {
    private var options:List<String> = listOf(question.option1,question.option2,question.option3,question.option4)
inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var optionView=itemView.findViewById<TextView>(R.id.quizOption)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.optionView.text=options[position]
        holder.itemView.setOnClickListener{
        //   Toast.makeText(context,options[position],Toast.LENGTH_SHORT).show()
            question.userAnswer=options[position]
            notifyDataSetChanged()

        }
        if(question.userAnswer==options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }

    }

    override fun getItemCount(): Int {
return options.size
    }
}