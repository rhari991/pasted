package me.rhari.pastoidlib.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "user", strict = false)
public class User {

    @Element(name = "user_name", required = false)
    private final String mUsername;

    @Element(name = "user_format_short", required = false)
    private final Format mFormat;

    @Element(name = "user_expiration", required = false)
    private final ExpiryDate mExpirationDate;

    @Element(name = "user_avatar_url", required = false)
    private final String mAvatarUrl;

    @Element(name = "user_private", required = false)
    private final Visibility mVisibility;

    @Element(name = "user_website", required = false)
    private final String mWebsite;

    @Element(name = "user_email", required = false)
    private final String mEmail;

    @Element(name = "user_location", required = false)
    private final String mLocation;

    @Element(name = "user_account_type", required = false)
    private final AccountType mAccountType;

    User(@Element(name = "user_name", required = false) String username,
         @Element(name = "user_format_short", required = false) Format format,
         @Element(name = "user_expiration", required = false) ExpiryDate expirationDate,
         @Element(name = "user_avatar_url", required = false) String avatarUrl,
         @Element(name = "user_private", required = false) Visibility visibility,
         @Element(name = "user_website", required = false) String website,
         @Element(name = "user_email", required = false) String email,
         @Element(name = "user_location", required = false) String location,
         @Element(name = "user_account_type", required = false) AccountType accountType) {
        mUsername = username;
        mFormat = format;
        mExpirationDate = expirationDate;
        mAvatarUrl = avatarUrl;
        mVisibility = visibility;
        mWebsite = website;
        mEmail = email;
        mLocation = location;
        mAccountType = accountType;
    }

    public String getUsername() {
        return mUsername;
    }

    public Format getFormat() {
        return mFormat;
    }

    public ExpiryDate getExpirationDate() {
        return mExpirationDate;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public Visibility getVisibility() {
        return mVisibility;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getLocation() {
        return mLocation;
    }

    public AccountType getAccountType() {
        return mAccountType;
    }
}
