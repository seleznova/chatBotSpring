package com.konex.chatbot.controller.chatBot.requestHandlers;

import com.konex.chatbot.enums.Commands;
import com.konex.chatbot.model.Response;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private GoodsHandler goodsHandler = new GoodsHandler();
    private StoreHandler storeHandler = new StoreHandler();

    public RequestHandler() {
    }

    public List<Response> requestHandler(String request) {
        if (request.contains(Commands.FINDGOODSBYNAME.getValue())) {
            return goodsHandler.getResponseByName(request.replace(Commands.FINDGOODSBYNAME.getValue(), ""));
        } else if (request.contains(Commands.FINDGOODSBYID.getValue())) {
            return goodsHandler.getResponseById(Long.valueOf(request.replace(Commands.FINDGOODSBYID.getValue(), "")));
        }
        else if (request.contains(Commands.FINDSTOREBYGOODSNAME.getValue())) {
            return storeHandler.getResponseByGoodsName(request.replace(Commands.FINDSTOREBYGOODSNAME.getValue(), ""));
        } else if (request.contains(Commands.FINDSTOREBYGOODSID.getValue())) {
            return storeHandler.getResponseByGoodsId(Long.valueOf(request.replace(Commands.FINDSTOREBYGOODSID.getValue(), "")));
        } else {
            List<Response> responseList = new ArrayList<>();
            responseList.add(new Response(request));
            return responseList;
        }
    }
}
