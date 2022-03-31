package com.example.roomdatabasenobel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    /** autoGenerate = True  id-ებს ავტომატურად ალაგებს ზრდის მიხედვით.
     * autoGenerate = false - ის შემთხვევაში ჩვენ ვაკონტროლებთ id-ებს. **/
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)
