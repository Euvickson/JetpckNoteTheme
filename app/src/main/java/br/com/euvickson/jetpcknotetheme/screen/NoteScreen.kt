package br.com.euvickson.jetpcknotetheme.screen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.euvickson.jetpcknotetheme.R
import br.com.euvickson.jetpcknotetheme.components.NoteButton
import br.com.euvickson.jetpcknotetheme.components.NoteInputText
import br.com.euvickson.jetpcknotetheme.data.NoteDataSource
import br.com.euvickson.jetpcknotetheme.model.Note
import br.com.euvickson.jetpcknotetheme.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
    onEditNote: (Note) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val buttonState = remember { mutableStateOf("Save") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon"
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFDADFE3))
        )

        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NoteInputText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )

            NoteInputText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )

            NoteButton(
                text = buttonState.value,
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty() && buttonState.value == "Save") {
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    } else if (title.isNotEmpty() && description.isNotEmpty() && buttonState.value == "Update") {
                        Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT).show()
                        title = ""
                        description = ""
                        buttonState.value = "Save"
                    }
                })

            Divider(modifier = Modifier.padding(10.dp))

            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note,
                        onNoteClicked = {onRemoveNote(it)},
                        onNoteLongClicked = {
                            title = it.title
                            description = it.description
                            buttonState.value = "Update"
                        }
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit,
    onNoteLongClicked: (Note) -> Unit){
    Surface (
        modifier
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(), color = Color(0xFFDFE6EB), tonalElevation = 6.dp){

        Column (
            modifier
                .combinedClickable(
                    onClick = { onNoteClicked(note) },
                    onLongClick = {onNoteLongClicked(note)}
                )
                .padding(horizontal = 14.dp, vertical = 6.dp)){

            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = note.description,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.labelSmall
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {}, onEditNote = {})
}