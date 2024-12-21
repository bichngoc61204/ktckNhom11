package com.ktck124.lop124LTDD04.nhom11.API;

import com.ktck124.lop124LTDD04.nhom11.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("api.php") // Đường dẫn API của bạn
    Call<List<Product>> getAllProducts();
}
