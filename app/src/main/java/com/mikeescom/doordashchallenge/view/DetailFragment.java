package com.mikeescom.doordashchallenge.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mikeescom.doordashchallenge.R;
import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;
import com.mikeescom.doordashchallenge.viewmodel.MainViewModel;

public class DetailFragment extends Fragment {
    private MainViewModel viewModel;

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
            updateUI(view, response);
        });

        return view;
    }

    private void updateUI(View view, RestaurantDetail restaurantDetail) {
        TextView textView = view.findViewById(R.id.name);
        textView.setText(restaurantDetail.getName());
    }
}