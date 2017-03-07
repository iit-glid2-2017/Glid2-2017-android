package com.iit.glid2017.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListActivityFragment extends Fragment implements ResultDialog.ResultDialogListener {

    private static final String CONTENT_LIST_KEY = "content_list_key";

    private ArrayList<DataModel> mContentList;
    private CustomAdapter customAdapter;

    public ListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        if (savedInstanceState != null) {
            Log.v("onCreateView", "restore");
            mContentList = (ArrayList<DataModel>) savedInstanceState.getSerializable(CONTENT_LIST_KEY);
        } else {
            mContentList = new ArrayList<>();
        }
        customAdapter = new CustomAdapter(getActivity().getApplicationContext(), mContentList);
        recyclerView.setAdapter(customAdapter);

        setHasOptionsMenu(true);


        return view;
    }

//    private void initContent() {
//        mContentList = new ArrayList<>();
//
//        for (int i = 0; i < 2000; i++) {
//            DataModel dataModel = new DataModel("Title " + i, "Description " + i, R.mipmap.ic_launcher);
//            mContentList.add(dataModel);
//        }
//    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CONTENT_LIST_KEY, mContentList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                Log.v("test", "add menu item clicked");
                addItemPerform();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItemPerform() {
        ResultDialog resultDialog = ResultDialog.newInstance(this);
        resultDialog.show(getFragmentManager(), "");
    }

    @Override
    public void onSave(String title, String description) {
        DataModel dataModel = new DataModel(title, description, R.mipmap.ic_launcher);
        mContentList.add(0, dataModel);


        customAdapter.notifyItemInserted(0);
    }
}
