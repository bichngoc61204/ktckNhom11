package com.ktck124.lop124LTDD04.nhom11;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.ktck124.lop124LTDD04.nhom11.R;

import java.util.Arrays;
import java.util.List;


public class ProfileFragment extends Fragment {
    private ListView orderHistoryListView;
    private List<String> orderStatusList;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forifile, container, false);

        orderHistoryListView = view.findViewById(R.id.orderHistoryListView);

        // Danh sách dữ liệu đơn hàng
        orderStatusList = Arrays.asList("Nguyễn Thị Bích Ngọc (Nhóm trưởng)", "MSV:22115053122124" , "Nguyễn Đình Thi",
                "MSV: 22115053122137", "Nguyễn Lê Anh Thơ", "MSV:22115053122139");

        // Tạo ArrayAdapter và gán vào ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, orderStatusList);
        orderHistoryListView.setAdapter(adapter);

        TextView pointsSection = view.findViewById(R.id.quaylai);
        pointsSection.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent); // Mở RewardsActivity khi nhấn vào "Điểm tích lũy"
        });



        return view;
    }
}