package com.cbf4lige.common;

/**
 * Created by lg on 17-4-30.
 */
public class StrategyFactory {

    public static IDeduction getDeduction(StrategyMan strategyMan){
        IDeduction deduction = null;
        try {
            deduction = (IDeduction) Class.forName(strategyMan.getValue()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deduction;
    }
}
