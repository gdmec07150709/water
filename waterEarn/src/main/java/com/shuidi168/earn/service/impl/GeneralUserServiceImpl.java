package com.shuidi168.earn.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shuidi168.earn.dao.IDeviceDAO;
import com.shuidi168.earn.dao.IGeneralUserDAO;
import com.shuidi168.earn.dao.ILockTypeDAO;
import com.shuidi168.earn.domain.Devices;
import com.shuidi168.earn.domain.GeneralUser;
import com.shuidi168.earn.domain.LockType;
import com.shuidi168.earn.exception.UserException;
import com.shuidi168.earn.service.IGeneralUserService;
import com.shuidi168.earn.util.AssertUtil;
import com.shuidi168.earn.util.CreateRandomVcode;
import com.shuidi168.earn.util.GeneralInviteCodeUtil;
import com.shuidi168.earn.util.JwtToken;
import com.shuidi168.earn.util.LoginErrCode;
import com.shuidi168.earn.util.MD5;
import com.shuidi168.earn.util.RegisterErrCode;
import com.shuidi168.earn.util.Validate;
import com.shuidi168.earn.vo.DeviceAndGeneralUserVo;
import com.shuidi168.earn.vo.LoginVo;
import com.shuidi168.earn.vo.TokenVo;

@Service
@Transactional
public class GeneralUserServiceImpl implements IGeneralUserService {

	@Autowired
	private IGeneralUserDAO generalUserDao;
	@Autowired
	private IDeviceDAO deviceDao;
	@Autowired
	private ILockTypeDAO lockTypeDao;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	// 通过手机号查询对象
	private final static String queryGeneralUserByPhone = "queryGeneralUserByPhone";
	// 通过手机号查询对象id
	private final static String queryGeneralUserIdByInviteCode = "queryGeneralUserIdByInviteCode";
	// 查询是否有登录用户
	private final static String queryGeneralUserByPasswordAndPhone = "queryGeneralUserByPasswordAndPhone";
	// 查询是否通过微信绑定
	private final static String queryPhoneByOpenId = "queryPhoneByOpenId";
	// token存放的天数
	private final static long TOKEN_DAYS = 15;
	// 私有静态断言类对象
	private static AssertUtil instance = AssertUtil.instance();
	// 保存在Redis中的验证码key前缀
	private final static String REGISTER_CODE = "G_registerCode";
	// 保存在Redis中的邀请码key前缀
	private final static String BIND_CODE = "G_bindCode";
	// 保存在Redis中的Token前缀
	private final static String GeneralToken = "GeneralToken";

	/**
	 * 用户注册业务
	 * 
	 * @param 注册对象
	 */
	@Override
	public void register(GeneralUser generalUser) {
		// 手机号
		String phone = generalUser.getPhone();
		// 密码
		String password = generalUser.getPassword();
		// 验证码
		String smsCode = generalUser.getSmsCode();
		// 邀请码
		String inviteCode = generalUser.getInviteCode();
		// 参数的基本校验
		Validate.validatePhone(phone);
		Validate.validatePassword(password);
		Validate.validateSmsCode(smsCode);
		// 若邀请码不为空则需要进行参数校验
		if (inviteCode != null && inviteCode.trim().length() != 0) {
			Validate.validateInviteCode(inviteCode);
		}
		// 手机号数据库验证，查询数据库是否已经存在当前注册手机号
		GeneralUser queryObject = generalUserDao.executeQueryOne(queryGeneralUserByPhone, phone);
		// 如果queryObject为null，表示已经存在该手机号
		instance.isNull(queryObject, "该手机号已被注册", RegisterErrCode.REGISTERED_PHONE);
		// 从Redis中查询出发送短信的手机验证码
		String code = (String) redisTemplate.opsForValue().get(REGISTER_CODE + phone);
		// Redis中查询出验证码
		instance.isNotNull(code, "验证码已失效,请重新发送短信", RegisterErrCode.SMS_CODE_FAILURE).isEquals(code, smsCode, "验证码错误",
				RegisterErrCode.SMS_CODE_NULL, RegisterErrCode.SMS_CODE_ERROR);
		// 判断邀请码是否为null，空就直接生成邀请码保存，如有则查询邀请码所对应的id添加到用户的parentId中
		if (!StringUtils.isEmpty(inviteCode)) {
			// 查询数据库所对应的邀请码用户id
			GeneralUser generalUser2 = generalUserDao.executeQueryOne(queryGeneralUserIdByInviteCode, inviteCode);
			// 若查询不到数据
			instance.isNotNull(generalUser2, "邀请码错误", RegisterErrCode.INVITE_CODE_ERROR);
			// 查询到数据，将对应的id添加到用户的parentId中
			generalUser.setParentId(generalUser2.getId());
		}
		// 对密码进行MD5加密
		String Md5Password = MD5.encode(password + phone);
		generalUser.setPassword(Md5Password);
		// 设置注册的时间戳
		generalUser.setRegTimestamp(new Timestamp(new Date().getTime()));
		//先保存获取生成id
		generalUserDao.save(generalUser);
		// 通过id生成邀请码并更新
		generalUser.setInviteCode(GeneralInviteCodeUtil.toSerialCode(generalUser.getId()));
		generalUserDao.update(generalUser);

	}

	/**
	 * 用户登录业务
	 * 
	 * @param 用户信息对象
	 * @return JwtToken字符串
	 */
	@Override
	public LoginVo login(DeviceAndGeneralUserVo deviceAndGeneralUserVo) {
		String phone = null;
		String brand = null;
		String model = null;
		String password = null;
		String osVersion = null;
		// 登录用户基本信息
		GeneralUser generalUser = deviceAndGeneralUserVo.getGeneralUser();
		// 登录设备
		Devices devices = deviceAndGeneralUserVo.getDevices();
		if (generalUser != null && devices != null) {
			// 手机号
			phone = generalUser.getPhone();
			// 密码
			password = generalUser.getPassword();
			// 操作系统版本
			osVersion = devices.getOsVersion();
			// 手机品牌
			brand = devices.getBrand();
			// 手机型号
			model = devices.getModel();
		}
		// 参数的基本校验
		Validate.validatePhone(phone);
		Validate.validatePassword(password);
		// 先对password进行MD5加密
		String MD5Password = MD5.encode(password + phone);
		// 根据手机号和密码查询数据库是否存在该用户
		GeneralUser queryObject = generalUserDao.executeQueryOne(queryGeneralUserByPasswordAndPhone, phone,
				MD5Password);
		instance.isNotNull(queryObject, "没有该用户", RegisterErrCode.USER_NULL);
		// 创建接受返参的VO对象
		LoginVo loginVo = new LoginVo();
		// 有该用户，则判断用户是否绑定了微信，即判断是否有wx_open_id
		generalUser = queryObject;
		instance.isNotNull(generalUser.getWxOpenId(), "没有微信授权", RegisterErrCode.WX_OPENID_NULL);
		// 判断用户是否被锁定
		if (generalUser.getIsLock() == 1) {
			int lockTypeId = generalUser.getLockTypeId();
			LockType lockType = lockTypeDao.findById(lockTypeId);
			String reason = lockType.getReason();
			throw new UserException(reason, LoginErrCode.LOCK);
		}
		// 查看是否有token
		String token = (String) redisTemplate.opsForValue().get(GeneralToken + generalUser.getId());
		if (!StringUtils.isEmpty(token)) {
			// 保存设备信息
			deviceDao.save(devices);
			generalUser.setDevicesId(devices.getId());
			generalUserDao.update(generalUser);
			// 延长token时间
			redisTemplate.opsForValue().set(GeneralToken + generalUser.getId(), token, TOKEN_DAYS, TimeUnit.DAYS);
		} else {
			try {
				// 保存设备信息
				deviceDao.save(devices);
				// 为该用户生成JwtToken
				String jwtToken = JwtToken.createToken(generalUser.getId());
				// 将jwtToken缓存到redis
				redisTemplate.opsForValue().set(GeneralToken + generalUser.getId(), jwtToken, TOKEN_DAYS,
						TimeUnit.DAYS);
				// 保存到Mysql数据库中
				generalUser.setJwtToken(jwtToken);
				generalUser.setDevicesId(devices.getId());
				generalUserDao.update(generalUser);
				loginVo.setJwtToken(jwtToken);
				loginVo.setBindWeChat(true);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return loginVo;
	}

	/**
	 * 通过openId查询手机
	 * 
	 * @param openid
	 */
	@Override
	public boolean PhoneBindWeChat(LoginVo loginVo) {
		String wxOpenId = null;
		String phone = null;
		if (loginVo != null) {
			phone = loginVo.getPhone();
			wxOpenId = loginVo.getWxOpenId();
		}
		// 查询对于的openid相应的phone是否存在
		GeneralUser objByOpenId = generalUserDao.executeQueryOne(queryPhoneByOpenId, wxOpenId);
		instance.isNull(objByOpenId, "此微信已绑定过其他手机", LoginErrCode.WECHAT_HAS_BEEN_BINDED);
		// 手机号数据库验证，查询数据库是否已经存在当前注册手机号
		GeneralUser generalUser = generalUserDao.executeQueryOne(queryGeneralUserByPhone, phone);
		instance.isNull(generalUser.getWxOpenId() == null, "该手机号已经被绑定", LoginErrCode.PHONE_HAS_BEEN_BINDED);
		// 生成token
		try {
			// 为该用户生成JwtToken
			String jwtToken = JwtToken.createToken(generalUser.getId());
			// 将jwtToken缓存到redis
			redisTemplate.opsForValue().set(GeneralToken + generalUser.getId(), jwtToken, TOKEN_DAYS, TimeUnit.DAYS);
			// 隔一段时间将token同步保存到数据库
			generalUser.setJwtToken(jwtToken);
			generalUser.setWxOpenId(wxOpenId);
			generalUser.setNickname("沃登");
			generalUser.setCountry("中国");
			generalUser.setCity("惠州");
			generalUser.setProvince("广东省");
			generalUserDao.update(generalUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 微信登录并且绑定手机号
	 */
	@Override
	public void weChatBindPhone(GeneralUser generalUser) {
		// 手机号
		String phone = generalUser.getPhone();
		// 密码
		String password = generalUser.getPassword();
		// 微信openid
		String wxOpenId = generalUser.getWxOpenId();
		// 验证码smsCode
		String smsCode = generalUser.getSmsCode();
		// 基本参数判断
		Validate.validatePhone(phone);
		Validate.validatePassword(password);
		Validate.validateSmsCode(smsCode);
		// 从Redis中查询出发送短信的手机验证码
		String code = (String) redisTemplate.opsForValue().get(BIND_CODE + phone);
		// Redis中查询出验证码
		instance.isNotNull(code, "验证码已失效,请重新发送短信", RegisterErrCode.SMS_CODE_FAILURE).isEquals(code, smsCode, "验证码错误",
				RegisterErrCode.SMS_CODE_NULL, RegisterErrCode.SMS_CODE_ERROR);
		// 查询对于的openid相应的phone是否存在
		GeneralUser objByOpenId = generalUserDao.executeQueryOne(queryPhoneByOpenId, wxOpenId);
		instance.isNull(objByOpenId, "该微信号已经被绑定", LoginErrCode.WECHAT_HAS_BEEN_BINDED);
		// 手机号数据库验证，查询数据库是否已经存在当前注册手机号
		GeneralUser objByPhone = generalUserDao.executeQueryOne(queryGeneralUserByPhone, phone);
		if (objByPhone != null) {
			// 获取对应手机的openid
			String openId = objByPhone.getWxOpenId();
			instance.isNull(openId, "手机号已经被绑定", LoginErrCode.PHONE_HAS_BEEN_BINDED);
			// 若未注册，则将openid和phone和微信其他相关信息保存到数据库中
			// 设置openid
			objByPhone.setWxOpenId(wxOpenId);
			// 设置手机号
			objByPhone.setPhone(phone);
			// 先对password进行MD5加密
			String MD5Password = MD5.encode(password + phone);
			// 设置密码
			objByPhone.setPassword(MD5Password);
			// 通过微信获取微信数据并设置微信相关的数据
			objByPhone.setNickname("沃登");
			objByPhone.setCountry("中国");
			objByPhone.setCity("惠州");
			objByPhone.setProvince("广东省");
			generalUserDao.update(objByPhone);
		} else {
			throw new UserException("该手机号已经被绑定", LoginErrCode.PHONE_HAS_BEEN_BINDED);
		}

	}

	/**
	 * 通过token获取user的id;
	 */
	public int validateToken(TokenVo tokenVo) {
		String token = tokenVo.getToken();
		Integer userId = JwtToken.getAppUID(token);
		return userId;
	}

}
