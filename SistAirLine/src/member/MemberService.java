/**
 *<pre>
 * com.hr.member
 * Class Name : MemberService.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-07           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-07 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
package member;

import java.util.List;

import com.hr.cmn.DTO;
import com.hr.cmn.MessageVO;

/**
 * @author sist
 *
 */
public class MemberService {
	private MemberDAO memberDao;
	
	public MemberService() {
		memberDao = new MemberDAO();
	}
	
	/**
	 * 
	 *Method Name:loginCheck
	 *작성일: 2020. 3. 12.
	 *작성자: sist
	 *설명: ID/비번 CHECK
	 *@param dto
	 *@return MessageVO
	 */
	public DTO loginCheck(DTO dto) {
		MessageVO  outVO=new MessageVO();
		//ID CHECK
		outVO = (MessageVO) memberDao.idCheck(dto);
		//10: 아이디 없음 , 20: 비번오류 30:관리자 40:일반회원
		if(outVO.getMsgId().equals("10")) {//아이디가 없는경우
			return outVO;
		}
		
		//ID/비번 CHECK
		outVO = (MessageVO) memberDao.passCheck(dto);
		if(outVO.getMsgId().equals("20")) {//아이디는 있는데 비번이 없는경우
			return outVO;
		}		
		
		//ID/비번 CHECK
		outVO = (MessageVO) memberDao.adminLogin(dto);
		if(outVO.getMsgId().equals("30")) {//관리자
			return outVO;
		}else if(outVO.getMsgId().equals("40")) {//일반회원
			return outVO;
		}		
		return outVO;
	}
	
	/**
	 * 
	 *Method Name:doSelectOne
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명: 
	 *@param dto
	 *@return
	 */
	public DTO doSelectOne(DTO dto) {
		return memberDao.doSelectOne(dto);
	}
	
	/**
	 * 
	 *Method Name:doUpdate
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public int doUpdate(DTO dto) {
		return memberDao.doUpdate(dto);
	}
	
	/**
	 * 
	 *Method Name:doDelete
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public int doDelete(DTO dto) {
		return memberDao.doDelete(dto);
	}
	
	/**
	 * 
	 *Method Name:doInsert
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public int doInsert(DTO dto) {
		return memberDao.doInsert(dto);
	}
	
	/**
	 * 
	 *Method Name:doRetrieve
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public List<?> doRetrieve(DTO dto) {
		return memberDao.doRetrieve(dto);
	}
	
}
