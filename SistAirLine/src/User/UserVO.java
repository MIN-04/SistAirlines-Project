/**
 *<pre>
 * UUSER
 * Class Name : UserVO.java
 * Description : 
 * Modification Information
 * 
 *   占쎈땾占쎌젟占쎌뵬      占쎈땾占쎌젟占쎌쁽              占쎈땾占쎌젟占쎄땀占쎌뒠
 *  ---------   ---------   -------------------------------
 *  2020-02-09           筌ㅼ뮇�겧占쎄문占쎄쉐
 *
 * @author 揶쏆뮆而삼옙遊쏙옙�쟿占쎌뿫占쎌뜗占쎄쾿 占쎈뼄占쎈뻬占쎌넎野껓옙 揶쏆뮆而삼옙占�
 * @since 2020-02-09 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
package User;

import com.hr.cmn.DTO;

/**
 * @author User
 *
 */
public class UserVO extends DTO {

	private int uNum;
	private String uId;
	private String uPw;
	private String uKname;
	private String uEname;
	private String uBir;
	private String uPhone;
	private String uEmail;
	private String uGender;
	private String uAddress;
	private String uCountry;
	private int uMileage;
	private String uMaindepart;
	private int authority;


	public UserVO() {

	}


	public int getuNum() {
		return uNum;
	}


	public void setuNum(int uNum) {
		this.uNum = uNum;
	}


	public String getuId() {
		return uId;
	}


	public void setuId(String uId) {
		this.uId = uId;
	}


	public String getuPw() {
		return uPw;
	}


	public void setuPw(String uPw) {
		this.uPw = uPw;
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


	public String getuGender() {
		return uGender;
	}


	public void setuGender(String uGender) {
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
		return authority;
	}


	public void setAuthority(int authority) {
		this.authority = authority;
	}


	@Override
	public String toString() {
		return "UserVO [uNum=" + uNum + ", uId=" + uId + ", uPw=" + uPw + ", uKname=" + uKname + ", uEname=" + uEname
				+ ", uBir=" + uBir + ", uPhone=" + uPhone + ", uEmail=" + uEmail + ", uGender=" + uGender
				+ ", uAddress=" + uAddress + ", uCountry=" + uCountry + ", uMileage=" + uMileage + ", uMaindepart="
				+ uMaindepart + ", authority=" + authority + ", getTotal()=" + getTotal() + ", getNum()=" + getNum()
				+ ", getPageSize()=" + getPageSize() + ", getPageNum()=" + getPageNum() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	

}