package com.example.moviedetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.moviedetails.DetailMoviePage;
import com.example.moviedetails.Login;
import com.example.moviedetails.ModelMovie;
import com.example.moviedetails.MovieAdapter;
import com.example.moviedetails.MovieAdapter.ContactsAdapterListener;
import com.example.moviedetails.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Home extends AppCompatActivity implements ContactsAdapterListener{


    RecyclerView recyclerView;
    ArrayList<ModelMovie> listDataMovie;
    private MovieAdapter listAdapterMovie;

    public void getApiMovie(){

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=c41889d54fbc24645c28dd9ce811d0ba&language=en-US&page=1";
        AndroidNetworking.get(url)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("sukses", "onResponse: " + jsonObject.toString());
                        try {
                            JSONArray jsonArrayMovie = jsonObject.getJSONArray("results");
                            for (int i = 0; i < jsonArrayMovie.length(); i++) {
                                ModelMovie myMovie = new ModelMovie();
                                JSONObject jsonMovie = jsonArrayMovie.getJSONObject(i);
                                myMovie.setOriginal_title(jsonMovie.getString("original_title"));
                                myMovie.setOverview(jsonMovie.getString("overview"));
                                myMovie.setPoster_path(jsonMovie.getString("poster_path"));
                                myMovie.setBackdrop_path(jsonMovie.getString("backdrop_path"));
                                listDataMovie.add(myMovie);
                            }
                            recyclerView = findViewById(R.id.rvMovie);
                            listAdapterMovie = new MovieAdapter(getApplicationContext(), listDataMovie, Home.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(listAdapterMovie);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("failed", "onError: " + anError.toString());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidemenu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout_menu){
            logoutUser();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listDataMovie = new ArrayList<>();

    }


    @Override
    public void onContactSelected(ModelMovie contact) {
        Intent intent = new Intent(Home.this, DetailMoviePage.class);
        intent.putExtra("myteam", contact);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setTitle("Perhatian!")
                .setMessage("Apakah kamu yakin ingin menghapus item ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika tombol OK diklik
                        listDataMovie.remove(position);
                        Toast.makeText(Home.this.getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika tombol Batal diklik
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void logoutUser() {
            startActivity(new Intent(Home.this, Login.class));
            finish();
        };
    }

