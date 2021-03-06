package com.codepath.apps.twitterappclient.restclient;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * This is the object responsible for communicating with a REST API.
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1/";
    public static final String REST_CONSUMER_KEY = "sLbPiDMODV8SqQ8FhpNFNlda6";
    public static final String REST_CONSUMER_SECRET = "1JFMd2ubGQ5cdY0xYba5FhWOwlgCGN3zwKJEWG7vS2WRq2Q3tb";
    public static final String REST_CALLBACK_URL = "oauth://twitterappclient";

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void postStatus(String status, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");

        RequestParams params = new RequestParams();
        params.put("status", status);

        client.post(apiUrl, params, handler);
    }

    public void getHomeTimeline(long sinceId, long maxId, AsyncHttpResponseHandler handler) {
        getTimeline(getApiUrl("statuses/home_timeline.json"),
                sinceId, maxId, handler);
    }

    public void getMentionsTimeline(long sinceId, long maxId, AsyncHttpResponseHandler handler) {
        getTimeline(getApiUrl("statuses/mentions_timeline.json"),
                sinceId, maxId, handler);
    }

    public void getUserTimeline(String screenName, long sinceId, long maxId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");

        RequestParams params = buildGetRequestParams(sinceId, maxId);
        params.put("screen_name", screenName);

        client.get(apiUrl, params, handler);
    }

    public void getCredentials(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("account/verify_credentials.json");

        client.get(apiUrl, handler);
    }

    private void getTimeline(String apiUrl, long sinceId, long maxId, AsyncHttpResponseHandler handler) {
        RequestParams params = buildGetRequestParams(sinceId, maxId);
        client.get(apiUrl, params, handler);
    }

    private RequestParams buildGetRequestParams(long sinceId, long maxId) {
        RequestParams params = new RequestParams();
        params.put("count", 25);
        params.put("since_id", sinceId);
        if (maxId > 0) {
            params.put("max_id", maxId);
        }

        return params;
    }
}