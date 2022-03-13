package com.example.kawa.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.kawa.R;
import com.example.kawa.models.Location;
import com.example.kawa.models.UserResultsItem;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<UserResultsItem> usersList;

    public ViewPagerAdapter(Context context, List<UserResultsItem> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, container, false);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvGender = view.findViewById(R.id.tvGender);
        TextView tvAddress = view.findViewById(R.id.tvAddress);
        ImageView ivUser = view.findViewById(R.id.ivUser);
        tvName.setText(Html.fromHtml("<u>" + usersList.get(position).getName().getTitle() + usersList.get(position).getName().getFirst() + " " + usersList.get(position).getName().getLast() + "</u> "));
        tvGender.setText(usersList.get(position).getGender());
        Location location = usersList.get(position).getLocation();
        tvAddress.setText(location.getStreet().getName() + ", " + location.getCity() + ", " + location.getState() + ", " + location.getCountry());
        Glide.with(context).load(usersList.get(position).getPicture().getMedium()).circleCrop().into(ivUser);
        container.addView(view);
        return view;
    }

}
