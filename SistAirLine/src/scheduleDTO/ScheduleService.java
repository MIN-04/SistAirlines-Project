package scheduleDTO;

import java.util.List;

import com.hr.cmn.DTO;

public class ScheduleService {

	private ScheduleDAO scheduleDao;
	public ScheduleService() {
		scheduleDao =new ScheduleDAO();
		
	}

	public DTO doSelectOne(DTO dto) {
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
	public List<?> doRetrieve(DTO dto) {
		return scheduleDao.doRetrieve(dto);
	}
	
	public List<?> doRetriveNotIN(DTO dto,List<ScheduleVO> list) {
		return scheduleDao.doRetriveNotIN(dto, list);
	}
	public List<?> doAdminRetrieve(DTO dto) {
		return scheduleDao.adminRetrive(dto);
	}
	
	
}
