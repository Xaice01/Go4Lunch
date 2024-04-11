package com.xavier_carpentier.go4lunch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.xavier_carpentier.go4lunch.databinding.ActivityMainBinding;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.AuthViewModel;


public class MainActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Configure the authViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.startSignInActivity(signInLauncher);

        binding.buttonLogin.setOnClickListener(v->{
            authViewModel.startSignInActivity(signInLauncher);

            //TODO with Livedata (asynchrone)
            //Snackbar.make(binding.main, authViewModel.getCurrentUser().getUsername(), Snackbar.LENGTH_SHORT).show();
        });

        binding.buttonLogout.setOnClickListener(v->{
            authViewModel.Logout();
            authViewModel.startSignInActivity(signInLauncher);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        authViewModel.startSignInActivity(signInLauncher);
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> authViewModel.onSignInResult(result)
    );

}