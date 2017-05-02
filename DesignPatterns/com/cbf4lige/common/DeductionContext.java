package com.cbf4lige.common;

/**
 * Created by lg on 17-4-30.
 */
public class DeductionContext {
    private IDeduction deduction = null;

    public DeductionContext(IDeduction deduction) {
        this.deduction = deduction;
    }

    public boolean exec(Card card,Trade trade){
        return this.deduction.exec(card,trade);
    }
}
