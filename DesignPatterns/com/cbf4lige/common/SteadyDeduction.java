package com.cbf4lige.common;

/**
 * Created by lg on 17-4-30.
 */
public class SteadyDeduction implements IDeduction {
    @Override
    public boolean exec(Card card, Trade trade) {
        int halfMoney = (int) Math.rint(trade.getAmount() / 2.0);

        card.setFreeMoney(card.getFreeMoney() - halfMoney);
        card.setSteadyMoney(card.getSteadyMoney() - halfMoney);
        return true;
    }
}
