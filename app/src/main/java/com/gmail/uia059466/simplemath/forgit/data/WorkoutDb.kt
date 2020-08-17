package com.gmail.uia059466.simplemath.forgit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gmail.uia059466.simplemath.forgit.data.converters.SkillTypeConverter
import com.gmail.uia059466.simplemath.forgit.data.stub.WorkoutRaw
import com.gmail.uia059466.simplemath.forgit.data.workout.WorkoutTypeConverter

@Entity(tableName = "workout_table")
class WorkoutDb (
      val order       :Int,
    @TypeConverters(SkillTypeConverter::class)
    val skill       :Skill,

    @TypeConverters(WorkoutTypeConverter::class)
    @ColumnInfo(name = "workout_type")
    val type        :WorkoutType,
    val from       : Int,
    val to         : Int,
    val delta       :Int=0,
    val result      :Int=0,
    
    @ColumnInfo(name = "is_available")
    val isAvailable :Boolean=true,
  
    @ColumnInfo(name = "is_complected")
    val isComplected:Boolean=false
){
  @PrimaryKey(autoGenerate = true)
  var id:Int=0
  
  constructor(order: Int, raw: WorkoutRaw): this(
          order, raw.skill, raw.type, raw.from, raw.to, raw.delta
                                                )
}