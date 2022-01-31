
package com.pmdm.saj.definitiveconcesionario.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pmdm.saj.definitiveconcesionario.R;
import com.pmdm.saj.definitiveconcesionario.entity.Car;
import com.squareup.picasso.Picasso;

public class CarActivity extends AppCompatActivity {
    Car car;

    private TextView tvBAddtitle, tvBAdddescription, tvBAddprice, tvBAddlocation,
            tvBAddfuel, tvBAddkms, tvBAddshift, tvBAddcolor, tvBAddpower, tvBAddnumDoors, tvBAddyear,
            tvBAddImgCounter, tvBAddRef;
    private ImageView ivBAddCar;

    private Button btBAddBack, btBAddNext, btBAddBuy, btBAddReturn;

    private int numImg = 0;

    String[] images;
    String imageCountPrefix, imageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        // Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initialize();
        setComponents();
        setListeners();
    }


    private void initialize() {
        this.car = getIntent().getParcelableExtra("car");
        images = car.images;
        imageCountPrefix = "/"+ car.images.length;

        tvBAddtitle = findViewById(R.id.tvBAddTitle);
        tvBAdddescription = findViewById(R.id.tvBAddDescription);
        tvBAddprice = findViewById(R.id.tvBAddPrice);
        tvBAddlocation = findViewById(R.id.tvBAddLocation);
        tvBAddfuel = findViewById(R.id.tvBAddFuel);
        tvBAddkms = findViewById(R.id.tvBAddKms);
        tvBAddshift = findViewById(R.id.tvBAddShift);
        tvBAddcolor = findViewById(R.id.tvBAddColor);
        tvBAddpower = findViewById(R.id.tvBAddPower);
        tvBAddnumDoors = findViewById(R.id.tvBAddDoors);
        tvBAddyear = findViewById(R.id.tvBAddYear);
        tvBAddImgCounter = findViewById(R.id.tvBAddImgCounter);
        tvBAddRef = findViewById(R.id.tvBAddRef);
        ivBAddCar = findViewById(R.id.ivBAddCar);

        btBAddBack = findViewById(R.id.btBAddBack);
        btBAddNext = findViewById(R.id.btBAddNext);
        btBAddBuy = findViewById(R.id.btBAddBuy);
        btBAddReturn = findViewById(R.id.btBAddReturn);
    }

    private void setComponents() {
        String price = car.price + "€",
                kms = car.kms + "km",
                power = car.power + "cv",
                doors = car.doors + " Puertas",
                ref = "ref: " + car.ref;
        imageCount = 1 + imageCountPrefix;

        tvBAddtitle.setText(car.title);
        tvBAdddescription.setText(car.description);
        tvBAddprice.setText(price);
        tvBAddlocation.setText(car.location);
        tvBAddfuel.setText(car.fuel);
        tvBAddkms.setText(kms);
        tvBAddshift.setText(car.shift);
        tvBAddcolor.setText(car.color);
        tvBAddpower.setText(power);
        tvBAddnumDoors.setText(doors);
        tvBAddyear.setText(String.valueOf(car.year));
        tvBAddImgCounter.setText(imageCount);
        tvBAddRef.setText(ref);
        loadImage(images[0]);
    }

    private void loadImage(String image) {
        Picasso.get().load(image).into(ivBAddCar);
    }

    private void setListeners() {
        btBAddBack.setOnClickListener(view -> {
            moveBack();
        });

        btBAddNext.setOnClickListener(view -> {
            moveNext();
        });

        btBAddBuy.setOnClickListener(view -> {
            goWeb(car.url);
        });

        btBAddReturn.setOnClickListener(view -> {
            finish();
        });
    }

    private void moveBack() {
        numImg--;
        if (numImg < 0){
            numImg = 0;
            Toast.makeText(this, "No hay más imágenes", Toast.LENGTH_SHORT).show();
        } else {
            imageCount = numImg + imageCountPrefix;
            tvBAddImgCounter.setText(imageCount);
            loadImage(images[numImg]);
        }
    }

    private void moveNext() {
        numImg++;
        if (numImg >= images.length){
            numImg = images.length-1;
            Toast.makeText(this, "No hay más imágenes", Toast.LENGTH_SHORT).show();
        } else {
            imageCount = (numImg+1) + imageCountPrefix;
            tvBAddImgCounter.setText(imageCount);
            loadImage(images[numImg]);
        }
    }

    private void goWeb(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String url;
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_policy:
                url = "https://www.milanuncios.com/legal/politica-privacidad";
                goWeb(url);
                break;

            case R.id.action_info:
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}