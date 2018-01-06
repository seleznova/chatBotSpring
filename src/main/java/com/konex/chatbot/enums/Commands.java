package com.konex.chatbot.enums;

public enum Commands {
    FINDGOODSBYNAME("findGoodsByName"),
    FINDGOODSBYID("findGoodsById"),
    FINDSTOREBYGOODSID("findStoreByGoodsId"),
    FINDSTOREBYGOODSNAME("findStoreByGoodsName"),
    FINDGOODSBYIDANDSTOREID("findGoodsByStoreIdAndId");

    private String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
