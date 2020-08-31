/**
 *<pre>
 * com.hr.cmn
 * Class Name : SearchVO.java
 * Description : �˻�VO
 * Modification Information
 * 
 *   ������      ������              ��������
 *  ---------   ---------   -------------------------------
 *  2020-02-05           ���ʻ���
 *
 * @author ���������ӿ�ũ ����ȯ�� ������
 * @since 2020-02-05 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.Reservation*/
package scheduleDTO;

/**
 * @author sist
 *
 */
public class SearchVO  {
	
	private String searchDiv;//�˻�����
	private String searchWord;//�˻���
	private String searchWord02;//�˻���
	
	public SearchVO() {}

	public SearchVO(String searchDiv, String searchWord, String searchWord02) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.searchWord02 = searchWord02;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchWord02() {
		return searchWord02;
	}

	public void setSearchWord02(String searchWord02) {
		this.searchWord02 = searchWord02;
	}

	@Override
	public String toString() {
		return "SearchVO [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", searchWord02=" + searchWord02
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
}
