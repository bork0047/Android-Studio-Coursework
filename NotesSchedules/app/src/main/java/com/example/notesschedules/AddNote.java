package com.example.notesschedules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ArrayList;

//main page for the notes part of the app
public class AddNote extends AppCompatActivity {

    static ArrayList<String> allNotes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        //traversing from AddNote to WriteNoteFragment
        if (item.getItemId() == R.id.add_note) {


            Intent intent = new Intent(getApplicationContext(), WriteNoteFragment.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.allNotes", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        //giving a first value to the list
        if (set == null) {

            allNotes.add("Note to edit");
        } else {
            allNotes = new ArrayList(set);
        }

        //creating a listview and setting its adapter
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, allNotes);

        listView.setAdapter(arrayAdapter);

        //when clicked, goes from AddNote to WriteNoteFragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), WriteNoteFragment.class);
                intent.putExtra("noteId", i);
                startActivity(intent);

            }
        });


        //method to delete notes from the ListView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;
                new AlertDialog.Builder(AddNote.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete the note?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            //function to delete the selected note object from the hashset
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                allNotes.remove(itemToDelete);
                                //notify the adapter
                                arrayAdapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet(AddNote.allNotes);
                                sharedPreferences.edit().putStringSet("allNotes", set).apply();
                            }
                        }).setNegativeButton("No", null).show();
                return true;
            }
        });
    }
}