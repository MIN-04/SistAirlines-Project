package scheduleDTO;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.SearchVO;

public class ScheduleDAOMain {
	
	private final Logger LOG = Logger.getLogger(ScheduleDAOMain.class);
	
	private ScheduleDAO dao;
	private ScheduleVO schedulevo;
	
	public ScheduleDAOMain() {
		dao = new ScheduleDAO();
		schedulevo = new ScheduleVO();
	}
	public void doRetrieve() {
		ScheduleVO scheduleVO = new ScheduleVO();
		scheduleVO.setPageSize(10);
		scheduleVO.setPageNum(1);
		scheduleVO.setScDate("20200217");
		scheduleVO.setScDcity("하노이");
		
		
		List<ScheduleVO> list = (List<ScheduleVO>) dao.adminRetrive(scheduleVO);
		LOG.debug("********************************");
		//Data출력
		for(ScheduleVO vo:list) {
			LOG.debug(vo);
		}
		LOG.debug("********************************");

	}
	
	public void doSelectOne() {
		
		schedulevo.setScCode("20200331INTO_14");
		ScheduleVO outVO = (ScheduleVO)dao.admindoSelectOne(schedulevo);
		
		LOG.debug("********************************");
		LOG.debug("outVO="+outVO);
		LOG.debug("********************************");
		
		
	}

	

	public static void main(String[] args) {
		ScheduleDAOMain daomain = new ScheduleDAOMain();
		daomain.doRetrieve();
		////daomain.doSelectOne();
		
	}

}
