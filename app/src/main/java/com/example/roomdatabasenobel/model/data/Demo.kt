package com.example.roomdatabasenobel.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "demo_user_table")
data class Demo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)
