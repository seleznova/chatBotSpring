package com.konex.chatbot.controller.chatBot.requestHandlers;



import com.konex.chatbot.model.Goods;
import com.konex.chatbot.model.Store;
import com.konex.chatbot.service.GoodsService;
import com.konex.chatbot.service.StoresService;

import java.util.ArrayList;
import java.util.List;

public class StoreHandler {
    private StoresService storesService = new StoresService();
    private GoodsService goodsService = new GoodsService();
    private List<Goods> goodsList = new ArrayList<Goods>();
    private List<Store> storeList = new ArrayList<Store>();

    public String getResponseByGoodsName(String name) {
        String realName = "";

        goodsList = goodsService.selectByName(name);

        if (goodsList.size() == 0)
            return "Данный препарат отсутствует в нашей сети";
        else if (goodsList.size() == 1)
            realName = goodsList.get(0).getName();
        else {
            String response = "У нас нашлось несколько препаратов, которые содержат это имя:";
            for (Goods g : goodsList)
                response += "\n\t - " + g.getName();
            response += "\n Какой именно препарат Вы имели в виду?";
            return response;
        }

        storesService.selectByGoods(realName);

        switch (storeList.size()) {
            case 0:
                return name + " отсутствует в нашей сети";
            case 1:
                Store store = storeList.get(0);
                return "Можно купить по адресу " + store.getAddress() + " '" + store.getName() + "'.";
            default:
                String response = "Можно купить по нескольким адресам:";
                for (Store s : storeList)
                    response += "\n\t - " + s.getAddress() + " в '" + s.getName() + "'";
                return response;
        }
    }
}
