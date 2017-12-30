package com.konex.chatbot.controller.chatBot.requestHandlers;

import com.konex.chatbot.model.Response;

public class RequestHandler {
    private GoodsHandler goodsHandler = new GoodsHandler();
    private StoreHandler storeHandler = new StoreHandler();

    public RequestHandler() {
    }

    public Response requestHandler(String request) {
        if (request.contains("findGoodsByName")) {
            return goodsHandler.getResponseByName(request.replace("findGoodsByName", ""));
//        } else if (request.contains("findStoreByGoodsName")) {
//            return storeHandler.getResponseByGoodsName(request.replace("findStoreByGoodsName", ""));
        } else {
            return new Response(request);
        }
    }
}
