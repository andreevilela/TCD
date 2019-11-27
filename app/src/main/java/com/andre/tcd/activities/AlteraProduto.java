package com.andre.tcd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andre.tcd.R;
import com.andre.tcd.dto.DtoProduct;
import com.andre.tcd.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlteraProduto extends AppCompatActivity {
    private static final String TAG = "AlteraProduto";
    EditText et_nome, et_descricao, et_imgUrl, et_preco;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("descripton");
        String imgUrl = intent.getStringExtra("imgUrl");
        String price = intent.getStringExtra("price");

        et_nome = findViewById(R.id.et_cadastro_prodduto_nome);
        et_descricao = findViewById(R.id.et_cadastro_produto_descricao);
        et_imgUrl = findViewById(R.id.et_cadastro_produto_imgurl);
        et_preco = findViewById(R.id.et_cadastro_produto_preco);

        et_nome.setText(name);
        et_descricao.setText(description);
        et_imgUrl.setText(imgUrl);
        et_preco.setText(price);
    }

    public void cadastrar(View view) {
        String name = ((EditText) findViewById(R.id.et_cadastro_prodduto_nome)).getText().toString();
        String description = ((EditText) findViewById(R.id.et_cadastro_produto_descricao)).getText().toString();
        String imgUrl = ((EditText) findViewById(R.id.et_cadastro_produto_imgurl)).getText().toString();
        String strPrice = ((EditText) findViewById(R.id.et_cadastro_produto_preco)).getText().toString();
        int price = Integer.parseInt(strPrice);

        DtoProduct dtoProduct = new DtoProduct(name, description, imgUrl, price);
        String token = getToken();

        RetrofitService.getServico(this).alteraProduto(dtoProduct, id, "Bearer " + token).enqueue(new Callback<DtoProduct>() {
            @Override
            public void onResponse(Call<DtoProduct> call, Response<DtoProduct> response) {
                Toast.makeText(AlteraProduto.this, "Produto alterado com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DtoProduct> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private String getToken() {
        SharedPreferences sp = getSharedPreferences("dados", 0);
        return sp.getString("token", null);
    }
}