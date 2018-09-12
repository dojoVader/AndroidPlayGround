package org.retani.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Current Intent
        Intent intent = getIntent();
        String messageText = intent.getStringExtra(MainActivity.MESSAGE);
        TextView textView = (TextView) findViewById(R.id.messageView);
        textView.setText(messageText);
    }
}
