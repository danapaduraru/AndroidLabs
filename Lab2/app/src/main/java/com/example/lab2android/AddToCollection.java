package com.example.lab2android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCollection extends AppCompatActivity {

    /* LAB 4 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_collection);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String message = bundle.getString(Intent.EXTRA_TEXT);
//        textCollection.setText(message);

        /* Use dialog windows (AlertDialog) to interact with the user (username, password, search filter, etc.) (2p) */
        Button btnCreateCollection = findViewById(R.id.btnCreateCollection);
        btnCreateCollection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(AddToCollection.this).create();
                builder.setTitle("Create new Collection");
                builder.setMessage("Your new collection has been created.");
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });
    }
}
