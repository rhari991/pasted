package me.rhari.pastoidlib.model;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root
@Convert(ExpiryDate.ExpiryDateConverter.class)
public enum ExpiryDate {

    NEVER("N", "Never"),
    TEN_MINUTES("10M", "Ten minutes"),
    ONE_HOUR("1H", "One hour"),
    ONE_DAY("1D", "One day"),
    ONE_WEEK("1W", "One week"),
    TWO_WEEKS("2W", "Two weeks"),
    ONE_MONTH("1M", "One month"),
    SIX_MONTHS("6M", "Six months"),
    ONE_YEAR("1Y", "One year");

    private final String mShortName;
    private final String mLongName;

    ExpiryDate(String shortName, String longName) {
        mShortName = shortName;
        mLongName = longName;
    }

    public static ExpiryDate parse(String name) {
        if (name == null) {
            return null;
        }

        for (ExpiryDate value : values()) {
            if (name.equalsIgnoreCase(value.getShortName()) ||
                    name.equalsIgnoreCase(value.getLongName())) {
                return value;
            }
        }
        return null;
    }

    public String getShortName() {
        return mShortName;
    }

    public String getLongName() {
        return mLongName;
    }

    @Override
    public String toString() {
        return getLongName();
    }

    static class ExpiryDateConverter implements Converter<ExpiryDate> {

        @Override
        public ExpiryDate read(InputNode node) throws Exception {
            String shortName = node.getValue();
            return ExpiryDate.parse(shortName);
        }

        @Override
        public void write(OutputNode node, ExpiryDate value) throws Exception {
            String shortName = value.getShortName();
            node.setValue(shortName);
        }
    }
}
