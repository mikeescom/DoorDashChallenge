package com.mikeescom.doordashchallenge.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikeescom.doordashchallenge.R;
import com.mikeescom.doordashchallenge.viewmodel.MainViewModel;

public class ListFragment extends Fragment {
    private MainViewModel viewModel;
    private ListAdapter adapter;

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        adapter = new ListAdapter(getContext());

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.init();
        viewModel.getRestaurantsResponseLiveData(37.422740,-122.139956).observe(getViewLifecycleOwner(), response -> {
            if (response != null) {
                adapter.setResults(response);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext()
                , DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnClickItemListener(id -> {
            Bundle bundle = new Bundle();
            bundle.putLong("ID", id);
            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_listFragment_to_detailFragment, bundle);
        });
        recyclerView.setAdapter(adapter);

        return view;
    }
}