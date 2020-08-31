package scheduleDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.hr.cmn.ContHandler;
import com.hr.cmn.MessageVO;
import com.hr.cmn.SearchVO;
import com.hr.cmn.StringUtil;


/**
 * Servlet implementation class BoardCont
 */
@WebServlet(description = "스케쥴", urlPatterns = { "/scheduleDTO/schedule.do" })
public class ScheduleCont extends HttpServlet implements ContHandler{
	private static final long serialVersionUID = 1L;
	private ScheduleService scheduleService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleCont() {
        super();
        // TODO Auto-generated constructor stub
        scheduleService=new ScheduleService();
       }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOG.debug("doGet---");
		
		serviceHandler(request, response);
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("1.doPost---");
		
		
		serviceHandler(request, response);
	}

	@Override
	public void serviceHandler(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. work_div: 작업구분 
		//2. requestEncoding: 
		//3. 기능별 분해 
		
//      거래분기:
//		do_update : 수정
//		do_delete : 삭제
//		do_insert : 등록
//		do_retrieve : 목록조회
//		do_selectOne : 단건조회
		String workDiv=StringUtil.nvl(req.getParameter("work_div"));
		LOG.debug("=================");
		LOG.debug("=doInsert.workDiv="+workDiv);
		LOG.debug("=================");
		
		HttpSession httpSession = req.getSession(true);
		 
	 req.setCharacterEncoding("utf-8");

	   String inputAdult= StringUtil.nvl(req.getParameter("inputAdult"),"0");
	   String inputChild = StringUtil.nvl(req.getParameter("inputChild"),"0");
	   String inputTodd = StringUtil.nvl(req.getParameter("inputTodd"),"0");
	   
	   httpSession.setAttribute("inputAdult",inputAdult);
	   httpSession.setAttribute("inputChild",inputChild);
	   httpSession.setAttribute("inputTodd",inputTodd);
	   
	   LOG.debug("어른들의 세계:"+inputAdult);
	   LOG.debug("소아들의 세계:"+inputChild);
	   LOG.debug("유아들의 세계:"+inputTodd);
	   



		switch (workDiv) {
		case "do_retrieve": //목록조회
			this.doRetrieve(req, res);
			break;
		case "do_reservation":
			this.doReservation(req,res);
			break;
			
		case "do_adminretrieve"://목록조회
			this.doAdminRetrieve(req, res);
			break;
		case "do_selectOne" ://단건조회 , 단건조회화면으로 이동
			this.doSelectOne(req, res);
		break;
		
		case "do_update":
			this.doUpdate(req, res);
		break;	
		
		default:
			LOG.debug("=================");
			LOG.debug("=작업구분을 확인하세요 "+workDiv);
			LOG.debug("=================");
			break;
		}
	}
	
	public void doReservation(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		//라디오버튼 값(편도/왕복/다구간)
		String radioValue = req.getParameter("trip-type");
		
		switch(radioValue) {
		case "1": //편도
			this.doRetrieveOne(req,res);
			break;
		case "2": //왕복
			this.doRetrieveAround(req,res);
			break;
		}
		
	}

	public void doRetrieveOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//편도 - 출발/도착
		String dCityOne = StringUtil.nvl(req.getParameter("dCityOne")); //출발지
		String aCityOne = StringUtil.nvl(req.getParameter("aCityOne")); //도착지
		//편도 날짜
		String outDateOne = StringUtil.nvl(req.getParameter("outDateOne"));
		
		boolean notIC = false;
		
		LOG.debug(dCityOne);
		LOG.debug(aCityOne);
		LOG.debug(outDateOne);
		
		ScheduleVO inOneVO=new ScheduleVO();
		
		List<ScheduleVO> listOneWay=null;
		List<ScheduleVO> listOneWay2=null;
		
		//입력한 나라들 중에 인천이 있는지 확인.
		if(dCityOne.equals("인천") || aCityOne.equals("인천")){
			//출발지 또는 도착지에 인천이 있을 때
			inOneVO.setScDcity(dCityOne);
			inOneVO.setScAcity(aCityOne);
			inOneVO.setScDate(outDateOne);
		
			LOG.debug("================================");
			LOG.debug("=inVO="+inOneVO);
			LOG.debug("================================");

			listOneWay= (List<ScheduleVO>)this.scheduleService.doRetrieve(inOneVO);
			req.setCharacterEncoding("UTF-8");
			req.setAttribute("radioValue","1");
			req.setAttribute("listOneWay", listOneWay);//목록 
			req.setAttribute("notIC", notIC);
			
			req.setAttribute("dCity", dCityOne);
			req.setAttribute("aCity", aCityOne);
				  
			//총글수
			RequestDispatcher dispatcher = req.getRequestDispatcher("/schedule.jsp");
			dispatcher.forward(req, res);
				  
			System.out.println("*****************************************");
			
		}else if(!dCityOne.equals("인천") && !aCityOne.equals("인천")){
			//둘다 인천이 없을 때
			notIC = true;
			
			ScheduleVO inOneVO2=new ScheduleVO();
			
			inOneVO.setScDcity(dCityOne);
			inOneVO.setScAcity("인천");
			inOneVO.setScDate(outDateOne);
			
			inOneVO2.setScDcity("인천");
			inOneVO2.setScAcity(aCityOne);
			inOneVO2.setScDate(outDateOne);
			
			listOneWay= (List<ScheduleVO>)this.scheduleService.doRetrieve(inOneVO);
			//왜 doRetrieveNotIn에 list가 있는지
			listOneWay2= (List<ScheduleVO>)this.scheduleService.doRetriveNotIN(inOneVO2,listOneWay);
			
			req.setCharacterEncoding("UTF-8");;
			req.setAttribute("radioValue","1");
			
			req.setAttribute("listOneWay2", listOneWay2);//목록 
			req.setAttribute("notIC", notIC);
			
			req.setAttribute("dCity", dCityOne);
			req.setAttribute("aCity", aCityOne);
		      
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/schedule.jsp");
		    dispatcher.forward(req, res);
		    
			System.out.println("*****************************************");
		}//인천있냐없냐 물어보는 elseif
	}
	
	public void doRetrieveAround(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//왕복 도시
		String dCityAr = StringUtil.nvl(req.getParameter("dCityAr")); //출발지
		String aCityAr= StringUtil.nvl(req.getParameter("aCityAr")); //도착지
		//왕복 날짜
		String outDateAr = StringUtil.nvl(req.getParameter("outDateAr"));
		String inDateAr = StringUtil.nvl(req.getParameter("inDateAr"));
		
		boolean notIC = false;
		
		LOG.debug(dCityAr);
		LOG.debug(aCityAr);
		LOG.debug(outDateAr);
		LOG.debug(inDateAr);
		
		List<ScheduleVO> listAround1=null;
		List<ScheduleVO> listAround2=null;
		List<ScheduleVO> listAround3=null;
		List<ScheduleVO> listAround4=null;
		
		ScheduleVO inAroundVO1=new ScheduleVO();
		ScheduleVO inAroundVO2=new ScheduleVO();
		
		if(dCityAr.equals("인천") || aCityAr.equals("인천")){ //인천이 있을 때
			inAroundVO1.setScDcity(dCityAr);
			inAroundVO1.setScAcity(aCityAr);
			inAroundVO1.setScDate(outDateAr);
			
			inAroundVO2.setScDcity(aCityAr);
			inAroundVO2.setScAcity(dCityAr);
			inAroundVO2.setScDate(inDateAr);
			
			listAround1= (List<ScheduleVO>) this.scheduleService.doRetrieve(inAroundVO1);
			listAround2= (List<ScheduleVO>) this.scheduleService.doRetrieve(inAroundVO2);
			
			LOG.debug("listAround1.size(): "+listAround1.size());
			LOG.debug("listAround2.size(): "+listAround2.size());
			
			req.setAttribute("listAround1", listAround1);//목록 
			req.setAttribute("listAround2", listAround2);//목록 
			req.setAttribute("notIC", notIC);
			req.setAttribute("radioValue","2");
			
			req.setAttribute("dCity",dCityAr);
			req.setAttribute("aCity",aCityAr);
		      
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/schedule.jsp");
		    dispatcher.forward(req, res);
		    
		}else if(!dCityAr.equals("인천") && !aCityAr.equals("인천")){ //인천이 없을 때
		
			ScheduleVO inAroundVO3 = new ScheduleVO(); //갈때
			ScheduleVO inAroundVO4 = new ScheduleVO(); //올때
			
			notIC = true;
			
			//갈 때 
			inAroundVO1.setScDcity(dCityAr);
			inAroundVO1.setScAcity("인천");
			inAroundVO1.setScDate(outDateAr);
			
			inAroundVO3.setScDcity("인천");
			inAroundVO3.setScAcity(aCityAr);
			inAroundVO3.setScDate(outDateAr);
			
			//올 때
			inAroundVO2.setScDcity(aCityAr);
			inAroundVO2.setScAcity("인천");
			inAroundVO2.setScDate(inDateAr);
			
			inAroundVO4.setScDcity("인천");
			inAroundVO4.setScAcity(dCityAr);
			inAroundVO4.setScDate(inDateAr);
			
			//갈 때
			listAround1= (List<ScheduleVO>)this.scheduleService.doRetrieve(inAroundVO1);
			listAround3= (List<ScheduleVO>)this.scheduleService.doRetriveNotIN(inAroundVO3,listAround1);
			
			//올 때
			listAround2= (List<ScheduleVO>)this.scheduleService.doRetrieve(inAroundVO2);
			listAround4= (List<ScheduleVO>)this.scheduleService.doRetriveNotIN(inAroundVO4,listAround2);
			
			req.setAttribute("radioValue","2");

			req.setAttribute("listAround3", listAround3);//왕복 - 인천 미포함 갈때
			req.setAttribute("listAround4", listAround4);//왕복 - 인천 미포함 올때 
			req.setAttribute("notIC", notIC);
			
			req.setAttribute("dCity",dCityAr);
			req.setAttribute("aCity",aCityAr);
			
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/schedule.jsp");
		    dispatcher.forward(req, res);
		}//else
		
	}
	
	public void doAdminRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1.param
		//2.param set VO
		//3.service호출
		//4.특정화면+data
		
//		String dcity  = StringUtil.replace(req.getParameter("dCity"));
//		String outdate = StringUtil.replace(req.getParameter("outDate"));
		ScheduleVO inVO = new ScheduleVO();
		String dCity  = StringUtil.nvl(req.getParameter("dCity"));
		String outDate = StringUtil.nvl(req.getParameter("outDate"));
		String pageSize   = StringUtil.nvl(req.getParameter("page_size"),"10");
		String pageNum    = StringUtil.nvl(req.getParameter("page_num"),"1");
		
		LOG.debug("=======================");
		LOG.debug("=dCity="+dCity);
		LOG.debug("=outDate="+outDate);
		LOG.debug("=======================");
		
		inVO.setScDcity(dCity);
		inVO.setScDate(outDate);
		inVO.setPageNum(Integer.parseInt(pageNum));
		inVO.setPageSize(Integer.parseInt(pageSize));
		
		
		
		LOG.debug("=======================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("=======================");
		
		List<ScheduleVO> sclist = (List<ScheduleVO>)this.scheduleService.doAdminRetrieve(inVO);
		LOG.debug("---------------------");
		for(ScheduleVO  vo :sclist) {
			LOG.debug(vo);
		}
		
		LOG.debug("---------------------");
		//http://localhost:8080/DaoWEB/board/board.do?search_div=10&search_word=&page_size=10&page_num=1&work_div=do_retrieve
		
		
		int totalCnt =0;
		//총글수
		if(null != sclist&&sclist.size()>0) {
			ScheduleVO totalVO = sclist.get(0);
			totalCnt = totalVO.getTotal();
			
		}
		
		
		req.setAttribute("sclist", sclist);//목록
		req.setAttribute("paramVO", inVO);//param
		req.setAttribute("totalCnt", totalCnt);
		
		
		RequestDispatcher  dispatcher=
				req.getRequestDispatcher("/administer_schedule.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1.param 읽는다
			//2. param to set vo
			//3. service call
			//4. request set 
			//5. forward
			
			ScheduleVO inVO = new ScheduleVO();
			LOG.debug("***doSelectOne");
			String sccode= req.getParameter("scCode");
			
			inVO.setScCode(sccode);
			scheduleService.doSelectOne(inVO);
			ScheduleVO outVO = (ScheduleVO)scheduleService.doSelectOne(inVO);
			
			req.setAttribute("vo", outVO);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/a_scheduleUpdate.jsp");
			dispatcher.forward(req, res);
		
	}
	
	
	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 LOG.debug("====================");
		  LOG.debug("=doUpdate=");
		  LOG.debug("====================");
		  
		  ScheduleVO  inVO=new ScheduleVO();//param
		  //1.param read
		  //2.param BoardVO
		  //3.service 메소드 호출
		  //SEQ
		  String scdate = req.getParameter("scdate");
		  String scdtime = req.getParameter("scdtime");
		  String scstime = req.getParameter("scstime");
		  String scdcity = req.getParameter("scdcity");
		  String scacity = req.getParameter("scacity");
		  String scprice = req.getParameter("scprice");
		  String scmileage = req.getParameter("scmileage");
		  String scaname = req.getParameter("scaname");
		  String sccapcacity = req.getParameter("sccapcacity");
		  String scunoccupied = req.getParameter("scunoccupied");
		  String sccode = req.getParameter("sccode");
		  String scstate = req.getParameter("scstate");
		  
	
		  
		  //inVO.setSeq(Integer.parseInt(seq));
		  inVO.setScDate(scdate);
		  inVO.setScDtime(scdtime);
		  inVO.setScStime(scstime);
		  inVO.setScDcity(scdcity);
		  inVO.setScAcity(scacity);
		  inVO.setScPrice(scprice);
		  inVO.setScMileage(Integer.parseInt(scmileage));
		  inVO.setScAname(scaname);
		  inVO.setScCapacity(sccapcacity);
		  inVO.setScUnoccupied(scunoccupied);
		  inVO.setScCode(sccode);
		  inVO.setScState(Integer.parseInt(scstate));
		  
		  //TODO
		  //login처리후 session으로 변환 할것
		 
		  
		  int flag = this.scheduleService.doUpdate(inVO);
		  LOG.debug("3====================");
		  LOG.debug("3=flag="+flag);
		  LOG.debug("3====================");
		  
		  //응답처리
		  res.setContentType("text/html;charset=utf-8");
		  PrintWriter out=res.getWriter();
//		  if(flag==1) {
//			  out.println("<script>alert('등록성공!');location.href='/DaoWEB/board/board_list.jsp'</script>");
//		  }else {
//			  out.println("<script>alert('실패!');history.go(-1);</script>");
//		  }

		  String msg = "";
		  Gson  gson = new Gson(); 
		  if(1==flag) {
			  msg = inVO.getScCode()+"이 수정 되었습니다.";
		  }else {
			  msg = inVO.getScCode()+" 수정 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);
		
	}

	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	
	
	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
