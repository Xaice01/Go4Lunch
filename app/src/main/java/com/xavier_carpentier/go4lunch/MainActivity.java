package com.xavier_carpentier.go4lunch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.xavier_carpentier.go4lunch.databinding.ActivityMainBinding;
import com.xavier_carpentier.go4lunch.presentation.ui.ListRestaurantFragment;
import com.xavier_carpentier.go4lunch.presentation.ui.ListWorkmatesFragment;
import com.xavier_carpentier.go4lunch.presentation.ui.MapFragment;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.AuthViewModel;


public class MainActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        replaceFragment(new MapFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.map :
                    replaceFragment(new MapFragment());
                    return true;
                case R.id.page_list_view :
                    replaceFragment(new ListRestaurantFragment());
                    return true;
                case R.id.page_workmates :
                    replaceFragment(new ListWorkmatesFragment());
                    return true;

            }

            return true;
        });

        binding.topAppBar.setNavigationOnClickListener(v->{
            binding.navigationView.setVisibility(View.VISIBLE);
        });

        //Configure the authViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

       // authViewModel.startSignInActivity(signInLauncher);
//
       // binding.buttonLogin.setOnClickListener(v->{
       //     authViewModel.startSignInActivity(signInLauncher);
//
       //     //TODO with Livedata (asynchrone)
       //     //Snackbar.make(binding.main, authViewModel.getCurrentUser().getUsername(), Snackbar.LENGTH_SHORT).show();
       // });
//
       // binding.buttonLogout.setOnClickListener(v->{
       //     authViewModel.Logout();
       //     authViewModel.startSignInActivity(signInLauncher);
       // });
    }
//
    //@Override
    //protected void onResume() {
    //    super.onResume();
    //    authViewModel.startSignInActivity(signInLauncher);
    //}
//
    //private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
    //        new FirebaseAuthUIActivityResultContract(),
    //        result -> authViewModel.onSignInResult(result)
    //);

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main_activity, fragment);
        fragmentTransaction.commit();
    }
}