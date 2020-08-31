package scheduleDTO;

import com.hr.cmn.DTO;

public class ScheduleVO extends DTO {
	private String scDate;
	private String scDtime;//��߽ð�
	private String scStime;
	private String scDcity;
	private String scAcity;
	private String scPrice;
	private int scMileage;
	private String scAname;
	private String scCapacity;
	private String scUnoccupied;
	private String scCode;
	private int scState;
	
	public ScheduleVO() {}
	
	public ScheduleVO(String scDate, String scDtime, String scStime, String scDcity, String scAcity, String scPrice,
			int scMileage, String scAname, String scCapacity, String scUnoccupied, String scCode, int scState) {
		super();
		this.scDate = scDate;
		this.scDtime = scDtime;
		this.scStime = scStime;
		this.scDcity = scDcity;
		this.scAcity = scAcity;
		this.scPrice = scPrice;
		this.scMileage = scMileage;
		this.scAname = scAname;
		this.scCapacity = scCapacity;
		this.scUnoccupied = scUnoccupied;
		this.scCode = scCode;
		this.scState = scState;
	}
	public String getScDate() {
		return scDate;
	}
	public void setScDate(String scDate) {
		this.scDate = scDate;
	}
	public String getScDtime() {
		return scDtime;
	}
	public void setScDtime(String scDtime) {
		this.scDtime = scDtime;
	}
	public String getScStime() {
		return scStime;
	}
	public void setScStime(String scStime) {
		this.scStime = scStime;
	}
	public String getScDcity() {
		return scDcity;
	}
	public void setScDcity(String scDcity) {
		this.scDcity = scDcity;
	}
	public String getScAcity() {
		return scAcity;
	}
	public void setScAcity(String scAcity) {
		this.scAcity = scAcity;
	}
	public String getScPrice() {
		return scPrice;
	}
	public void setScPrice(String scPrice) {
		this.scPrice = scPrice;
	}
	public int getScMileage() {
		return scMileage;
	}
	public void setScMileage(int scMileage) {
		this.scMileage = scMileage;
	}
	public String getScAname() {
		return scAname;
	}
	public void setScAname(String scAname) {
		this.scAname = scAname;
	}
	public String getScCapacity() {
		return scCapacity;
	}
	public void setScCapacity(String scCapacity) {
		this.scCapacity = scCapacity;
	}
	public String getScUnoccupied() {
		return scUnoccupied;
	}
	public void setScUnoccupied(String scUnoccupied) {
		this.scUnoccupied = scUnoccupied;
	}
	public String getScCode() {
		return scCode;
	}
	public void setScCode(String scCode) {
		this.scCode = scCode;
	}
	public int getScState() {
		return scState;
	}
	public void setScState(int scState) {
		this.scState = scState;
	}

	@Override
	public String toString() {
		return "ScheduleVO [scDate=" + scDate + ", scDtime=" + scDtime + ", scStime=" + scStime + ", scDcity=" + scDcity
				+ ", scAcity=" + scAcity + ", scPrice=" + scPrice + ", scMileage=" + scMileage + ", scAname=" + scAname
				+ ", scCapacity=" + scCapacity + ", scUnoccupied=" + scUnoccupied + ", scCode=" + scCode + ", scState="
				+ scState + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
}
