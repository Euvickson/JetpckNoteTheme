package br.com.euvickson.jetpcknotetheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.euvickson.jetpcknotetheme.data.NoteDataSource
import br.com.euvickson.jetpcknotetheme.model.Note
import br.com.euvickson.jetpcknotetheme.screen.NoteScreen
import br.com.euvickson.jetpcknotetheme.screen.NoteViewModel
import br.com.euvickson.jetpcknotetheme.ui.theme.JetpckNoteThemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpckNoteThemeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)

                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val notesList = noteViewModel.getAllNotes()
    NoteScreen(
        notes = notesList,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpckNoteThemeTheme {

    }
}