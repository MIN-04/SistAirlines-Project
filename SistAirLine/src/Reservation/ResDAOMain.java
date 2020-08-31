package Reservation;

import java.util.List;

import org.apache.log4j.Logger;



public class ResDAOMain {
	
	private final Logger LOG = Logger.getLogger(ResDAOMain.class);
	
	private ResDao dao;
	
	
	public ResDAOMain() {
		dao = new ResDao();
		
	}
	public void doRetrieve() {
		
		ResVO resVO = new ResVO();
		resVO.setPageSize(10);
		resVO.setPageNum(1);
		resVO.setResState(1); //int
		resVO.setResNum("20200306");
		
		
		List<ResVO> list = (List<ResVO>) dao.doRetriev(resVO);
		LOG.debug("********************************");
		//Data출력
		for(ResVO vo:list) {
			LOG.debug(vo);
		}
		LOG.debug("********************************");

	}
	
	
	
	
	public void doSelectOne() {
		ResVO resVO = new ResVO();
		resVO.setResNum("202002230251654");
		ResVO outVO = (ResVO)dao.doSelectOne(resVO);
		
		LOG.debug("********************************");
		LOG.debug("outVO="+outVO);
		LOG.debug("********************************");
		
		
	}
	
	public void doUpdate() {
			ResVO resVO = new ResVO();
			
		  resVO.setResNum("202003160000002");
		  resVO.setResPassport("M153A4569");
		  resVO.setResEname("ear_uuu");
		  resVO.setResBir("1991-01-20");
		  resVO.setResphone("01012341234");
		  resVO.setResEmail("rokrnrepd");
		  resVO.setResGender(Integer.parseInt("1"));
		  resVO.setResState(Integer.parseInt("1"));
		  resVO.setResPay(Integer.parseInt("1"));
		  resVO.setResMileage(Integer.parseInt("1"));
		  resVO.setResScode("20200227");
		  resVO.setuNum("123");
		
		
		
			int upFlag = dao.doUpdate(resVO);

			LOG.debug("Flag : " + upFlag);
			if (upFlag == 1) {
				LOG.debug("===========================");
				LOG.debug("성공");
				LOG.debug("===========================");
			} else {
				LOG.debug("===========================");
				LOG.debug("실패");
				LOG.debug("===========================");
			}
		
		
		
		
		
		ResVO outVO = (ResVO)dao.doSelectOne(resVO);
		
		LOG.debug("********************************");
		LOG.debug("outVO="+outVO);
		LOG.debug("********************************");
		
		
	}


	

	public static void main(String[] args) {
		ResDAOMain daomain = new ResDAOMain();
		//daomain.doRetrieve();
		//daomain.doSelectOne();
		daomain.doUpdate();
	}

}
