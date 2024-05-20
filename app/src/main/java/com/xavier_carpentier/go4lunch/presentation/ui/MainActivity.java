package com.xavier_carpentier.go4lunch.presentation.ui;

import static android.content.ContentValues.TAG;

import static com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity.KEY_RESTAURANT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.databinding.ActivityMainBinding;
import com.xavier_carpentier.go4lunch.presentation.ui.autocompleteSearchView.AutocompleteListAdapter;
import com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity;
import com.xavier_carpentier.go4lunch.presentation.ui.list_restaurants.ListRestaurantFragment;
import com.xavier_carpentier.go4lunch.presentation.ui.list_workmates.ListWorkmatesFragment;
import com.xavier_carpentier.go4lunch.presentation.ui.map_restaurant.MapFragment;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.AuthViewModel;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.MainViewModel;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private AuthViewModel authViewModel;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private boolean mLocationPermissionGranted;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        replaceFragment(new MapFragment());

        viewModel = new MainViewModel(getApplicationContext());


        viewModel.checkPermissionLocation().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldRequest) {
                if (shouldRequest) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                    viewModel.permissionHandled();
                }
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.map :
                    replaceFragment(new MapFragment());
                    return true;
                case R.id.page_list_view :
                    replaceFragment(ListRestaurantFragment.newInstance());
                    return true;
                case R.id.page_workmates :
                    replaceFragment(ListWorkmatesFragment.newInstance());
                    return true;
            }

            return true;
        });

        binding.topAppBar.setNavigationOnClickListener(v->{
            binding.navigationView.setVisibility(View.VISIBLE);
        });

        //Configure the authViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        authViewModel.Logout();

        //authViewModel.startSignInActivity(signInLauncher);

        initAutocompleteSearchView();
        getSearchViewQuery();



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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.setLocation();
            } else {
                viewModel.onSomeActionThatRequiresPermission();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //if(authViewModel.getCurrentUser()==null) {
        //    authViewModel.startSignInActivity(signInLauncher);
        //}
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> authViewModel.onSignInResult(result)
    );

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main_activity, fragment);
        fragmentTransaction.commit();
    }



    private void initAutocompleteSearchView() {
        AutocompleteListAdapter adapter = new AutocompleteListAdapter();


        adapter.setOnItemClickListener((position, restaurant) ->{
            Intent intent = new Intent(this, DetailRestaurantActivity.class);
            intent.putExtra(KEY_RESTAURANT, restaurant);
            startActivity(intent);
            Log.d(TAG,"on click listener");
        });

        binding.mainAutocompleteRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.mainAutocompleteRecyclerview.getContext(), DividerItemDecoration.VERTICAL);
        binding.mainAutocompleteRecyclerview.addItemDecoration(dividerItemDecoration);
        binding.mainAutocompleteRecyclerview.setAdapter(adapter);

        viewModel.getLiveDataPrediction().observe(this, predictionViewStateList -> {
                    adapter.submitList(predictionViewStateList);
                }
        );
    }

    private void getSearchViewQuery() {
        binding.searchBar.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Log.d(TAG, "on text change : " + newText);
                        viewModel.onQueryChanged(newText);
                        return true;
                    }
                }
        );

        binding.searchBar.setOnQueryTextFocusChangeListener(
                (v, hasFocus) -> {
                    if (!hasFocus) {
                        binding.mainAutocompleteRecyclerview.setVisibility(View.GONE);
                    } else {
                        binding.mainAutocompleteRecyclerview.setVisibility(View.VISIBLE);
                    }
                }
        );

        binding.searchBar.setOnCloseListener(() -> {

                    binding.searchBar.onActionViewCollapsed();
                    viewModel.onPredictionPlaceIdReset();
                    hideSoftKeyboard();
                    return true;
                }
        );
    }
    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}