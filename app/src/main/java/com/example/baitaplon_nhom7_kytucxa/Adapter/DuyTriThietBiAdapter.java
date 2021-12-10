package com.example.baitaplon_nhom7_kytucxa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.Utiti.DuyTriThietBi;

import java.util.List;

public class DuyTriThietBiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DuyTriThietBi> duyTriThietBiList;

    public DuyTriThietBiAdapter(Context context, int layout, List<DuyTriThietBi> duyTriThietBiList) {
        this.context = context;
        this.layout = layout;
        this.duyTriThietBiList = duyTriThietBiList;
    }

    @Override
    public int getCount() {
        return duyTriThietBiList.size();
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

        TextView tvTenThietBi =  convertView.findViewById(R.id.tvTenThietBi);
        TextView tvTinhTrang =  convertView.findViewById(R.id.tvTinhTrang);
        TextView tvTenPhong =  convertView.findViewById(R.id.tvTenPhong);
        TextView tvNgayLapDat =  convertView.findViewById(R.id.tvNgayLapDat);

        DuyTriThietBi duyTriThietBi = duyTriThietBiList.get(position);

        tvTenThietBi.setText(duyTriThietBi.getTenThietBi());
        tvTinhTrang.setText(duyTriThietBi.getTinhTrang());
        tvTenPhong.setText(duyTriThietBi.getMaPhong());
        tvNgayLapDat.setText(duyTriThietBi.getNgayLap());

        return convertView;
    }
}
