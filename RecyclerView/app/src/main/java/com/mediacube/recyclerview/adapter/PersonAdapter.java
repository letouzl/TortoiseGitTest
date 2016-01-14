package com.mediacube.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mediacube.recyclerview.R;
import com.mediacube.recyclerview.domain.Person;

import java.util.List;


/**
 * Created by Administrator on 2016/1/6.
 */
public class PersonAdapter extends RecyclerView.Adapter{

    public static interface OnRecyclerViewListener{

        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener mOnRecyclerViewListener;

//    public OnRecyclerViewListener getmOnRecyclerViewListener() {
//        return mOnRecyclerViewListener;
//    }

    public void setmOnRecyclerViewListener(OnRecyclerViewListener mOnRecyclerViewListener) {
        this.mOnRecyclerViewListener = mOnRecyclerViewListener;
    }

    private static final String TAG = PersonAdapter.class.getSimpleName();
    private List<Person> mlist;

    public PersonAdapter(List<Person> list){

       this.mlist = list;
    }
   /**
    *  这个方法主要为每个Item inflater生成一个View，但是返回值是ViewHolder
    *  也就是把View直接封装在ViewHolder中，直接面向的是ViewHolder这个实例，
    *  当然ViewHolder需要我们自己编写，直接省去了当初的convertView.setTag(holder)和convertView.getTag()这些繁琐的步骤。
    * */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        Log.d(TAG, "onCreateViewHolder,i:  " + i);
        View view= LayoutInflater.from(viewGroup.getContext()).
                                  inflate(R.layout.adapter_item_view, null);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams
                     (ViewGroup.LayoutParams.MATCH_PARENT,
                      ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);

        return new PersonViewHolder(view);
    }

    /**
     *   这个方法主要用于适配渲染（填充）数据到View中，给你提供了一个ViewHolder，而不是原来的convertView
     *
     * */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

       Log.d(TAG,"onBindViewHolder，i: "+i+",viewHolder :"+viewHolder);
       PersonViewHolder holder = (PersonViewHolder)viewHolder;
       holder.position = i;
       Person person = mlist.get(i);
       holder.nameTv.setText(person.getName());
       holder.ageTv.setText(person.getAge()+"岁");
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    /**
     *   ViewHolder类
     * */
    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        public View rootView;
        public TextView nameTv;
        public TextView ageTv;
        public int position;

        public PersonViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView)itemView.findViewById(R.id.recycler_view_name_tv);
            ageTv = (TextView)itemView.findViewById(R.id.recycler_view_age_tv);
            rootView = itemView.findViewById(R.id.recycler_view_test_item_person);

            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
           if(null != mOnRecyclerViewListener){
               mOnRecyclerViewListener.onItemClick(position);

           }
        }

        @Override
        public boolean onLongClick(View v) {
           if(null != mOnRecyclerViewListener){

               return mOnRecyclerViewListener.onItemLongClick(position);
           }
           return false;
        }
    }


}
