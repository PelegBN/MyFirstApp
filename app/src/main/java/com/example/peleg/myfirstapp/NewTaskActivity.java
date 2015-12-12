package com.example.peleg.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void cancleNew(View view){
//        Intent intent = new Intent(this, NewTaskActivity.class);
//        startActivity(intent);
        finish();
    }

    public void addNew(View view){
        String msg = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("MESSAGE", msg);
        setResult(2, intent);
        finish();
    }
}
