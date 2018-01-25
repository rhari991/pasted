package me.rhari.pastoidlib.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "paste", strict = false)
public class Paste {

    @Element(name = "paste_key", required = false)
    private final String mKey;

    @Element(name = "paste_date", required = false)
    private final long mCreatedDate;

    @Element(name = "paste_title", required = false)
    private final String mTitle;

    @Element(name = "paste_size", required = false)
    private final long mSize;

    @Element(name = "paste_expire_date", required = false)
    private final long mExpiryDate;

    @Element(name = "paste_private", required = false)
    private final Visibility mVisibility;

    @Element(name = "paste_format_short", required = false)
    private final Format mFormat;

    @Element(name = "paste_url", required = false)
    private final String mUrl;

    @Element(name = "paste_hits", required = false)
    private final long mHits;

    Paste(@Element(name = "paste_key", required = false) String key,
          @Element(name = "paste_date", required = false) long createdDate,
          @Element(name = "paste_title", required = false) String title,
          @Element(name = "paste_size", required = false) long size,
          @Element(name = "paste_expire_date", required = false) long expiryDate,
          @Element(name = "paste_private", required = false) Visibility visibility,
          @Element(name = "paste_format_short", required = false) Format format,
          @Element(name = "paste_url", required = false) String url,
          @Element(name = "paste_hits", required = false) long hits) {
        mKey = key;
        mCreatedDate = createdDate;
        mTitle = title;
        mSize = size;
        mExpiryDate = expiryDate;
        mVisibility = visibility;
        mFormat = format;
        mUrl = url;
        mHits = hits;
    }

    public String getKey() {
        return mKey;
    }

    public long getCreatedDate() {
        return mCreatedDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public long getSize() {
        return mSize;
    }

    public long getExpiryDate() {
        return mExpiryDate;
    }

    public Visibility getVisibility() {
        return mVisibility;
    }

    public Format getFormat() {
        return mFormat;
    }

    public String getUrl() {
        return mUrl;
    }

    public long getHits() {
        return mHits;
    }
}
