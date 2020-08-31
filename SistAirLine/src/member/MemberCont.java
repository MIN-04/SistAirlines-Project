package member;

import com.google.gson.Gson;
import com.hr.cmn.ContHandler;
import com.hr.cmn.MessageVO;
import com.hr.cmn.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberCont
 */
@WebServlet(description = "회원관리 로그인", urlPatterns = { "/member/member.do","/member/member.json" })
public class MemberCont extends HttpServlet implements ContHandler {
   private static final long serialVersionUID = 1L;
    private MemberService memberService;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCont() {
       memberService = new MemberService();
    }

   /**
     * @see ContHandler#doDel(HttpServletRequest, HttpServletResponse)
     */
    public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

   /**
     * @see ContHandler#doInsert(HttpServletRequest, HttpServletResponse)
     */
    public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

   /**
     * @see ContHandler#doSelectOne(HttpServletRequest, HttpServletResponse)
     */
    public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

   /**
     * @see ContHandler#doUpdate(HttpServletRequest, HttpServletResponse)
     */
    public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

   /**
     * @see ContHandler#serviceHandler(HttpServletRequest, HttpServletResponse)
     */
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
         case "do_login"://로그인
            this.doLogin(req, res);
         break;
         case "do_logout"://로그아웃
            this.doLogout(req, res);
         break;
         
         default:
              LOG.debug("====================");
              LOG.debug("=작업구분을 확인 하세요.workDiv="+workDiv);
              LOG.debug("====================");
            break;
      
      }
    }
    /**
     * 
     *Method Name:doLogout
     *작성일: 2020. 3. 13.
     *작성자: sist
     *설명:
     *@param req
     *@param res
     *@throws ServletException
     *@throws IOException
     */
    public void doLogout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
       //1.param read
      //2.param MemberVO
      //3.service 메소드 호출
       
        HttpSession  httpSession  =req.getSession();
        if(null !=httpSession) {
           httpSession.removeAttribute("user");
           httpSession.invalidate();
           LOG.debug("httpSession:"+httpSession);
        }
       LOG.debug("LogOut");
        
        String path = "/SistAirLine/index.jsp";
        res.sendRedirect(path);
//        RequestDispatcher dequestDispatcher=req.getRequestDispatcher(path);
//        dequestDispatcher.forward(req, res);
        
    }
    
   /**
    * 
    *Method Name:doLogin
    *작성일: 2020. 3. 12.
    *작성자: sist
    *설명: 로그인 처리 
    *@param req
    *@param res
    *@throws ServletException
    *@throws IOException
    */
    public void doLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
       //1.param read
      //2.param MemberVO
      //3.service 메소드 호출
        MemberVO  inVO=new MemberVO();
        MemberVO  outVO=new MemberVO();
        String memberId = StringUtil.nvl(req.getParameter("uId"));
        String password = StringUtil.nvl(req.getParameter("uPw"));
        inVO.setuID(memberId);
        inVO.setuPW(password);
        
        LOG.debug("-----------------------");
        LOG.debug("-inVO-"+inVO);
        LOG.debug("-----------------------");
        MessageVO checkMsg = (MessageVO)this.memberService.loginCheck(inVO);
      //10: 아이디 없음 , 20: 비번오류 30:관리자 40:일반회원

        if(checkMsg.getMsgId().equals("30")) {//관리자
           outVO = (MemberVO) memberService.doSelectOne(inVO);
           HttpSession  httpSession  =req.getSession();
           httpSession.setAttribute("user", outVO);
        }else if(checkMsg.getMsgId().equals("40")) {//일반
           outVO = (MemberVO) memberService.doSelectOne(inVO);
           HttpSession  httpSession  =req.getSession();
           httpSession.setAttribute("user", outVO);
        }
        
        //checkMsg -> JSON
        Gson gson=new Gson();
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter  out = res.getWriter();
        String gsonStr = "";
        gsonStr = gson.toJson(checkMsg);
        LOG.debug("gsonStr:"+gsonStr);
        out.print(gsonStr);
    }
    
    
    
    
   /**
     * @see ContHandler#doRetrieve(HttpServletRequest, HttpServletResponse)
     */
    public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      LOG.debug("1. doGet------");
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