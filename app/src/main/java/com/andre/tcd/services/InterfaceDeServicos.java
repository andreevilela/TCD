package com.andre.tcd.services;

import com.andre.tcd.dto.DtoLogin;
import com.andre.tcd.dto.DtoProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceDeServicos {

    /*@POST("/users")
    Call<DtoUser> cadastroUsuario(@Body DtoUser dtoUser);*/

    @POST("/auth/login")
    Call<DtoLogin> login(@Body DtoLogin dtoLogin);

    /*@GET("/users")
    Call<List<DtoUser>> todosUsuarios(@Header("Authorization") String authorization);

    @PUT("/users/{id}")
    Call<DtoUser> alteraUsuario(@Body DtoUser user, @Path("id") int id, @Header("Authorization") String authorization);

    @DELETE("/users/{id}")
    Call<Void> excluiUsuario(@Path("id") int id, @Header("Authorization") String token);*/

    @POST("/products")
    Call<DtoProduct> cadastroProduto(@Body DtoProduct dtoProduct, @Header("Authorization") String authorization);

    @GET("/products")
    Call<List<DtoProduct>> todosProdutos(@Header("Authorization") String authorization);

    @PUT("/products/{id}")
    Call<DtoProduct> alteraProduto(@Body DtoProduct user, @Path("id") int id, @Header("Authorization") String authorization);

    @DELETE("/products/{id}")
    Call<Void> excluiProduto(@Path("id") int id, @Header("Authorization") String token);
}

