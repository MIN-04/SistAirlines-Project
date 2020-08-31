/**
 *<pre>
 * com.hr.code
 * Class Name : CodeVO.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-27           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-27 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
package com.hr.cmn;



/**
 * @author sist
 *
 */
public class CodeVO extends DTO {
	private String	mstId	;/**	마스터코드	*/
	private String	dtlId	;/**	상세코드	*/
	private String	mstNm	;/**	마스터코드명	*/
	private String	dtlNm	;/**	상세코드명	*/
	private String	pMsgId;  /**	상위 마스터코드	*/
	private int  	seq	;    /**	순서	*/
	private String	useYn	;/**	사용여부	*/
	private String	regId	;/**	등록자아이디	*/
	private String	regDt	;/**	등록일	*/
	private String	modId	;/**	아이디ID	*/
	private String	modDt	;/**	수정일	*/
	
	public CodeVO() {}

	/**
	 * @return the mstId
	 */
	public String getMstId() {
		return mstId;
	}

	/**
	 * @param mstId the mstId to set
	 */
	public void setMstId(String mstId) {
		this.mstId = mstId;
	}

	/**
	 * @return the dtlId
	 */
	public String getDtlId() {
		return dtlId;
	}

	/**
	 * @param dtlId the dtlId to set
	 */
	public void setDtlId(String dtlId) {
		this.dtlId = dtlId;
	}

	/**
	 * @return the mstNm
	 */
	public String getMstNm() {
		return mstNm;
	}

	/**
	 * @param mstNm the mstNm to set
	 */
	public void setMstNm(String mstNm) {
		this.mstNm = mstNm;
	}

	/**
	 * @return the dtlNm
	 */
	public String getDtlNm() {
		return dtlNm;
	}

	/**
	 * @param dtlNm the dtlNm to set
	 */
	public void setDtlNm(String dtlNm) {
		this.dtlNm = dtlNm;
	}

	/**
	 * @return the pMsgId
	 */
	public String getpMsgId() {
		return pMsgId;
	}

	/**
	 * @param pMsgId the pMsgId to set
	 */
	public void setpMsgId(String pMsgId) {
		this.pMsgId = pMsgId;
	}

	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * @return the modId
	 */
	public String getModId() {
		return modId;
	}

	/**
	 * @param modId the modId to set
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}

	/**
	 * @return the modDt
	 */
	public String getModDt() {
		return modDt;
	}

	/**
	 * @param modDt the modDt to set
	 */
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "CodeVO [mstId=" + mstId + ", dtlId=" + dtlId + ", mstNm=" + mstNm + ", dtlNm=" + dtlNm + ", pMsgId="
				+ pMsgId + ", seq=" + seq + ", useYn=" + useYn + ", regId=" + regId + ", regDt=" + regDt + ", modId="
				+ modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
	
}
