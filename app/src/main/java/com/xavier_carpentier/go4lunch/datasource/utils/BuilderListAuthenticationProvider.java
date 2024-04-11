package com.xavier_carpentier.go4lunch.datasource.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BuilderListAuthenticationProvider{
    public List<String> getList(Context context) {
        List<String> authenticationProviders = new ArrayList<>();
        try
        {
            InputStream jsonFile = context.getAssets().open("ListAuthenticationProviders.json");
            byte[] buffer = new byte[jsonFile.available()];
            //no need the result of jsonFile.read()
            //noinspection ResultOfMethodCallIgnored
            jsonFile.read(buffer);
            jsonFile.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
    
            // Parse the JSON array into Project objects
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
    
                authenticationProviders.add(jsonObject.getString("Provider"));
            }
        } catch(IOException |
        JSONException e)
        {
            Log.w("Error",e);
        }
            return authenticationProviders;
    }
}
