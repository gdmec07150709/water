package com.shuidi168.earn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shuidi168.earn.domain.AgentUser;
import com.shuidi168.earn.exception.UserException;
import com.shuidi168.earn.service.IAgentUserService;
import com.shuidi168.earn.util.JsonResult;
import com.shuidi168.earn.vo.DeviceAndAgentUserVo;
import com.shuidi168.earn.vo.LoginVo;

@RestController
@RequestMapping(value = "Agent")
public class AgentUserController {

	@Autowired
	private IAgentUserService agentUserService;

	/**
	 * 用户注册
	 * 
	 * @param agentUser
	 * @return
	 */
	@PostMapping(value = "register")
	public JsonResult register(@RequestBody AgentUser agentUser) {
		try {
			agentUserService.register(agentUser);
		} catch (UserException e) {
			return new JsonResult(e.getMessage(), e.getErrCode());
		}
		return new JsonResult();
	}

	/**
	 * 使用手机号和密码登录
	 * 
	 * @param agentUser
	 * @return
	 */
	@GetMapping(value = "login")
	public JsonResult login(@RequestBody DeviceAndAgentUserVo deviceAndAgentUserVo) {
		try {
			 agentUserService.login(deviceAndAgentUserVo);
		} catch (UserException e) {
			return new JsonResult(e.getMessage(), e.getErrCode());
		}
		return new JsonResult();
	}

	/**
	 * 微信登录验证
	 * @param 手机号(可以为空)
	 */
	@GetMapping(value = "bindWeChat")
	public JsonResult wxLogin(@RequestBody LoginVo loginVo) {
		// 在这里模拟已经获取到了openid，通过openid查询是否有手机号，若无，就先绑定手机号
		try {
			agentUserService.PhoneBindWeChat(loginVo);
		} catch (UserException e) {
			return new JsonResult(e.getMessage(), e.getErrCode());
		}
		return new JsonResult();
	}

	/**
	 * 手机号绑定
	 * 
	 * @param 手机号
	 *            phone
	 * @param 微信
	 *            openid						
	 */
	@PostMapping(value = "weChatLogin")
	public JsonResult bindPhone(@RequestBody AgentUser agentUser) {
		// 调用业务
		try{
			agentUserService.weChatBindPhone(agentUser);			
		}catch(UserException e){
			return new JsonResult(e.getMessage(), e.getErrCode());
		}
		return new JsonResult();
	}
}
