package com.example.commonmodule.gson;

import java.util.ArrayList;
import java.util.List;



public class TestMsgActivityItemList {

	private MainMsg mMainMsg = new MainMsg();

	public MainMsg getMainMsg() {
		if (this.mMainMsg == null)
			this.mMainMsg = new MainMsg();
		return this.mMainMsg;
	}

	public void setMainMsg(MainMsg mainMsg) {
		this.mMainMsg = mainMsg;
	}

	public static class MainMsg {

		private String SendTime = "";// mMsgSendTime
		private List<ActivityItem> MsgContent = new ArrayList<ActivityItem>();

		public String getMsgSendTime() {
			return this.SendTime;
		}

		public void setMsgSendTime(String msgSendTime) {
			this.SendTime = msgSendTime;
		}

		public void setMsgContent(List<ActivityItem> msgContent) {
			this.MsgContent = msgContent;
		}

		public List<ActivityItem> getMsgContent() {
			if (this.MsgContent == null)
				MsgContent = new ArrayList<ActivityItem>();
			return this.MsgContent;
		}

	}
}
