package com.konex.chatbot.controller.chatBot.requestHandlers;

import com.konex.chatbot.model.Goods;
import com.konex.chatbot.model.Response;
import com.konex.chatbot.service.GoodsService;

import java.util.ArrayList;
import java.util.List;

public class GoodsHandler {
    private GoodsService goodsService = new GoodsService();
    private List<Goods> goodsList = new ArrayList<Goods>();

    public Response getResponseByName(String name) {
        goodsList = goodsService.selectByName(name);
        return getResponse();
    }

    private Response getResponse() {
        switch (goodsList.size()) {
            case 0:
                return new Response("Данный препарат отсутствует в нашей сети");
            case 1:
                Goods goods = goodsList.get(0);
                return new Response(goods.getName().toUpperCase() + " стоит " + goods.getPrice() + " руб.");
            default:
                Response response = new Response();
                response.setMessage("У нас нашлось несколько препаратов, которые содержат это название. " +
                        "Какой именно препарат Вы имели в виду?");
                response.setUser(false);
                response.setSelect(true);

                List<String> options = new ArrayList<>();
                for (Goods g : goodsList)
                    options.add(g.getName());
                response.setOptions(options);

                return response;
        }
    }
}
