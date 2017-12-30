package com.konex.chatbot.controller.chatBot;

import com.konex.chatbot.controller.chatBot.requestHandlers.RequestHandler;
import com.konex.chatbot.model.Response;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.utils.IOUtils;

import java.io.File;

public class Chatbot {
    Chat chatSession;
    RequestHandler rh = new RequestHandler();

    public Chatbot() {
        try {
            Bot bot = new Bot("super", getResourcesPath());
            bot.brain.nodeStats();
            bot.writeAIMLFiles();
            chatSession = new Chat(bot);
            MagicBooleans.trace_mode = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }

    public Response getResponse(String textLine) {
        String request = chatSession.multisentenceRespond(textLine);
        System.out.println(textLine + " > " + request);
        return rh.requestHandler(request);
    }
}
