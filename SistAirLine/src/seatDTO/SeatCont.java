package seatDTO;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.cmn.ContHandler;
import com.hr.cmn.StringUtil;

import scheduleDTO.ScheduleService;

@WebServlet(description = "게시판", urlPatterns = { "/seatDTO/seat.do" })

public class SeatCont extends HttpServlet implements ContHandler{
	private static final long serialVersionUID = 1L;
	private SeatService seatService;
       
    public SeatCont() {
        super();
        seatService =new SeatService();
       }

    
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
	
		String workDiv=StringUtil.nvl(req.getParameter("work_div"));
		LOG.debug("=================");
		LOG.debug("=doInsert.workDiv="+workDiv);
		LOG.debug("=================");

		switch (workDiv) {

		case "do_update": //등록화면으로 이동
			this.doUpdate(req, res);
			break;
		case "do_insert": //단건등록
			this.doInsert(req, res);
			break;
		case "doOneWay": //등록화면으로 이동
			this.doUpdate(req, res);
			break;
		case "doRoundTrip": //단건등록
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

	@Override
	public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String scCode=StringUtil.nvl(req.getParameter("scCode"));
		SeatDTO inVO =new SeatDTO();
		inVO.setScCode(scCode);
		List<SeatDTO> outList=(List<SeatDTO>)this.seatService.doRetrieve(inVO);
		

		req.setAttribute("listAround3", listAround3);//왕복 - 인천 미포함 갈때
		req.setAttribute("listAround4", listAround4);//왕복 - 인천 미포함 올때 

		RequestDispatcher dispatcher=req.getRequestDispatcher("/seats/selectSeats2.jsp");
		dispatcher.forward(req, res);
		
	}

}
