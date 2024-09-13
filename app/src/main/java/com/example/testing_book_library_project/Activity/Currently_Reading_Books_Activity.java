package com.example.testing_book_library_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing_book_library_project.Adapter.books_rec_view_adapter;
import com.example.testing_book_library_project.R;
import com.example.testing_book_library_project.Utils.Utils;

public class Currently_Reading_Books_Activity extends AppCompatActivity {

    public static final String CURRENTLY_READ_BOOKS = "currentlyReading";

    private TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_currently_reading_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        toolbar_title.setText("Books You Are Currently Reading");

        RecyclerView recyclerView = findViewById(R.id.bookRecView);

        //ONCE CREATED AN ADAPTER CAN BE USED MANY TIMES . so what we are doing creating the recyclerView, then using the recycler view adaptera,
        // setting the adapter, then setting the layout manager and finally settng the books from the util class

        books_rec_view_adapter adapter = new books_rec_view_adapter(this,CURRENTLY_READ_BOOKS);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adapter.setBooks(Utils.getInstance().getAlready_Read_Books());
        adapter.setBooks(Utils.getInstance(this).getCurrently_read_books());
    }

    private void initView() {
        toolbar_title = findViewById(R.id.toolbar_title);
    }

    @Override// this OVERRIDE METHOD "onBackPressed()" is used here to return to the mainActivity by clicking the backbutton not to the prev activity....you will understand
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        //by adding this two new Flags, we clearing the back stack and we are defining this intent as new task otherwise it will be like jumble of things,
        //the main reason is that each time we visit to a new activity the program will create some snacks keeping the list of activity, to prevent these 2 flags is added.

        startActivity(intent);

    }
}