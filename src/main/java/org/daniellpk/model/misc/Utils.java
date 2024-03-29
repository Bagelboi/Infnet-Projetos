package org.daniellpk.model.misc;

import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.daniellpk.Main;
import org.daniellpk.service.DbService;

import java.io.IOException;
import java.lang.reflect.Type;

public class Utils {
    public static String getFromApi(String path)  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "{}";
        }
    }

}
