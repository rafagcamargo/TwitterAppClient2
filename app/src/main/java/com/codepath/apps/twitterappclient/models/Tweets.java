package com.codepath.apps.twitterappclient.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.codepath.apps.twitterappclient.models.Tweet.fromJson;

public class Tweets {

    private long lowestId;
    private long greatestId;
    private ArrayList<Tweet> list;

    public Tweets() {
        this.lowestId = 0;
        this.greatestId = 1;
        this.list = new ArrayList<>();
    }

    public static Tweets fromJsonArray(JSONArray jsonArray) {
        Tweets tweets = new Tweets();
        if (jsonArray != null) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                Tweet tweet = fromJson(jsonObject);
                if (tweet != null) {
                    tweets.lowestId = tweet.getId();
                    tweets.greatestId = tweet.getId();
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    tweet = fromJson(jsonObject);
                    if (tweet != null) {
                        tweets.list.add(tweet);
                        tweets.lowestId = Math.min(tweets.lowestId, tweet.getId());
                        tweets.greatestId = Math.max(tweets.greatestId, tweet.getId());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return tweets;
    }

    public long getLowestId() {
        return lowestId;
    }

    public long getGreatestId() {
        return greatestId;
    }

    public ArrayList<Tweet> getList() {
        return list;
    }
}
