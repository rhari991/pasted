package me.rhari.pastoidlib.model;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root
@Convert(Format.FormatConverter.class)
public enum Format {

    CS_4("4cs", "4CS"),
    ACME_CROSS_ASSEMBLER_6502("6502acme", "6502 ACME Cross Assembler"),
    NONE("text", "None");

    private final String mShortName;
    private final String mLongName;

    Format(String shortName, String longName) {
        mShortName = shortName;
        mLongName = longName;
    }

    public static Format parse(String name) {
        if (name == null) {
            return null;
        }

        for (Format value : values()) {
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

    static class FormatConverter implements Converter<Format> {

        @Override
        public Format read(InputNode node) throws Exception {
            String shortName = node.getValue();
            return Format.parse(shortName);
        }

        @Override
        public void write(OutputNode node, Format value) throws Exception {
            String shortName = value.getShortName();
            node.setValue(shortName);
        }
    }
}
