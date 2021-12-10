package com.example.baitaplon_nhom7_kytucxa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Nguoi;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;

import java.util.ArrayList;

public class ListviewSinVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Nguoi> svlist;

    public ListviewSinVienAdapter(Context context, int layout, ArrayList<Nguoi> svlist) {
        this.context = context;
        this.layout = layout;
        this.svlist = svlist;
    }

    @Override
    public int getCount() {
        return svlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtMasv,txtTensv,txtLop,txtKhoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view ==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(layout,null);
             holder.txtMasv = (TextView) view.findViewById(R.id.txtMasv);
             holder.txtTensv = (TextView) view.findViewById(R.id.txtTensv);
             holder.txtLop = (TextView) view.findViewById(R.id.txtLop);
             holder.txtKhoa = (TextView) view.findViewById(R.id.txtKhoa);
             view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }

        Nguoi nguoi = svlist.get(i);
        holder.txtMasv.setText("Mã SV: : "+nguoi.getMasv()+"");
        holder.txtTensv.setText("Tên SV: "+nguoi.getHovaten()+"");
        holder.txtLop.setText("Lớp: "+nguoi.getLop()+"");
        holder.txtKhoa.setText("Khoa: "+nguoi.getKhoa()+"");

        return view;
    }
}
