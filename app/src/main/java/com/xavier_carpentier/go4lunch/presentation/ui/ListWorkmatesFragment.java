package com.xavier_carpentier.go4lunch.presentation.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavier_carpentier.go4lunch.R;
public class ListWorkmatesFragment extends Fragment{
    private RecyclerView mRecyclerView;

    public ListWorkmatesFragment newInstance() {
        return new ListWorkmatesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_workmates, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //TODO ViewmodelProvider ici
        initList();
    }
    private void initList() {
        ListWorkmatesAdapter adapter = new ListWorkmatesAdapter();

        adapter.setOnItemClickListener(new ListWorkmatesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String restaurantUid) {

                if(restaurantUid!=null){}
                //TODO created restaurantView and start this activity

                //    Intent intent = new Intent(getActivity(), NeighbourViewActivity.class);
                //    intent.putExtra(KEY_NEIGHBOUR, id);
                //    startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(adapter);

        //TODO Viewmodel
        // viewmodel.getUsersWithRestaurant().observe(getViewLifecycleOwner(), users -> adapter.submitList(users);}
    }
}