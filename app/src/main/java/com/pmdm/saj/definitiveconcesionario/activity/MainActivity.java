package com.pmdm.saj.definitiveconcesionario.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.pmdm.saj.definitiveconcesionario.R;
import com.pmdm.saj.definitiveconcesionario.adapter.CarAdapter;
import com.pmdm.saj.definitiveconcesionario.entity.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "jdbc:mysql://146.59.237.189:3306/dam208_sajconcesionario";
    private static final String USER = "dam208_saj";
    private static final String PASSWORD = "dam208_saj";

    public Context context;

    private void goWeb(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        new InfoAsyncTask().execute();
        

        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
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

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, ArrayList<Car>>  {
        @Override
        protected ArrayList<Car> doInBackground(Void... voids) {
            ArrayList<Car> info = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
                String sql = "SELECT * FROM coches";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    int ref = resultSet.getInt("ref"),
                            price = resultSet.getInt("precio"),
                            kms = resultSet.getInt("km"),
                            year = resultSet.getInt("anno"),
                            doors = resultSet.getInt("npuertas"),
                            power = resultSet.getInt("potencia");
                    String title = resultSet.getString("titulo"),
                            fuel = resultSet.getString("combustible"),
                            shift = resultSet.getString("cambio"),
                            color = resultSet.getString("color"),
                            description = resultSet.getString("descripcion"),
                            location = resultSet.getString("localizacion"),
                            url = resultSet.getString("url"),
                            images = resultSet.getString("imagenes");

                    Car car = new Car(ref, price, kms, year, doors, power, title, fuel, shift, color,
                            description, location, url, images);
                    info.add(car);
                }
                return info;
            } catch (SQLException throwables) {
                Log.e("InfoAsyncTask", "Error reading car information", throwables);
            }

            return info;
        }

        @Override
        protected void onPostExecute(ArrayList<Car> cars) {
            if (!cars.isEmpty()){
                RecyclerView rv = findViewById(R.id.rvCarList);
                rv.setLayoutManager(new LinearLayoutManager(context));
                CarAdapter carAdapter = new CarAdapter(context);
                rv.setAdapter(carAdapter);
                carAdapter.setList(cars);
            }
        }
    }
}