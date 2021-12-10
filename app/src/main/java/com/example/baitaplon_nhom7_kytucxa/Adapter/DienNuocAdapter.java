package com.example.baitaplon_nhom7_kytucxa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.Utiti.DienNuoc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;

public class DienNuocAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DienNuoc> dienNuocList;

    public DienNuocAdapter(Context context, int layout, List<DienNuoc> dienNuocList) {
        this.context = context;
        this.layout = layout;
        this.dienNuocList = dienNuocList;
    }

    @Override
    public int getCount() {
        return dienNuocList.size();
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

        TextView tvTenPhong =  convertView.findViewById(R.id.tvTenPhongDN);
        TextView tvThang =  convertView.findViewById(R.id.tvThangDN);
        TextView tvSoDien =  convertView.findViewById(R.id.tvSoDien);
        TextView tvSoNuoc =  convertView.findViewById(R.id.tvSoNuoc);
        TextView tvTongTien =  convertView.findViewById(R.id.tvTongTien);

        DienNuoc dienNuoc = dienNuocList.get(position);

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setGroupingSeparator(',');
        NumberFormat formatter = new DecimalFormat("#,###", otherSymbols);

        tvTenPhong.setText(dienNuoc.getPhong());
        tvThang.setText(dienNuoc.getThang());
        tvSoDien.setText(Integer.toString(dienNuoc.getSoDien()));
        tvSoNuoc.setText(Integer.toString(dienNuoc.getSoNuoc()));
        tvTongTien.setText(formatter.format(dienNuoc.getTongTien()));

        return convertView;
    }
}
