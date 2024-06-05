package com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.databinding.ActivityDetailRestaurantBinding;
import com.xavier_carpentier.go4lunch.presentation.ui.list_workmates.ListWorkmatesAdapter;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.DetailRestaurantViewModel;

public class DetailRestaurantActivity extends AppCompatActivity {

    private DetailRestaurantViewModel detailRestaurantViewModel;
    private ActivityDetailRestaurantBinding binding;
    private RecyclerView mRecyclerView;
    private String restaurantID;
    public static final String KEY_RESTAURANT = "krestaurant";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailRestaurantBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mRecyclerView = view.findViewById(R.id.detail_restaurant_workmates_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        //recupere le restaurant de la liste
        restaurantID = getIntent().getStringExtra(KEY_RESTAURANT);
        detailRestaurantViewModel = new ViewModelProvider(this).get(DetailRestaurantViewModel.class);


        detailRestaurantViewModel.initRestaurant(restaurantID);


        bind();
        initListWorkmate();
        setListener();
    }

    private void setListener(){
        binding.floatingActionButtonFavoris.setOnClickListener(v -> detailRestaurantViewModel.onFavorisClick());

        binding.buttonCallDetailRestaurant.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", detailRestaurantViewModel.getPhoneNumber(), null));
            startActivity(intent);
        });

        binding.buttonLikeDetailRestaurant.setOnClickListener(v -> detailRestaurantViewModel.OnLikeCLick());

        binding.buttonWebsiteDetailRestaurant.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(detailRestaurantViewModel.getWebsiteUrl()));
            startActivity(intent);
        });
    }


    private void bind() {

        detailRestaurantViewModel.getRestaurantDetail().observe(this, restaurantDetail ->{ Glide.with(this)
                .load(restaurantDetail.getPicture())
                .centerCrop()
                .error(R.drawable.detail_restaurant_picture)
                .fallback(R.drawable.detail_restaurant_picture)
                .into(binding.imageViewPictureRestaurant);

            Log.d(TAG, "glide"+ restaurantDetail.getPicture());

            binding.TextViewNameRestaurantDetailRestaurant.setText(restaurantDetail.getName());
            binding.TextViewNote.setText(detailRestaurantViewModel.getRatingRestaurantInStingBuilder());
            binding.TextViewTypeRestaurantAndAddress.setText(detailRestaurantViewModel.getTypeAndAddress());

        });

        detailRestaurantViewModel.getIsLike().observe(this, isLike -> {
            if(isLike){
                binding.buttonLikeDetailRestaurant.setText(R.string.Like);
                binding.buttonLikeDetailRestaurant.setIconResource(R.drawable.baseline_star_rate_24);
            }else{
                binding.buttonLikeDetailRestaurant.setText(R.string.Unlike);
                binding.buttonLikeDetailRestaurant.setIconResource(R.drawable.baseline_star_border_24);
            }
        });

        detailRestaurantViewModel.choiceToEatHere(restaurantID).observe(this, isEat ->{
            if(isEat){
                binding.floatingActionButtonFavoris.setImageResource(R.drawable.baseline_check_circle_24);
            }else{
                binding.floatingActionButtonFavoris.setImageResource(R.drawable.baseline_check_circle_outline_24);
            }
        });

    }
    private void initListWorkmate() {
        ListWorkmatesAdapter adapter = new ListWorkmatesAdapter();

        mRecyclerView.setAdapter(adapter);

        detailRestaurantViewModel.getWorkmateToEat().observe(this,adapter::updateList);
    }
}