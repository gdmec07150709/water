package com.shuidi168.earn.vo;

import java.math.BigDecimal;

public class CountVo {

	private BigDecimal TaskMoney;
	private BigDecimal TotalMoney;
	private BigDecimal LowerLevelMoney;
	private BigDecimal PackageMoney;

	public BigDecimal getTaskMoney() {
		return TaskMoney;
	}

	public void setTaskMoney(BigDecimal taskMoney) {
		TaskMoney = taskMoney;
	}

	public BigDecimal getTotalMoney() {
		return TotalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		TotalMoney = totalMoney;
	}

	public BigDecimal getLowerLevelMoney() {
		return LowerLevelMoney;
	}

	public void setLowerLevelMoney(BigDecimal lowerLevelMoney) {
		LowerLevelMoney = lowerLevelMoney;
	}

	public BigDecimal getPackageMoney() {
		return PackageMoney;
	}

	public void setPackageMoney(BigDecimal packageMoney) {
		PackageMoney = packageMoney;
	}

}
