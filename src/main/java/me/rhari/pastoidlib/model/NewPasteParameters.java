package me.rhari.pastoidlib.model;

public class NewPasteParameters {

    private final String mText;
    private final String mTitle;
    private final Format mFormat;
    private final Visibility mVisibility;
    private final ExpiryDate mExpiryDate;

    private NewPasteParameters(String text, String title, Format format, Visibility visibility,
                               ExpiryDate expiryDate) {
        mText = text;
        mTitle = title;
        mFormat = format;
        mVisibility = visibility;
        mExpiryDate = expiryDate;
    }

    public String getText() {
        return mText;
    }

    public String getTitle() {
        return mTitle;
    }

    public Format getFormat() {
        return mFormat;
    }

    public Visibility getVisibility() {
        return mVisibility;
    }

    public ExpiryDate getExpiryDate() {
        return mExpiryDate;
    }

    public static final class Builder {

        private String mText;
        private String mTitle;
        private Format mFormat;
        private Visibility mVisibility;
        private ExpiryDate mExpiryDate;

        public Builder(String text) {
            if (text == null || text.trim().isEmpty()) {
                throw new IllegalArgumentException("Paste text cannot be null or empty");
            }
            mText = text;
        }

        public Builder title(String title) {
            mTitle = title;
            return this;
        }

        public Builder format(Format format) {
            mFormat = format;
            return this;
        }

        public Builder visibility(Visibility visibility) {
            mVisibility = visibility;
            return this;
        }

        public Builder expiryDate(ExpiryDate expiryDate) {
            mExpiryDate = expiryDate;
            return this;
        }

        public NewPasteParameters build() {
            return new NewPasteParameters(mText, mTitle, mFormat, mVisibility, mExpiryDate);
        }
    }
}
