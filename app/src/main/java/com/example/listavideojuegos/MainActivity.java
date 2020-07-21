package com.example.listavideojuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import Modelo.Game;
import Modelo.RespuestaGame;
import RappiApi.AppiServicio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="Game";
    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaGameAdapter listaGameAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaGameAdapter = new ListaGameAdapter();
        //recyclerView.setAdapter(listaGameAdapter);
//        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);


        retrofit= new Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/contacts/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    obtenerDato();

    }

    private void obtenerDato() {

        AppiServicio service = retrofit.create( AppiServicio.class);
        Call<RespuestaGame>respuestaGameCall = service.obtenerListaGame();
        respuestaGameCall.enqueue(new Callback<RespuestaGame>() {
            @Override
            public void onResponse(Call<RespuestaGame> call, Response<RespuestaGame> response) {
                if (response.isSuccessful()){
                    RespuestaGame respuestaGame=response.body();
                  ArrayList<Game> ListaGame =respuestaGame.getResults();

                  listaGameAdapter.adiconarGame(ListaGame);


                }else {
                    Log.e(TAG,"onResponse"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RespuestaGame> call, Throwable t) {
                Log.e(TAG,"onFailure"+ t.getMessage());

            }
        });

    }
}
