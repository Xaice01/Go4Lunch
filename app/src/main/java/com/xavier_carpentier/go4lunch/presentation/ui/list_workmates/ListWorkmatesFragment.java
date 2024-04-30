package com.xavier_carpentier.go4lunch.presentation.ui.list_workmates;

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
import com.xavier_carpentier.go4lunch.presentation.viewmodel.ListWorkmatesViewModel;

public class ListWorkmatesFragment extends Fragment{

    private ListWorkmatesViewModel listWorkmatesViewModel;
    private RecyclerView mRecyclerView;

    public static ListWorkmatesFragment newInstance() {
        return new ListWorkmatesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_workmates, container, false);
        Context context = view.getContext();
        mRecyclerView = view.findViewById(R.id.recycler_view_workmates);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        listWorkmatesViewModel = new ViewModelProvider(this).get(ListWorkmatesViewModel.class);
        initList();
    }
    private void initList() {
        ListWorkmatesAdapter adapter = new ListWorkmatesAdapter();

        adapter.setOnItemClickListener((position, restaurantUid) -> {
                Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
                intent.putExtra(KEY_RESTAURANT, restaurantUid);
                startActivity(intent);

        });
        mRecyclerView.setAdapter(adapter);
        listWorkmatesViewModel.getAllWorkmates().observe(getViewLifecycleOwner(), adapter::updateList);
    }
}