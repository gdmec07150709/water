package com.shuidi168.earn.service;

import com.shuidi168.earn.domain.AgentUser;
import com.shuidi168.earn.vo.DeviceAndAgentUserVo;
import com.shuidi168.earn.vo.LoginVo;
import com.shuidi168.earn.vo.TokenVo;

public interface IAgentUserService {

	/**
	 * 用户注册业务接口
	 */
	void register(AgentUser agentUser);

	LoginVo login(DeviceAndAgentUserVo deviceAndAgentUserVo);

	boolean PhoneBindWeChat(LoginVo loginVo);

	void weChatBindPhone(AgentUser agentUser);

	int validateToken(TokenVo tokenVo);

}
