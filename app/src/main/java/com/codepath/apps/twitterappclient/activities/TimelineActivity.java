package com.codepath.apps.twitterappclient.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.twitterappclient.R;
import com.codepath.apps.twitterappclient.adapter.TweetsPageAdapter;
import com.codepath.apps.twitterappclient.application.TwitterApplication;
import com.codepath.apps.twitterappclient.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

public class TimelineActivity extends AppCompatActivity {

    private TextView textViewTab1;
    private TextView textViewTab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new TweetsPageAdapter(this, getSupportFragmentManager()));

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setOnPageChangeListener(onPageChangeListener());

        LinearLayout view = (LinearLayout) pagerSlidingTabStrip.getChildAt(0);
        textViewTab1 = (TextView) view.getChildAt(0);
        textViewTab1.setTypeface(Typeface.DEFAULT_BOLD);
        textViewTab2 = (TextView) view.getChildAt(1);
        textViewTab2.setTypeface(Typeface.DEFAULT);

        getUserAuthenticated();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setLogo(R.drawable.ic_twitter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void onClickNewTweet(MenuItem item) {
        Intent intent = new Intent(this, TweetActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void onClickProfileView(MenuItem item) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void getUserAuthenticated() {
        TwitterApplication.getRestClient().getCredentials(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                User user = User.fromJson(response);
                user.save();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private ViewPager.OnPageChangeListener onPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textViewTab1.setTypeface(position == 0 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
                textViewTab2.setTypeface(position == 0 ? Typeface.DEFAULT : Typeface.DEFAULT_BOLD);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
    }
}
