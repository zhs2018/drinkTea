package com.wxsoft.drinkTea.framework.utils;

import java.math.BigDecimal;

public class MoneyUtils {

	/**
	 * 根据本次答对个数与已经答对的个数进行结算
	 * @param nowRight  本次答对多少道
	 * @param rightAll  一共答对多少道（不包括本次）
	 * @param money  本次获得的红包数
	 * @return
	 */
	public static float getMoneyByRight(int nowRight, int rightAll) {
		float money = 0.00f;
		if(rightAll == 12){ //已经答对了12道题目
                if(nowRight==0){
	           money=0;
                }else if(nowRight==1){
                	money=200;
                }
		}else if(rightAll==13){
			if(nowRight==0){
		           money=0;
	                }else if(nowRight==1){
	                	money=500;
	                }
		}else if(rightAll==14){
			if(nowRight==0){
		           money=0;
	                }else if(nowRight==1){
	                	money=1000;
	                }
		}else{
			if(nowRight==0){
		           money=0;
	                }else if(nowRight==1){
	                	money = 1;
	                }
		}
		return money;
	}

	/**
	 * 根据微信规定将钱转为分单位
	 * @param money String类型的钱数
	 * @return Integer
	 */
	public static Long getPriceByPenny(String money){
		if(money != null && !"".equals(money)){
			Double dMoney;
			try {
				dMoney = Double.valueOf(money);
				Long price = Math.round(dMoney * 100);
				return price;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return 0l;
			}
		}else{
			return 0l;
		}
	}

	public static Long getPriceByPenny(Double money){
		if(money != null && money!=0){
			try {
				Long price = Math.round(money * 100);
				return price;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return 0l;
			}
		}else{
			return 0l;
		}
	}
	/**
	 * double类型四舍五入保留2位
	 * @param money
	 * @return
	 */
	public static String doubleToString(Double money){
		BigDecimal   b   =   new   BigDecimal(money);
		return String.valueOf(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	public static void main(String[] args) {
		System.out.println(MoneyUtils.getMoneyByRight(20, 0));
	}
}
