package com.example.testing_book_library_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.testing_book_library_project.Domain.Books;
import com.example.testing_book_library_project.R;
import com.example.testing_book_library_project.Utils.Utils;

import java.util.ArrayList;

public class Book_Activity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView txt_book_name, txt_author, txt_page, txt_description;
    private Button btn_add_current_read, btn_add_want_to_read, btn_add_already_read,btn_add_favourite;
    private ImageView book_img;

    private TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        toolbar_title.setText("Book Details");

    // TODO : Get the data from the recycler view in here

        Intent intent = getIntent();

        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);       //it's a key so that

            if(bookId != -1){
                Books incoming_book = Utils.getInstance(this).getBookById(bookId);

                if(null != incoming_book){
                    setdata(incoming_book);
                    
                    //create another method if a book is already added to wishlist, then disable the other button
                    handleAlreadyRead(incoming_book);

                    handleWantToReadBooks(incoming_book);

                    handleCurrentlyReadingBooks(incoming_book);

                    handleFavouriteBooks(incoming_book);
                    
                }
            }
        }
    }

    private void handleFavouriteBooks(final Books book) {

        ArrayList<Books> favourite_books = Utils.getInstance(this).getFavourite_books();        //1st we are getting the already read book list

        boolean existInFavouriteBooks = false;

        for(Books b : favourite_books){
            if(b.getId() == book.getId()){
                existInFavouriteBooks = true;
                break;
            }
        }

        if(existInFavouriteBooks){
            btn_add_favourite.setEnabled(false);
            //Toast.makeText(this, "This book is alreay added. Why add again ? Are you dumb or what ...!!!", Toast.LENGTH_SHORT).show();
        }

        else{
            btn_add_favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(Utils.getInstance(Book_Activity.this).add_favourite_books(book)){
                        Toast.makeText(Book_Activity.this, "Book added", Toast.LENGTH_SHORT).show();
                        btn_add_favourite.setEnabled(false);

                        //TODO : navigate the user      //completed

                        Intent intent = new Intent(Book_Activity.this, Favourite_Books_Activity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(Book_Activity.this, "Something WRONG happenned ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Books book) {

        ArrayList<Books> currently_reading_books = Utils.getInstance(this).getCurrently_read_books();        //1st we are getting the already read book list

        boolean existInCurrentReading = false;

        for(Books b : currently_reading_books){
            if(b.getId() == book.getId()){
                existInCurrentReading = true;
                break;
            }
        }

        if(existInCurrentReading){
            btn_add_current_read.setEnabled(false);
            //Toast.makeText(this, "This book is alreay added. Why add again ? Are you dumb or what ...!!!", Toast.LENGTH_SHORT).show();
        }

        else{
            btn_add_current_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(Utils.getInstance(Book_Activity.this).add_currently_reading(book)){
                        Toast.makeText(Book_Activity.this, "Book added", Toast.LENGTH_SHORT).show();
                        btn_add_current_read.setEnabled(false);

                        //TODO : navigate the user      //completed

                        Intent intent = new Intent(Book_Activity.this, Currently_Reading_Books_Activity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(Book_Activity.this, "Something WRONG happenned ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleWantToReadBooks(final Books book) {  //making it final bcz we hove to use it inside the OnClickListener

        ArrayList<Books> wantToReadBooks = Utils.getInstance(this).getWant_to_read_books();        //1st we are getting the already read book list

        boolean existInWantToReadBooks = false;

        for(Books b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
                break;
            }
        }

        if(existInWantToReadBooks){
            btn_add_want_to_read.setEnabled(false);
            //Toast.makeText(this, "This book is alreay added. Why add again ? Are you dumb or what ...!!!", Toast.LENGTH_SHORT).show();
        }

        else{
            btn_add_want_to_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(Utils.getInstance(Book_Activity.this).add_want_to_read(book)){
                        Toast.makeText(Book_Activity.this, "Book added", Toast.LENGTH_SHORT).show();
                        btn_add_want_to_read.setEnabled(false);

                        //TODO : navigate the user      //completed

                        Intent intent = new Intent(Book_Activity.this, Want_To_Read_Activity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(Book_Activity.this, "Something WRONG happenned ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }



    }

    //Enable and disable button
    //Add the book to Already Book Arraylist
    private void handleAlreadyRead(final Books book) {

        ArrayList<Books> alreadyReadBooks = Utils.getInstance(this).getAlready_Read_Books();        //1st we are getting the already read book list

        boolean existInAlreadyReadBooks = false;

        for(Books b : alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
                break;
            }
        }

        if(existInAlreadyReadBooks){
            btn_add_already_read.setEnabled(false);
            //Toast.makeText(this, "This book is alreay added. Why add again ? Are you dumb or what ...!!!", Toast.LENGTH_SHORT).show();
        }

        else{
            btn_add_already_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(Utils.getInstance(Book_Activity.this).addToAlreadyRead(book)){
                        Toast.makeText(Book_Activity.this, "Book added", Toast.LENGTH_SHORT).show();
                        btn_add_already_read.setEnabled(false);

                        //TODO : navigate the user      //completed

                        Intent intent = new Intent(Book_Activity.this, Already_Read_Book_Activity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(Book_Activity.this, "Something WRONG happenned ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

//        if(existInAlreadyReadBooks){
//
//            btn_add_already_read.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(Book_Activity.this, "This book is alreay added. Why add again ? Are you dumb or what ...!!!", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }





    }

    private void setdata(Books book) {

        txt_book_name.setText(book.getName());
        txt_author.setText(book.getAuthor());
        txt_page.setText(String.valueOf(book.getPages()));
        txt_description.setText(book.getLong_Des());

        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(book_img);

    }

    private void initView() {

        txt_book_name = findViewById(R.id.txt_book_name);
        txt_author = findViewById(R.id.txt_author);
        txt_page = findViewById(R.id.txt_page);
        txt_description = findViewById(R.id.long_description);

        btn_add_current_read = findViewById(R.id.btn_add_current_read);
        btn_add_want_to_read = findViewById(R.id.btn_add_want_to_read);
        btn_add_already_read = findViewById(R.id.btn_add_already_read);
        btn_add_favourite = findViewById(R.id.btn_add_favourite);

        book_img = findViewById(R.id.book_img);

        toolbar_title = findViewById(R.id.toolbar_title);
    }
}