package com.konex.chatbot.controller.chatBot.requestHandlers;

import com.konex.chatbot.enums.Commands;
import com.konex.chatbot.enums.Constants;
import com.konex.chatbot.model.Goods;
import com.konex.chatbot.model.Response;
import com.konex.chatbot.service.GoodsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsHandler {
    private GoodsService goodsService = new GoodsService();
    private List<Goods> goodsList = new ArrayList<Goods>();

    public List<Response> getResponseByName(String name) {
        List<Response> responseList = new ArrayList<>();
        goodsList = goodsService.selectByName(name);
        responseList.add(getResponse());
        return responseList;
    }

    private Response getResponse() {
        switch (goodsList.size()) {
            case 0:
                return new Response(Constants.NO_PRODUCT);
            case 1:
                Goods goods = goodsList.get(0);
                return new Response(goods.getName().toUpperCase() + " стоит " + goods.getPrice() + " руб.");
            default:
                return isSeveralGoods(goodsList, Commands.FINDGOODSBYID.getValue());
        }
    }

    public static Response isSeveralGoods(List<Goods> goodsList, String template) {
        Response response = new Response();
        response.setMessage(Constants.SEVERAL_GOODS + " " + Constants.CHOSE_FROM_LIST);
        response.setUser(false);
        response.setSelect(true);
        response.setTemplate(template);

        Map<String, String> options = new HashMap<>();
        for (Goods g : goodsList)
            options.put(String.valueOf(g.getId()), g.getName());
        response.setOptions(options);

        return response;
    }

    public List<Response> getResponseById(Long id) {
        List<Response> responseList = new ArrayList<>();
        goodsList = goodsService.selectById(id);
        responseList.add(new Response(goodsList.get(0).getName(), true));
        responseList.add(getResponse());
        return responseList;
    }
}
