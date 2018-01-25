package me.rhari.pastoidlib;

import java.io.IOException;

import me.rhari.pastoidlib.model.NewPasteParameters;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Converter;

class NewPasteParametersConverter implements Converter<NewPasteParameters, RequestBody> {

    private static NewPasteParametersConverter INSTANCE;

    private NewPasteParametersConverter() {
    }

    static NewPasteParametersConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewPasteParametersConverter();
        }
        return INSTANCE;
    }

    @Override
    public RequestBody convert(NewPasteParameters value) throws IOException {
        FormBody.Builder requestBodyBuilder = new FormBody.Builder()
                .add(Constants.Fields.API_PASTE_CODE, value.getText());
        if (value.getTitle() != null) {
            requestBodyBuilder.add(Constants.Fields.API_PASTE_NAME, value.getTitle());
        }
        if (value.getFormat() != null) {
            requestBodyBuilder.add(Constants.Fields.API_PASTE_FORMAT,
                    value.getFormat().getShortName());
        }
        if (value.getVisibility() != null) {
            requestBodyBuilder.add(Constants.Fields.API_PASTE_PRIVATE,
                    String.valueOf(value.getVisibility().getCode()));
        }
        if (value.getExpiryDate() != null) {
            requestBodyBuilder.add(Constants.Fields.API_PASTE_EXPIRE_DATE,
                    value.getExpiryDate().getShortName());
        }
        return requestBodyBuilder.build();
    }
}
