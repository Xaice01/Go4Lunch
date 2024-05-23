package com.xavier_carpentier.go4lunch.presentation.ui.list_restaurants;

import static com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity.KEY_RESTAURANT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.ListRestaurantsViewModel;

public class ListRestaurantFragment extends Fragment {
    private ListRestaurantsViewModel listRestaurantsViewModel;
    private RecyclerView mRecyclerView;

    public static ListRestaurantFragment newInstance() {
        return new ListRestaurantFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);
        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.recycler_view_restaurant);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        listRestaurantsViewModel=new ListRestaurantsViewModel(getActivity().getApplication());
        // listRestaurantsViewModel = new ViewModelProvider(this).get(ListRestaurantsViewModel.class);
        initList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listRestaurantsViewModel.stopLocationUpdates();
    }
    private void initList() {
        ListRestaurantsAdapter adapter = new ListRestaurantsAdapter();

        adapter.setOnItemClickListener((position, restaurantUid) -> {
                Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
                intent.putExtra(KEY_RESTAURANT, restaurantUid);
                startActivity(intent);
        });

        mRecyclerView.setAdapter(adapter);
        listRestaurantsViewModel.getAllRestaurant().observe(getViewLifecycleOwner(), adapter::updateList);
    }
}