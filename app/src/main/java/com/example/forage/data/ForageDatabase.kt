/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.forage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forage.model.Forageable

/**
 * FILE 04
 *
 * Room database to persist data for the Forage app.
 * This database stores a [Forageable] entity
 */
// TO DO: create the database with all necessary annotations, methods, variables, etc.
// public abstract class that extends RoomDatabase
// This new abstract class you defined acts as a database holder
// Set exportSchema to false, so as not to keep schema version history backups.
@Database(entities = [Forageable::class], version = 1, exportSchema = false)
abstract class ForageDatabase : RoomDatabase() {

    abstract fun foragableDao(): Forageable

    companion object {


        // will keep a reference to the database, when one has been created.
        // This helps in maintaining a single instance of the database opened at a given time.
        @Volatile
        private var INSTANCE: ForageDatabase? = null


        fun getDatabase(context: Context): ForageDatabase {

            /**
             * Multiple threads can potentially run into a race condition and ask for a database
             * instance at the same time, resulting in two databases instead of one. Wrapping the
             * code to get the database inside a synchronized block means that only one thread of
             * execution at a time can enter this block of code, which makes sure the database only
             * gets initialized once.

             */
            return INSTANCE ?: synchronized(this) {

                // Use Room's Room.databaseBuilder to create your (forageable_database) database only
                // if it doesn't exist. Otherwise, return the existing database.

                // Inside the synchronized block, initialize the instance variable, and
                // use the database builder to get a database.

                // Pass in:
                // 1. application context
                // 2. database class
                // 3.  name for the database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ForageDatabase::class.java,
                    "forageable_database"
                )
                    // Migration Strategy
                    .fallbackToDestructiveMigration()
                    .build()

                // Defined recently with application context, database class and database name
                INSTANCE = instance

                return instance
            }

        }

    }

}
