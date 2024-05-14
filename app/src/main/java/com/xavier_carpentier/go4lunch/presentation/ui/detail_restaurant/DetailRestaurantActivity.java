package com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant;

import android.net.Uri;
import android.os.Bundle;
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


        //recupere le neighbour de la liste de mNeighbours de l'Apiservice
        String restaurantID = getIntent().getStringExtra(KEY_RESTAURANT);
        detailRestaurantViewModel = new ViewModelProvider(this).get(DetailRestaurantViewModel.class);

        //TODO TEST UIDRESTAURANT
        restaurantID = "ChIJY5NpzITVwkcRSkdvo3wsU1Y";
        detailRestaurantViewModel.initRestaurant(restaurantID);


        bind();
        initListWorkmate();

        //todo created listener for floatingActionButton_favoris, button_call_detailRestaurant, button_like_detailRestaurant, button_website_detailRestaurant
    }

    private void bind() {


        //show the picture restaurant
        //String uriPicture = detailRestaurantViewModel.getRestaurantDetail().getPicture();
        detailRestaurantViewModel.getRestaurantDetail().observe(this, restaurantDetail ->{ Glide.with(this)
                .load(restaurantDetail.getPicture())
                .centerCrop()
                .error(R.drawable.background_picture)
                .fallback(R.drawable.background_picture)
                .into(binding.imageViewPictureRestaurant);

            binding.TextViewNameRestaurantDetailRestaurant.setText(restaurantDetail.getName());
            binding.TextViewNote.setText(detailRestaurantViewModel.getRatingRestaurantInStingBuilder());
            binding.TextViewTypeRestaurantAndAddress.setText(detailRestaurantViewModel.getTypeAndAddress());
        });


        //Glide.with(this).load(uriPicture).into(binding.imageViewPictureRestaurant);

        //Glide.with(this)
        //        .load(uriPicture)
        //        .centerCrop()
        //        .error(R.drawable.background_picture)
        //        .fallback(R.drawable.background_picture)
        //        .into(binding.imageViewPictureRestaurant);
        //binding.imageViewPictureRestaurant.setImageResource(detailRestaurantViewModel.getRestaurantDetail().getPicture());

        //binding.TextViewNameRestaurantDetailRestaurant.setText(detailRestaurantViewModel.getRestaurantDetail().getName());
        //binding.TextViewNote.setText(detailRestaurantViewModel.getRatingRestaurantInStingBuilder());
        //binding.TextViewTypeRestaurantAndAddress.setText(detailRestaurantViewModel.getTypeAndAddress());

    }
    private void initListWorkmate() {
        ListWorkmatesAdapter adapter = new ListWorkmatesAdapter();

        mRecyclerView.setAdapter(adapter);

        detailRestaurantViewModel.getWorkmateToEat().observe(this,adapter::updateList);
    }
}