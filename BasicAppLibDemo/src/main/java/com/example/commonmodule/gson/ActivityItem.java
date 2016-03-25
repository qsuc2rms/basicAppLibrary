package com.example.commonmodule.gson;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings("serial")
@DatabaseTable(tableName = "ActivityItem")
public class ActivityItem implements java.io.Serializable{

	@DatabaseField(id = true)
	private String ID;

	@DatabaseField
	private String CREATORID;

	@DatabaseField
	private String PHONE;

	@DatabaseField
	private String SPORTCODE;

	@DatabaseField
	private long CREATETIME;

	@DatabaseField
	private long ACTIVITETIME;

	@DatabaseField
	private String TITLE;

	@DatabaseField
	private String CONTENT;

	@DatabaseField
	private Integer MAXNUM;

	@DatabaseField
	private Double LATITUDE;

	@DatabaseField
	private Double LONGITUDE;

	@DatabaseField
	private String LOCATIONDESC;

	@DatabaseField
	private Long SERIALNO;

	@DatabaseField
	private Integer INTERESTNUM;

	@DatabaseField
	private Integer JOINERNUM;
	
	@DatabaseField
	private String CITY;

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	/**
	 * @return
	 */
	public String getCREATORID() {
		return CREATORID;
	}

	/**
	 * @param creatorId
	 */
	public void setCREATORID(String creatorId) {
		CREATORID = creatorId;
	}

	public String getPHONE() {
		return PHONE;
	}

	/**
	 * @param creatorId
	 */
	public void setPHONE(String phone) {
		PHONE = phone;
	}

	/**
	 * @return
	 */
	public String getSPORTCODE() {
		return SPORTCODE;
	}

	/**
	 * @param sportCode
	 */
	public void setSPORTCODE(String sportCode) {
		SPORTCODE = sportCode;
	}

	/**
	 * @return
	 */
	public long getCREATETIME() {
		return CREATETIME;
	}

	/**
	 * @param createTime
	 */
	public void setCREATETIME(long createTime) {
		CREATETIME = createTime;
	}

	public long getACTIVITETIME() {
		return ACTIVITETIME;
	}

	public void setACTIVITETIME(long activiteTime) {
		ACTIVITETIME = activiteTime;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String title) {
		TITLE = title;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String content) {
		CONTENT = content;
	}

	public Integer getMAXNUM() {
		return MAXNUM;
	}

	public void setMAXNUM(Integer maxNum) {
		MAXNUM = maxNum;
	}

	public Double getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(Double latitude) {
		LATITUDE = latitude;
	}

	public Double getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(Double longitude) {
		LONGITUDE = longitude;
	}

	public String getLOCATIONDESC() {
		return LOCATIONDESC;
	}

	public void setLOCATIONDESC(String locationDesc) {
		LOCATIONDESC = locationDesc;
	}

	public Long getSERIALNO() {
		return SERIALNO;
	}

	public void setSERIALNO(Long serialNO) {
		SERIALNO = serialNO;
	}

	public Integer getINTERESTNUM() {
		return INTERESTNUM;
	}

	public void setINTERESTNUM(Integer interestNum) {
		INTERESTNUM = interestNum;
	}

	public Integer getJOINERNUM() {
		return JOINERNUM == null ? 0 : JOINERNUM;
	}

	public void setJOINERNUM(Integer joinerNum) {
		JOINERNUM = joinerNum;
	}
	
	public String getCITY() {
		return CITY;
	}

	public void setCITY(String city) {
		CITY = city;
	}
}
