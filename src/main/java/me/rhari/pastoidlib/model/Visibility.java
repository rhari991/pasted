package me.rhari.pastoidlib.model;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root
@Convert(Visibility.VisibilityConverter.class)
public enum Visibility {

    PUBLIC("Public", 0),
    UNLISTED("Unlisted", 1),
    PRIVATE("Private", 2);

    private final String mName;
    private final int mCode;

    Visibility(String name, int code) {
        mName = name;
        mCode = code;
    }

    public static Visibility parse(int code) {
        for (Visibility value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public static Visibility parse(String name) {
        if (name == null) {
            return null;
        }

        for (Visibility value : values()) {
            if (name.equalsIgnoreCase(value.getName()) ||
                    name.equalsIgnoreCase(String.valueOf(value.getCode()))) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return mName;
    }

    public int getCode() {
        return mCode;
    }

    @Override
    public String toString() {
        return getName();
    }

    static class VisibilityConverter implements Converter<Visibility> {

        @Override
        public Visibility read(InputNode node) throws Exception {
            final int code = Integer.valueOf(node.getValue());
            return Visibility.parse(code);
        }

        @Override
        public void write(OutputNode node, Visibility value) throws Exception {
            String code = String.valueOf(value.getCode());
            node.setValue(code);
        }
    }
}
