package com.example.notesschedules;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//class to help/view/edit the notes
public class WriteNoteFragment extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText editText = findViewById(R.id.editText);

        // get the data that is passed from AddNote in order to save it
        Intent intent = getIntent();

        // Accessing and saving the data
        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {
            editText.setText(AddNote.allNotes.get(noteId));
        } else {

            AddNote.allNotes.add("");
            noteId = AddNote.allNotes.size() - 1;
            AddNote.arrayAdapter.notifyDataSetChanged();

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AddNote.allNotes.set(noteId, String.valueOf(charSequence));
                AddNote.arrayAdapter.notifyDataSetChanged();

                // Making a shared preferences object to store data on the device
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.allNotes", Context.MODE_PRIVATE);

                //storing it in a hashset, hence it is an object which has text inside, othewise would have used arraylist
                HashSet<String> set = new HashSet(AddNote.allNotes);
                sharedPreferences.edit().putStringSet("allNotes", set).apply();
            }

            //could write a message for after the text has changed, but becomes too much into on the screen
            @Override
            public void afterTextChanged(Editable editable) {
                //function to pop a message after clicking the note, but decided to take it out

                /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        //int position gives the position of the item we touched on the listView
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(AddNote.this,"clicked item:" + position + " " + arrayList.get(position).toString(), Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}