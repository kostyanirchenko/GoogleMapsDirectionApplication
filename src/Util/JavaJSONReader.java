package Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Nirchenko Kostya for GoogleMapsDirectionApplication.
 * @since at 18.07.2017
 */
public class JavaJSONReader {

    private static String readAll(final Reader reader) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int currentPosition;

        while((currentPosition = reader.read()) != -1) {
            stringBuilder.append((char) currentPosition);
        }

        return stringBuilder.toString();
    }

    public static JSONObject read(final String url) throws IOException, JSONException {
        final InputStream inputStream = new URL(url).openStream();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            final String jsonText = readAll(bufferedReader);
            return new JSONObject(jsonText);
        } finally {
            inputStream.close();
        }
    }

}
