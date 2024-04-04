package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.ViewModel;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xavier_carpentier.go4lunch.MainActivity;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetBuilderListAuthenticationProvidersUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetCurrentUserUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.LogoutUseCase;

import java.util.Arrays;
import java.util.List;

public class AuthViewModel extends ViewModel {

    //Dependency injection
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();

    //USECASE
    private final LogoutUseCase logoutUseCase = new LogoutUseCase(authRepositoryFirebase);
    private final GetBuilderListAuthenticationProvidersUseCase getBuilderListAuthenticationProvidersUseCase = new GetBuilderListAuthenticationProvidersUseCase(authRepositoryFirebase);
    private final GetCurrentUserUseCase getCurrentUserUseCase = new GetCurrentUserUseCase(authRepositoryFirebase);


    public FirebaseUser getCurrentUser(){
        return getCurrentUserUseCase.getCurrentUser();
    }

    public void Logout(Context context){
        logoutUseCase.logout(context);
    }

    public void startSignInActivity(ActivityResultLauncher<Intent> signInLauncher){


        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(getBuilderListAuthenticationProvidersUseCase.getBuilderListAuthenticationProviders())
                .build();
        signInLauncher.launch(signInIntent);
    }

}
