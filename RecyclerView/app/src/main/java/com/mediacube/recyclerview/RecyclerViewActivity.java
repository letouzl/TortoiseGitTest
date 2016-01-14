package com.mediacube.recyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mediacube.recyclerview.adapter.PersonAdapter;
import com.mediacube.recyclerview.domain.Person;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

     private RecyclerView mRecyclerView;
     private RecyclerView.LayoutManager mLayoutManager;
     private Context context;
     private ArrayList<Person> mPersonList;
     private PersonAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //使RecycleView保持固定的大小，提高它的性能
        mRecyclerView.setHasFixedSize(true);
        //显示的是横向滚动的列表或者竖直滚动的列表，则使用这个LayoutManager。
        //生成这个LinearLayoutManager之后可以设置他滚动的方向，默认竖直滚动，所以这里没有显式地设置。
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // initData();
        mAdapter = new PersonAdapter(mPersonList);
        //mAdapter.setmOnRecyclerViewListener();
        mRecyclerView.setAdapter(mAdapter);
    }
}
