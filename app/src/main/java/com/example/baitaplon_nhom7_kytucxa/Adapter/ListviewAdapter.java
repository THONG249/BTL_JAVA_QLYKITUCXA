package com.example.baitaplon_nhom7_kytucxa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baitaplon_nhom7_kytucxa.R;
import com.example.baitaplon_nhom7_kytucxa.SQL.Database;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Phong;
import com.example.baitaplon_nhom7_kytucxa.Utiti.Nguoi;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Phong> phonglist;




 public ListviewAdapter(Context context, int layout, ArrayList<Phong> phonglist) {
        this.context = context;
        this.layout = layout;
        this.phonglist = phonglist;


    }

    @Override
    public int getCount() {
        return phonglist.size();
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
        TextView txtMa,txtSogiuong,txtSonguoi,txtLoai;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view ==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(layout,null);
             holder.txtMa = (TextView) view.findViewById(R.id.txtMaphong);
             holder.txtSogiuong = (TextView) view.findViewById(R.id.txtSogiuong);
             holder.txtSonguoi = (TextView) view.findViewById(R.id.txtSonguoi);
             holder.txtLoai = (TextView) view.findViewById(R.id.txtLoaiphong);
             view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }


        Phong phong = phonglist.get(i);
        holder.txtMa.setText("Mã phòng : "+phong.getMaphong()+"");
        holder.txtSogiuong.setText("Số giường: "+phong.getSogiuong()+"");
        holder.txtSonguoi.setText("Số sv(Max): "+phong.getSonguoi()+"");
        holder.txtLoai.setText("Loại phòng: "+phong.getLoaiphong()+"");


        return view;
    }
}
