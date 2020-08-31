package Reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Reservation.ResService;
import Reservation.ResVO;
import scheduleDTO.MessageVO;
import scheduleDTO.ScheduleVO;

import com.google.gson.Gson;
import com.hr.cmn.ContHandler;
import com.hr.cmn.StringUtil;
//import Reservation.CodeService;


/**
 * Servlet implementation class ResCont
 */
@WebServlet(description = "예약목록", urlPatterns = { "/Reservation/res.do" })
public class ResCont extends HttpServlet implements ContHandler {
	private static final long serialVersionUID = 1L;
      
	/** ResService객체 */
    private ResService  service;
    
    /**CodeService 객체 */
	//CodeService  cdService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResCont() {
    	   super();
           service   = new ResService();
         //  cdService = new CodeService();
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
					case "do_update"://수정
						this.doUpdate(req, res);
					break;
				
					case "do_delete"://삭제
						this.doDel(req, res);
					break;
					case "do_selectOne"://단건조회,단건조회 화면으로 이동
						this.doSelectOne(req, res);
					break;
				
					case "move_to_save"://등록화면으로 이동
						//doMoveToSave(req, res);
					break;
				
					case "do_insert"://단건 등록
						this.doInsert(req, res);
						break;
					case "do_retrieve"://목록조회
						this.doRetrieve(req, res);
						break;
					case "do_retriev":// 예약목록조회
						this.doRetriev(req, res);
						break;	
					default:
						  LOG.debug("====================");
						  LOG.debug("=작업구분을 확인 하세요.workDiv="+workDiv);
						  LOG.debug("====================");
						break;
				
				}
				
				
		
	}
    // 일반 
	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 //--------------------------doRetrieve--------------------------------------------
	    ResVO inVO=new ResVO();
	   // String resState  = StringUtil.nvl(req.getParameter("res_state"));
		//String searchDiv  = req.getParameter("search_div");
		//String resNum = StringUtil.nvl(req.getParameter("res_num"));
		String pageSize   = StringUtil.nvl(req.getParameter("page_size"),"20"); ///paging
		String pageNum    = StringUtil.nvl(req.getParameter("page_num"),"1");
		
		
//		inVO.setResState(Integer.parseInt(resState));
//		inVO.setResNum(resNum);
		inVO.setPageNum(Integer.parseInt(pageNum));
		inVO.setPageSize(Integer.parseInt(pageSize));
		
		LOG.debug("=======================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("=======================");

		List<ResVO> list = (List<ResVO>) this.service.doRetrieve(inVO);
		
		
		for(ResVO vo :list) {
			LOG.debug(vo);
		}
		
		
		int totalCnt = 0;
		//총글수
		if(null !=list && list.size()>0) {
			ResVO totalVO=list.get(0);
			totalCnt = totalVO.getTotal();
		}
		
		req.setAttribute("list", list);//목록
		req.setAttribute("paramVO", inVO);//param
		req.setAttribute("totalCnt", totalCnt);//총글수
		
		
		RequestDispatcher  dispatcher=
				req.getRequestDispatcher("/administer_reservation.jsp");
		dispatcher.forward(req, res);
		

		}
	// 검색 
	/**
	 *   입력값이 있을때 목록 조회
	 */

	public void doRetriev(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 String a  =	req.getParameter("res_state");
		 String b = 	req.getParameter("res_num");
		 
		 LOG.debug(a);
		 LOG.debug(b);
			
	 //--------------------------doRetriev--------------------------------------------
		    ResVO inVO=new ResVO();
		    //String searchDiv  = req.getParameter("search_div");
		   
		    String resState = StringUtil.nvl(req.getParameter("res_state"));
				
			String resNum = StringUtil.nvl(req.getParameter("res_num"));
			String pageSize   = StringUtil.nvl(req.getParameter("page_size"),"20");
			String pageNum    = StringUtil.nvl(req.getParameter("page_num"),"1");
			
			LOG.debug("=======================");
			LOG.debug("=resState="+resState);
			LOG.debug("=resNum="+resNum);
			LOG.debug("=======================");
			
			if(resState==null || resState.equals("")) {
				resState="0";
			}
			inVO.setResState(Integer.parseInt(resState));
			inVO.setResNum(resNum);
			inVO.setPageNum(Integer.parseInt(pageNum));
			inVO.setPageSize(Integer.parseInt(pageSize));
			
			LOG.debug("=======================");
			LOG.debug("=inVO="+inVO);
			LOG.debug("=======================");
			//------------------------------------
			// Code조회                                             
			// BOARD_SEARCH_DIV(검색조건)
			// COM_PAGE_SIZE(페이지 사이즈)
			//-------------------------------------
			  
			List<ResVO> list = (List<ResVO>) this.service.doRetriev(inVO);
			
			LOG.debug("--------------");
			for(ResVO vo :list) {
				LOG.debug(vo);
			}
			

		  
//			  //응답처리
			res.setContentType("text/html;charset=utf-8"); 
		

	

			
			int totalCnt = 0;
			//총글수
			if(null !=list && list.size()>0) {
				ResVO totalVO=list.get(0);
				totalCnt = totalVO.getTotal();
			}
			
			req.setAttribute("list", list);//목록
			req.setAttribute("paramVO", inVO);//param
			req.setAttribute("totalCnt", totalCnt);//총글수
			
			

			
			RequestDispatcher  dispatcher=
					req.getRequestDispatcher("/administer_reservation_paging.jsp");
			dispatcher.forward(req, res);
			
			
					
						
			
			
			
			

		}
		

	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 1.param 읽는다
		//2. param to set vo
		//3. service call
		//4. request set 
		//5. forward
		
		ResVO inVO=new ResVO();
		LOG.debug("***doSelectOne");
	    String resNum  = StringUtil.nvl(req.getParameter("resNum"));
	    String resPassport  = StringUtil.nvl(req.getParameter("resPassport"));
	    
	    inVO.setResNum(resNum);
	    inVO.setResPassport(resPassport);
	    
		service.doSelectOne(inVO);
		ResVO outVO = (ResVO)service.doSelectOne(inVO);
		
		//jstl 사용하기 위해 보내준다.
		req.setAttribute("vo", outVO);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/a_resUpdate.jsp");
		dispatcher.forward(req, res);
	
	
	
	 }
	    
		
	

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 LOG.debug("====================");
		  LOG.debug("=doUpdate=");
		  LOG.debug("====================");
		  //StringToDate st = new StringToDate();
		  SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD");
		  ResVO  inVO=new ResVO();//param
		  //1.param read
		  //2.param BoardVO
		  //3.service 메소드 호출
		  //SEQ
		  String resNum = req.getParameter("res_num");
		  String resPassport = req.getParameter("res_passport");
		  String resEname = req.getParameter("res_ename");
		  String resBir = req.getParameter("res_bir");
		 // String resBir=sdf.format(req.getParameter("res_bir"));
		  String resphone = req.getParameter("res_phone");
		  String resEmail = req.getParameter("res_email");
		  String resGender = req.getParameter("res_gender");
		  String resState = req.getParameter("res_state");
		  String resPay = req.getParameter("res_pay");
		  String resMileage = req.getParameter("res_mileage");
		  String resScode = req.getParameter("res_scode");
		  String uNum = req.getParameter("u_num");
		  
	
		  
		  //inVO.setSeq(Integer.parseInt(seq));
		  
		  inVO.setResNum(resNum);
		  inVO.setResPassport(resPassport);
		  inVO.setResEname(resEname);
		  inVO.setResBir(resBir);
		  inVO.setResphone(resphone);
		  inVO.setResEmail(resEmail);
		  inVO.setResGender(Integer.parseInt(resGender));
		  inVO.setResState(Integer.parseInt(resState));
		  inVO.setResPay(Integer.parseInt(resPay));
		  inVO.setResMileage(Integer.parseInt(resMileage));
		  inVO.setResScode(resScode);
		  inVO.setuNum(uNum);
		  
		  //TODO
		  //login처리후 session으로 변환 할것
		 
		  
		  int flag = this.service.doUpdate(inVO);
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
			  msg = inVO.getResNum()+"이\n수정 되었습니다.";
		  }else {
			  msg = inVO.getResNum()+"이\n수정 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
