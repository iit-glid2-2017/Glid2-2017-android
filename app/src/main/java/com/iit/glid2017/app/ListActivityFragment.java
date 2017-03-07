package com.iit.glid2017.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListActivityFragment extends Fragment {

    private ArrayList<DataModel> mContentList;

    public ListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        initContent();

        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), mContentList);
        recyclerView.setAdapter(customAdapter);


        return view;
    }

    private void initContent() {
        mContentList = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            DataModel dataModel = new DataModel("Title " + i, "Description " + i, R.mipmap.ic_launcher);
            mContentList.add(dataModel);
        }
    }
}
