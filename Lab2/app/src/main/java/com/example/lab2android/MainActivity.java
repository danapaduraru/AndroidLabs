package com.example.lab2android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LAB 5
        loadBackgroundColor();

        /* LAB 1 */

        final ListView productsList = (ListView) findViewById(R.id.productsList);
        final TextView bookDescription = (TextView) findViewById(R.id.bookDescription);

        String[] bookTitles = new String[]{
                "Life of Pi",
                "Crime and Punishment",
                "The Karamazov Brothers",
                "War and Peace"
        };

        ArrayList<String> products = new ArrayList<>(Arrays.asList(bookTitles));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, products);

        productsList.setAdapter(arrayAdapter);
        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTitle = (String) productsList.getItemAtPosition(position);
                String bookAuthor = "";
                switch (selectedTitle) {
                    case "Life of Pi":
                        bookAuthor = "Yann Martel";
                        break;
                    case "Crime and Punishment":
                        bookAuthor = "Fyodor Dostoyevsky";
                        break;
                    case "The Karamazov Brothers":
                        bookAuthor = "Fyodor Dostoyevsky";
                        break;
                    case "War and Peace":
                        bookAuthor = "Lev Tolstoy";
                        break;
                    default:
                        break;
                }
                bookDescription.setText("This book was written by " + bookAuthor);
            }
        });

        /* LAB 4 */
        /* Create another Activity (or more) and connect them using Intent Filters. ( i.e. intents for sending a SMS, starting a voice call, open an URL, etc.) (2p) */
        Button btnAddToCollection = findViewById(R.id.btnAddToCollection);
        btnAddToCollection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction((Intent.ACTION_SEND));
                intent.putExtra(Intent.EXTRA_TEXT, "Add a book to collection using...");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Add a book to collection using..."));
            }
        });
    }
    /*  LAB 3 */

    /*
    App Life Cycle
    onCreate (Created)
    onStart (Visible)
    onResume (Active)
    onPause (Paused)
    onStop (Stopped)
    onDestroy (Destroyed)
    onRestart (Restarting)
     */

    @Override
    protected void onStart() {
        final TextView bookDescription = (TextView) findViewById(R.id.bookDescription);
        bookDescription.setText(loadContent()); // LAB 5 - load book description saved in configurare.txt
        super.onStart();
        Log.d("lifecycle","onStart invoked");
    }

    @Override
    protected void onResume() {
        loadBackgroundColor(); // LAB 5
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }

    @Override
    protected void onStop() {
        saveContent(R.id.bookDescription); // LAB 5 save book description to configuration.txt
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }

    @Override
    protected void onSaveInstanceState(Bundle onSaveState) {

        TextView bookDescription = (TextView) findViewById(R.id.bookDescription);
        description = bookDescription.getText().toString();
        onSaveState.putString("bookDescription", description);
        super.onSaveInstanceState(onSaveState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle onRestoreState) {
        super.onRestoreInstanceState(onRestoreState);
        TextView bookDescription = (TextView) findViewById(R.id.bookDescription);
        description = onRestoreState.getString("bookDescription");
        bookDescription.setText(description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /* LAB 4 */

    /* Implement functionalities for the menu you created in Lab 3 (2p) */

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.menuSettings) {
            // LAB 5
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        }
        // respond to menu item selection
        String menuMsg = "";
        if(menuItem.getItemId() == R.id.menuAbout) {
            menuMsg = "This application is an online shop for buying books.";
        }
        else if(menuItem.getItemId() == R.id.menuHelp) {
            menuMsg = "Click on each book title to see more details about that book.";
        }
        Toast toast = Toast.makeText(this, menuMsg, Toast.LENGTH_LONG);
        toast.show();
        return true;
    }

    /* Create another Activity (or more) and connect them using Intent Filters. ( i.e. intents for sending a SMS, starting a voice call, open an URL, etc.) (2p) */
    /* Created AddToCollection activity */

    /* LAB 5
    *  Create a PreferenceActivity for your application and store the settings using SharedPreferences (4p)
    *  Add support for saving App Information on disk (InternalStorage/ExternalStorage/Database) (4p)
     * */

    private void loadBackgroundColor(){
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
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


    // Add support for saving App Information on disk (InternalStorage/ExternalStorage/Database) (4p)
    private void saveContent(int id) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("configurare.txt", MODE_PRIVATE));
            String text = ((TextView) findViewById(id)).getText().toString();
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String loadContent(){
        String toReturn = "";
        try {
            InputStream inputStream = this.openFileInput("configurare.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder fullString = new StringBuilder();

                String currentLine = bufferedReader.readLine();
                while (currentLine != null) {
                    fullString.append(currentLine);
                    currentLine = bufferedReader.readLine();
                }
                inputStream.close();
                toReturn = fullString.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

}