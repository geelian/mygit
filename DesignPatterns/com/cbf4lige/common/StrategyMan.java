package com.cbf4lige.common;

/**
 * Created by lg on 17-4-30.
 */
public enum  StrategyMan {
    SteedyDeduction("com.cbf4lige.common.SteadyDeduction"),
    FreeDeduction("com.cbf4lige.common.FreeDeduction");

    String value = "";

    private StrategyMan(String _value){
        this.value = _value;
    }

    public String getValue(){
        return this.value;
    }

}
