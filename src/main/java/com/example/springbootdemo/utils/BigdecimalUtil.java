package com.example.springbootdemo.utils;

import java.math.BigDecimal;

/**
 * @author qin
 * @description
 * @date 2020/3/9
 */
public class BigdecimalUtil {
    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal("1000");
        BigDecimal survAmount = new BigDecimal("1000");
        BigDecimal incrsAmount = new BigDecimal("1000");
        BigDecimal buyLoAmount = new BigDecimal("1000");
        BigDecimal currAmount = new BigDecimal("1900");

        if (amount.compareTo(survAmount) == -1) {
            System.out.println("转让金额小于最低转让金额");
            return;
        }
//        if(amount.divideToIntegralValue(mxShareTransferDetailRes.getIncrsAmount()))
        BigDecimal number = amount.divide(incrsAmount,2,BigDecimal.ROUND_HALF_DOWN); //倍数
        System.out.println(number.intValue());
        //判断是否能整除
        if (new BigDecimal(number.intValue()).compareTo(number) != 0) {
            System.out.println("转让金额不符合递增规则！");
            return;
        }
        if (currAmount.subtract(amount).compareTo(buyLoAmount) == -1) {
            System.out.println("剩余持有份额小于该笔资产的起存金额");
            return;
        }
    }
}
