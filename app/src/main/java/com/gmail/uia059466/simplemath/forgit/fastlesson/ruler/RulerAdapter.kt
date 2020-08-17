package com.gmail.uia059466.simplemath.forgit.fastlesson.ruler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gmail.uia059466.simplemath.forgit.R


class RulerAdapter(private val numbers: List<NumberOfRuler>) : RecyclerView.Adapter<RulerAdapter
.ViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layout = R.layout.ruler_item
    val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val current = holder.adapterPosition
    holder.tvName.text = numbers[current].unit
    
    val gray = ContextCompat.getColor(holder.tvName.context, R.color.dark_gray_for_text_boring)
    val green = ContextCompat.getColor(holder.tvName.context, R.color.greenDark)
    val orange = ContextCompat.getColor(holder.tvName.context, R.color.orange)
    
    val orangeCircle = R.drawable.ruler_circle_orange
    val greenCircle = R.drawable.ruler_circle_green
    val grayCircle = R.drawable.rule_circle_gray
    
    when {
      numbers[current].isLongSelected -> configureItemView(orange, orangeCircle, holder)
      numbers[current].isSelected     -> configureItemView(green, greenCircle, holder)
      else                            -> configureItemView(gray, grayCircle, holder)
    }
    
    holder.tvName.setOnClickListener {
      numbers[current].isSelected = !numbers[current].isSelected
      numbers[current].isLongSelected = false
      notifyItemChanged(current)
    }
    
    holder.tvName.setOnLongClickListener {
      numbers[current].isLongSelected = !numbers[current].isLongSelected
      notifyItemChanged(current)
      true
    }
  }
  
  private fun configureItemView(textColor: Int, drawableBackground: Int, holder: ViewHolder) {
    holder.tvName.setTextColor(textColor)
    holder.imgCircle.setImageResource(drawableBackground)
  }
  
  override fun getItemCount(): Int {
    return numbers.size
  }
  
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvName: TextView = itemView.findViewById<View>(R.id.name_tv) as TextView
    var imgCircle: ImageView = itemView.findViewById<ImageView>(R.id.circle_img) as ImageView
  }
}