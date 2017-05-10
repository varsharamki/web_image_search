package smule.web.search.image.utils;


import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class ImageProcessors {


    public static byte[] getImageToByteArray(boolean compress, Bitmap bMap, int byteSize) {
        // based on the bMap size chooce to either compress of or not
        // byte size is availabe form teh JSON resposnse
        try {
            if (compress) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bMap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                return stream.toByteArray();
            } else {
                int bytes = byteSize;
                ByteBuffer buffer = ByteBuffer.allocate(bytes);
                bMap.copyPixelsToBuffer(buffer);
                return buffer.array();
            }
        } catch (Exception e) {

        }
        return null;
    }


}
