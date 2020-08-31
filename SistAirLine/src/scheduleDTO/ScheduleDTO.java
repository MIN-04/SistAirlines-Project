package scheduleDTO;

import com.hr.cmn.DTO;

public class ScheduleDTO extends DTO{
	private String scDate;
	private String scDtime;//출발시간
	private String scStime;
	private String scDcity;
	private String scAcity;
	private String scPrice;
	private String scMileage;
	private String scAname;
	private String scCapacity;
	private String scUnoccupied;
	private String scCode;
	
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
	public String getScMileage() {
		return scMileage;
	}
	public void setScMileage(String scMileage) {
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
	

	@Override
	public String toString() {
		return "ScheduleDTO [scDate=" + scDate + ", scDtime=" + scDtime + ", scStime=" + scStime + ", scDcity="
				+ scDcity + ", scAcity=" + scAcity + ", scPrice=" + scPrice + ", scMileage=" + scMileage + ", scAname="
				+ scAname + ", scCapacity=" + scCapacity + ", scUnoccupied=" + scUnoccupied + ", scCode=" + scCode
				+ "]";
	}
	
	
	
	
}
