package com.example.baitaplon_nhom7_kytucxa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.Utiti.ThietBi;

import java.util.List;

public class ThietBiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ThietBi> thietBiList;

    public ThietBiAdapter(Context context, int layout, List<ThietBi> thietBiList) {
        this.context = context;
        this.layout = layout;
        this.thietBiList = thietBiList;
    }

    @Override
    public int getCount() {
        return thietBiList.size();
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
        convertView = inflater.inflate(layout, null);

        TextView tvTenTB = convertView.findViewById(R.id.tvTenTB);
        TextView tvXuatXu = convertView.findViewById(R.id.tvXuatXu);
        TextView tvNgayNhap = convertView.findViewById(R.id.tvNgayNhap);

        ThietBi thietBi = thietBiList.get(position);

        tvTenTB.setText(thietBi.getTenThietBi());
        tvXuatXu.setText(thietBi.getXuatXu());
        tvNgayNhap.setText(thietBi.getNgayNhap());

        return convertView;
    }
}
