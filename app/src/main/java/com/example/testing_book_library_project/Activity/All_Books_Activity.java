package com.example.testing_book_library_project.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing_book_library_project.R;
import com.example.testing_book_library_project.Utils.Utils;
import com.example.testing_book_library_project.Adapter.books_rec_view_adapter;

public class All_Books_Activity extends AppCompatActivity {

    public static final String ALL_BOOKS = "allBooks";
    private RecyclerView books_rec_view;
    private books_rec_view_adapter adapter;

    private TextView toolbar_title;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        toolbar_title.setText("All Books");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new books_rec_view_adapter(this,ALL_BOOKS);
        books_rec_view = findViewById(R.id.books_Rec_View);

        books_rec_view.setAdapter(adapter);
        books_rec_view.setLayoutManager(new LinearLayoutManager(this));

        //adapter.setBooks(books);

        adapter.setBooks(Utils.getInstance(this).getAll_Books());
    }

    private void initView() {
        toolbar_title = findViewById(R.id.toolbar_title);
    }
}