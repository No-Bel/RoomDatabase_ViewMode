package com.example.roomdatabasenobel.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** იმპლემენტაციას ვუკეთებთ Database-ს.
 * ვაზუსტებთ entities ანუ დათა ობიექტს.
 * ამ შემთხვევაში ჩვენ გვაქვს მხოლოდ 1 entity -- [User::class] .
 * version = 1 - Database-ის ვერსია.
 * ამ კლასში ცვლილებებთან ერთად version-იც უნდა შეიცვალოს.
 * exportSchema = false - თუ არ გვინდა ვერსიების ჩამატება ცალკე მიცემულ ფოლდერში.
 * false -  იმიტომ რომ True დეფულტად აქვს. **/
@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    /** companion object - ყველაფერი რაც ამ ობიექტშია,ხდება ხილულია ყველა კლასისთვის  **/
    companion object {

        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            /** თუ aInstance ნალია ჩვენ ვქმნით ახალ instance-ს  **/
                val bInstance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                /** და ვუტოლებთ INSTANCE ახალ bInstance. **/
                INSTANCE = bInstance
                return bInstance
        }
    }
}