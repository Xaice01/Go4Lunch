package com.xavier_carpentier.go4lunch.presentation.ui;

import static android.content.ContentValues.TAG;

import static com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity.KEY_RESTAURANT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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

    private TextView textViewEmail;
    private TextView textViewName;
    private ImageView imageViewUser;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        replaceFragment(MapFragment.newInstance());

        View headerView = binding.navigationView.getHeaderView(0);
        textViewEmail = headerView.findViewById(R.id.nav_header_email);
        textViewName = headerView.findViewById(R.id.nav_header_name);
        imageViewUser = headerView.findViewById(R.id.imageView_user);

        //Configure the authViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        authViewModel.startSignInActivity(signInLauncher);

        viewModel = new MainViewModel(getApplication());
        viewModel.scheduleDailyNotification();

        viewModel.checkPermissionLocation().observe(this, shouldRequest -> {
            if (shouldRequest) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                viewModel.permissionHandled();
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.map :
                    binding.searchBar.setVisibility(View.VISIBLE);
                    replaceFragment(MapFragment.newInstance());
                    return true;
                case R.id.page_list_view :
                    binding.searchBar.setVisibility(View.VISIBLE);
                    replaceFragment(ListRestaurantFragment.newInstance());
                    return true;
                case R.id.page_workmates :
                    binding.searchBar.setVisibility(View.GONE);
                    replaceFragment(ListWorkmatesFragment.newInstance());
                    return true;
            }
            return true;
        });

        binding.topAppBar.setNavigationOnClickListener(v-> binding.navigationView.setVisibility(View.VISIBLE));


        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_your_lunch:
                    viewModel.checkWorkmateRestaurant();
                    break;
                case R.id.nav_settings:
                    Intent intentSetting = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intentSetting.setData(uri);
                    startActivity(intentSetting);
                    break;
                case R.id.nav_logout:
                    authViewModel.Logout();
                    authViewModel.startSignInActivity(signInLauncher);
                    break;
            }
            binding.navigationView.setVisibility(View.GONE);
            return true;
        });

        // Observer for navigation
        viewModel.getNavigateToDetailEvent().observe(this, event -> {
            String uidRestaurant = event.getContentIfNotHandled();
            if (uidRestaurant != null) {
                Intent intent = new Intent(this, DetailRestaurantActivity.class);
                intent.putExtra(KEY_RESTAURANT, uidRestaurant);
                startActivity(intent);
            }
        });

        // Observer for showing toast
        viewModel.getShowToastEvent().observe(this, event -> {
            if (event.getContentIfNotHandled() == null) {
                Toast.makeText(this, R.string.toast_no_restaurant, Toast.LENGTH_SHORT).show();
            }
        });

        onBindNavigationView();
        initAutocompleteSearchView();
        getSearchViewQuery();

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

        viewModel.getLiveDataPrediction().observe(this, adapter::submitList
        );
    }

    //for AutoComplete
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

    private void onBindNavigationView(){

        authViewModel.isLogging().observe(this, isLogging->{
            if (isLogging != null && isLogging) {
                authViewModel.getCurrentUser().observe(this, user -> {
                    if (user != null) {
                        if (user.getUsername() != null) {
                            textViewName.setText(user.getUsername());
                        }
                        if(user.getUid()!=null){
                            viewModel.setUserUid(user.getUid());
                        }
                        if (user.getUrlPicture() != null) {
                            Glide.with(this)
                                    .load(user.getUrlPicture())
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(imageViewUser);
                        }
                    }
                });
                authViewModel.getEmail().observe(this, email -> {
                    if (email != null) {
                        textViewEmail.setText(email);
                    }
                });
            }
        });
    }
}