package com.shuidi168.earn.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shuidi168.earn.dao.IGeneralUserDAO;
import com.shuidi168.earn.domain.Devices;
import com.shuidi168.earn.domain.GeneralUser;
import com.shuidi168.earn.exception.UserException;
import com.shuidi168.earn.service.IDevicesService;
import com.shuidi168.earn.service.IGeneralUserVerifyService;
import com.shuidi168.earn.util.AssertUtil;
import com.shuidi168.earn.util.CreateRandomVcode;
import com.shuidi168.earn.util.JsonResult;
import com.shuidi168.earn.util.RegisterConstants;
import com.shuidi168.earn.util.RegisterErrCode;
import com.shuidi168.earn.util.Validate;
import com.shuidi168.earn.vo.VerifyVo;

@Service
@Transactional
public class GeneralUserVerifyServiceImpl implements IGeneralUserVerifyService {

	@Autowired
	private IGeneralUserDAO generalUserDao;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	// 通过手机号查询对象
	private final static String queryGeneralUserByPhone = "queryGeneralUserByPhone";
	// token存放的时间(分)
	private final static long TOKEN_MINUTES = 15;
	// 私有静态断言类对象
	private static AssertUtil instance = AssertUtil.instance();
	// 保存在Redis中的验证码key前缀
	private final static String REGISTER_CODE = "G_registerCode";
	// 保存在Redis中的邀请码key前缀
	private final static String BIND_CODE = "G_bindCode";

	public String sendSmsGetVerify(VerifyVo verifyVo) {
		String phone = verifyVo.getPhone();
		int type = verifyVo.getType();
		// 手机号基本校验
		Validate.validatePhone(phone);
		// 手机号数据库验证，查询数据库是否已经存在当前注册手机号
		List<GeneralUser> list = generalUserDao.executeQuery(queryGeneralUserByPhone, phone);
		if(!list.isEmpty()){
			throw new UserException("手机号已经被注册", RegisterErrCode.REGISTERED_PHONE);
		}
		// 模拟手机短信生成随机验证码
		String smsCode = CreateRandomVcode.createRandomVcode();
		if (type == 1) {
			redisTemplate.opsForValue().set(REGISTER_CODE + phone, smsCode, TOKEN_MINUTES, TimeUnit.MINUTES);
		} else {
			redisTemplate.opsForValue().set(BIND_CODE + phone, smsCode, TOKEN_MINUTES, TimeUnit.MINUTES);
		}
		return smsCode;
	}

}
