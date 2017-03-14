package com.iit.glid2017.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by slim on 07/03/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ItemViewHolder> {


    private ArrayList<DataModel> mDataList;
    private LayoutInflater mLayoutInflater;

    public CustomAdapter(Context context, ArrayList<DataModel> dataList) {
        mDataList = dataList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.data_item_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        DataModel dataModel = mDataList.get(position);

        holder.mTitle.setText(dataModel.getTitle());
        holder.mDescription.setText(dataModel.getDescription());
        holder.mImage.setImageResource(dataModel.getImageRes());
        holder.mCheckBox.setChecked(dataModel.isChecked());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

        TextView mTitle;
        TextView mDescription;
        ImageView mImage;
        CheckBox mCheckBox;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_box);
            mCheckBox.setOnCheckedChangeListener(this);

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean state) {

            mDataList.get(getAdapterPosition()).setChecked(state);

        }
    }
}
