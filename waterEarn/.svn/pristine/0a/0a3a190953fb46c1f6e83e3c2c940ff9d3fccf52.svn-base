package com.shuidi168.earn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shuidi168.earn.dao.IAdvertiseDao;
import com.shuidi168.earn.domain.Advertise;
import com.shuidi168.earn.service.IAdvertiseService;

@Service
@Transactional
public class AdvertiseServiceImpl implements IAdvertiseService {
    @Autowired
    private IAdvertiseDao advertiseDao;
    //广告需要显示的条数
    private final static int row = 3;
	@Override
	public List<Advertise> queryAdvertise() {
		// TODO Auto-generated method stub
		return advertiseDao.findAll();
	}
	@Override
	public List<Advertise> queryRows(int type) {
		// TODO Auto-generated method stub
		return advertiseDao.querylimit("queryRows", 0, row, type);
	}
	
}
