package com.cbf4lige.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lg on 17-4-30.
 */
public class Clinet {
    public static void main(String [] args){
        Card card = initIC();
        System.out.println("begin ...");
        showCard(card);
        boolean flag = true;
        while(flag){
            Trade trade = createTrade();
            DeductionFacade.deduct(card,trade);
            System.out.println("\n====交易====");
            System.out.println(trade.getTradeNo() + "交易成功");
            System.out.println("本次发生的交易金额位" + trade.getAmount()/100.0 + "元");

            showCard(card);
            System.out.print("\n 是否退出");
            if(getInput().equalsIgnoreCase("y")){
                flag = false;
            }
        }
    }

    private static Trade createTrade() {
        Trade trade = new Trade();
        System.out.print("请输入交易编号：");
        trade.setTradeNo(getInput());
        return trade;
    }

    private static void showCard(Card card) {
        System.out.println("IC卡编号:"+ card.getCardNo());
        System.out.println("固定类型余额" + card.getSteadyMoney() / 100.0 + "元");
        System.out.println("自由类型余额" + card.getSteadyMoney() / 100.0 + "元");
    }

    private static Card initIC() {
        Card card = new Card();
        card.setCardNo("1100010001000");
        card.setFreeMoney(100000);
        card.setSteadyMoney(80000);
        return card;
    }

    public static String getInput() {
        String str = "";
        try {
            str = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
