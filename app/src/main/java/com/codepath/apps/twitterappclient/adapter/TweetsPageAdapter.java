package com.codepath.apps.twitterappclient.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codepath.apps.twitterappclient.R;
import com.codepath.apps.twitterappclient.fragments.HomeTimelineFragment;
import com.codepath.apps.twitterappclient.fragments.MentionsTimelineFragment;

public class TweetsPageAdapter extends FragmentPagerAdapter {

    private static final int TABLE_TITLES[] = new int[]{
            R.string.home_timeline,
            R.string.mentions_timeline
    };

    private Context mContext;

    public TweetsPageAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return TABLE_TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeTimelineFragment();
        } else if (position == 1) {
            return new MentionsTimelineFragment();
        } else {
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(TABLE_TITLES[position]);
    }
}
