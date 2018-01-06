package com.konex.chatbot.controller.chatBot.requestHandlers;


import com.konex.chatbot.enums.Commands;
import com.konex.chatbot.enums.Constants;
import com.konex.chatbot.model.Goods;
import com.konex.chatbot.model.Response;
import com.konex.chatbot.model.Store;
import com.konex.chatbot.service.GoodsService;
import com.konex.chatbot.service.StoresService;
import groovyjarjarantlr.CommonAST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreHandler {
    private StoresService storesService = new StoresService();
    private GoodsService goodsService = new GoodsService();
    private List<Goods> goodsList = new ArrayList<Goods>();
    private List<Store> storeList = new ArrayList<Store>();

    public List<Response> getResponseByGoodsName(String name) {
        List<Response> responseList = new ArrayList<>();
        String realName = "";

        goodsList = goodsService.selectByName(name);

        if (goodsList.size() == 0) {
            responseList.add(new Response(Constants.NO_PRODUCT));
            return responseList;
        } else if (goodsList.size() == 1)
            realName = goodsList.get(0).getName();
        else {
            responseList.add(GoodsHandler.isSeveralGoods(goodsList, Commands.FINDSTOREBYGOODSID.getValue()));
            return responseList;
        }

        storeList = storesService.selectByGoodsName(realName);

        switch (storeList.size()) {
            case 0:
                responseList.add(new Response(Constants.NO_PRODUCT));
                break;
            case 1:
                Store store = storeList.get(0);
                responseList.add(new Response(Constants.BUY_AT + store.getAddress() + " '" + store.getName() + "'."));
                break;
            default:
                String response = Constants.BUY_FOR_SEVERAL_ADRRESSES;
                for (Store s : storeList)
                    response += "\n\t - " + s.getAddress() + " в '" + s.getName() + "'";
                responseList.add(new Response("\n" + response + " " + Constants.CHOSE_FROM_LIST));
        }

        return responseList;
    }

    public List<Response> getResponseByGoodsId(Long id) {
        List<Response> responseList = new ArrayList<>();
        List<Store> storeList = storesService.selectByGoodsId(id);
        responseList.add(new Response(goodsService.selectById(id).get(0).getName(), true));

        switch (storeList.size()) {
            case 1:
                Store store = storeList.get(0);
                responseList.add(new Response(Constants.BUY_AT + store.getAddress() + " '" + store.getName() + "'."));
                break;
            default: {
                Response response = new Response();
                response.setMessage(Constants.BUY_FOR_SEVERAL_ADRRESSES + " " + Constants.CHOSE_FROM_LIST);
                response.setUser(false);
                response.setSelect(true);
                response.setTemplate(Commands.FINDGOODSBYIDANDSTOREID.getValue());

                Map<String, String> options = new HashMap<>();
                for (Store s : storeList)
                    options.put(s.getId() + "&" + id, s.getName() + ", " + s.getAddress());
                response.setOptions(options);

                responseList.add(response);
            }
        }

        return responseList;
    }

    public List<Response> getResponseByGoodsIdAndStoreId(String[] id) {
        List<Response> responseList = new ArrayList<>();
        List<Goods> goodsList = storesService.getResponseByGoodsIdAndStoreId(id[0], id[1]);
        responseList.add(new Response(storesService.selectById(Long.valueOf(id[1])).get(0).getName(), true));

        Goods goods = goodsList.get(0);
        responseList.add(new Response(goods.getName().toUpperCase() + " стоит " + goods.getPrice() + " руб."));

        return responseList;
    }

}
