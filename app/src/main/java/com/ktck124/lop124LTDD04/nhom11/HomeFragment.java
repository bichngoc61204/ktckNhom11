package com.ktck124.lop124LTDD04.nhom11;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ktck124.lop124LTDD04.nhom11.API.APIClient;
import com.ktck124.lop124LTDD04.nhom11.API.APIService;
import com.ktck124.lop124LTDD04.nhom11.API.ApiService;
import com.ktck124.lop124LTDD04.nhom11.API.RetrofitClient;
import com.ktck124.lop124LTDD04.nhom11.Activity.CartActivity;
import com.ktck124.lop124LTDD04.nhom11.Adapter.BannerAdapter;
import com.ktck124.lop124LTDD04.nhom11.Adapter.ProductAdapter;
import com.ktck124.lop124LTDD04.nhom11.Model.Product;
import com.ktck124.lop124LTDD04.nhom11.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private ViewPager2 viewPagerBanner;

    private List<Integer> bannerImages;
    private Handler bannerHandler = new Handler(Looper.getMainLooper());
    private RecyclerView recyclerViewProducts;
    private RecyclerView recyclerViewProductsBest;

    private ProductAdapter productAdapter1;
    private ProductAdapter productAdapter2;
    private List<Product> productList1;
    private List<Product> productList2;
    private ImageView cartIcon;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Tham chiếu đến icon giỏ hàng
        cartIcon = view.findViewById(R.id.cart_icon);

        // Đặt sự kiện OnClickListener
        cartIcon.setOnClickListener(v -> {
            // Chuyển đến CartActivity
            Intent intent = new Intent(getActivity(), CartActivity.class);
            startActivity(intent);
        });

        //-------------------
        // Thiết lập RecyclerView cho các sản phẩm mới
        recyclerViewProducts = view.findViewById(R.id.recycler_view_products);
        productList1 = new ArrayList<>();
        productAdapter1 = new ProductAdapter(getContext(), productList1, 0); // Khởi tạo adapter với danh sách trống
        recyclerViewProducts.setAdapter(productAdapter1);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Thiết lập RecyclerView cho các sản phẩm bán chạy
        recyclerViewProductsBest = view.findViewById(R.id.recycler_view_productbest);
        productList2 = new ArrayList<>();
        productAdapter2 = new ProductAdapter(getContext(), productList2, 1); // Khởi tạo adapter cho sản phẩm bán chạy
        recyclerViewProductsBest.setAdapter(productAdapter2);
        recyclerViewProductsBest.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Gọi API để lấy dữ liệu sản phẩm
        fetchProducts();

        return view;
    }

    private void fetchProducts() {
        // Cấu hình Retrofit để gọi API

        APIService apiService = APIClient.getClient().create(APIService.class);
        apiService.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();

                    // Cập nhật danh sách sản phẩm vào RecyclerView
                    productList1.clear();
                    productList1.addAll(products);
                    productAdapter1.notifyDataSetChanged(); // Cập nhật lại adapter sau khi có dữ liệu mới

                    // Cập nhật danh sách sản phẩm bán chạy vào RecyclerView thứ hai (nếu có)
                    productList2.clear();
                    productList2.addAll(products);  // Lấy thêm sản phẩm cho danh sách bán chạy
                    productAdapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        // Dừng tự động chuyển trang khi Fragment không hiển thị
        bannerHandler.removeCallbacks(bannerRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tiếp tục tự động chuyển trang khi Fragment hiển thị trở lại
        bannerHandler.postDelayed(bannerRunnable, 2000);
    }
}
