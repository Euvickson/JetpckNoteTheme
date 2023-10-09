package br.com.euvickson.jetpcknotetheme.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import br.com.euvickson.jetpcknotetheme.data.NoteDataSource
import br.com.euvickson.jetpcknotetheme.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes():List<Note> {
        return noteList
    }

}