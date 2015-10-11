package com.codepath.apps.twitterappclient.models;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tweet implements Comparable<Tweet> {

    private boolean possiblySensitiveAppealable;
    private String inReplyToStatusIdStr;
    private int inReplyToStatusId;
    private String createdAt;
    private Date createdAtInDate;
    private String source;
    private int retweetCount;
    private boolean retweeted;
    private String geo;
    private String inReplyToScreenName;
    private boolean quoteStatus;
    private String idStr;
    private long id;
    private String inReplyToUserIdStr;
    private int inReplyToUserId;
    private int favoriteCount;
    private String text;
    private String place;
    private String lang;
    private boolean favorited;
    private boolean possiblySensitive;
    private boolean truncated;
    private User user;

    public static Tweet fromJson(JSONObject jsonObject) {
        Tweet tweet = new Tweet();

        try {
            tweet.possiblySensitiveAppealable = jsonObject.optBoolean("possibly_sensitive_appealable");
            tweet.inReplyToStatusIdStr = jsonObject.optString("in_reply_to_status_id_str");
            tweet.inReplyToStatusId = jsonObject.optInt("in_reply_to_status_id");
            tweet.createdAt = jsonObject.optString("created_at");
            tweet.createdAtInDate = parse(tweet.createdAt);
            tweet.source = jsonObject.optString("source");
            tweet.retweetCount = jsonObject.getInt("retweet_count");
            tweet.retweeted = jsonObject.getBoolean("retweeted");
            tweet.geo = jsonObject.optString("geo");
            tweet.inReplyToScreenName = jsonObject.optString("in_reply_to_screen_name");
            tweet.quoteStatus = jsonObject.getBoolean("is_quote_status");
            tweet.idStr = jsonObject.getString("id_str");
            tweet.inReplyToUserIdStr = jsonObject.getString("in_reply_to_user_id_str");
            tweet.inReplyToUserId = jsonObject.optInt("in_reply_to_user_id");
            tweet.favoriteCount = jsonObject.getInt("favorite_count");
            tweet.id = jsonObject.getLong("id");
            tweet.text = jsonObject.getString("text");
            tweet.place = jsonObject.optString("place");
            tweet.lang = jsonObject.getString("lang");
            tweet.favorited = jsonObject.getBoolean("favorited");
            tweet.possiblySensitive = jsonObject.optBoolean("possibly_sensitive");
            tweet.truncated = jsonObject.getBoolean("truncated");
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    public boolean isPossiblySensitiveAppealable() {
        return possiblySensitiveAppealable;
    }

    public String getInReplyToStatusIdStr() {
        return inReplyToStatusIdStr;
    }

    public int getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Date getCreatedAtInDate() {
        return createdAtInDate;
    }

    public String getSource() {
        return source;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public String getGeo() {
        return geo;
    }

    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    public boolean isQuoteStatus() {
        return quoteStatus;
    }

    public String getIdStr() {
        return idStr;
    }

    public long getId() {
        return id;
    }

    public String getInReplyToUserIdStr() {
        return inReplyToUserIdStr;
    }

    public int getInReplyToUserId() {
        return inReplyToUserId;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public String getText() {
        return text;
    }

    public String getPlace() {
        return place;
    }

    public String getLang() {
        return lang;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isPossiblySensitive() {
        return possiblySensitive;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        return idStr.equals(tweet.idStr);
    }

    @Override
    public int hashCode() {
        return idStr.hashCode();
    }

    @Override
    public int compareTo(@NonNull Tweet another) {
        return another.createdAtInDate.compareTo(this.createdAtInDate);
    }

    private static Date parse(String date) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.getDefault());
        try {
            return simpleFormatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
