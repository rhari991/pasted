package me.rhari.pastoidlib;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;

import java.io.IOException;
import java.util.List;

import me.rhari.pastoidlib.model.Paste;
import okhttp3.ResponseBody;
import retrofit2.Converter;

class PasteListConverter implements Converter<ResponseBody, List<Paste>> {

    private final Serializer mSerializer;

    private PasteListConverter(Serializer xmlSerializer) {
        mSerializer = xmlSerializer;
    }

    static PasteListConverter newInstance(Serializer xmlSerializer) {
        return new PasteListConverter(xmlSerializer);
    }

    @Override
    public List<Paste> convert(ResponseBody value) throws IOException {
        String originalResponse = value.string();
        String correctedResponse = "<pastes>\r\n" + originalResponse + "\r\n</pastes>";
        try {
            PasteGroup pasteGroup = mSerializer.read(PasteGroup.class, correctedResponse);
            return pasteGroup.getPastes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Root(name = "pastes", strict = false)
    private static class PasteGroup {

        @ElementList(name = "pastes", inline = true, required = false)
        private List<Paste> mPastes;

        PasteGroup(@ElementList(name = "pastes", inline = true, required = false)
                           List<Paste> pastes) {
            mPastes = pastes;
        }

        List<Paste> getPastes() {
            return mPastes;
        }
    }
}
