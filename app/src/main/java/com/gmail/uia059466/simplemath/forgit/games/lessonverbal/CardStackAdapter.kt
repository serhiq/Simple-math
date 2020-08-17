package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.saynext.getColorStrategy

class CardStackAdapter(
        var words: List<String>
                      ) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return ViewHolder(
            inflater.inflate(R.layout.verbal_card_adapter_item, parent, false)
                     )
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val text = words[position]
    val colorStrategy = getColorStrategy(text)
    
    if (colorStrategy != null) {
      holder.name.text = colorStrategy.createColored(text)
    } else {
      holder.name.text = words[position]
    }
  }
  
  override fun getItemCount(): Int {
    return words.size
  }
  
  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.item_name)
  }
}
