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
package com.example.forage

import android.app.Application
import com.example.forage.data.ForageDatabase

/**
 * File 05
 *
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [ForageDatabase]
 */

// We will instantiate the database instance in the Application class
class BaseApplication : Application() {

    // TO DO: provide a ForageDatabase value by lazy here
    // Use lazy delegate so the instance database is lazily created when you first need/access the
    // reference (rather than when the app starts) .
    val database: ForageDatabase by lazy {

        //  Instantiate the database instance by calling getDatabase() on ForageDatabase passing in the context
        ForageDatabase.getDatabase(this)

    }

}

//  This will create the database (the physical database on the disk) on the first access.