package com.codepath.apps.twitterappclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterappclient.R;
import com.codepath.apps.twitterappclient.models.Tweet;
import com.codepath.apps.twitterappclient.ui.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class TweetsArrayAdapter extends RecyclerView.Adapter<TweetsArrayAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<Tweet> tweets;

    private OnImageProfileClickListener imageProfileClickListener;

    public TweetsArrayAdapter(Context context, ArrayList<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public TweetsArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(TweetsArrayAdapter.ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);

        Date date = tweet.getCreatedAtInDate();
        String createdTime = "";
        if (date != null) {
            createdTime = DateUtils.getRelativeTimeSpanString(date.getTime(),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_ALL).toString();
        }

        holder.ivProfile.setImageResource(R.drawable.ic_person_white_48dp);
        holder.tvScreenName.setText(context.getString(R.string.screen_name, tweet.getUser().getScreenName()));
        holder.tvName.setText(tweet.getUser().getName());
        holder.tvText.setText(tweet.getText());
        createdTime = formatCreatedTime(createdTime);
        holder.tvCreatedAt.setText(createdTime);

        Linkify.addLinks(holder.tvText, Linkify.ALL);

        Picasso.with(context)
                .load(tweet.getUser().getProfileImageUrlOriginal())
                .placeholder(R.drawable.ic_person_white_48dp)
                .resize(140, 140)
                .transform(new RoundedTransformation(5, 0))
                .into(holder.ivProfile);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void updateList(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Tweet> tweets) {
        TreeSet<Tweet> tweetTreeSet = new TreeSet<>(this.tweets);
        tweetTreeSet.addAll(tweets);
        updateList(new ArrayList<>(tweetTreeSet));
    }

    public Tweet getItem(int position) {
        return tweets.get(position);
    }

    private String formatCreatedTime(String createdTime) {
        if (createdTime.contains("ago")) {
            createdTime = createdTime.replace("ago", "");

            if (createdTime.contains("days")) {
                createdTime = createdTime.replace("days", "d");
            }

            if (createdTime.contains("secs") || createdTime.contains("sec")) {
                createdTime = createdTime.replace("secs", "s");
                createdTime = createdTime.replace("sec", "s");
            }

            if (createdTime.contains("mins") || createdTime.contains("min")) {
                createdTime = createdTime.replace("mins", "m");
                createdTime = createdTime.replace("min", "m");
            }

            if (createdTime.contains("hours") || createdTime.contains("hour")) {
                createdTime = createdTime.replace("hours", "h");
                createdTime = createdTime.replace("hour", "h");
            }

            return deleteWhitespace(createdTime);
        } else if (createdTime.contains("in")) {
            createdTime = createdTime.replace("in", "");
            return deleteWhitespace(createdTime);
        }

        if (createdTime.contains("secs") || createdTime.contains("sec")) {
            createdTime = createdTime.replace("secs", "s");
            createdTime = createdTime.replace("sec", "s");
            return deleteWhitespace(createdTime);
        }

        if (createdTime.contains("mins") || createdTime.contains("min")) {
            createdTime = createdTime.replace("mins", "m");
            createdTime = createdTime.replace("min", "m");
            return deleteWhitespace(createdTime);
        }

        return createdTime;
    }

    private String deleteWhitespace(final String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        final int sz = str.length();
        final char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        return new String(chs, 0, count);
    }

    public interface OnImageProfileClickListener {
        void onImageProfileClick(View itemView, int position);
    }

    public void setImageProfileClickListener(OnImageProfileClickListener imageProfileClickListener) {
        this.imageProfileClickListener = imageProfileClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfile;
        public TextView tvScreenName;
        public TextView tvName;
        public TextView tvText;
        public TextView tvCreatedAt;

        public ViewHolder(View itemView) {
            super(itemView);

            ivProfile = (ImageView) itemView.findViewById(R.id.ivProfile);
            tvScreenName = (TextView) itemView.findViewById(R.id.tvScreenName);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tvCreatedAt);

            ivProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageProfileClickListener != null) {
                        imageProfileClickListener.onImageProfileClick(ivProfile, getLayoutPosition());
                    }
                }
            });
        }
    }
}
