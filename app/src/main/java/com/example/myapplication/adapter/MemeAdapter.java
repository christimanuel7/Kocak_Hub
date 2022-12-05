package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MainModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MemeAdapter extends BaseAdapter {
    private List<MainModel> results=new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public MemeAdapter(List<MainModel> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(inflater==null)
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null){
            convertView=inflater.inflate(R.layout.grid_item,null);
        }

        MainModel result=results.get(position);

        ImageView imageView=convertView.findViewById(R.id.imgMeme);
        TextView textView=convertView.findViewById(R.id.txtMemeTitle);

        return convertView;
    }

    public void setData(List<MainModel.Result> data){
        results.clear();
        notifyDataSetChanged();
    }
}
