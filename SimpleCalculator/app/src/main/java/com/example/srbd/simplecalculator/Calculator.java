package com.example.srbd.simplecalculator;

/**
 * Created by srbd on 3/15/19.
 */

class Calculator {
    public enum Operator{ADD,SUB,DIV,MUL};

    public Double add(double firstOperand,double secondOperand){
        return firstOperand+secondOperand ;
    }

    public Double sub(double firstOperand,double secondOperand){
        return firstOperand-secondOperand ;
    }

    public Double div(double firstOperand,double secondOperand){
        return firstOperand/secondOperand ;
    }

    public Double mul(double firstOperand,double secondOperand){
        return firstOperand*secondOperand ;
    }
}
