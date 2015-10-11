package com.codepath.apps.twitterappclient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.twitterappclient.R;
import com.codepath.apps.twitterappclient.adapter.TweetsArrayAdapter;
import com.codepath.apps.twitterappclient.application.TwitterApplication;
import com.codepath.apps.twitterappclient.decorator.DividerItemDecoration;
import com.codepath.apps.twitterappclient.listener.EndlessRecyclerOnScrollListener;
import com.codepath.apps.twitterappclient.models.Tweets;
import com.codepath.apps.twitterappclient.restclient.TwitterClient;

public abstract class TweetsListFragment extends Fragment {

    private static final String TAG = TweetsListFragment.class.getSimpleName();

    private TweetsArrayAdapter tweetsArrayAdapter;
    private Tweets tweets;

    protected TwitterClient twitterClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets_list, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        RecyclerView rvTweets = (RecyclerView) view.findViewById(R.id.rvTweets);
        rvTweets.setAdapter(tweetsArrayAdapter);

        rvTweets.setLayoutManager(linearLayoutManager);
        rvTweets.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvTweets.addItemDecoration(itemDecoration);
        rvTweets.setItemAnimator(new DefaultItemAnimator());

        rvTweets.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.d(TAG, "onLoadMore(" + tweets.getLowestId() + ")");
                populateTimeline(1, tweets.getLowestId());
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tweets = new Tweets();
        tweetsArrayAdapter = new TweetsArrayAdapter(getActivity(), tweets.getList());

        twitterClient = TwitterApplication.getRestClient();
    }

    @Override
    public void onResume() {
        super.onResume();
        populateTimeline(tweets.getGreatestId(), 0);
    }

    public void addAll(Tweets tweets) {
        this.tweets = tweets;
        tweetsArrayAdapter.addAll(tweets.getList());
    }

    protected abstract void populateTimeline(long sinceId, long maxId);
}
