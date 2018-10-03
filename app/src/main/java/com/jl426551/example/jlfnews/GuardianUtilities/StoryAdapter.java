package com.jl426551.example.jlfnews.GuardianUtilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jl426551.example.jlfnews.BingUtilities.News;
import com.jl426551.example.jlfnews.R;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private int totalCount;
    private ArrayList<News> list;

    public StoryAdapter(ArrayList<News> stories) {
        list = stories;
        totalCount = list.size();
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean attachToParent = false;

        View viewToProcess = inflater.inflate(R.layout.single_news_layout, parent, attachToParent);
        StoryViewHolder holder = new StoryViewHolder(viewToProcess);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.StoryViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return totalCount;
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ImageView sourceImageView;
        TextView titleView;
        TextView sourceTextView;

        public StoryViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            sourceImageView = itemView.findViewById(R.id.source_image);
            sourceImageView.setVisibility(View.GONE);
            titleView = itemView.findViewById(R.id.news_title);
            sourceTextView = itemView.findViewById(R.id.news_source);
        }

        void bind(int currentPosition) {
            News currentStory = list.get(currentPosition);

            titleView.setText(currentStory.getTitle());
            sourceTextView.setText(currentStory.getDate());
        }
    }
}