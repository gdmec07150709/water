package com.shuidi168.earn.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shuidi168.earn.domain.Advertise;
import com.shuidi168.earn.domain.Announce;
import com.shuidi168.earn.service.IAdvertiseService;
import com.shuidi168.earn.service.IAnnounceService;
import com.shuidi168.earn.service.ICountService;
import com.shuidi168.earn.service.IGeneralUserService;
import com.shuidi168.earn.service.IQRCodeService;
import com.shuidi168.earn.util.JsonResult;
import com.shuidi168.earn.util.QRCodeUtil;
import com.shuidi168.earn.vo.CountVo;
import com.shuidi168.earn.vo.HomeVo;

@RestController
@RequestMapping(value = "Home")
public class HomeController {
	@Autowired
	private IGeneralUserService generalUserService;
	@Autowired
	private IAdvertiseService advertiseService;
	@Autowired
	private ICountService countService;
	@Autowired
	private IAnnounceService announceService;
	@Autowired
	private IQRCodeService qrCodeService;
	//图片格式
	private static final String FORMAT_NAME = ".png";  
	//图片前缀
	private static final String QR_CODE_GENERAL = "qr_code_general_";  
	@GetMapping(value = "home")
	public JsonResult home(@RequestBody HomeVo homeVo,HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//通过token获取用户id
		int generalUserId = generalUserService.validateToken(homeVo.getTokenVo());
		//生成二维码需要的参数
		String logoImgPath = request.getSession().getServletContext().getRealPath("/images/mn.jpg");;
		String imgName = QR_CODE_GENERAL+generalUserId+FORMAT_NAME;
		String path=request.getSession().getServletContext().getRealPath("/images");
		path = path.replace("\\", "/");
		File file = new File(path,imgName);
		//获取广告
		List<Advertise> advertises = advertiseService.queryRows(homeVo.getType());
		//获取公告
		List<Announce> announces = announceService.queryAnnounceById();
		//今日收入
		CountVo countVo = countService.countTodayMoney(generalUserId);
		//生成二维码并保存
		qrCodeService.generalQRCode("http://www.baidu.com", logoImgPath, file);
		map.put("advertises", advertises);
		map.put("todayIncome", countVo);
		map.put("announces", announces);
		map.put("qr_url",path+"/"+imgName);
		map.put("userId", generalUserId);
		return new JsonResult(map);
	}
	
}
