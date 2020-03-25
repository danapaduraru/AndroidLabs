package com.example.lab2android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        checkBackground();
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences prefs = getSharedPreferences("bgColor", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                LinearLayout linearLayout = findViewById(R.id.linearLayoutSettings);

                String colourSelected= "";
                switch (checkedId){
                    case R.id.radioBtnGray:
                        colourSelected = "Gray";
                        editor.putString("bgColor", colourSelected);
                        editor.commit();
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        break;
                    case R.id.radioBtnBlue:
                        colourSelected = "Blue";
                        editor.putString("bgColor", colourSelected);
                        editor.commit();
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                        break;
                    case R.id.radioBtnGreen:
                        colourSelected = "Green";
                        editor.putString("bgColor", colourSelected);
                        editor.commit();
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorLightGreen));
                        break;
                    case R.id.radioBtnPink:
                        colourSelected = "Pink";
                        editor.putString("bgColor", colourSelected);
                        editor.commit();
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorLightPink));
                        break;
                }



            }
        });
    }

    private void checkBackground(){
        LinearLayout linearLayout = findViewById(R.id.linearLayoutSettings);
        SharedPreferences settingsSharedPref = this.getSharedPreferences("bgColor", MODE_PRIVATE);
        String background = settingsSharedPref.getString("bgColor","");
        if(!background.equals("")){
            switch (background) {
                case "Gray":
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.colorGray));
                    break;
                case "Blue":
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                    break;
                case "Green":
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.colorLightGreen));
                    break;
                case "Pink":
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.colorLightPink));
                    break;
            }
        }
    }

}