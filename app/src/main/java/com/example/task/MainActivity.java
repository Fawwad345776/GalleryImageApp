package com.example.task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class MainActivity extends AppCompatActivity implements ProgrammingAdapter.CallbackInterface{


    RecyclerView recyclerView;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.programming_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] languages = {"HP", "Dell", "Toshiba", "Lenovo"};
        String[] languages2 = {"Rs.30000", "Rs.20000", "Rs.25000", "Rs.15000"};
        int[] images = {R.drawable.lap1, R.drawable.lap2, R.drawable.lap3, R.drawable.lap4};
        recyclerView.setAdapter(new ProgrammingAdapter(this,images, languages, languages2));


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {

            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


    @Override
    public void onHandleSelection(int position, ProgrammingAdapter.ProgrammingViewHolder holder)
    {
        imageView = holder.imageView;
        Toast.makeText(this, "Select item in list for "+ position, Toast.LENGTH_SHORT).show();
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,1);
    }
}
