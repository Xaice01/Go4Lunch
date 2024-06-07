package com.xavier_carpentier.go4lunch.presentation.viewmodel;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.data.repository.AuthRepositoryFirebase;
import com.xavier_carpentier.go4lunch.data.repository.UserRepositoryFirestore;
import com.xavier_carpentier.go4lunch.domain.usecase.CreateUserInDataBaseUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetBuilderListAuthenticationProvidersUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetCurrentUserUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.GetEmailUseCase;
import com.xavier_carpentier.go4lunch.domain.usecase.LogoutUseCase;
import com.xavier_carpentier.go4lunch.presentation.model.AuthProviderTypeUi;
import com.xavier_carpentier.go4lunch.presentation.model.User;

import java.util.ArrayList;
import java.util.List;


public class AuthViewModel extends ViewModel {
    //------------------------------------
    // DATA
    //------------------------------------
    //Dependency injection
    private final AuthRepositoryFirebase authRepositoryFirebase = AuthRepositoryFirebase.getInstance();
    private final UserRepositoryFirestore userRepositoryFirestore = UserRepositoryFirestore.getInstance();

    //----------------------------------------------------
    //UseCase
    //----------------------------------------------------
    //TODO DI a faire (authRepositoryFirebase a mettre dans le DI)
    private final LogoutUseCase logoutUseCase = new LogoutUseCase(authRepositoryFirebase);
    private final GetBuilderListAuthenticationProvidersUseCase getBuilderListAuthenticationProvidersUseCase = new GetBuilderListAuthenticationProvidersUseCase(authRepositoryFirebase);
    private final GetCurrentUserUseCase getCurrentUserUseCase = new GetCurrentUserUseCase(authRepositoryFirebase);
    private final GetEmailUseCase getEmailUseCase = new GetEmailUseCase(authRepositoryFirebase);
    private final CreateUserInDataBaseUseCase createUserInDataBaseUseCase = new CreateUserInDataBaseUseCase(userRepositoryFirestore);

    MutableLiveData<Boolean> isLogging = new MutableLiveData<>(false);
    MutableLiveData<String> liveDataEmail = new MutableLiveData<>();
    MutableLiveData<User> liveDataUser = new MutableLiveData<>();
    private ActivityResultLauncher<Intent> signInLauncher;

    public LiveData<Boolean> isLogging(){
        return isLogging;
    }
    public LiveData<String> getEmail(){
        return liveDataEmail;
    }

    public LiveData<User> getCurrentUser(){
        return liveDataUser;
    }

    public void Logout(){
        isLogging.setValue(false);
        logoutUseCase.invoke();
    }


    public void startSignInActivity(ActivityResultLauncher<Intent> signInLauncher){
        this.signInLauncher=signInLauncher;
        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.LoginTheme)
                .setIsSmartLockEnabled(false,true)
                .setAvailableProviders(getBuilderListAuthenticationProviders())
                .setLogo(R.mipmap.ic_go4lunch)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private List<AuthUI.IdpConfig> getBuilderListAuthenticationProviders(){
        List<AuthUI.IdpConfig> providers =new ArrayList<>();
        List<AuthProviderTypeUi> listProvider = getBuilderListAuthenticationProvidersUseCase.invoke();

        for(AuthProviderTypeUi provider:listProvider){
            switch (provider) {
                case TWITTER:
                    providers.add(new AuthUI.IdpConfig.TwitterBuilder().build());
                    break;
                case EMAIL:
                    providers.add(new AuthUI.IdpConfig.EmailBuilder().build());
                    break;

                case GOOGLE:
                    providers.add(new AuthUI.IdpConfig.GoogleBuilder().build());
                    break;

                case FACEBOOK:
                    providers.add(new AuthUI.IdpConfig.FacebookBuilder().build());
                    break;
            }
        }
        return providers;
    }

    public void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            Log.d("uidUser",getCurrentUserUseCase.invoke().getUid());

            createUserInDataBaseUseCase.invoke(getCurrentUserUseCase.invoke());
            liveDataUser.setValue(getCurrentUserUseCase.invoke());
            liveDataEmail.setValue(getEmailUseCase.invoke());
            isLogging.setValue(true);
        } else {
            isLogging.setValue(false);
            startSignInActivity(signInLauncher);

        }
    }

}
