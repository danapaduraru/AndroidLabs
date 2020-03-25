package com.example.lab2android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AddToCollection extends AppCompatActivity {

    /* LAB 4 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_collection);

        checkBackgroundColor();  // LAB 5

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

    // LAB 5
    private void checkBackgroundColor(){
        ConstraintLayout constraintLayout = findViewById(R.id.layoutAddToCollection);
        SharedPreferences settingsSharedPref = this.getSharedPreferences("bgColor", MODE_PRIVATE);
        String background = settingsSharedPref.getString("bgColor","");
        if(!background.equals("")){
            switch (background) {
                case "Gray":
                    constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorGray));
                    break;
                case "Blue":
                    constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                    break;
                case "Green":
                    constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorLightGreen));
                    break;
                case "Pink":
                    constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorLightPink));
                    break;
            }
        }
    }
}
