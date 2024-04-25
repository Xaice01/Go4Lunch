package com.xavier_carpentier.go4lunch.presentation.ui.ListRestaurants;

import android.content.Context;
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        listRestaurantsViewModel = new ViewModelProvider(this).get(ListRestaurantsViewModel.class);
        //TODO ViewmodelProvider ici
        initList();
    }
    private void initList() {
        ListRestaurantsAdapter adapter = new ListRestaurantsAdapter();

        adapter.setOnItemClickListener((position, restaurantUid) -> {

            if(restaurantUid!=null){}
            //TODO created restaurantView and start this activity

            //    Intent intent = new Intent(getActivity(), NeighbourViewActivity.class);
            //    intent.putExtra(KEY_NEIGHBOUR, id);
            //    startActivity(intent);
        });

        mRecyclerView.setAdapter(adapter);

        //TODO Viewmodel
        // viewmodel.getUsersWithRestaurant().observe(getViewLifecycleOwner(), users -> adapter.submitList(users);}
        listRestaurantsViewModel.getAllRestaurant().observe(getViewLifecycleOwner(), adapter::updateList);
    }
}