package com.gmail.uia059466.simplemath.forgit.listworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.workout.Workout

class WorkoutListAdapter(
        private val data: List<DataItemWorkout>,
        private val listener: WorkoutListListener
                        ) : RecyclerView.Adapter<WorkoutListAdapter.ViewHolder>() {
  
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
    private val description: TextView = itemView.findViewById(R.id.description_tv)
    private val stateImage: ImageView = itemView.findViewById(R.id.done_img)
    lateinit var workout: Workout
    
    fun bind(item: DataItemWorkout) {
      itemView.setOnClickListener { listener.onWorkoutClicked(item.workout) }
      
      description.visibility = View.VISIBLE
      this.workout = item.workout
      name.text = item.name.getString(itemView.context)
      result.visibility = View.GONE
      description.text = item.description.getString(itemView.context)
      stateImage.setImageResource(
              when (item.isComplected) {
                true -> R.drawable.ic_circle_done
                else -> R.drawable.ic_circle
              }
                                 )
    }
  }
  
  interface WorkoutListListener {
    fun onWorkoutClicked(item: Workout)
  }
}