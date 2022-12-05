package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MemeAdapter;

public class MyMemeFragment extends Fragment {

    private GridView gridView;
    private MemeAdapter memeAdapter;
    String[] memeName = {"ONE","TWO","THREE","FOUR","FIVE","SIX"};
    int[] imgMeme= {R.drawable.hihang,R.drawable.hihang,R.drawable.hihang,R.drawable.hihang,R.drawable.hihang,R.drawable.hihang};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_meme, container,false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(memeAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}