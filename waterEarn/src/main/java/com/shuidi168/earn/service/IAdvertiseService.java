package com.shuidi168.earn.service;

import java.util.List;

import com.shuidi168.earn.domain.Advertise;

public interface IAdvertiseService {

	List<Advertise> queryAdvertise();

	List<Advertise> queryRows(int type);

}
