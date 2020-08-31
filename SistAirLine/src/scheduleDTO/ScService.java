package scheduleDTO;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.DTO;



;

public class ScService {
	private final Logger LOG =Logger.getLogger(this.getClass());
	private ScheduleDAO scheduleDao;
	
	public ScService() {
		scheduleDao = new ScheduleDAO();
	}
	
	
	public List<?> doRetrieve(DTO dto) {
		return scheduleDao.adminRetrive(dto);
	}
	
	public DTO doSelectOne(DTO dto) {
		//조회 Count증가
		//int readCnt = scheduleDao.readCount(dto);
		
		//단건조회
		return scheduleDao.admindoSelectOne(dto);
	}
	
	public int doInsert(DTO dto) {
		return scheduleDao.doInsert(dto);
	}
	
	public int doUpdate(DTO dto) {
		return scheduleDao.doUpdate(dto);
	}
	
	public int doDelete(DTO dto) {
		return scheduleDao.doDelete(dto);
	}
	


}
