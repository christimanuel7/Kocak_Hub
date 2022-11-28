package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageSlider carousel=findViewById(R.id.carousel);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Menyajikan berita terkini secara cepat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2,"Menyajikan informasi berita yang akurat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3,"Menyajikan informasi dari sumber yang kredibel", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner4,"Menyajikan informasi dari sumber yang kredibel", ScaleTypes.FIT));
        carousel.setImageList(slideModels);
    }
}