package com.shuidi168.earn.vo;

import com.shuidi168.earn.domain.Devices;
import com.shuidi168.earn.domain.AgentUser;

public class DeviceAndAgentUserVo {

	private AgentUser agentUser;
	private Devices devices;

	public AgentUser getAgentUser() {
		return agentUser;
	}

	public void setAgentUser(AgentUser agentUser) {
		this.agentUser = agentUser;
	}

	public Devices getDevices() {
		return devices;
	}

	public void setDevices(Devices devices) {
		this.devices = devices;
	}

}
