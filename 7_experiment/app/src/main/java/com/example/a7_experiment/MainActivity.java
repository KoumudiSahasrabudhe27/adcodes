package com.example.a7_experiment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect ImageSlider from XML
        imageSlider = findViewById(R.id.image_slider);

        // Create image list
        ArrayList<SlideModel> imageList = new ArrayList<>();

        // Add images from drawable with ScaleType
        imageList.add(new SlideModel(R.drawable.one, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.two, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.three, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.four, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.five, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.six, ScaleTypes.FIT));

        // Set images to slider
        imageSlider.setImageList(imageList);
    }
}
