package com.example.testing_book_library_project.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testing_book_library_project.R;
import com.example.testing_book_library_project.Utils.Utils;

public class MainActivity extends AppCompatActivity {

    private TextView toolbar_title;

    private Button btn_all_books, btn_currently_read, btn_already_read, btn_want_to_read, btn_favourites, btn_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        toolbar_title.setText("Testing Book Library Project");

        btn_all_books.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, All_Books_Activity.class);
            startActivity(intent);
        });

        btn_already_read.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Already_Read_Book_Activity.class);
            startActivity(intent);
        });

        btn_want_to_read.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Want_To_Read_Activity.class);
            startActivity(intent);
        });

        btn_currently_read.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Currently_Reading_Books_Activity.class);
            startActivity(intent);
        });

        btn_favourites.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Favourite_Books_Activity.class);
            startActivity(intent);
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and Developed with Love by Iron Heart.\n" +
                                        "Check my website (Not yet created ) for more awesome applications : ");

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO : SHOW THE WEBSITE       //completed

                        Intent intent = new Intent(MainActivity.this, Website_Activity.class);
                        intent.putExtra("url","https://www.google.com/");
                        startActivity(intent);
                    }
                });

                builder.setCancelable(false);
                builder.create().show();
                //this builder method is very useful, there are all sorts of method here
            }
        });

        Utils.getInstance(this);
    }

    private void initViews() {

        Log.d("TAG", "initViews: started");

        btn_all_books = findViewById(R.id.btn_all_books);
        btn_currently_read = findViewById(R.id.btn_currently_reading);
        btn_already_read = findViewById(R.id.btn_already_books);
        btn_want_to_read = findViewById(R.id.btn_want_to_read);
        btn_favourites = findViewById(R.id.btn_favourite);
        btn_about = findViewById(R.id.btn_about);

        toolbar_title = findViewById(R.id.toolbar_title);
    }
}