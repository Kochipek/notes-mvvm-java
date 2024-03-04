package com.kochipek.noteapp.View;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.kochipek.noteapp.R;
import com.kochipek.noteapp.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}