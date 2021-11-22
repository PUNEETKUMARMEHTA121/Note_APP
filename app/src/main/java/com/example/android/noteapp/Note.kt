package com.example.android.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// on below line we are specifying our table name
@Entity(tableName = "notesTable")

// on below line we are specifying our column infor
// and inside that we are passing our column name
class Note (@ColumnInfo(name = "title")val noteTitle :String,
            @ColumnInfo(name = "description")val noteDescription :String,
            @ColumnInfo(name = "timestamp")val timeStamp :String) {
    // on below line we are specifying our key and
    // then auto generate as true and we are
    // specifying its initial value as 0
    @PrimaryKey(autoGenerate = true) var id = 0
}
