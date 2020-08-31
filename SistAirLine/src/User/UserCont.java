package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

import scheduleDTO.ScheduleVO;


/**
 * Servlet implementation class BoardCont
 */
@WebServlet(description = "사용자", urlPatterns = { "/User/user.do" })
public class UserCont extends HttpServlet implements ContHandler{
	private static final long serialVersionUID = 1L;
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCont() {
        super();
        // TODO Auto-generated constructor stub
        userService=new UserService();
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
//http://localhost:8080/SistAirLine/User/user.do?work_div=do_Login&uId=iod1124&uPw=1234
		switch (workDiv) {

		case "do_Login": //등록화면으로 이동
			this.doLogin(req, res);
			break;
		case "do_update": //등록화면으로 이동
			this.doUpdate(req, res);
			break;
		case "do_insert": //단건등록
			this.doInsert(req, res);
			break;
			
		case "do_retrieve": //목록조회
			this.doRetrieve(req, res);
			break;
		
		case "do_selectOne": //단건조회/단건조회화면으로 이동
			this.doSelectOne(req, res);
			break;	
		case "do_delete": //삭제
			this.doDel(req, res);
			break;
			
		default:
			LOG.debug("=================");
			LOG.debug("=작업구분을 확인하세요 "+workDiv);
			LOG.debug("=================");
			
			break;
		}
		
		
	}

	
	public void doLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserVO outVO=new UserVO();
		UserVO inVO=new UserVO();
		int uNum=0;
		String uId=StringUtil.nvl(req.getParameter("uId"));
		String uPw=StringUtil.nvl(req.getParameter("uPw"));
		
		inVO.setuId(uId);
		inVO.setuPw(uPw);
		
		outVO= (UserVO) this.userService.loginMethod(inVO);
		uNum=outVO.getuNum();
		String uKname=outVO.getuKname();
		LOG.debug("Controller:"+outVO);
		LOG.debug("Controller:"+outVO.getuKname());
		if(uNum!=0) {
			
			req.setAttribute("uNum",uNum);
			req.setAttribute("uKname", uKname);//목록 
		      
		       //총글수
//			ServletContext context=getServletContext();
//			RequestDispatcher rd=context.getRequestDispatcher("/index2.jsp");
//			   rd.forward(req, res);   
			RequestDispatcher dispatcher = req.getRequestDispatcher("/schedule/schedule2.jsp");
		   dispatcher.forward(req, res);

			
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/SistAirLine/index2.jsp");
			dispatcher.forward(req, res);
			   
		}
		
		
	}
	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	
	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	/***
	 * 단건조회
	 * 
	 */
	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
/***
 * 등록화면으로 이동
 * @param req
 * @param res
 * @throws ServletException
 * @throws IOException
 */
	public void loginMethod(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher=req.getRequestDispatcher("../LoginProcess.jsp");
		dispatcher.forward(req, res);
	}
	
	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			}

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
	}

}
