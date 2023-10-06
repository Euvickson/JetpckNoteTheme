package br.com.euvickson.jetpcknotetheme.data

import br.com.euvickson.jetpcknotetheme.model.Note

class NoteDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "A good Day", description = "We went on a vacation yesterday"),
            Note(title = "Android Compose", description = "Working on the Android App"),
            Note(title = "Keep At it", description = "Sometimes things just happen"),
            Note(title = "A good Day", description = "We went on a vacation yesterday"),
            Note(title = "A good Day", description = "We went on a vacation yesterday"),
            Note(title = "A good Day", description = "We went on a vacation yesterday"),
            Note(title = "A good Day", description = "We went on a vacation yesterday"),
            Note(title = "A good Day", description = "We went on a vacation yesterday"),
            Note(title = "A good Day", description = "We went on a vacation yesterday")
        )
    }
}