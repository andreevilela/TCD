package com.andre.tcd.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.andre.tcd.R;
import com.andre.tcd.dto.DtoProduct;
import com.andre.tcd.helpers.ProductAdapter;
import com.andre.tcd.helpers.SwipeToDeleteCallback;
import com.andre.tcd.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaProduto extends AppCompatActivity {
    private static final String TAG = "ListaProduto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);
        buscaDados();
    }

    private void preencheRecyclerview(List<DtoProduct> lista){
        RecyclerView mRecyclerView = findViewById(R.id.rv_lista_produto);
        ProductAdapter mAdapter = new ProductAdapter(this, lista);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //swipe
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void buscaDados() {
        String token = getToken();

        RetrofitService.getServico(this).todosProdutos("Bearer "+token).enqueue(new Callback<List<DtoProduct>>() {
            @Override
            public void onResponse(Call<List<DtoProduct>> call, Response<List<DtoProduct>> response) {
                List<DtoProduct> lista = response.body();
                preencheRecyclerview(lista);
            }

            @Override
            public void onFailure(Call<List<DtoProduct>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private String getToken() {
        SharedPreferences sp = getSharedPreferences("dados", 0);
        return sp.getString("token", null);
    }

    public void excluirItem(int id){
        String token = getToken();
        RetrofitService.getServico(this).excluiProduto(id, "Bearer " + token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ListaProduto.this, "Código de retorno: "+response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
