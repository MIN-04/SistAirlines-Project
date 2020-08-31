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

import com.google.gson.Gson;
import com.hr.cmn.StringUtil;

/**
 * Servlet implementation class AshceudleCont
 */
@WebServlet(description = "스케줄리스트", urlPatterns = { "/AshceudleCont" })
public class AshceudleCont extends HttpServlet implements ContHandler {
	private static final long serialVersionUID = 1L;
	
	/**service객체*/
    private ScService scservice;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AshceudleCont() {
        super();
        scservice = new ScService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
				LOG.debug("doGet------");
				serviceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("1. doPost------");
		serviceHandler(request, response);
	}

	@Override
	public void serviceHandler(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1.work_div: 작업구분
		//2.request Encoding: utf-8
		//3.기능별 분해
		    //거래분기:
	    	//do_update:수정
	    	//do_delete:삭제
	    	//do_insert:등록
	    	//do_retrieve:목록조회
	    	//do_selectOne:단건조회
		req.setCharacterEncoding("utf-8");//request의 인코딩: utf-8
		String workDiv = StringUtil.nvl(req.getParameter("work_div"));//null -> ""
		LOG.debug("2====================");
		LOG.debug("2=workDiv="+workDiv);
		LOG.debug("2====================");	
		switch(workDiv) {//거래분기
		
		case "change_page"://삭제
			this.doChangePage(req, res);
		break;
		case "do_update"://삭제
			this.doUpdate(req, res);
		break;
		
		case "do_delete"://삭제
			this.doDel(req, res);
		break;
	
		case "do_selectOne" ://단건조회 , 단건조회화면으로 이동
			this.doSelectOne(req, res);
		break;
	
//		case "move_to_save"://등록화면으로 이동
//			doMoveToSave(req,res);
//			break;
		case "do_insert"://단건 등록
			this.doInsert(req, res);
			break;
		case "do_retrieve"://목록조회
			this.doRetrieve(req, res);
			break;
		default:
			  LOG.debug("====================");
			  LOG.debug("=작업구분을 확인 하세요.workDiv="+workDiv);
			  LOG.debug("====================");
			break;
		}
	
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
		 
		  
		  int flag = this.scservice.doUpdate(inVO);
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
			  msg = inVO.getScCode()+"이\n수정 되었습니다.";
		  }else {
			  msg = inVO.getScCode()+"이\n수정 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);
		
	}
	public void doChangePage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
	}
	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1.param
		//2.param set VO
		//3.service호출
		//4.특정화면+data
		
//		String dcity  = StringUtil.replace(req.getParameter("dCity"));
//		String outdate = StringUtil.replace(req.getParameter("outDate"));
		ScheduleVO inVO = new ScheduleVO();
		String dCity  = req.getParameter("dCity");
		String outDate = req.getParameter("outDate");
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
		
		List<ScheduleVO> sclist = (List<ScheduleVO>)this.scservice.doRetrieve(inVO);
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
				req.getRequestDispatcher("/a_scheduleList.jsp");
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
			scservice.doSelectOne(inVO);
			ScheduleVO outVO = (ScheduleVO)scservice.doSelectOne(inVO);
			
			req.setAttribute("vo", outVO);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/a_scheduleUpdate.jsp");
			dispatcher.forward(req, res);
		
	}

}
