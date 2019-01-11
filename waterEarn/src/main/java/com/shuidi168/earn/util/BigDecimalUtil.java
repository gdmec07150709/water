package com.shuidi168.earn.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

	public static BigDecimal bigDecimalEqualZero(BigDecimal bigDecimal){
		if(bigDecimal==null){
			bigDecimal = BigDecimal.ZERO;
		}
		return bigDecimal;
	}
}
