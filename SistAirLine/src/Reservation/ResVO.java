/**
 * 
 */
package Reservation;

import com.hr.cmn.DTO;

/**
 * @author sist
 *
 */
public class ResVO extends DTO {
	private String resNum;
	private String resPassport;
	private String resEname;
	private String resBir;
	private String resphone;
	private String resEmail;
	private int    resWay;
	private int    resPtype;
	private int    resGender;
	private int    resState;
	private int    resPay;
	private int    resMileage;
	private String resScode;
	private String uNum;

	
	public ResVO() {}


	public ResVO(String resNum, String resPassport, String resEname, String resBir, String resphone,
			String resEmail, int resWay, int resPtype, int resGender, int resState, int resPay, int resMileage,
			String resScode, String uNum) {
		super();
		this.resNum = resNum;
		this.resPassport = resPassport;
		this.resEname = resEname;
		this.resBir = resBir;
		this.resphone = resphone;
		this.resEmail = resEmail;
		this.resWay = resWay;
		this.resPtype = resPtype;
		this.resGender = resGender;
		this.resState = resState;
		this.resPay = resPay;
		this.resMileage = resMileage;
		this.resScode = resScode;
		this.uNum = uNum;
	}


	public String getResNum() {
		return resNum;
	}


	public void setResNum(String resNum) {
		this.resNum = resNum;
	}


	public String getResPassport() {
		return resPassport;
	}


	public void setResPassport(String resPassport) {
		this.resPassport = resPassport;
	}


	public String getResEname() {
		return resEname;
	}


	public void setResEname(String resEname) {
		this.resEname = resEname;
	}


	public String getResBir() {
		return resBir;
	}


	public void setResBir(String resBir) {
		this.resBir = resBir;
	}


	public String getResphone() {
		return resphone;
	}


	public void setResphone(String resphone) {
		this.resphone = resphone;
	}


	public String getResEmail() {
		return resEmail;
	}


	public void setResEmail(String resEmail) {
		this.resEmail = resEmail;
	}


	public int getResWay() {
		return resWay;
	}


	public void setResWay(int resWay) {
		this.resWay = resWay;
	}


	public int getResPtype() {
		return resPtype;
	}


	public void setResPtype(int resPtype) {
		this.resPtype = resPtype;
	}


	public int getResGender() {
		return resGender;
	}


	public void setResGender(int resGender) {
		this.resGender = resGender;
	}


	public int getResState() {
		return resState;
	}


	public void setResState(int resState) {
		this.resState = resState;
	}


	public int getResPay() {
		return resPay;
	}


	public void setResPay(int resPay) {
		this.resPay = resPay;
	}


	public int getResMileage() {
		return resMileage;
	}


	public void setResMileage(int resMileage) {
		this.resMileage = resMileage;
	}


	public String getResScode() {
		return resScode;
	}


	public void setResScode(String resScode) {
		this.resScode = resScode;
	}


	public String getuNum() {
		return uNum;
	}


	public void setuNum(String uNum) {
		this.uNum = uNum;
	}


	@Override
	public String toString() {
		return "ResVO [resNum=" + resNum + ", resPassport=" + resPassport + ", resEname=" + resEname
				+ ", resBir=" + resBir + ", resphone=" + resphone + ", resEmail=" + resEmail + ", resWay=" + resWay
				+ ", resPtype=" + resPtype + ", resGender=" + resGender + ", resState=" + resState + ", resPay="
				+ resPay + ", resMileage=" + resMileage + ", resScode=" + resScode + ", uNum=" + uNum + ", toString()="
				+ super.toString() + "]";
	}


	
}
