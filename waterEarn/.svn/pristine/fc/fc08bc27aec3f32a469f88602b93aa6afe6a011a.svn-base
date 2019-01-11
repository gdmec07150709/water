package com.shuidi168.earn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shuidi168.earn.dao.IAnnounceDao;
import com.shuidi168.earn.domain.Announce;
import com.shuidi168.earn.service.IAnnounceService;

@Service
@Transactional
public class AnnounceServiceImpl implements IAnnounceService {
    @Autowired
    private IAnnounceDao announceDao;
    //通过倒叙查询指定条数的数据
    //公告需要显示的条数
    private final static int row = 3;
	
    public List<Announce> queryAnnounceById(){
    	List<Announce> list = announceDao.querylimit("queryAnnounceById", 0, row, null);
    	return list;
    }
}