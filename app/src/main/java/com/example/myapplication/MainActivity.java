package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    EditText editTopText,editBottomText;
    TextView topText,bottomText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTopText=findViewById(R.id.editTop);
        editBottomText=findViewById(R.id.editBottom);

        relativeLayout=findViewById(R.id.memecontent);

        Button button=findViewById(R.id.btnSaveGallery);
        Button button2=findViewById(R.id.btnAccept);

        topText=findViewById(R.id.toptext);
        bottomText=findViewById(R.id.bottomtext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topText.setText(editTopText.getText());
                bottomText.setText(editBottomText.getText());
            }
        });
    }

    private void saveImage(){
        relativeLayout.setDrawingCacheEnabled(true);
        relativeLayout.buildDrawingCache();
        relativeLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap=relativeLayout.getDrawingCache();
        save(bitmap);
    }

    private void save(Bitmap bitmap) {

        String root= Environment.getExternalStorageDirectory().getAbsolutePath();
        File file=new File(root+"/Download");
        String fileName="meme2.jpg";
        File myfile=new File(file,fileName);


        if(myfile.exists()){
            myfile.delete();
        }

        try{
            FileOutputStream fileOutputStream=new FileOutputStream(myfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this,"Saved Meme to Download...",Toast.LENGTH_SHORT).show();
            relativeLayout.setDrawingCacheEnabled(false);

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}