package com.codepath.apps.twitterappclient.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Table(name = "Users")
public class User extends Model implements Parcelable {

    private int utcOffset;
    @Column(name = "FriendsCount")
    private int friendsCount;
    private String profileImageUrlHttps;
    private int listedCount;
    private String profileBackgroundImageUrl;
    private boolean defaultProfileImage;
    private int favouritesCount;
    @Column(name = "Description")
    private String description;
    private String createdAt;
    private boolean isTranslator;
    private String profileBackgroundImageUrlHttps;
    private boolean mProtected;
    @Column(name = "ScreenName")
    private String screenName;
    private String idStr;
    private String profileLinkColor;
    private boolean isTranslationEnabled;
    private int twitterUserId;
    private boolean geoEnabled;
    private String profileBackgroundColor;
    private String lang;
    private boolean hasExtendedProfile;
    private String profileSidebarBorderColor;
    private String profileTextColor;
    private boolean verified;
    @Column(name = "ProfileImageUrl")
    private String profileImageUrl;
    private String timeZone;
    private String url;
    private boolean contributorsEnabled;
    private boolean profileBackgroundTile;
    private String profileBannerUrl;
    private int statusesCount;
    private boolean followRequestSent;
    @Column(name = "FollowersCount")
    private int followersCount;
    private boolean profileUseBackgroundImage;
    private boolean defaultProfile;
    private boolean following;
    @Column(name = "Name")
    private String name;
    private String location;
    private String profileSidebarFillColor;
    private boolean notifications;

    public User() {
        super();
    }

//    public User(String screenName, String profileImageUrl, String name) {
//        this();
//        this.screenName = screenName;
//        this.profileImageUrl = profileImageUrl;
//        this.name = name;
//    }

    public static User fromJson(JSONObject jsonObject) {
        User user = new User();

        try {
            user.twitterUserId = jsonObject.getInt("id");
            user.idStr = jsonObject.getString("id_str");
            user.name = jsonObject.getString("name");
            user.screenName = jsonObject.getString("screen_name");
            user.location = jsonObject.getString("location");
            user.description = jsonObject.getString("description");
            user.url = jsonObject.getString("url");
            user.mProtected = jsonObject.getBoolean("protected");
            user.followersCount = jsonObject.getInt("followers_count");
            user.friendsCount = jsonObject.getInt("friends_count");
            user.listedCount = jsonObject.getInt("listed_count");
            user.createdAt = jsonObject.getString("created_at");
            user.favouritesCount = jsonObject.getInt("favourites_count");
            user.utcOffset = jsonObject.optInt("utc_offset");
            user.timeZone = jsonObject.getString("time_zone");
            user.geoEnabled = jsonObject.getBoolean("geo_enabled");
            user.verified = jsonObject.getBoolean("verified");
            user.statusesCount = jsonObject.getInt("statuses_count");
            user.lang = jsonObject.getString("lang");
            user.contributorsEnabled = jsonObject.getBoolean("contributors_enabled");
            user.isTranslator = jsonObject.getBoolean("is_translator");
            user.isTranslationEnabled = jsonObject.getBoolean("is_translation_enabled");
            user.profileBackgroundColor = jsonObject.getString("profile_background_color");
            user.profileBackgroundImageUrl = jsonObject.getString("profile_background_image_url");
            user.profileBackgroundImageUrlHttps = jsonObject.getString("profile_background_image_url_https");
            user.profileBackgroundTile = jsonObject.getBoolean("profile_background_tile");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            user.profileImageUrlHttps = jsonObject.getString("profile_image_url_https");
            user.profileBannerUrl = jsonObject.optString("profile_banner_url");
            user.profileLinkColor = jsonObject.getString("profile_link_color");
            user.profileSidebarBorderColor = jsonObject.getString("profile_sidebar_border_color");
            user.profileSidebarFillColor = jsonObject.getString("profile_sidebar_fill_color");
            user.profileTextColor = jsonObject.getString("profile_text_color");
            user.profileUseBackgroundImage = jsonObject.getBoolean("profile_use_background_image");
            user.hasExtendedProfile = jsonObject.getBoolean("has_extended_profile");
            user.defaultProfile = jsonObject.getBoolean("default_profile");
            user.defaultProfileImage = jsonObject.getBoolean("default_profile_image");
            user.following = jsonObject.getBoolean("following");
            user.followRequestSent = jsonObject.getBoolean("follow_request_sent");
            user.notifications = jsonObject.getBoolean("notifications");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public int getListedCount() {
        return listedCount;
    }

    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    public boolean isDefaultProfileImage() {
        return defaultProfileImage;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isTranslator() {
        return isTranslator;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return profileBackgroundImageUrlHttps;
    }

    public boolean ismProtected() {
        return mProtected;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getIdStr() {
        return idStr;
    }

    public String getProfileLinkColor() {
        return profileLinkColor;
    }

    public boolean isTranslationEnabled() {
        return isTranslationEnabled;
    }

    public int getTwitterUserId() {
        return twitterUserId;
    }

    public boolean isGeoEnabled() {
        return geoEnabled;
    }

    public String getProfileBackgroundColor() {
        return profileBackgroundColor;
    }

    public String getLang() {
        return lang;
    }

    public boolean isHasExtendedProfile() {
        return hasExtendedProfile;
    }

    public String getProfileSidebarBorderColor() {
        return profileSidebarBorderColor;
    }

    public String getProfileTextColor() {
        return profileTextColor;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getProfileImageUrlOriginal() {
        if (!TextUtils.isEmpty(profileImageUrl)) {
            return profileImageUrl.replace("_normal", "");
        }
        return profileImageUrl;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getUrl() {
        return url;
    }

    public boolean isContributorsEnabled() {
        return contributorsEnabled;
    }

    public boolean isProfileBackgroundTile() {
        return profileBackgroundTile;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public int getStatusesCount() {
        return statusesCount;
    }

    public boolean isFollowRequestSent() {
        return followRequestSent;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public boolean isProfileUseBackgroundImage() {
        return profileUseBackgroundImage;
    }

    public boolean isDefaultProfile() {
        return defaultProfile;
    }

    public boolean isFollowing() {
        return following;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getProfileSidebarFillColor() {
        return profileSidebarFillColor;
    }

    public boolean isNotifications() {
        return notifications;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.utcOffset);
        dest.writeInt(this.friendsCount);
        dest.writeString(this.profileImageUrlHttps);
        dest.writeInt(this.listedCount);
        dest.writeString(this.profileBackgroundImageUrl);
        dest.writeByte(defaultProfileImage ? (byte) 1 : (byte) 0);
        dest.writeInt(this.favouritesCount);
        dest.writeString(this.description);
        dest.writeString(this.createdAt);
        dest.writeByte(isTranslator ? (byte) 1 : (byte) 0);
        dest.writeString(this.profileBackgroundImageUrlHttps);
        dest.writeByte(mProtected ? (byte) 1 : (byte) 0);
        dest.writeString(this.screenName);
        dest.writeString(this.idStr);
        dest.writeString(this.profileLinkColor);
        dest.writeByte(isTranslationEnabled ? (byte) 1 : (byte) 0);
        dest.writeInt(this.twitterUserId);
        dest.writeByte(geoEnabled ? (byte) 1 : (byte) 0);
        dest.writeString(this.profileBackgroundColor);
        dest.writeString(this.lang);
        dest.writeByte(hasExtendedProfile ? (byte) 1 : (byte) 0);
        dest.writeString(this.profileSidebarBorderColor);
        dest.writeString(this.profileTextColor);
        dest.writeByte(verified ? (byte) 1 : (byte) 0);
        dest.writeString(this.profileImageUrl);
        dest.writeString(this.timeZone);
        dest.writeString(this.url);
        dest.writeByte(contributorsEnabled ? (byte) 1 : (byte) 0);
        dest.writeByte(profileBackgroundTile ? (byte) 1 : (byte) 0);
        dest.writeString(this.profileBannerUrl);
        dest.writeInt(this.statusesCount);
        dest.writeByte(followRequestSent ? (byte) 1 : (byte) 0);
        dest.writeInt(this.followersCount);
        dest.writeByte(profileUseBackgroundImage ? (byte) 1 : (byte) 0);
        dest.writeByte(defaultProfile ? (byte) 1 : (byte) 0);
        dest.writeByte(following ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeString(this.profileSidebarFillColor);
        dest.writeByte(notifications ? (byte) 1 : (byte) 0);
    }

    protected User(Parcel in) {
        this.utcOffset = in.readInt();
        this.friendsCount = in.readInt();
        this.profileImageUrlHttps = in.readString();
        this.listedCount = in.readInt();
        this.profileBackgroundImageUrl = in.readString();
        this.defaultProfileImage = in.readByte() != 0;
        this.favouritesCount = in.readInt();
        this.description = in.readString();
        this.createdAt = in.readString();
        this.isTranslator = in.readByte() != 0;
        this.profileBackgroundImageUrlHttps = in.readString();
        this.mProtected = in.readByte() != 0;
        this.screenName = in.readString();
        this.idStr = in.readString();
        this.profileLinkColor = in.readString();
        this.isTranslationEnabled = in.readByte() != 0;
        this.twitterUserId = in.readInt();
        this.geoEnabled = in.readByte() != 0;
        this.profileBackgroundColor = in.readString();
        this.lang = in.readString();
        this.hasExtendedProfile = in.readByte() != 0;
        this.profileSidebarBorderColor = in.readString();
        this.profileTextColor = in.readString();
        this.verified = in.readByte() != 0;
        this.profileImageUrl = in.readString();
        this.timeZone = in.readString();
        this.url = in.readString();
        this.contributorsEnabled = in.readByte() != 0;
        this.profileBackgroundTile = in.readByte() != 0;
        this.profileBannerUrl = in.readString();
        this.statusesCount = in.readInt();
        this.followRequestSent = in.readByte() != 0;
        this.followersCount = in.readInt();
        this.profileUseBackgroundImage = in.readByte() != 0;
        this.defaultProfile = in.readByte() != 0;
        this.following = in.readByte() != 0;
        this.name = in.readString();
        this.location = in.readString();
        this.profileSidebarFillColor = in.readString();
        this.notifications = in.readByte() != 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
