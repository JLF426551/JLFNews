package com.jl426551.example.jlfnews.DataUtilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jl426551.example.jlfnews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private int totalCount;
    private ArrayList<News> list;

    public NewsAdapter(ArrayList<News> news) {
        if (news != null) {

            list = news;
            totalCount = news.size();
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean attachToParent = false;

        View viewToProcess = inflater.inflate(R.layout.single_news_layout, parent, attachToParent);
        NewsViewHolder holder = new NewsViewHolder(viewToProcess);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return totalCount;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ImageView sourceImageView;
        TextView titleView;
        TextView sourceTextView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            sourceImageView = itemView.findViewById(R.id.source_image);
            titleView = itemView.findViewById(R.id.news_title);
            sourceTextView = itemView.findViewById(R.id.news_source);
        }

        void bind(int currentPosition) {
            News currentNews = list.get(currentPosition);

            Picasso.get().load(currentNews.getImageURL()).into(sourceImageView);
            titleView.setText(currentNews.getTitle());
            sourceTextView.setText(currentNews.getSource());
        }
    }
}
