package com.fuicuiedu.idedemo.interview_explain;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MyAdapter myAdapter;
    private List<String> list;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        handler = new Handler();

        listView = (ListView) findViewById(R.id.main_lv);//alt + enter
        list = new ArrayList<>();

        for (int i = 0;i<30;i++){
            list.add("第 " + i + " 条数据");
        }

        myAdapter = new MyAdapter(this,list);

        //加上listvewi的底部View，注意要放在setadapter之前
        addMoreView(listView);

        listView.setAdapter(myAdapter);

    }

    //加上listview的底部View，注意要放在setadapter之前
    private void addMoreView(ListView listView){
        //拿到moreView
        View view = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.view_more,null);
        //listView添加foot布局
        listView.addFooterView(view);

        final Button moreBtn = (Button) view.findViewById(R.id.view_more_btn);
        final ProgressBar morePb = (ProgressBar) view.findViewById(R.id.view_more_pb);

        //加载更多
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreBtn.setVisibility(View.INVISIBLE);
                morePb.setVisibility(View.VISIBLE);
                //模拟网络请求数据方式，让转圈圈
                //用handler发送一个延迟请求2s
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        loadMoreDate();//加载更多数据
                        moreBtn.setVisibility(View.VISIBLE);
                        morePb.setVisibility(View.INVISIBLE);
                        myAdapter.notifyDataSetChanged();//通知adapter刷新数据
                    }
                },2000);
            }
        });
    }

    //加载数据
    private void loadMoreDate(){
        for (int i = 0;i<5;i++){
            list.add("第 " + i + " 条数据(新)");
        }
    }

    //动态设置listview的高度
    public void setListViewHeight(ListView listView){
        //获取listview对应adapter
        ListAdapter listAdapter = listView.getAdapter();
        //判断是否为空，如果为空那这个方法就没用，所以直接return
        if (listAdapter == null){
            return;
        }

        int totalHeight = 0;
        // listadapter.getCount()返回数据项的数目
        for (int i = 0 , len = listAdapter.getCount();i<len;i++){
            //拿到itemView
            View itemView = listAdapter.getView(i,null,listView);
            //计算子项View的宽高
            itemView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);//父view
            //统计所有子项的总的高度
            totalHeight += itemView.getMeasuredHeight();
        }
        //拿到listveiw的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //listView.getDividerHeight()获取子项间分隔符占用的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() -1));

        listView.setLayoutParams(params);
    }


}
