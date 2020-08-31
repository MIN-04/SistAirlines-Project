package seatDTO;

import com.hr.cmn.DTO;

public class SeatDTO extends DTO{
	private String scCode;//스케줄코드 
	private String seName;//좌석명
	private String seOccupied;//좌석차지여부
	private String sePassport;//여권번호
	private String seCode;//좌석코드 pk
	

	public String getScCode() {
		return scCode;
	}
	public void setScCode(String scCode) {
		this.scCode = scCode;
	}
	public String getSeName() {
		return seName;
	}
	public void setSeName(String seName) {
		this.seName = seName;
	}
	public String getSeOccupied() {
		return seOccupied;
	}
	public void setSeOccupied(String seOccupied) {
		this.seOccupied = seOccupied;
	}
	public String getSePassport() {
		return sePassport;
	}
	public void setSePassport(String sePassport) {
		this.sePassport = sePassport;
	}
	public String getSeCode() {
		return seCode;
	}
	public void setSeCode(String seCode) {
		this.seCode = seCode;
	}
	
	@Override
	public String toString() {
		return "SeatDTO [scCode=" + scCode + ", seName=" + seName + ", seOccupied=" + seOccupied + ", sePassport="
				+ sePassport + ", seCode=" + seCode + "]";
	}

}
