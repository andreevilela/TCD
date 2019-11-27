package com.andre.tcd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andre.tcd.R;
import com.andre.tcd.dto.DtoProduct;
import com.andre.tcd.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroProduto extends AppCompatActivity {

    private static final String TAG = "CadastroProduto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
    }

    public void cadastrar(View view) {
        String name = ((EditText)findViewById(R.id.et_cadastro_prodduto_nome)).getText().toString();
        String description = ((EditText)findViewById(R.id.et_cadastro_produto_descricao)).getText().toString();
        String imgUrl = ((EditText)findViewById(R.id.et_cadastro_produto_imgurl)).getText().toString();
        String strPrice = ((EditText)findViewById(R.id.et_cadastro_produto_preco)).getText().toString();
        int price = Integer.parseInt(strPrice);

        DtoProduct dtoProduct = new DtoProduct(name,description,imgUrl,price);
        String token = getToken();

        RetrofitService.getServico(this).cadastroProduto(dtoProduct, "Bearer "+token).enqueue(new Callback<DtoProduct>() {
            @Override
            public void onResponse(Call<DtoProduct> call, Response<DtoProduct> response) {
                Toast.makeText(CadastroProduto.this, "Produto cadastrado com Id: "+response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DtoProduct> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    private String getToken() {
        SharedPreferences sp = getSharedPreferences("dados", 0);
        return sp.getString("token", null);
    }
    //Toast.makeText(CadastroProduto.this, ""+response.body(), Toast.LENGTH_SHORT).show();
    //Log.d(TAG, "onFailure: "+t.getMessage());
}
