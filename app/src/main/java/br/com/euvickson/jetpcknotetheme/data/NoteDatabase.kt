package br.com.euvickson.jetpcknotetheme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.euvickson.jetpcknotetheme.model.Note
import br.com.euvickson.jetpcknotetheme.util.DateConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}