package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.ViewModel;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetBuilderListAuthenticationProvidersUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetCurrentUserUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.LogoutUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.User;


public class AuthViewModel extends ViewModel {

    //Dependency injection
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();

    //USECASE
    //TODO DI a faire (authRepositoryFirebase a mettre dans le DI)
    private final LogoutUseCase logoutUseCase = new LogoutUseCase(authRepositoryFirebase);
    private final GetBuilderListAuthenticationProvidersUseCase getBuilderListAuthenticationProvidersUseCase = new GetBuilderListAuthenticationProvidersUseCase(authRepositoryFirebase);
    private final GetCurrentUserUseCase getCurrentUserUseCase = new GetCurrentUserUseCase(authRepositoryFirebase);


    public User getCurrentUser(){
        return getCurrentUserUseCase.getCurrentUser();
    }

    public void Logout(){
        logoutUseCase.logout();
    }

    public void startSignInActivity(ActivityResultLauncher<Intent> signInLauncher){
        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false,true)
                .setAvailableProviders(getBuilderListAuthenticationProvidersUseCase.getBuilderListAuthenticationProviders())
                .build();
        signInLauncher.launch(signInIntent);
    }

    public void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            Log.d("Xaice",authRepositoryFirebase.getUser().getUid());

            //TODO add livedata

            // ...
        } else {

            //TODO add livedata

            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

}
