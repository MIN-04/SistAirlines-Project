package member;

import com.hr.cmn.DTO;

public class MemberVO extends DTO {
	
	private int uNum;
	private String uID;
	private String uPW;
	private String uKname;
	private String uEname;
	private String uBir;
	private String uPhone;
	private String uEmail;
	private int uGender;
	private String uAddress;
	private String uCountry;
	private int uMileage;
	private String uMaindepart;
	private int Authority;
	
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getuPW() {
		return uPW;
	}
	public void setuPW(String uPW) {
		this.uPW = uPW;
	}
	public String getuKname() {
		return uKname;
	}
	public void setuKname(String uKname) {
		this.uKname = uKname;
	}
	public String getuEname() {
		return uEname;
	}
	public void setuEname(String uEname) {
		this.uEname = uEname;
	}
	public String getuBir() {
		return uBir;
	}
	public void setuBir(String uBir) {
		this.uBir = uBir;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public int getuGender() {
		return uGender;
	}
	public void setuGender(int uGender) {
		this.uGender = uGender;
	}
	public String getuAddress() {
		return uAddress;
	}
	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}
	public String getuCountry() {
		return uCountry;
	}
	public void setuCountry(String uCountry) {
		this.uCountry = uCountry;
	}
	public int getuMileage() {
		return uMileage;
	}
	public void setuMileage(int uMileage) {
		this.uMileage = uMileage;
	}
	public String getuMaindepart() {
		return uMaindepart;
	}
	public void setuMaindepart(String uMaindepart) {
		this.uMaindepart = uMaindepart;
	}
	public int getAuthority() {
		return Authority;
	}
	public void setAuthority(int authority) {
		Authority = authority;
	}
	
	@Override
	public String toString() {
		return "MemberVO [uNum=" + uNum + ", uID=" + uID + ", uPW=" + uPW + ", uKname=" + uKname + ", uEname=" + uEname
				+ ", uBir=" + uBir + ", uPhone=" + uPhone + ", uEmail=" + uEmail + ", uGender=" + uGender
				+ ", uAddress=" + uAddress + ", uCountry=" + uCountry + ", uMileage=" + uMileage + ", uMaindepart="
				+ uMaindepart + ", Authority=" + Authority + ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
