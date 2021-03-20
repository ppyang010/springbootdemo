package com.code.My;

import java.math.BigDecimal;

public class MY {

    public static void main(String[] args) {
        cal("489");
        cal2("489", "569");
    }


    /**
     * 计算最低出售价格
     *
     * @param costStr 购入价格 单位元
     */
    public static void cal(String costStr) {
        int cost = PriceUtil.yuan2Cent(costStr);
        //挂牌出售价格
        int sale = cost;
        //实际收益 / 净利润
        int profit = 0;
        while (profit <= 0) {
            sale += 100;
            //出售后实际到手
            int realSale = calRealsale(sale);
            profit = realSale - cost;
        }
        System.out.println("最低挂牌售价=" + PriceUtil.cent2Yuan(sale) + "元");

    }

    /**
     * 计算收益
     *
     * @param costStr 购入价格 单位分
     * @param saleStr 挂牌价格 单位分
     * @return
     */
    public static void cal2(String costStr, String saleStr) {
        int sale = PriceUtil.yuan2Cent(saleStr);
        int cost = PriceUtil.yuan2Cent(costStr);
        int realSale = calRealsale(sale);
        int profit = realSale - cost;
        System.out.println("购买价格为" + PriceUtil.cent2Yuan(cost) + "元,挂牌价格为"
                + PriceUtil.cent2Yuan(sale) + "元,收益为" + PriceUtil.cent2Yuan(profit) + "元");
    }


    /**
     * 计算出售后实际到手
     *
     * @param sale 挂牌价格 单位分
     * @return
     */
    public static int calRealsale(int sale) {
        //操作服务费
        //查验费 8 元
        //鉴别费 15元
        //包装服务费 10元
        int cost1 = 800 + 1500 + 1000;

        BigDecimal saleDec = new BigDecimal(sale);
        //技术服务费 5%
        //转账手续费 1%
        int cost2 = saleDec.multiply(new BigDecimal("0.06")).intValue();
        //快递费 15元假设
        int cost3 = 1500;
        return sale - cost1 - cost2 - cost3;
    }


}
