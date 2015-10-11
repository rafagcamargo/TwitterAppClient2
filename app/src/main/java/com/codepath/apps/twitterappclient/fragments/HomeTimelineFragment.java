package com.codepath.apps.twitterappclient.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.codepath.apps.twitterappclient.activities.ProfileActivity;
import com.codepath.apps.twitterappclient.adapter.TweetsArrayAdapter;
import com.codepath.apps.twitterappclient.models.Tweet;
import com.codepath.apps.twitterappclient.models.Tweets;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomeTimelineFragment extends TweetsListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweetsArrayAdapter.setImageProfileClickListener(new TweetsArrayAdapter.OnImageProfileClickListener() {
            @Override
            public void onImageProfileClick(View itemView, int position) {
                Tweet tweet = tweetsArrayAdapter.getItem(position);

                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.USER_EXTRA, tweet.getUser());
                startActivity(intent);
            }
        });
    }

    protected void populateTimeline(long sinceId, long maxId) {
        twitterClient.getHomeTimeline(sinceId, maxId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                addAll(Tweets.fromJsonArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
