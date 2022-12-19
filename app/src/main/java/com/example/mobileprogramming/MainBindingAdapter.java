package com.example.mobileprogramming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.mobileprogramming.data.WeatherWrnData;
import com.example.mobileprogramming.databinding.WeatherWrnItemViewBinding;
import com.example.mobileprogramming.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainBindingAdapter {

    @BindingAdapter({"text_sunSet", "text_sunRise"})
    public static void setTextSun(TextView textView, String sunSet, String sunRise) {
        if (sunRise != null && sunSet != null && !sunSet.isEmpty() && !sunRise.isEmpty()) {
            textView.setText(String.format("%s %s", sunRise, sunSet));
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"img_url"})
    public static void setImage(ImageView imageView, String url) {
        if (url != null) {
            Glide.with(imageView)
                    .load(url)
                    .into(imageView);
        }
    }

    @BindingAdapter({"set_weatherWrnData"})
    public static void setViewPager(RecyclerView recyclerView, ArrayList<WeatherWrnData> weatherWrnData) {
        class CustomHolder extends RecyclerView.ViewHolder {
            WeatherWrnItemViewBinding binding;

            public CustomHolder(WeatherWrnItemViewBinding view) {
                super(view.getRoot());
                binding = view;
            }

            public void bind(String content) {
                binding.txtContent.setText(content);
            }
        }
        class CustomAdapter extends RecyclerView.Adapter<CustomHolder> {

            public ArrayList<WeatherWrnData> dataList = new ArrayList<>();
            @NonNull
            @Override
            public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                WeatherWrnItemViewBinding binding = WeatherWrnItemViewBinding.inflate(LayoutInflater.from(parent.getContext()));
                return new CustomHolder(binding);
            }

            @Override
            public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
                holder.bind(dataList.get(position).getContent());
            }

            @Override
            public int getItemCount() {
                return dataList.size();
            }

            public void setDataList(ArrayList<WeatherWrnData> data) {
                dataList = data;
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CustomAdapter());
        SnapHelper snapHelper = new LinearSnapHelper();
        recyclerView.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(recyclerView);
        ((CustomAdapter) Objects.requireNonNull(recyclerView.getAdapter())).setDataList(weatherWrnData);
    }

    @BindingAdapter({"set_key", "set_map"})
    public static void setVisible(View view, String key, HashMap<String, Boolean> map) {
        if (map.get(key) != null) {
            view.setVisibility(Boolean.TRUE.equals(map.get(key)) ? View.VISIBLE : View.GONE);
        }
    }
}
