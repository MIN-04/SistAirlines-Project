/**
 *<pre>
 * com.hr.board
 * Class Name : BoardService.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-06           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-06 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
package Reservation;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.DTO;



/**
 * @author sist
 *
 */
public class ResService {
	private final Logger LOG = Logger.getLogger(ResService.class);
	private ResDao ResDao;
	
	public ResService() {
		ResDao = new ResDao();
	}
	
	public DTO doSelectOne(DTO dto) {
		//조회수 증가
		//int readCnt = ResDao.readCount(dto);
		
		//단건 조회
		return ResDao.doSelectOne(dto);
	}
	
	public int doInsert(DTO dto) {
		return ResDao.doInsert(dto);
	}
	
	public int doUpdate(DTO dto) {
		return ResDao.doUpdate(dto);
	}
	
	public int doDelete(DTO dto) {
		return ResDao.doDelete(dto);
	}
	
	public List<?> doRetrieve(DTO dto) {
		return ResDao.doRetrieve(dto);
	}
	public List<?> doRetriev(DTO dto) {
		return ResDao.doRetriev(dto);
	}
}


