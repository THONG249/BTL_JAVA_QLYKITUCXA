package com.example.baitaplon_nhom7_kytucxa.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.Utiti.ThanhToan;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;

public class ThanhToanAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ThanhToan> thanhToanList;

    public ThanhToanAdapter(Context context, int layout, List<ThanhToan> thanhToanList) {
        this.context = context;
        this.layout = layout;
        this.thanhToanList = thanhToanList;
    }


    @Override
    public int getCount() {
        return thanhToanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView tvTenPhong =  convertView.findViewById(R.id.tvTenPhongTT);
        TextView tvThang =  convertView.findViewById(R.id.tvThangTT);
        TextView tvDienNuoc =  convertView.findViewById(R.id.tvDienNuoc);
        TextView tvDichVu =  convertView.findViewById(R.id.tvDichVu);
        TextView tvTongTien =  convertView.findViewById(R.id.tvTongTienTT);
        TextView tvTinhTrang = convertView.findViewById(R.id.tvTinhTrangTT);

        ThanhToan thanhToan = thanhToanList.get(position);

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setGroupingSeparator(',');
        NumberFormat formatter = new DecimalFormat("#,###", otherSymbols);

        tvTenPhong.setText(thanhToan.getPhong());
        tvThang.setText(thanhToan.getThang());
        tvDienNuoc.setText(Integer.toString(thanhToan.getTienDienNuoc()));
        tvDichVu.setText(Integer.toString(thanhToan.getTienDichVu()));
        tvTongTien.setText(formatter.format(thanhToan.getTongTien()));
        tvTinhTrang.setText(thanhToan.getTinhTrang());
        if (thanhToan.getTinhTrang().equals("Đã thanh toán")) {
            tvTinhTrang.setTextColor(Color.parseColor("#5CB85C"));
        }

        return convertView;
    }
}
