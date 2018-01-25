package me.rhari.pastoidlib.model;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root
@Convert(AccountType.AccountTypeConverter.class)
public enum AccountType {

    NORMAL("Normal", 0),
    PRO("Pro", 1);

    private final String mName;
    private final int mCode;

    AccountType(String name, int code) {
        mName = name;
        mCode = code;
    }

    public static AccountType parse(int code) {
        for (AccountType value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public static AccountType parse(String name) {
        if (name == null) {
            return null;
        }

        for (AccountType value : values()) {
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

    static class AccountTypeConverter implements Converter<AccountType> {

        @Override
        public AccountType read(InputNode node) throws Exception {
            final int code = Integer.valueOf(node.getValue());
            return AccountType.parse(code);
        }

        @Override
        public void write(OutputNode node, AccountType value) throws Exception {
            String code = String.valueOf(value.getCode());
            node.setValue(code);
        }
    }
}
