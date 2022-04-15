package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.model.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText edittext_title, edittext_notes;
    ImageView imageView_save;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save = findViewById(R.id.imageView_save);
        edittext_title = findViewById(R.id.edittext_title);
        edittext_notes = findViewById(R.id.edittext_notes);

        notes = new Notes();
        try{
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            edittext_title.setText(notes.getTitle());
            edittext_notes.setText(notes.getNote());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edittext_title.getText().toString();
                String note = edittext_notes.getText().toString();

                if(note.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please add some notes", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM YYYY HH:mm a");
                Date date = new Date();

                if(!isOldNote)
                    notes = new Notes();

                notes.setTitle(title);
                notes.setNote(note);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }
}