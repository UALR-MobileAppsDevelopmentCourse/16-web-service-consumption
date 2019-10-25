package edu.ualr.networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import edu.ualr.networking.viewmodel.ArticlesViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArticlesViewModel viewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
    }
}
