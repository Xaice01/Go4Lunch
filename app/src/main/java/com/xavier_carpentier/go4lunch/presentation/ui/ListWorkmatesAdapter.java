package com.xavier_carpentier.go4lunch.presentation.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.UserWithRestaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListWorkmatesAdapter extends RecyclerView.Adapter<ListWorkmatesViewHolder> {
    private List<UserWithRestaurant> users = new ArrayList<>();
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position, String restaurantUid);
    }
    @NonNull
    @Override
    public ListWorkmatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_workmates_item, parent, false);
        return new ListWorkmatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListWorkmatesViewHolder holder, int position) {
        holder.bind(users.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public void updateList(List<UserWithRestaurant> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new UserDiffCallback(newList, this.users));
        this.users = new ArrayList<>(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    private static class UserDiffCallback extends DiffUtil.Callback {

        private final List<UserWithRestaurant> oldUsers;
        private final List<UserWithRestaurant> newUsers;

        public UserDiffCallback(List<UserWithRestaurant> newUsers, List<UserWithRestaurant> oldUsers) {
            this.newUsers = newUsers;
            this.oldUsers = oldUsers;
        }

        @Override
        public int getOldListSize() {
            return oldUsers.size();
        }

        @Override
        public int getNewListSize() {
            return newUsers.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return Objects.equals(oldUsers.get(oldItemPosition).getUid(), newUsers.get(newItemPosition).getUid());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldUsers.get(oldItemPosition).equals(newUsers.get(newItemPosition));
        }
    }
}
