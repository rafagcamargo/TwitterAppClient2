package com.codepath.apps.twitterappclient.fragments;

import android.os.Bundle;

import com.codepath.apps.twitterappclient.models.Tweets;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserTimelineFragment extends TweetsListFragment {

    public static final String SCREEN_NAME = "screen_name";

    public static UserTimelineFragment newInstance(String screenName) {
        UserTimelineFragment userTimelineFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString(SCREEN_NAME, screenName);
        userTimelineFragment.setArguments(args);
        return userTimelineFragment;
    }

    protected void populateTimeline(long sinceId, long maxId) {
        String screenName = getArguments().getString(SCREEN_NAME);

        twitterClient.getUserTimeline(screenName, sinceId, maxId, new JsonHttpResponseHandler() {
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
