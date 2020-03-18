package com.example.lab2android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        super.onStart();
        Log.d("lifecycle","onStart invoked");
    }

    @Override
    protected void onResume() {
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
}