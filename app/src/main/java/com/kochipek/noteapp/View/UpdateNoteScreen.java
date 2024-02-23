package com.kochipek.noteapp.View;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.kochipek.noteapp.databinding.AddNoteScreenBinding;
import com.kochipek.noteapp.databinding.UpdateNotesScreenBinding;

public class UpdateNoteScreen extends AppCompatActivity {

    private UpdateNotesScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UpdateNotesScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
