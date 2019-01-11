package com.shuidi168.earn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shuidi168.earn.exception.UserException;
import com.shuidi168.earn.service.IAgentUserVerifyService;
import com.shuidi168.earn.service.IGeneralUserVerifyService;
import com.shuidi168.earn.util.JsonResult;
import com.shuidi168.earn.vo.VerifyVo;

/**
 * 手机短信验证码相关
 * 
 * @author chenyumin
 * @data 2018/12/28
 */
@RestController
@RequestMapping(value = "Verify")
public class VerifyController {

	@Autowired
	private IGeneralUserVerifyService generalUserVerifyService;
	@Autowired
	private IAgentUserVerifyService agentUserVerifyService;

	/**
	 * GeneralUser获取短信验证码
	 * 
	 * @param VerifyVo
	 *            包含以下参数的业务对象
	 * @param phone
	 *            手机号
	 * @param type
	 *            验证码类型 1注册 2绑定手机
	 * @return 返回state：1表示成功
	 */
	@GetMapping(value = "getGeneralUserVerifyCode")
	public JsonResult getGeneralUserVerifyCode(@RequestBody VerifyVo verifyVo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String smsCode = generalUserVerifyService.sendSmsGetVerify(verifyVo);
			map.put("smsCode", smsCode);
		} catch (UserException e) {
			return new JsonResult(e.getMessage(), e.getErrCode());
		}
		return new JsonResult(map);
	}

	@GetMapping(value = "getAgentUserVerifyCode")
	public JsonResult getAgentUserVerifyCode(@RequestBody VerifyVo verifyVo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String smsCode = agentUserVerifyService.sendSmsGetVerify(verifyVo);
			map.put("smsCode", smsCode);
		} catch (UserException e) {
			return new JsonResult(e.getMessage(), e.getErrCode());
		}
		return new JsonResult(map);
	}
}
