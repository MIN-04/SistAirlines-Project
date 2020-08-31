package Reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
import com.hr.cmn.DTO;
import com.hr.cmn.MessageVO;
import com.hr.cmn.SearchVO;
import com.hr.cmn.StringUtil;

import User.UserVO;
import member.MemberService;
import member.MemberVO;
import scheduleDTO.ScheduleDAO;
import scheduleDTO.ScheduleService;
import scheduleDTO.ScheduleVO;



@WebServlet(description = "결제페이지", urlPatterns = { "/reservationtest/reservationtest.do" })
public class ReservationCont extends HttpServlet implements ContHandler{
   private static final long serialVersionUID = 1L;
   /** resService 객체 */  
   private  ReservationService resService; 
   /** userService 객체 */
   private MemberService memberService;
   private ScheduleService scheduleService;
   
   
   /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationCont() {
       super();
       resService= new ReservationService();
        //cdService = new CodeService(); 
    }    
    
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      LOG.debug("doGet------");
      serviceHandler(request,response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      LOG.debug("1.doPost------");
      serviceHandler(request,response);
   }

   @Override
   public void serviceHandler(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      //1. work_div: 작업구분
      //2. request Encoding: utf-8
      //3. 기능별 분해
      //   거래분기:
      //   do_update : 수정
      //   do_delete : 삭제
      //   do_insert : 등록 
      //   do_retrieve : 목록조회
      //   do_selectOne : 단건조회
      
      
      req.setCharacterEncoding("utf-8");//resquest의 인코딩: utf-8
      String workDiv = StringUtil.nvl(req.getParameter("work_div"));//null -> ""
      LOG.debug("2.========================");
      LOG.debug("2.=workDiv="+workDiv);
      LOG.debug("2.========================");
      switch(workDiv) {//거래분기
      	  case "do_changeReservation"://수정
	          this.doChangeReservation(req, res);
	          break;
	         
      	  case "do_update"://수정
             this.doUpdate(req, res);
             break;
          case "do_delete"://삭제
             this.doDel(req, res);
             break;
          case"do_selectOne"://단건조회,단건조회 화면으로 이동
             this.doSelectOne(req, res);
             break;
          case "move_to_save"://등록화면으로 이동 
             doMoveToSave(req, res);
             break;
         case "do_insert"://단건 등록             
            this.doInsert(req,res);
            break;
         case "do_retrieve"://목록조회
             this.doRetrieve(req, res);
             break;
         case "do_member_retrieve"://목록조회
             this.doMemberRetrieve(req, res);
             break;
         case "do_Res_Retrieve"://목록조회
             this.doResRetrieve(req, res);
             break;
          default:
            LOG.debug("========================");
            LOG.debug("=작업구분을 확인하세요.workDiv="+workDiv);
            LOG.debug("========================");
            break;
      }
   
   }

   public void doChangeReservation(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	      LOG.debug("3.========================");
	      LOG.debug("3.=       doUpdate       =");
	      LOG.debug("3.========================");
	      
	      ReservationVO inVO = new ReservationVO();//param
	      //1.param read
	      //2.param BoardVO
	      //3.service 메소드 호출
	      
	      String resNum = StringUtil.nvl(req.getParameter("resNum"),"");
	      //작성자,수정자
	      String resPassport= StringUtil.nvl(req.getParameter("resPassport"),"");
	      //내용
	      String resState = StringUtil.nvl(req.getParameter("resState"),"");
	      int state=Integer.parseInt(resState);
	      
	      inVO.setResNum(resNum);
	      inVO.setResPassport(resPassport);
	      inVO.setResState(state);
	      
	      int flag = this.resService.doChangeState(inVO);
	      LOG.debug("3.========================");
	      LOG.debug("3.=         flag         ="+flag);
	      LOG.debug("3.========================");
	      
	      //응답처리 
	      res.setContentType("text/html;charset = utf-8 ");
	      PrintWriter out = res.getWriter();

	      String msg = "";
		  Gson  gson = new Gson(); 
		  if(1==flag && state==1) {
			  msg = "예약 상태를 '취소'상태로 변경하였습니다";
		  }else if(1==flag && state==2) {
			  msg = "예약 상태를 '예약대기'상태로 변경하였습니다";
		  }else {
			  msg = "예약 상태가 변경되지 않았습니다.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);

	      
	   }

   
   @Override
   public void doUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      LOG.debug("3.========================");
      LOG.debug("3.=       doUpdate       =");
      LOG.debug("3.========================");
      
      ReservationVO inVO = new ReservationVO();//param
      //1.param read
      //2.param BoardVO
      //3.service 메소드 호출
      
      //SEQ
      String seq = StringUtil.nvl(req.getParameter("seq"),"0");
      //제목
      String title = StringUtil.nvl(req.getParameter("title"),"");
      //작성자,수정자
      String regId = StringUtil.nvl(req.getParameter("reg_id"),"");
      //내용
      String contents = StringUtil.nvl(req.getParameter("contents"),"");
      

      
      int flag = this.resService.doUpdate(inVO);
      LOG.debug("3.========================");
      LOG.debug("3.=         flag         ="+flag);
      LOG.debug("3.========================");
      
      //응답처리 
      res.setContentType("text/html;charset = utf-8 ");
      PrintWriter out = res.getWriter();


      
   }

   @Override
   public void doInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         LOG.debug("3.========================");
         LOG.debug("3.=       doInsert       =");
         LOG.debug("3.========================");
         
         //resVO inVO = new resVO();//param
         ReservationVO inVO   = new ReservationVO();
         ReservationVO inVOA   = new ReservationVO();
         ReservationVO inVOT   = new ReservationVO();
         ReservationVO inVOC   = new ReservationVO();
            
         List<ReservationVO> inresVO = new ArrayList<ReservationVO>();
         
         
         String uNum  = StringUtil.nvl(req.getParameter("uNum"), "0") ;
         String adult = StringUtil.nvl(req.getParameter("adult"), "0") ;
         String child = StringUtil.nvl(req.getParameter("child"), "0") ;
         String todd  = StringUtil.nvl(req.getParameter("todd"), "0") ;
         String triptype  = StringUtil.nvl(req.getParameter("triptype"), "0") ;
         String notIC  = StringUtil.nvl(req.getParameter("notIC"), "0") ;
         String scPriceIn  = StringUtil.nvl(req.getParameter("scPrice"), "0") ;
         String scMileageIn  = StringUtil.nvl(req.getParameter("scMileage"), "0") ;
         
         int scPrice = Integer.parseInt(scPriceIn.replace(",","").trim());
         
         int scMileage=Integer.parseInt(scMileageIn);
         
         LOG.debug("triptype:"+triptype);
         LOG.debug("notIC:"+notIC);
         int flag = 0;
         int iCnt = 0;
         int uNum01=Integer.parseInt(uNum); 
         //int adultCnt=Integer.parseInt(adult); 
         //int childCnt=Integer.parseInt(child);
         //int toddCnt=Integer.parseInt(todd);
         
        
        /**        This is  성인 */ 
        String[] gender_A = req.getParameterValues("gender_A");   
 	    String[] ename_A = req.getParameterValues("ename_A");
 	    String[] scode_A = req.getParameterValues("scode_A");
 	    String[] passport_A = req.getParameterValues("passport_A");      
 	    String[] year_A = req.getParameterValues("year_A"); 
 	    String[] month_A = req.getParameterValues("month_A"); 
 	    String[] day_A = req.getParameterValues("day_A");
 	    
 	   /**        This is  소아 */ 
        String[] gender_C = req.getParameterValues("gender_C");   
 	    String[] ename_C = req.getParameterValues("ename_C");
 	    String[] scode_C = req.getParameterValues("scode_C");
 	    String[] passport_C = req.getParameterValues("passport_C");      
 	    String[] year_C = req.getParameterValues("year_C"); 
 	    String[] month_C = req.getParameterValues("month_C"); 
 	    String[] day_C = req.getParameterValues("day_C");
 	    
 	   /**        This is  유아 */ 
        String[] gender_T = req.getParameterValues("gender_T");   
 	    String[] ename_T = req.getParameterValues("ename_T");
 	    String[] scode_T = req.getParameterValues("scode_T");
 	    String[] passport_T = req.getParameterValues("passport_T");      
 	    String[] year_T = req.getParameterValues("year_T"); 
 	    String[] month_T = req.getParameterValues("month_T"); 
 	    String[] day_T = req.getParameterValues("day_T");
 	    
 	    
         if(triptype.equals("1") && notIC.equals("false")) {
        	 iCnt=1;
         }else if(triptype.equals("1") && notIC.equals("true")){
        	 iCnt=2;
         }else if(triptype.equals("2") && notIC.equals("false")){
        	 iCnt=2;
         }else if(triptype.equals("2") && notIC.equals("true")){
        	 iCnt=4;
         }
         LOG.debug("iCnt:"+iCnt);
         
         Enumeration<String> enums = req.getParameterNames();
         while(enums.hasMoreElements()) {
        	 String paramname =enums.nextElement();
        	 LOG.debug( "paramname: "+paramname);
         }
         
         if(uNum01 != 0) {//회원이면
         //회원인서트
         String[] gender_M = req.getParameterValues("gender_M");   
  	     String[] ename_M = req.getParameterValues("ename_M");
  	     String[] scode_M = req.getParameterValues("scode_M");;
  	     String[] passport_M = req.getParameterValues("passport_M");      
  	     String[] year_M = req.getParameterValues("year_M"); 
  	     String[] month_M = req.getParameterValues("month_M"); 
  	     String[] day_M = req.getParameterValues("day_M");
  	     String[] phone_M = req.getParameterValues("phone_M"); 
  	     String[] email_M = req.getParameterValues("email_M");
  	     
  	     for(int i=0; i<scode_M.length; i++) {
	  	     LOG.debug( "gender: "+gender_M[i]);
	  	     LOG.debug( "ename: "+ename_M[i]);
	  	     LOG.debug( "scode: "+scode_M[i]);
	  	     LOG.debug( "passport: "+passport_M[i]);
	  	     LOG.debug( "year: "+year_M[i]);
	  	     LOG.debug( "month: "+month_M[i]);
	  	     LOG.debug( "day: "+day_M[i]);
	  	     LOG.debug( "phone: "+phone_M[i]);
	  	     LOG.debug( "email: "+email_M[i]);
  	     }
  	    
  	  
  	     for(int i=0 ; i<iCnt ; i++) {//회원
  	    	String ResNum = resService.doSelectResNo(); 
  	    	 
  	    	 if(uNum != "0") {
  	    		 inVO.setResNum(ResNum);
  	    		 inVO.setResEname(ename_M[i]);
  	    		 inVO.setResPassport(passport_M[i] );
  	    		 inVO.setResBir(year_M[i]+month_M[i]+day_M[i]);
  	    		 inVO.setResphone(phone_M[i]);
  	    		 inVO.setResEmail(email_M[i]);
  	    		 inVO.setResWay(Integer.parseInt(triptype));
  	    		 inVO.setResPtype(1);
  	    		 inVO.setResGender(Integer.parseInt(gender_M[i]));
  	    		 inVO.setResState(0);
  	    		 inVO.setResPay(scPrice);
  	    		 inVO.setResMileage(scMileage);
  	    		 inVO.setResScode(scode_M[i]);
  	    		 inVO.setuNum(Integer.parseInt(uNum));
  	    		 
  	    		//성인
  	    		 if(Integer.parseInt(adult)>0) {
	    		 inVOA.setResNum(ResNum);
	    		 inVOA.setResEname(ename_A[i]);
	    		 inVOA.setResPassport(passport_A[i] );
	    		 inVOA.setResBir(year_A[i]+month_A[i]+day_A[i]);
	    		 inVOA.setResphone("");
	    		 inVOA.setResEmail("");
	    		 inVOA.setResWay(Integer.parseInt(triptype));
	    		 inVOA.setResPtype(1);
	    		 inVOA.setResGender(Integer.parseInt(gender_A[i]));
	    		 inVOA.setResState(0);
	    		 inVOA.setResPay(scPrice);
	    		 inVOA.setResMileage(scMileage);
	    		 inVOA.setResScode(scode_A[i]);
	    		 inVOA.setuNum(0);
  	    		 }
	    		 //소아
  	    		 if(Integer.parseInt(child)>0) {
	    		 inVOC.setResNum(ResNum);
	    		 inVOC.setResEname(ename_C[i]);
	    		 inVOC.setResPassport(passport_C[i] );
	    		 inVOC.setResBir(year_C[i]+month_C[i]+day_C[i]);
	    		 inVOC.setResphone("");
	    		 inVOC.setResEmail("");
	    		 inVOC.setResWay(Integer.parseInt(triptype));
	    		 inVOC.setResPtype(0.8);
	    		 inVOC.setResGender(Integer.parseInt(gender_C[i]));
	    		 inVOC.setResState(0);
	    		 inVOC.setResPay(scPrice*0.8);
	    		 inVOC.setResMileage(scMileage*0.8);
	    		 inVOC.setResScode(scode_C[i]);
	    		 inVOC.setuNum(0);
  	    		 }
	    		 //유아
  	    		 if(Integer.parseInt(todd)>0) {
	    		 inVOT.setResNum(ResNum);
	    		 inVOT.setResEname(ename_T[i]);
	    		 inVOT.setResPassport(passport_T[i] );
	    		 inVOT.setResBir(year_T[i]+month_T[i]+day_T[i]);
	    		 inVOT.setResphone("");
	    		 inVOT.setResEmail("");
	    		 inVOT.setResWay(Integer.parseInt(triptype));
	    		 inVOT.setResPtype(0.5);
	    		 inVOT.setResGender(Integer.parseInt(gender_T[i]));
	    		 inVOT.setResState(0);
	    		 inVOT.setResPay(scPrice*0.5);
	    		 inVOT.setResMileage(scMileage*0.5);
	    		 inVOT.setResScode(scode_T[i]);
	    		 inVOT.setuNum(0);
  	    		 }
  	    		 
  	    		 	flag = this.resService.doInsert(inVO); 
	    		 if(Integer.parseInt(adult)>0) {
	    			 flag = this.resService.doInsert(inVOA); 
	    		 }
	    		 if(Integer.parseInt(child)>0) {
	    			 flag = this.resService.doInsert(inVOC);
	    		 }
	    		 if(Integer.parseInt(todd)>0) {
	    			 flag = this.resService.doInsert(inVOT);
	    		 }  	 	 
  	    	 }
  	    	 //회원 
  	    	  
  	    	 
  	    	 
  	     }
  	     
  	 
  	    LOG.debug("3.========================");
  	    LOG.debug("3.=         flag         ="+flag);
  	    LOG.debug("3.========================");
               
         }else { //비회원이면
         //비회원
  	     
  	    String[] gender_N = req.getParameterValues("gender_N");   
	    String[] ename_N = req.getParameterValues("ename_N");
	    String[] scode_N = req.getParameterValues("scode_N");
	    String[] passport_N = req.getParameterValues("passport_N");      
	    String[] year_N = req.getParameterValues("year_N"); 
	    String[] month_N = req.getParameterValues("month_N"); 
	    String[] day_N = req.getParameterValues("day_N");
	    String[] phone_N = req.getParameterValues("phone_N"); 
	    String[] email_N = req.getParameterValues("email_N");
	   
	    for(int i=0; i<scode_N.length; i++) {
		     LOG.debug( "gender_N: "+gender_N[0]);
		     LOG.debug( "ename_N: "+ename_N[0]);
		     LOG.debug( "scode_N: "+scode_N[0]);
		     LOG.debug( "passport_N: "+passport_N[0]);
		     LOG.debug( "year_N: "+year_N[0]);
		     LOG.debug( "month_N: "+month_N[0]);
		     LOG.debug( "day_N: "+day_N[0]);
		     LOG.debug( "phone_N: "+phone_N[0]);
		     LOG.debug( "email_N: "+email_N[0]);
	    } 
	    
	     for(int i=0 ; i<iCnt ; i++) {
	    	String ResNum =  resService.doSelectResNo();
	    	 
	    	 if(uNum != null) {
	    		 inVO.setResNum(ResNum);
	    		 inVO.setResEname(ename_N[i]);
	    		 inVO.setResPassport(passport_N[i] );
	    		 inVO.setResBir(year_N[i]+month_N[i]+day_N[i]);
	    		 inVO.setResphone(phone_N[i]);
	    		 inVO.setResEmail(email_N[i]);
	    		 inVO.setResWay(Integer.parseInt(triptype));
	    		 inVO.setResPtype(1);
	    		 inVO.setResGender(Integer.parseInt(gender_N[i]));
	    		 inVO.setResState(0);
	    		 inVO.setResPay(scPrice);
	    		 inVO.setResMileage(scMileage);
	    		 inVO.setResScode(scode_N[i]);
	    		 inVO.setuNum(0);
  	    		 
	    		
	    		 //성인
	    		 if(Integer.parseInt(adult)>0) {
	    		 inVOA.setResNum(ResNum);
	    		 inVOA.setResEname(ename_A[i]);
	    		 inVOA.setResPassport(passport_A[i] );
	    		 inVOA.setResBir(year_A[i]+month_A[i]+day_A[i]);
	    		 inVOA.setResphone("");
	    		 inVOA.setResEmail("");
	    		 inVOA.setResWay(Integer.parseInt(triptype));
	    		 inVOA.setResPtype(1);
	    		 inVOA.setResGender(Integer.parseInt(gender_A[i]));
	    		 inVOA.setResState(0);
	    		 inVOA.setResPay(scPrice);
	    		 inVOA.setResMileage(scMileage);
	    		 inVOA.setResScode(scode_A[i]);
	    		 inVOA.setuNum(0);
	    		 }
	    		 if(Integer.parseInt(child)>0) {
	    		 //소아
	    		 inVOC.setResNum(ResNum);
	    		 inVOC.setResEname(ename_C[i]);
	    		 inVOC.setResPassport(passport_C[i] );
	    		 inVOC.setResBir(year_C[i]+month_C[i]+day_C[i]);
	    		 inVOC.setResphone("");
	    		 inVOC.setResEmail("");
	    		 inVOC.setResWay(Integer.parseInt(triptype));
	    		 inVOC.setResPtype(0.8);
	    		 inVOC.setResGender(Integer.parseInt(gender_C[i]));
	    		 inVOC.setResState(0);
	    		 inVOC.setResPay(scPrice*0.8);
	    		 inVOC.setResMileage(scMileage*0.8);
	    		 inVOC.setResScode(scode_C[i]);
	    		 inVOC.setuNum(0);
	    		 }
	    		 //유아
	    		 if(Integer.parseInt(todd)>0) {
	    		 inVOT.setResNum(ResNum);
	    		 inVOT.setResEname(ename_T[i]);
	    		 inVOT.setResPassport(passport_T[i] );
	    		 inVOT.setResBir(year_T[i]+month_T[i]+day_T[i]);
	    		 inVOT.setResphone("");
	    		 inVOT.setResEmail("");
	    		 inVOT.setResWay(Integer.parseInt(triptype));
	    		 inVOT.setResPtype(0.5);
	    		 inVOT.setResGender(Integer.parseInt(gender_T[i]));
	    		 inVOT.setResState(0);
	    		 inVOT.setResPay(scPrice*0.5);
	    		 inVOT.setResMileage(scMileage*0.5);
	    		 inVOT.setResScode(scode_T[i]);
	    		 inVOT.setuNum(0);
	    		 }
	    		 
	    		 flag = this.resService.doInsert(inVO); 
	    		 if(Integer.parseInt(adult)>0) {
	    		 flag = this.resService.doInsert(inVOA);
	    		 }
	    		 if(Integer.parseInt(child)>0) {
	    		 flag = this.resService.doInsert(inVOC);
	    		 }
	    		 if(Integer.parseInt(todd)>0) {
	    		 flag = this.resService.doInsert(inVOT);
	    		 }
	    		 }
	      }
	    
	    //flag = this.resService.doInsert(inVO); 
	    LOG.debug("3.========================");
	    LOG.debug("3.=         flag         ="+flag);
	    LOG.debug("3.========================");
  	    
      }//비회원구분
        LOG.debug("여까지 왔니?");
//        req.setAttribute("price", scPrice);//목록
// 	    RequestDispatcher  dispatcher=req.getRequestDispatcher("../Payment.jsp");
// 		dispatcher.forward(req, res);
 		 res.setContentType("text/html;charset=utf-8");
		  PrintWriter out=res.getWriter();


		  String msg = "";
		  Gson  gson = new Gson(); 
		 
		  if(1==flag) {
			  msg ="결제페이지로\n이동합니다.";
		  }else {
			  msg = "스미마셍!\n등록 실패.";
		  }
		  
		  String gsonStr =  gson.toJson(new MessageVO(String.valueOf(flag), msg));
		  LOG.debug("====================");
		  LOG.debug("=gsonStr="+gsonStr);
		  LOG.debug("====================");
		  out.println(gsonStr);
        
     }
   @Override
   public void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      LOG.debug("=====================");
      LOG.debug("=       doDel       =");
      LOG.debug("=====================");
      
      //1.vo 변수 선언
      //2.param read
      //3.vo에 param set
      //4.service호출
      //5.Gson message
      //6.forword
      
      ReservationVO inVO = new ReservationVO();
      String seq = StringUtil.nvl(req.getParameter("seq"));
      //inVO.setSeq(Integer.parseInt(seq));
      
      int flag = this.resService.doDelete(inVO);
      
      Gson gson = new Gson();
      String msg = "";
      String gsonString = "";
      MessageVO msgVO = new MessageVO();
      
      if(flag > 0) {
         msg = "삭제되었습니다.";
      }else {
         msg = "삭제 실패다 임마~!!";
      }
      
      msgVO.setMsgId(String.valueOf(flag));
      msgVO.setMsgContents(msg);
      
      gsonString = gson.toJson(msgVO);
      LOG.debug("gsonString="+gsonString);
      
      res.setContentType("text/html;charset=utf-8");
      PrintWriter out = res.getWriter();
      out.print(gsonString);
   }
   
   /**
    * 
    *Method Name:doSelectOne
    *작성일: 2020. 2. 26.
    *작성자: sist
    *설명: 단건조회 화면으로 이동
    *@param req
    *@param res
    *@throws ServletException
    *@throws IOException
    */
   @Override
   public void doSelectOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      //1.param
      //2.param to set vo
      //3.service call
      //4.request set
      //5.forword
      ReservationVO inVO = new ReservationVO();
      LOG.debug("*** doSelectOne");
      String resNum = StringUtil.nvl(req.getParameter("resNum"));
      String resPassport = StringUtil.nvl(req.getParameter("resPassport"));
      LOG.debug("resNum:"+resNum);
      LOG.debug("resPassport:"+resPassport);
      
      inVO.setResNum(resNum);
      inVO.setResPassport(resPassport);
      
      //inVO.setSeq(Integer.parseInt(seq));
      ReservationVO outVO = (ReservationVO)resService.doSelectOne(inVO);
      
      req.setAttribute("outVO", outVO);
      
      RequestDispatcher dispatcher = req.getRequestDispatcher("../selectOneReservation.jsp");
      dispatcher.forward(req , res);
   }
   
   /**
    * 
    *Method Name:doMoveToSave
    *작성일: 2020. 2. 26.
    *작성자: sist
    *설명: 등록화면으로 이동
    *@param req
    *@param res
    *@throws ServletException
    *@throws IOException
    */
   public void doMoveToSave(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/board/board_save.jsp");
      dispatcher.forward(req, res);
   }
   
   @Override
   public void doRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      //1.param
      //2.param set VO
      //3.service호출
      //4.특정화면+data
      SearchVO inVO = new SearchVO();
      
      String searchDiv  = StringUtil.nvl(req.getParameter("search_div"));
      String searchWord = StringUtil.nvl(req.getParameter("search_word"));
      String pageSize   = StringUtil.nvl(req.getParameter("page_size"),"10");
      String pageNum    = StringUtil.nvl(req.getParameter("page_num"),"1");
      
      inVO.setSearchDiv(searchDiv);
      inVO.setSearchWord(searchWord);
      inVO.setPageNum(Integer.parseInt(pageNum));
      inVO.setPageSize(Integer.parseInt(pageSize));
      
      LOG.debug("===================");
      LOG.debug("=inVO="+inVO);
      LOG.debug("===================");
      
      //---------------------------------
      
      
      List<ReservationVO> list = (List<ReservationVO>)this.resService.doRetrieve(inVO);
      
      LOG.debug("-----------------");
      for(ReservationVO vo : list) {
         LOG.debug(vo);
      }
      
      
      LOG.debug("-----------------");
      //http://localhost:8080/DaoWEB/board/board.do?search_div=10&search_word=&page_size=10&page_num=1&work_div=do_retrieve
      
      int totalCnt = 0;
      //총글수
      
      if(null != list && list.size() > 0) {
         ReservationVO totalVO = list .get(0);
         totalCnt = totalVO.getTotal();
      }
      //code
//      req.setAttribute("searchList", searchList);//search Code
//      req.setAttribute("pageSizeList", pageSizeList);//pageSize Code
      
      req.setAttribute("list", list);//목록 
      req.setAttribute("paramVO", inVO);//PARAM
      req.setAttribute("totalCnt", totalCnt);//총글수
      
       //총글수
      RequestDispatcher dispatcher = req.getRequestDispatcher("/board/board_list.jsp");
      dispatcher.forward(req, res);
      
   }
   
   public void doMemberRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   	
	   String resNum=(String)req.getParameter("resNum");
	   	LOG.debug("resNum: "+resNum);
	   
	      ReservationVO inVO = new ReservationVO();
	     inVO.setResNum(resNum);
	      
	      
	      LOG.debug("===================");
	      LOG.debug("=inVO="+inVO);
	      LOG.debug("===================");
	      
	      //---------------------------------
	      
	      
	      List<ReservationVO> list = (List<ReservationVO>)this.resService.doMemberRetrieve(inVO);
	      
	      LOG.debug("-----------------");
	      for(ReservationVO vo : list) {
	         LOG.debug(vo);
	      }
	      
	      
	      LOG.debug("-----------------");
	      //http://localhost:8080/DaoWEB/board/board.do?search_div=10&search_word=&page_size=10&page_num=1&work_div=do_retrieve
	      
	      int totalCnt = 0;
	      //총글수
	      
	      if(null != list && list.size() > 0) {
	         ReservationVO totalVO = list .get(0);
	         totalCnt = totalVO.getTotal();
	      }
	      //code
//	      req.setAttribute("searchList", searchList);//search Code
//	      req.setAttribute("pageSizeList", pageSizeList);//pageSize Code
	      
	      req.setAttribute("list", list);//목록 
	      
	       //총글수
	      RequestDispatcher dispatcher = req.getRequestDispatcher("../selectOneReservation.jsp");
	      dispatcher.forward(req, res);
	      
	   }
   
   
   public void doResRetrieve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   	
	   HttpSession  session  =req.getSession();
	   MemberVO memberVO=(MemberVO)session.getAttribute("user");
	   	LOG.debug("memberVO: "+memberVO);
	   
	     ReservationVO inVO = new ReservationVO();
	     inVO.setuNum(memberVO.getuNum());
	      
	      
	      LOG.debug("===================");
	      LOG.debug("=inVO="+inVO);
	      LOG.debug("===================");
	      
	      //---------------------------------
	      
	      
	      List<ReservationVO> list = (List<ReservationVO>)this.resService.doResRetrieve(inVO);
	      List<ScheduleVO> scList=new ArrayList<ScheduleVO>();
	      ScheduleDAO scDao=new ScheduleDAO();
	      
	      LOG.debug("-----------------");
	      for(ReservationVO vo : list) {
	    	  ScheduleVO scVO=new ScheduleVO();
	    	  scVO.setScCode(vo.getResScode());
	    	  scList.add((ScheduleVO)scDao.doSelectOne(scVO));  
	      }
	      
	      
	      LOG.debug("-----------------");
	      //http://localhost:8080/DaoWEB/board/board.do?search_div=10&search_word=&page_size=10&page_num=1&work_div=do_retrieve
	      
	      int totalCnt = 0;
	      //총글수
	      
	      if(null != list && list.size() > 0) {
	         ReservationVO totalVO = list .get(0);
	         totalCnt = totalVO.getTotal();
	      }
	      //code
//	      req.setAttribute("searchList", searchList);//search Code
//	      req.setAttribute("pageSizeList", pageSizeList);//pageSize Code
	      
	      req.setAttribute("scList", scList);//목록 
	      req.setAttribute("list", list);//목록 
	      req.setAttribute("paramVO", inVO);//PARAM
	      req.setAttribute("totalCnt", totalCnt);//총글수
	      
	       //총글수
	      RequestDispatcher dispatcher = req.getRequestDispatcher("../selectReservation.jsp");
	      dispatcher.forward(req, res);
	      
	   }
}