package com.shuidi168.earn.service;

import com.shuidi168.earn.domain.GeneralUser;
import com.shuidi168.earn.vo.DeviceAndGeneralUserVo;
import com.shuidi168.earn.vo.LoginVo;
import com.shuidi168.earn.vo.TokenVo;

public interface IGeneralUserService {

	/**
	 * 用户注册业务接口
	 */
	void register(GeneralUser generalUser);

	LoginVo login(DeviceAndGeneralUserVo deviceAndGeneralUserVo);

	boolean PhoneBindWeChat(LoginVo loginVo);

	void weChatBindPhone(GeneralUser generalUser);

	int validateToken(TokenVo tokenVo);

}
