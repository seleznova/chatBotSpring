package com.konex.chatbot.util;

import com.konex.chatbot.model.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    public static String getResponseJSON(Response response) {
        return getResponseJSON(response.getMessage(), response.isUser(), response.isSelect(), response.getOptions());
    }

    public static String getResponseJSON(String message, boolean isUser, boolean isSelect, Object options) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", message);
            jsonObject.put("isUser", isUser);
            jsonObject.put("isSelect", isSelect);
            jsonObject.put("options", options);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
