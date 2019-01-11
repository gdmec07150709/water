package com.shuidi168.earn.service;

import com.shuidi168.earn.domain.Devices;
import com.shuidi168.earn.domain.GeneralUser;
import com.shuidi168.earn.vo.DeviceAndGeneralUserVo;
import com.shuidi168.earn.vo.LoginVo;

public interface IDevicesService {

	/**
	 * 设备信息保存
	 */
	void save(Devices devices);


}
