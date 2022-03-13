package com.example.kawa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kawa.R;
import com.example.kawa.models.Name;
import com.example.kawa.models.UserResponse;
import com.example.kawa.models.UserResultsItem;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder> {

    private Context context;
    private List<UserResultsItem> usersList;
    private UserCardClickListener userCardClickListener;
    private CardView cvLastSelected;
    private Integer selectedPosition;

    public UserAdapter(Context context, List<UserResultsItem> usersList, UserCardClickListener userCardClickListener) {
        this.context = context;
        this.usersList = usersList;
        this.userCardClickListener = userCardClickListener;
    }

    public void updateItem(int position){
        UserResultsItem user= usersList.get(position);
        if(cvLastSelected!=null){
            cvLastSelected.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        selectedPosition = position;
        notifyDataSetChanged();
//        holder.cvUser.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
    }

    @NonNull
    @Override
    public UserAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.Viewholder holder, int position) {
        UserResultsItem user = usersList.get(position);

        if(selectedPosition!=null && selectedPosition == position){
            holder.cvUser.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
            cvLastSelected = holder.cvUser;
        }

        holder.clUser.setOnClickListener(v -> {
            if(cvLastSelected!=null){
                cvLastSelected.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
            holder.cvUser.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
            cvLastSelected = holder.cvUser;
            userCardClickListener.userCardClick(position);
        });

        holder.tvGender.setText(user.getGender() + " . " + user.getNat());
        Name name = user.getName();
        holder.tvName.setText(name.getTitle() + " " + name.getFirst() + " " + name.getLast());
        holder.tvEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView tvGender, tvName, tvEmail;
        ConstraintLayout clUser;
        CardView cvUser;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            clUser = itemView.findViewById(R.id.clUser);
            cvUser = itemView.findViewById(R.id.cvUser);
        }
    }

    public interface UserCardClickListener{
        void userCardClick(int position);
    }
}
