package br.com.euvickson.jetpcknotetheme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.euvickson.jetpcknotetheme.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}