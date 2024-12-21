package com.ktck124.lop124LTDD04.nhom11.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ktck124.lop124LTDD04.nhom11.Model.Product;
import com.ktck124.lop124LTDD04.nhom11.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;
    private final Context context;
    private final int viewType;

    // Constructor
    public ProductAdapter(Context context, List<Product> productList, int viewType) {
        this.context = context;
        this.productList = productList;
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType; // Trả về viewType, có thể phân biệt loại hiển thị
    }
    public void updateData(List<Product> newProducts) {
        this.productList.clear();
        this.productList.addAll(newProducts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        // Kiểm tra kiểu RecyclerView để chọn layout tương ứng
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);  // Layout với CardView
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product_blank, parent, false);  // Layout nền trắng
        }
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Set tên sản phẩm
        holder.textProductName.setText(product.getName());

        // Dùng Picasso để tải hình ảnh từ URL lấy từ cơ sở dữ liệu
        String hinhAnhUrl = "http://192.168.1.73:8081/appadroidbanquanao/images/" + product.getImageUrl();  // Đây là URL của hình ảnh

        // Log URL để kiểm tra
        Log.d("ImageURL", hinhAnhUrl);

        // Tải lại hình ảnh từ URL và đảm bảo tải từ mạng, không dùng cache
        Picasso.get()
                .load(hinhAnhUrl)
                .networkPolicy(NetworkPolicy.NO_CACHE)  // Đảm bảo tải từ mạng (khong dung lai bo nho cache cu)
                .into(holder.imageProduct);  // Gán hình ảnh vào ImageView



    }

    @Override
    public int getItemCount() {

        return productList.size();  // Trả về số lượng sản phẩm
    }

    // ViewHolder cho từng item trong RecyclerView
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        final TextView textProductName;
        final ImageView imageProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            // Khởi tạo các view
            textProductName = itemView.findViewById(R.id.product_name);
            imageProduct = itemView.findViewById(R.id.product_image);
        }
    }
}
