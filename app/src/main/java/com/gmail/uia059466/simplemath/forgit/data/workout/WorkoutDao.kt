package com.gmail.uia059466.simplemath.forgit.data.workout

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.data.WorkoutDb
import com.gmail.uia059466.simplemath.forgit.data.WorkoutType

@Dao
interface WorkoutDao {
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(record: WorkoutDb)
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(records: List<WorkoutDb>)
  
  @Query("DELETE FROM workout_table")
  fun deleteAll()
  
  @Delete
  fun delete(record: WorkoutDb)
  
  @Query("select * from workout_table where id=:id")
  fun getById(id: Int): WorkoutDb?
  
  @Query("select * from workout_table")
  fun getAll(): List<WorkoutDb>
  
  @Query("SELECT * FROM workout_table WHERE is_available and not is_complected order by `order` limit 1")
  fun getCurrent(): WorkoutDb?
  
  @Query("UPDATE workout_table SET is_available = :isAvailable WHERE workout_type = :type")
  fun update(type: WorkoutType, isAvailable: Boolean): Int
  
  @Query("UPDATE workout_table SET is_complected = :isComplected WHERE id = :id")
  fun update(id: Int, isComplected: Boolean): Int
  
  @Query("SELECT COUNT(id) FROM workout_table WHERE skill = :skill")
  fun getCount(skill: Skill): Int
  
  @Query("SELECT COUNT(id) FROM workout_table WHERE skill = :skill and is_complected")
  fun getCountComplected(skill: Skill): Int
  
  @Query("select * from workout_table")
  fun getO(): List<WorkoutDb>
  
  @Query("SELECT * FROM workout_table WHERE skill = :skill")
  fun getWorkoutBySkill(skill: Skill):List<WorkoutDb>
}