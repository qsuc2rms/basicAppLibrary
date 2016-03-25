package com.example.commonmodule.gson;



public class JM003ActivityItem {

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
		private ActivityItem MsgContent = new ActivityItem();

		public String getMsgSendTime() {
			return this.SendTime;
		}

		public void setMsgSendTime(String msgSendTime) {
			this.SendTime = msgSendTime;
		}

		public void setMsgContent(ActivityItem msgContent) {
			this.MsgContent = msgContent;
		}

		public ActivityItem getMsgContent() {
			if (this.MsgContent == null)
				MsgContent = new ActivityItem();
			return this.MsgContent;
		}

	}
}
