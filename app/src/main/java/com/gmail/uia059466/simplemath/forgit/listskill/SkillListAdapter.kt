package com.gmail.uia059466.simplemath.forgit.listskill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.Skill

class SkillListAdapter(
        private val data: List<DataItemSkill>,
        private val listener: LessonListListener
                      ) : RecyclerView.Adapter<SkillListAdapter.ViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
    return ViewHolder(view)
  }
  
  override fun getItemCount(): Int {
    return data.size
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val element = data[position]
    holder.bind(element)
  }
  
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val result: TextView = itemView.findViewById(R.id.result_tv)
    private val name: TextView = itemView.findViewById(R.id.name_tv)
    private val stateImage: ImageView = itemView.findViewById(R.id.done_img)
    private lateinit var skill: Skill
    
    fun bind(item: DataItemSkill) {
      itemView.setOnClickListener { listener.onSkillClicked(item.skill) }
      this.skill = item.skill
      name.text = itemView.context.getString(item.name)
      result.text = item.count
      stateImage.setImageResource(
              when (item.isComplected) {
                true -> R.drawable.ic_circle_done
                else -> R.drawable.ic_circle
              }
                                 )
    }
  }
  
  interface LessonListListener {
    fun onSkillClicked(item: Skill)
  }
}
