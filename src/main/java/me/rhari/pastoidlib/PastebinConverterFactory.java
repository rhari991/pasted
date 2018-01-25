package me.rhari.pastoidlib;


import org.simpleframework.xml.Serializer;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import me.rhari.pastoidlib.model.NewPasteParameters;
import me.rhari.pastoidlib.model.Paste;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


class PastebinConverterFactory extends Converter.Factory {

    private PasteListConverter mPasteListConverter;

    static PastebinConverterFactory create(Serializer serializer) {
        return new PastebinConverterFactory(serializer);
    }

    private PastebinConverterFactory(Serializer serializer) {
        mPasteListConverter = PasteListConverter.newInstance(serializer);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[]
            annotations, Retrofit retrofit) {
        if (type instanceof ParameterizedType) {
            Type innerType = getParameterUpperBound(0, (ParameterizedType) type);
            if (getRawType(type) == List.class && innerType == Paste.class) {
                return mPasteListConverter;
            }
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[]
            parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (getRawType(type) == NewPasteParameters.class) {
            return NewPasteParametersConverter.getInstance();
        }
        return null;
    }
}
