package com.cbf4lige.common;

/**
 * Created by lg on 17-4-30.
 */
public class FreeDeduction implements IDeduction {
    @Override
    public boolean exec(Card card, Trade trade) {
        card.setFreeMoney(card.getFreeMoney() - trade.getAmount());
        return true;

    }
}
