package com.example.testing_book_library_project.Adapter;

import static com.example.testing_book_library_project.Activity.All_Books_Activity.ALL_BOOKS;
import static com.example.testing_book_library_project.Activity.Already_Read_Book_Activity.ALREADY_READ;
import static com.example.testing_book_library_project.Activity.Book_Activity.BOOK_ID_KEY;
import static com.example.testing_book_library_project.Activity.Currently_Reading_Books_Activity.CURRENTLY_READ_BOOKS;
import static com.example.testing_book_library_project.Activity.Want_To_Read_Activity.WANT_TO_READ;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.example.testing_book_library_project.Activity.Book_Activity;
import com.example.testing_book_library_project.Domain.Books;
import com.example.testing_book_library_project.R;
import com.example.testing_book_library_project.Utils.Utils;

import java.util.ArrayList;

public class books_rec_view_adapter extends RecyclerView.Adapter<books_rec_view_adapter.ViewHolder> {

    private static final String TAG = "books_rec_view_adapter";
    private ArrayList<Books> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public books_rec_view_adapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public books_rec_view_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_books,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@SuppressLint("RecyclerView") @NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Log.d(TAG, "onBindViewHolder: called");

        holder.book_title.setText(books.get(position).getName());

        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBooks);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, books.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, Book_Activity.class);

                // here we are just passing bookId
                intent.putExtra(BOOK_ID_KEY,books.get(position).getId());      //first argument is the key or name of the value and the 2nd is the value almost like KEY VALUE PAIR
                //its also possible to pass the obj using "parcelable value"


                context.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txt_des.setText(books.get(position).getShort_Des());

        if(books.get(position).getExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.extended_view.setVisibility(View.VISIBLE);
            holder.down_arrow.setVisibility(View.GONE);

            if(parentActivity.equals(ALL_BOOKS)){
                holder.btnDelete.setVisibility(View.GONE);
            }

            else if (parentActivity.equals(ALREADY_READ)) {

                holder.btnDelete.setVisibility(View.VISIBLE);

                String book_name = books.get(position).getName();       //here creating the string is necessary bcz after the deleting the book there is no way to
                                                                        // get the position to get the book name

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setMessage("Are you sure you want to delete \" " + books.get(position).getName() +" \" from the list ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(context).removeFromAlreadyRead(books.get(position))){
                                    Toast.makeText(context, "\"" +book_name + " \" Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });

            }

            else if (parentActivity.equals(WANT_TO_READ)) {

                holder.btnDelete.setVisibility(View.VISIBLE);

                String book_name = books.get(position).getName();       //here creating the string is necessary bcz after the deleting the book there is no way to
                                                                        // get the position to get the book name

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setMessage("Are you sure you want to delete \" " + books.get(position).getName() +" \" from the list ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(context).removeFromWantToRead(books.get(position))){
                                    Toast.makeText(context, "\"" +book_name + " \" Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });


            }

            else if (parentActivity.equals(CURRENTLY_READ_BOOKS)) {

                holder.btnDelete.setVisibility(View.VISIBLE);

                String book_name = books.get(position).getName();       //here creating the string is necessary bcz after the deleting the book there is no way to
                // get the position to get the book name

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setMessage("Are you sure you want to delete \" " + books.get(position).getName() +" \" from the list ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(context).removeFromCurrentlyRead(books.get(position))){
                                    Toast.makeText(context, "\"" +book_name + " \" Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });

            }

            else{

                holder.btnDelete.setVisibility(View.VISIBLE);

                String book_name = books.get(position).getName();       //here creating the string is necessary bcz after the deleting the book there is no way to
                // get the position to get the book name

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setMessage("Are you sure you want to delete \"" + books.get(position).getName() +"\" from the list ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(context).removeFromFavouriteBooks(books.get(position))){
                                    Toast.makeText(context, "\"" +book_name + "\" Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });

            }
        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.extended_view.setVisibility(View.GONE);
            holder.down_arrow.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setBooks(ArrayList<Books> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final CardView parent;
        private final ImageView imgBooks;
        private final TextView book_title;

        private TextView txtAuthor, txt_des;
        private ImageView up_arrow,down_arrow;
        private RelativeLayout extended_view;
        private TextView btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imgBooks = itemView.findViewById(R.id.imgBook);
            book_title = itemView.findViewById(R.id.txt_book_name);

            txtAuthor = itemView.findViewById(R.id.author_name);
            txt_des = itemView.findViewById(R.id.short_des);
            up_arrow = itemView.findViewById(R.id.btn_up_arrow);
            down_arrow = itemView.findViewById(R.id.btn_down_arrow);
            extended_view = itemView.findViewById(R.id.extended_Rel_layout);

            btnDelete = itemView.findViewById(R.id.btn_delete);

            down_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Books book = books.get(getAdapterPosition());       // here to get position we using getAdapterPosition() this is how we can get a book from the viewholder
                    book.setExpanded(!book.getExpanded());              // here if the user clicked on the down arrow then expanded view will be true
                    notifyItemChanged(getAdapterPosition());            // to notify that the item is changed

                    //

                }
            });

            up_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Books book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}
