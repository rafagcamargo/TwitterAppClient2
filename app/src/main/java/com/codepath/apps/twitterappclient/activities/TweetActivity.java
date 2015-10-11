package com.codepath.apps.twitterappclient.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.codepath.apps.twitterappclient.R;
import com.codepath.apps.twitterappclient.application.TwitterApplication;
import com.codepath.apps.twitterappclient.models.User;
import com.codepath.apps.twitterappclient.restclient.TwitterClient;
import com.codepath.apps.twitterappclient.ui.RoundedTransformation;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;

public class TweetActivity extends AppCompatActivity {

    private static int MAX_COUNT = 140;

    private TwitterClient twitterClient;

    private EditText etTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setLogo(R.drawable.ic_twitter);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        ImageView ivUser = (ImageView) findViewById(R.id.ivUser);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        final TextView tvCount = (TextView) findViewById(R.id.tvCount);

        etTweet = (EditText) findViewById(R.id.etTweet);
        etTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //no-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //no-op
            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = MAX_COUNT - s.length();
                tvCount.setText(String.valueOf(count));
            }
        });

        User user = new Select().from(User.class).executeSingle();

        if (user != null) {
            Picasso.with(this)
                    .load(user.getProfileImageUrlOriginal())
                    .placeholder(R.drawable.ic_person_white_48dp)
                    .resize(140, 140)
                    .transform(new RoundedTransformation(5, 0))
                    .into(ivUser);
            tvName.setText(user.getName());
            tvScreenName.setText(getString(R.string.screen_name, user.getScreenName()));
        }

        twitterClient = TwitterApplication.getRestClient(); // singleton
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tweet, menu);
        return true;
    }

    public void onClickPostTweet(MenuItem item) {
        //Hide keyboard
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        //Show progress
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while posting...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();

        //Call API
        twitterClient.postStatus(etTweet.getText().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                dialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Toast.makeText(TweetActivity.this, "Error tweeting", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
