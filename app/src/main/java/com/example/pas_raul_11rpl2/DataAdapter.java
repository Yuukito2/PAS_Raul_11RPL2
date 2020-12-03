package com.example.pas_raul_11rpl2;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DatakuViewHolder> {

    private ArrayList<ModelFootballRealm> dataList;
    private Callback callback;
    View viewku;
    int posku;

    interface Callback {
        void onClick(int position);
        void test();
    }


    public DataAdapter(ArrayList<ModelFootballRealm> dataList, Callback callback) {
        this.callback = callback;
        this.dataList = dataList;
        Log.d("makanan", "MahasiswaAdapter: "+dataList.size()+"");
    }

    @Override
    public DatakuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_rv, parent, false);
        return new DatakuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DatakuViewHolder holder, final int position) {
        holder.tv_Name.setText(dataList.get(position).getTeam());
        holder.tv_Description.setText(dataList.get(position).getDescription());
        Log.d("makananku", "onBindViewHolder: "+dataList.get(position).getImageUrl());
        Glide.with(holder.itemView)
                .load(dataList.get(position).getImageUrl())
                .override(Target.SIZE_ORIGINAL)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.iv_Profile);

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DatakuViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView tv_Name, tv_Description;
        CardView cardview;
        ImageView iv_Profile;

        public DatakuViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            iv_Profile = (ImageView) itemView.findViewById(R.id.iv_Profile);
            tv_Name = (TextView) itemView.findViewById(R.id.tv_Name);
            tv_Description = (TextView) itemView.findViewById(R.id.tv_Description);
            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
            posku=getAdapterPosition();
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

    }
    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 1:
                    //Do stuff
                    Toast.makeText(viewku.getContext(), ""+posku, Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    //Do stuff

                    break;
            }
            return true;
        }
    };

}