package com.example.alfred.recycleview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter {

    
    private ArrayList<Article> mArticles = new ArrayList<>();

    
    private RecyclerView mRecyclerView;
    private Context mContext;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;

        public MyViewHolder(View pItem) {
            super(pItem);
            mTitle = (TextView) pItem.findViewById(R.id.article_title);
            mContent = (TextView) pItem.findViewById(R.id.article_subtitle);
        }
    }

    public MyAdapter(ArrayList<Article> pArticles, RecyclerView pRecyclerView, Context pContext){
        mArticles = pArticles;
        mRecyclerView = pRecyclerView;
        mContext = pContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
       
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_layout, viewGroup, false);

        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                int position = mRecyclerView.getChildAdapterPosition(v);


                Intent openSecond = new Intent(mContext, SecondActivity.class);

                openSecond.putExtra("headerText", mArticles.get(position).getTitle());
                openSecond.putExtra("mainText", mArticles.get(position).getContent());
                openSecond.putExtra("pos", position );

                ((Activity)mContext).startActivityForResult(openSecond,1);
                //view.getContext().startActivityForResult(openSecond);
                //MainActivity.this.startActivityForResult(REQUEST_CODE, openSecond);

                /*
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                mArticles.remove(positionToDelete);
                notifyItemRemoved(positionToDelete);
                */
            }
        });

        
        return new MyViewHolder(view);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1)
            if(resultCode == Activity.RESULT_OK) {
                if( data.getExtras().get("delete").equals("true")) {
                    mArticles.remove((int)data.getExtras().get("pos"));
                    notifyItemRemoved((int)data.getExtras().get("pos"));

                }
            }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        
        Article article = mArticles.get(i);
        ((MyViewHolder) viewHolder).mTitle.setText(article.getTitle());
        ((MyViewHolder) viewHolder).mContent.setText(article.getContent());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
