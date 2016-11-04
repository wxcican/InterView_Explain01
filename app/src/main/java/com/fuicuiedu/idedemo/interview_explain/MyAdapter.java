package com.fuicuiedu.idedemo.interview_explain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class MyAdapter extends BaseAdapter {
    private List<String> mDatas;
    private Context context;
    private LayoutInflater inflater;

    public MyAdapter(Context context,List<String> mDatas) {
        this.mDatas = mDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_mian_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        String data = mDatas.get(position);
        holder.mTv.setText(data);

        return convertView;
    }

    private class ViewHolder{
        TextView mTv;
        Button mBtn;

        public ViewHolder(View view){
            mTv = (TextView) view.findViewById(R.id.main_item_tv);
            mBtn = (Button) view.findViewById(R.id.main_item_btn);
        }
    }
}
