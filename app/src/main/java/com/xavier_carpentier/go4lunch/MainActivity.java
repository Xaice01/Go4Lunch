package com.xavier_carpentier.go4lunch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.databinding.ActivityMainBinding;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.AuthViewModel;

import java.util.Arrays;
import java.util.List;

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

        binding.buttonLogin.setOnClickListener(v->{
            authViewModel.startSignInActivity(signInLauncher);
        });

        binding.buttonLogout.setOnClickListener(v->{
            authViewModel.Logout(this);
        });


    }

    private void logoutUser(){

        AuthUI.getInstance()
                .signOut(this);

    }

    private void startSignInActivity(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);



        // Launch the activity
        //startActivityForResult(
        //        AuthUI.getInstance()
        //                .createSignInIntentBuilder()
        //                .setTheme(R.style.LoginTheme)
        //                .setAvailableProviders(providers)
        //                .setIsSmartLockEnabled(false, true)
        //                .setLogo(R.drawable.ic_logo_auth)
        //                .build(),
        //        RC_SIGN_IN);
    }
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> onSignInResult(result)
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = authViewModel.getCurrentUser();
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
}