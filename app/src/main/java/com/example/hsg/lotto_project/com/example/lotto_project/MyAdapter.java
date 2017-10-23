package com.example.hsg.lotto_project.com.example.lotto_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsg.lotto_project.R;

import java.util.List;

/**
 * Created by hsg on 2017. 10. 9..
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //헤더인지 아닌지 구분 하기 위한 플래그
    static private boolean headerFlag = false;

    private Context context;
    private List<ActivityName> list;

    public MyAdapter(Context context, List<ActivityName> list) {
        this.context = context;
        this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View v;
//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View view ;
        //viewType이 0이면 헤더이므로 flag를 설정하고 헤더 아이템을 inflater를 이용해서 할당한다
        if(viewType == 0) {
            headerFlag = true;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_header_item, parent,false);
        }
        //헤더가 아닌 나머지 콘텐츠들
        else {
            headerFlag = false;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent,false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;



    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(!holder.isHeader)
            bodyBindInit(holder,position);
        else // 헤더인 경우 바인딩
            headerBindInit(holder);

    }

    //헤더 바인드
    private void headerBindInit(final ViewHolder holder)
    {
        holder.mHeadertext.setText("Header");


    }

    //헤더가 아닌경우 게시물들을 바인드
    private void bodyBindInit(final ViewHolder holder, final int position)
    {
        holder.mTextView.setText(list.get(position).getName());

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,list.get(position).getActivityClass());
                intent.putExtra("title", list.get(position).getName());
                context.startActivity(intent);
            }
        });

    }



    //RecyclerView에서 게시물 순서 꼬이는 문제 해결을 위한 코드
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size()  ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        boolean isHeader = headerFlag;

        public TextView mTextView, mHeadertext;
//        public ViewHolder(View itemview) {
//            super(itemview);

//        }

        public ViewHolder(View itemView)
        {
            super(itemView);

            if(!isHeader)
                init(itemView);
            else
                headerInit(itemView);
        }

        //헤더 값 초기화
        private void headerInit(View itemView)
        {
            mHeadertext = (TextView) itemView.findViewById(R.id.mheadertext);
        }

        //게시글 값 초기화
        private void init(View itemView)
        {
            mTextView = (TextView) itemView.findViewById(R.id.mTextView);
        }


    }


}


