package com.yteam.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText editNote;
    SharedPreferences sharedPreferences;
    @Override
    public void onBackPressed() {
        if(!editNote.getText().toString().equals("")) {
            MainActivity.notes.add(0, editNote.getText().toString());
        }
        sharedPreferences = this.getSharedPreferences("com.yteam.notes", Context.MODE_PRIVATE);
        try{
            sharedPreferences.edit().putString("notes",ObjectSerializer.serialize(MainActivity.notes)).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
        MainActivity.arrayAdapter.notifyDataSetChanged();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editNote = findViewById(R.id.editText);
        Intent intent = getIntent();
        if(intent.getIntExtra("place",-1)>=0){
            editNote.setText(MainActivity.notes.get(intent.getIntExtra("place",-1)));
            MainActivity.notes.remove(intent.getIntExtra("place",-1));
        }else{
            editNote.setText("");
        }
    }
}
