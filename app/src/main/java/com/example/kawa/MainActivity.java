package com.example.kawa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kawa.adapters.UserAdapter;
import com.example.kawa.adapters.ViewPagerAdapter;
import com.example.kawa.api.ApiClient;
import com.example.kawa.api.ApiService;
import com.example.kawa.databinding.ActivityMainBinding;
import com.example.kawa.models.UserResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity implements View.OnClickListener {

    private ApiService apiService;
    ActivityMainBinding binding;
    private String TAG = MainActivity.class.getName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        apiService = ApiClient.getClient().create(ApiService.class);

        Glide.with(context).load(R.drawable.progressbar).into(binding.ivProgress);
        binding.ivMenu.setOnClickListener(this);
        String inc = "gender,name,nat,location,picture,email";
        String results = "20";
        apiService.apiGetUserResponse(inc, results).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response!=null && response.body()!=null){
                    binding.ivProgress.setVisibility(View.GONE);
                    Log.d(TAG, "Response: " + new Gson().toJson(response.body()));
                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context, response.body().getResults());
                    binding.vpUser.setAdapter(viewPagerAdapter);

                    UserAdapter userAdapter = new UserAdapter(context, response.body().getResults(), new UserAdapter.UserCardClickListener() {
                        @Override
                        public void userCardClick(int position) {
                            binding.vpUser.setCurrentItem(position);
                        }
                    });

                    binding.ivNext.setOnClickListener(v -> {
                        try{
                            binding.vpUser.setCurrentItem(binding.vpUser.getCurrentItem() + 1);
                            userAdapter.updateItem(binding.vpUser.getCurrentItem());
                            userAdapter.notifyDataSetChanged();
                        } catch (Exception e){}
                    });

                    binding.ivPrevious.setOnClickListener(v -> {
                        try{
                            binding.vpUser.setCurrentItem(binding.vpUser.getCurrentItem() - 1);
                            userAdapter.updateItem(binding.vpUser.getCurrentItem());
                            userAdapter.notifyDataSetChanged();
                        } catch (Exception e){}
                    });

                    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                    binding.rvUsers.setLayoutManager(layoutManager);
                    binding.rvUsers.setAdapter(userAdapter);

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, "API FAILURE: " + t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivMenu:
                showPopupMenu();
                break;
        }
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(context, binding.ivMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                TextView tvItem = findViewById(item.getItemId());
                tvItem.setTextColor(getResources().getColor(R.color.red));
                return true;
            }
        });
        popupMenu.show();
    }
}