package com.codepath.apps.twitterappclient.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.codepath.apps.twitterappclient.R;
import com.codepath.apps.twitterappclient.fragments.UserTimelineFragment;
import com.codepath.apps.twitterappclient.models.User;
import com.codepath.apps.twitterappclient.ui.RoundedTransformation;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        User user = new Select().from(User.class).executeSingle();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setLogo(R.drawable.ic_twitter);
        }

        if (user != null) {
            populateProfileHeader(user);
        }

        String screenName = getIntent().getStringExtra("screen_name");

        if (savedInstanceState == null) {
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContainer, userTimelineFragment);
            fragmentTransaction.commit();
        }


    }

    private void populateProfileHeader(User user) {
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

        tvName.setText(user.getName());
        tvScreenName.setText(getString(R.string.screen_name, user.getScreenName()));
        tvTagline.setText(user.getDescription());
        tvFollowers.setText(String.valueOf(user.getFollowersCount()));
        tvFollowing.setText(String.valueOf(user.getFriendsCount()));

        Picasso.with(this)
                .load(user.getProfileImageUrlOriginal())
                .placeholder(R.drawable.ic_person_white_48dp)
                .resize(140, 140)
                .transform(new RoundedTransformation(10, 4))
                .into(ivProfileImage);
    }
}
