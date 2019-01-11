package com.shuidi168.earn.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shuidi168.earn.dao.IReceiveFixPackageDAO;
import com.shuidi168.earn.dao.IReceivePackageDAO;
import com.shuidi168.earn.dao.IReceiveTaskDAO;
import com.shuidi168.earn.service.ICountService;
import com.shuidi168.earn.util.BigDecimalUtil;
import com.shuidi168.earn.util.DateUtil;
import com.shuidi168.earn.vo.CountVo;

/**
 * 
 * 统计主页中的今日收入
 * 
 * @author chenyumin
 *
 */
@Service
@Transactional
public class CountServiceImpl implements ICountService {
	@Autowired
	private IReceiveTaskDAO receiveTaskDAO;
	@Autowired
	private IReceiveFixPackageDAO fixPackageDAO;
	@Autowired
	private IReceivePackageDAO packageDAO;
	@Autowired
	private  RedisTemplate<String, Object> redisTemplate;
	// 通过id查询当天任务金额
	private final static String queryTaskByGeneralUserId = "queryTaskByGeneralUserId";
	// 通过id查询当天定时红包金额
	private final static String queryFixPackageByGeneralUserId = "queryFixPackageByGeneralUserId";
	// 通过id查询当天地图红包金额
	private final static String queryPackageByGeneralUserId = "queryPackageByGeneralUserId";
	// 保存在Redis中的今日任务金额key前缀
	private final static String TASK_MONEY = "G_Task_Money_Day";
	// 保存在Redis中的今日下线推广金额key前缀
	private final static String LOWERLEVEL_MONEY = "G_LowerLevel_Money_Day";
	// 保存在Redis中的今日红包金额key前缀
	private final static String PACKAGE_MONEY = "G_Package_Money_Day";
	// 保存在Redis中的今日总金额key前缀
	private final static String TOTAL_MONEY = "G_Total_Money_Day";
	// 今日收入存放的分钟
	private final static long COUNT_MINUTES = 5;

	@SuppressWarnings("unused")
	@Override
	public CountVo countTodayMoney(int generalUserId) {
		// 创建接受今日收入的Vo对象
		CountVo countVo = new CountVo();
		// 先查询Redis中的数据
		BigDecimal taskMoney = getBigDecimalToRedis(TASK_MONEY + generalUserId);
		BigDecimal lowerLevelMoney = getBigDecimalToRedis(LOWERLEVEL_MONEY + generalUserId);
		BigDecimal packageMoney = getBigDecimalToRedis(PACKAGE_MONEY + generalUserId);
		BigDecimal totalMoney = getBigDecimalToRedis(TOTAL_MONEY + generalUserId);
		if (totalMoney != null) {
			countVo.setTaskMoney(taskMoney);
			countVo.setLowerLevelMoney(lowerLevelMoney);
			countVo.setPackageMoney(packageMoney);
			countVo.setTotalMoney(totalMoney);
			return countVo;
		}

		// 获取当天凌晨的时间戳
		Timestamp startTimestamp = DateUtil.getStartTimestamp();
		// 无，则需要查询数据库
		BigDecimal countTaskMoney = BigDecimalUtil.bigDecimalEqualZero(
				receiveTaskDAO.queryAccount(queryTaskByGeneralUserId, startTimestamp, generalUserId));
		BigDecimal countFixPackageMoney = BigDecimalUtil.bigDecimalEqualZero(
				fixPackageDAO.queryAccount(queryFixPackageByGeneralUserId, startTimestamp, generalUserId));
		BigDecimal countLowerLevelMoney = BigDecimal.ZERO;
		BigDecimal countPackageMoney = BigDecimalUtil.bigDecimalEqualZero(
				packageDAO.queryAccount(queryPackageByGeneralUserId, startTimestamp, generalUserId));
		BigDecimal totalPackageMoney = BigDecimal.ZERO;
		BigDecimal countTotalMoney = BigDecimal.ZERO;
		totalPackageMoney = countFixPackageMoney.add(countPackageMoney);
		countTotalMoney = countFixPackageMoney.add(countPackageMoney).add(countTaskMoney);
		countVo.setTotalMoney(countTotalMoney);
		countVo.setLowerLevelMoney(countLowerLevelMoney);
		countVo.setTaskMoney(countTaskMoney);
		countVo.setPackageMoney(totalPackageMoney);
		// 将数据缓存到Redis中保存

		redisTemplate.opsForValue().set(TASK_MONEY + generalUserId, countTaskMoney.toPlainString(), COUNT_MINUTES,
				TimeUnit.MINUTES);
		redisTemplate.opsForValue().set(TOTAL_MONEY + generalUserId, countTotalMoney.toPlainString(), COUNT_MINUTES,
				TimeUnit.MINUTES);
		redisTemplate.opsForValue().set(LOWERLEVEL_MONEY + generalUserId, countLowerLevelMoney.toPlainString(),
				COUNT_MINUTES, TimeUnit.MINUTES);
		redisTemplate.opsForValue().set(PACKAGE_MONEY + generalUserId, totalPackageMoney.toPlainString(), COUNT_MINUTES,
				TimeUnit.MINUTES);

		return countVo;
	}

	public  BigDecimal getBigDecimalToRedis(String key) {
		BigDecimal bigDecimal = null;
		if (redisTemplate.opsForValue().get(key) != null) {
			bigDecimal = new BigDecimal(redisTemplate.opsForValue().get(key).toString());
		}
		return bigDecimal;
	}
}
