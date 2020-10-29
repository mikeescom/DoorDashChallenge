package com.mikeescom.doordashchallenge.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.mikeescom.doordashchallenge.R;
import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;
import com.mikeescom.doordashchallenge.viewmodel.MainViewModel;

public class DetailFragment extends Fragment {
    private MainViewModel viewModel;
    private ProgressBar progressBar;

    private TextView name;
    private TextView description;
    private TextView address;
    private ImageView imageHeader;

    public DetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();
        long id = bundle.getLong("ID");
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.init();
        viewModel.getRestaurantDetailResponseLiveData(id).observe(getViewLifecycleOwner(), response -> {
            if (response != null) {
                updateUI(response);
            }
        });

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        imageHeader = view.findViewById(R.id.image_header);
        name = view.findViewById(R.id.name);
        description = view.findViewById(R.id.description);
        address = view.findViewById(R.id.address);
    }

    private void updateUI(RestaurantDetail restaurantDetail) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        Glide.with(getContext())
                .load(restaurantDetail.getCover_img_url())
                .centerCrop()
                .placeholder(R.drawable.placeholder_item)
                .into(imageHeader);

        name.setText(restaurantDetail.getName());
        description.setText(restaurantDetail.getDescription());
        address.setText(restaurantDetail.getAddress().getPrintable_address());
    }
}