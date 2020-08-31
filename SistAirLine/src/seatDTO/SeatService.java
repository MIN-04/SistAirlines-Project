package seatDTO;

import java.util.List;

import com.hr.cmn.DTO;

import scheduleDTO.ScheduleVO;

public class SeatService {
		private SeatDAO seatDao;
	public SeatService() {
		seatDao=new SeatDAO();
	}
	
	public DTO doSelecteOne(DTO dto) {
		return seatDao.doSelectOne(dto);
	}
	public int doInsert(DTO dto) {
		return seatDao.doInsert(dto);
	}
	public int doUpdate(DTO dto) {
		return seatDao.doUpdate(dto);
	}
	public int doDelete(DTO dto) {
		return seatDao.doDelete(dto);
	}
	public List<?> doRetrieve(DTO dto) {
		return seatDao.doRetrieve(dto);
	}
	
	
	
}
