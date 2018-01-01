package com.konex.chatbot.util;

import com.konex.chatbot.enums.Commands;
import com.konex.chatbot.model.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Util {
    public static String getResponseJSON(Response response) {
        return getResponseJSON(response.getMessage(), response.isUser(), response.isSelect(), response.getOptions(), response.getTemplate());
    }

    public static String getResponseJSON(String message, boolean isUser, boolean isSelect, Object options, String template) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", message);
            jsonObject.put("isUser", isUser);
            jsonObject.put("isSelect", isSelect);
            jsonObject.put("template", template);
            jsonObject.put("options", options);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public static boolean isCommand(String message){
        return Arrays.stream(Commands.values())
                .anyMatch(d -> message.contains(d.getValue()));
    }

    public static String replaceMark(String message){
        return message.replaceAll("\\?", " ");
    }
}
