


package com.hr.cmn;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * 모든 메소드는 static method
 * @author sist
 *
 */
public class StringUtil {
   private static final Logger LOG = Logger.getLogger(StringUtil.class);
   //scCode,scCodeR,scCodeAdd,scCodeRAdd,scDcity,scAcity,scDcityR,scAcityR
   public static String memberInput(int userGender, String userEngName, String year, String month, String day,String scCode
		   							,String scCodeR,String scCodeAdd,String scCodeRAdd,String scDcity,String scAcity,String scDcityR,String scAcityR
		   							,String gender,String ename,String sCode,String seatNum, String passport, String phone, String email) {
	   StringBuilder html=new StringBuilder();
		   html.append("<div class=\"container mt-4\"   style=\"max-width: 800px;\">                                       \n");
		   html.append("<h1>회원</h1>                                                                                       \n");
		   html.append("<table class =\"table table-hover table-bordered\">                                                \n");
		   html.append("<thead class=\"bg-primary text-center\">                                                           \n");
		   html.append("<th class=\"text-center\" colspan=\"2\">정보</th>                                                   \n");
		   html.append("</thead>                                                                                           \n");
		   html.append("<tbody class=\"text-center\">                                                                      \n");
		   html.append("<tr class=\"text-center\">                                                                         \n");
		   html.append("<td><b>승객구분</b></td>                                                                             \n");
		   html.append("<td><b> 																						   \n");
	   if(userGender==1) {
		   html.append("<input type=\"radio\" name=\""+gender+"\" value=\"1\" checked>남                                                               \n");
		   html.append("<input type=\"radio\" value=\"2\">여                                                                                                                \n");
	   }else {
		   html.append("<input type=\"radio\" value=\"1\">남                                                                                                                \n");
		   html.append("<input type=\"radio\" name=\""+gender +"\" value=\"2\" checked>여                                                             \n");
	   }
		   html.append("</b>                                                                                                \n");
		   html.append("</td>                                                                                               \n");
		   html.append("</tr>                                                                                               \n");
		   html.append("<tr>                                                                                                \n");
		   html.append("<td>영어이름</td>                                                                                     \n");
		   html.append("<td>                                                                                                \n");
		   html.append("<input type=\"text\" name=\""+ename+"\" id=\"ename\" class=\"form-control mr-sm-2\"                 \n");
		   html.append("        placeholder=\"영어이름을 입력하세요.\"	 value=\""+userEngName+"\">                                 \n");
		   html.append("</td>                                                                                               \n");
		   html.append("</tr>                                                                                               \n");
		   html.append("<tr>                                                                                                \n");
		  
		   html.append("				<tr>                                                                                \n");
		   html.append("					<td>["+scDcity+"-"+scAcity+"행] 좌석선택"+"</td>                                   \n");
		   html.append("					<td>                                                                            \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"1\"                \n");
		   html.append("						class=\"form-control mr-sm-2\">                                             \n");
		   html.append("					                                                                                \n");
		   html.append("					                                                                                \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                    \n");
		   html.append("							onclick=\"openChild('"+scCode+"','"+seatNum+"1')\">좌석선택1</label>      \n");
		   html.append("					</td>                                                                           \n");
		   html.append("				</tr>                                                                               \n");
	 
	   if(scCodeR!=null && scAcityR==null) {//왕복 인천있는
		   html.append("				<tr>                                                                                \n");
		   html.append("					<td>["+scAcity+"-"+scDcity+"행] 좌석선택"+"</td>                                   \n");
		   html.append("					<td>                                                                            \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"2\"                \n");
		   html.append("						class=\"form-control mr-sm-2\">                                             \n");
		   html.append("					                                                                                \n");
		   html.append("					                                                                                \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                    \n");
		   html.append("							onclick=\"openChild('"+scCodeR+"','"+seatNum+"2')\">좌석선택2</label>     \n");
		   html.append("					</td>                                                                           \n");
		   html.append("				</tr>                                                                               \n");
		  }
	   if(scCodeR!=null && scAcityR!=null) {//편도 인천없는
		   html.append("				<tr>                                                                                \n");
		   html.append("					<td>["+scDcityR+"-"+scAcityR+"행] 좌석선택"+"</td>                                 \n");
		   html.append("					<td>                                                                            \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"2\"                \n");
		   html.append("						class=\"form-control mr-sm-2\">                                             \n");
		   html.append("					                                                                                \n");
		   html.append("					                                                                                \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                    \n");
		   html.append("							onclick=\"openChild('"+scCodeR+"','"+seatNum+"2')\">좌석선택2</label>     \n");
		   html.append("					</td>                                                                           \n");
		   html.append("				</tr>                                                                               \n");
		  }
	   if(scCodeAdd!=null) {//왕복 인천없는
		   html.append("				<tr>                                                                                \n");
		   html.append("					<td>["+scAcityR+"-"+scDcityR+"행] 좌석선택"+"</td>                                 \n");
		   html.append("					<td>                                                                            \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"3\"                \n");
		   html.append("						class=\"form-control mr-sm-2\">                                             \n");
		   html.append("					                                                                                \n");
		   html.append("					                                                                                \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                    \n");
		   html.append("							onclick=\"openChild('"+scCodeAdd+"','"+seatNum+"3')\">좌석선택3</label>   \n");
		   html.append("					</td>                                                                           \n");
		   html.append("				</tr>                                                                               \n");
	   }
	  if(scCodeRAdd!=null) {
		   html.append("				<tr>                                                                                 \n");
		   html.append("					<td>["+scAcity+"-"+scDcity+"행] 좌석선택"+"</td>                                    \n");
		   html.append("					<td>                                                                             \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"4\"                 \n");
		   html.append("						class=\"form-control mr-sm-2\">                                              \n");
		   html.append("					                                                                                 \n");
		   html.append("					                                                                                 \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                     \n");
		   html.append("							onclick=\"openChild('"+scCodeRAdd+"','"+seatNum+"4')\">좌석선택4</label>   \n");
		   html.append("					</td>                                                                            \n");
		   html.append("				</tr>                                                                                \n");
	  }
	
		   html.append("<td>여권번호</td>                                                                                      \n");
		   html.append("<td>                                                                                                 \n");
		   html.append("<input type=\"text\" name=\""+passport+"\" id=\""+passport+"\"	class=\"form-control mr-sm-2\"       \n");
		   html.append("       required=\"required\" placeholder=\"여권번호을 입력하세요.\">                                       \n");
		   html.append("</td>                                                                                                \n");
		   html.append("</tr>                                                                                                \n");
		   html.append("<tr>                                                                                                 \n");
		   html.append("<td>생년월일</td>                                                                                      \n");
		   html.append("<td>                                                                                                 \n");
		   html.append("<script language=\"javascript\">               													     \n");
		   html.append(" Today("+year+","+month+","+day+", '_M','year_M','month_M','day_M','year','month','day');     	     \n");
		   html.append("</script>																							 \n");
		   html.append("<lable class=\"btn btn-outline-success my-2 my-sm-0\"                                                \n");
		   html.append("                onclick=\"openChild('"+scCode+"','userseatNum')\">좌석선택</label>                     \n");
		   html.append("</td>                                                                                                \n");
		   html.append("</tr>                                                                                                \n");
		   html.append("</tbody>                                                                                             \n");
		   html.append("</table>                                                                                             \n");
		   html.append("<hr/>                                                                                                \n");
		   html.append("</div>	                                                                                             \n");  
		   
		   html.append("<div class=\"container\" style=\"max-width: 800px;\">                        \n");      
	       html.append("	<h1>연락처 정보</h1>                                                        \n");     
	       html.append("                                                                             \n");     
		   html.append(" 	<table class=\"table table-hover  table-bordered\">                      \n");     
		   html.append("                                                                             \n");     
		   html.append("        <thead class=\"bg-primary text-center\"><!-- 테이블 header -->         \n");     
		   html.append("              <th class=\"text-center\" colspan=\"3\">정보</th>               \n");     
		   html.append("        </thead>                                                             \n");     
		   html.append("                                                                             \n");     
		   html.append("           <tbody class=\"text-center\"><!-- data영역 -->                      \n");     
		   html.append("              <tr class=\"text-left\">                                       \n");     
		   html.append("                 <td colspan=\"2\">연락처</td>                                 \n");     
		   html.append("                 <td>                                                        \n");     
		   html.append("                   <input type=\"text\" name=\""+phone+"\" id=\""+phone+"\"  \n");     
		   html.append("                    class=\"form-control\" placeholder=\"연락처를 입력하세 요.\">  \n");     
		   html.append("                  </td>                                                      \n");     
		   html.append("              </tr>                                                          \n");     
		   html.append("                                                                             \n");     
		   html.append("              <tr class=\"text-left\">                                       \n");     
		   html.append("                 <td colspan=\"2\">이메일</td>                                 \n");            
		   html.append("                 <td  colspan=2>                                             \n");     
		   html.append("                    <input type=\"text\" name=\""+email+"\" id=\""+email+"\" \n");     
		   html.append("                    class=\"form-control\" placeholder=\"이메일을 입력하세요.\">   \n");     
		   html.append("                 </td>                                                       \n");     
		   html.append("              </tr>                                                          \n");     
		   html.append("           </tbody>                                                          \n");     
		   html.append("    </table>                                                                 \n");     
	  	   html.append("</div>                                                                       \n"); 
		   return html.toString();
     }
//------------------------------------------------------------------- 멤버 끝 -------------------------------------------------------------------//

//------------------------------------------------------------------- 논멤버  -------------------------------------------------------------------//   
   public static String nonMemberInput(String scCode ,String scCodeR,String scCodeAdd,String scCodeRAdd
		   								,String scDcity,String scAcity,String scDcityR,String scAcityR
		   								,String gender,String ename,String sCode,String seatNum
		   								, String passport, String phone, String email) {
	   StringBuilder html=new StringBuilder();
		   html.append("<div class=\"container mt-5\" style=\"max-width: 800px;\">                                           \n");
		   html.append("		<h1> 비회원 </h1>                                                                              \n");
		   html.append("                                                                                                     \n");
		   html.append("		<table class=\"table table-hover  table-bordered\">                                          \n");
		   html.append("                                                                                                     \n");
		   html.append("			<thead class=\"bg-primary text-center\">                                                 \n");
		   html.append("				<!-- 테이블 header -->                                                                 \n");
		   html.append("				<th class=\"text-center\" colspan=\"2\">정보</th>                                     \n");
		   html.append("			</thead>                                                                                 \n");
		   html.append("                                                                                                     \n");
		   html.append("			<tbody class=\"text-center\">                                                            \n");
		   html.append("				<!-- data영역 -->                                                                      \n");
		   html.append("				<tr class=\"text-center\">                                                           \n");
		   html.append("				  <td><b>승객구분</b></td>                                                             \n");
		   html.append("				  <td>                                                                               \n");
		   html.append("				    <b>                                                                              \n");
		   html.append("				      <input type=\"radio\" name=\""+gender+"\" value=\"1\" checked>남                           \n");
		   html.append("					  <input type=\"radio\" name=\""+gender+"\" value=\"2\">여                                         \n");
		   html.append("					</b>                                                                             \n");
		   html.append("				  </td>                                                                              \n");
		   html.append("				</tr>                                                                                \n");
		   html.append("				                                                                                     \n");
		   html.append("				<tr>                                                                                 \n");
		   html.append("					<td>영어이름</td>                                                                  \n");
		   html.append("					<td>                                                                             \n");
		   html.append("				      <input type=\"text\" name=\""+ename+"\" id=\""+ename+"\"                       \n");
		   html.append("					  class=\"form-control mr-sm-2\" placeholder=\"영어이름을 입력하세요.\">               \n");
		   html.append("					</td>                                                                            \n");
		   html.append("				</tr>                                                                                \n");
		   html.append("                                                                                                     \n");
		   html.append("				<tr>                                                                                 \n");
		   
		   html.append("				<tr>                                                                                 \n");
		   html.append("					<td>["+scDcity+"-"+scAcity+"행] 좌석선택"+"</td>                                    \n");
		   html.append("					<td>                                                                             \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"1\"                 \n");
		   html.append("						class=\"form-control mr-sm-2\">                                              \n");
		   html.append("					                                                                                 \n");
		   html.append("					                                                                                 \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                     \n");
		   html.append("							onclick=\"openChild('"+scCode+"','"+seatNum+"1')\">좌석선택1</label>       \n");
		   html.append("					</td>                                                                            \n");
		   html.append("				</tr>                                                                                \n");
	 
	   if(scCodeR!=null && scAcityR==null) {//왕복 인천있는
		   html.append("				<tr>                                                                                 \n");
		   html.append("					<td>["+scAcity+"-"+scDcity+"행] 좌석선택"+"</td>                                    \n");
		   html.append("					<td>                                                                             \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"2\"                 \n");
		   html.append("						class=\"form-control mr-sm-2\">                                              \n");
		   html.append("					                                                                                 \n");
		   html.append("					                                                                                 \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                     \n");
		   html.append("							onclick=\"openChild('"+scCodeR+"','"+seatNum+"2')\">좌석선택2</label>      \n");
		   html.append("					</td>                                                                            \n");
		   html.append("				</tr>                                                                                \n");
		  }
	   if(scCodeR!=null && scAcityR!=null) {//편도 인천없는
		   html.append("				<tr>                                                                                 \n");
		   html.append("					<td>["+scDcityR+"-"+scAcityR+"행] 좌석선택"+"</td>                                  \n");
		   html.append("					<td>                                                                             \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"2\"                 \n");
		   html.append("						class=\"form-control mr-sm-2\">                                              \n");
		   html.append("					                                                                                 \n");
		   html.append("					                                                                                 \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                     \n");
		   html.append("							onclick=\"openChild('"+scCodeR+"','"+seatNum+"2')\">좌석선택2</label>      \n");
		   html.append("					</td>                                                                            \n");
		   html.append("				</tr>                                                                                \n");
		  }
	   if(scCodeAdd!=null) {//왕복 인천없는
		   html.append("				<tr>                                                                                 \n");
		   html.append("					<td>["+scAcityR+"-"+scDcityR+"행] 좌석선택"+"</td>                                  \n");
		   html.append("					<td>                                                                             \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"3\"                 \n");
		   html.append("						class=\"form-control mr-sm-2\">                                              \n");
		   html.append("					                                                                                 \n");
		   html.append("					                                                                                 \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                     \n");
		   html.append("							onclick=\"openChild('"+scCodeAdd+"','"+seatNum+"3')\">좌석선택3</label>    \n");
		   html.append("					</td>                                                                            \n");
		   html.append("				</tr>                                                                                \n");
	      }
	   if(scCodeRAdd!=null) {
		   html.append("				<tr>                                                                                 \n");
		   html.append("					<td>["+scAcity+"-"+scDcity+"행] 좌석선택"+"</td>                                    \n");
		   html.append("					<td>                                                                             \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"4\"                 \n");
		   html.append("						class=\"form-control mr-sm-2\">                                              \n");
		   html.append("					                                                                                 \n");
		   html.append("					                                                                                 \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                     \n");
		   html.append("							onclick=\"openChild('"+scCodeRAdd+"','"+seatNum+"4')\">좌석선택4</label    \n");
		   html.append("					</td>                                                                            \n");
		   html.append("				</tr>                                                                                \n");
	      }   
		   html.append("				<tr>                                                                                 \n");
		   html.append("				  <td>여권번호</td>                                                                    \n");
		   html.append("				  <td>                                                                               \n");
		   html.append("				      <input type=\"text\" name=\""+passport+"\" id=\""+passport+"\"                 \n");
		   html.append("					  class=\"form-control mr-sm-2\" required=\"required\"                           \n");
		   html.append("					  placeholder=\"여권번호을 입력하세요.\">                                              \n");
		   html.append("				  </td>                                                                              \n");
		   html.append("				</tr>                                                                                \n");
		   html.append("				                                                                                     \n");
		   html.append("				<tr>                                                                                 \n");
		   html.append("				  <td>생년월일</td>                                                                    \n");
		   html.append("				  <td>                                                                               \n");
		   html.append("					  <script language=\"javascript\"> 										         \n");
		   html.append("						Today(null,null,null,'_N','year_N','month_N','day_N','year','month','day');	 \n");	
		   html.append("                      </script>																	     \n");
		   html.append("				  </td>                                                                              \n");
		   html.append("				</tr>                                                                                \n");
		   html.append("			</tbody>                                                                                 \n");
		   html.append("		 </table>                                                                                    \n");
		   html.append("	   <hr/>                                                                                         \n");
		   html.append("	</div>																						     \n");
		   
		   html.append("<div class=\"container\" style=\"max-width: 800px;\">                        \n");      
	       html.append("	<h1>연락처 정보</h1>                                                        \n");     
	       html.append("                                                                             \n");     
		   html.append(" 	<table class=\"table table-hover  table-bordered\">                      \n");     
		   html.append("                                                                             \n");     
		   html.append("        <thead class=\"bg-primary text-center\"><!-- 테이블 header -->         \n");     
		   html.append("              <th class=\"text-center\" colspan=\"3\">정보</th>               \n");     
		   html.append("        </thead>                                                             \n");     
		   html.append("                                                                             \n");     
		   html.append("           <tbody class=\"text-center\"><!-- data영역 -->                      \n");     
		   html.append("              <tr class=\"text-left\">                                       \n");     
		   html.append("                 <td colspan=\"2\">연락처</td>                                 \n");     
		   html.append("                 <td>                                                        \n");     
		   html.append("                   <input type=\"text\" name=\""+phone+"\" id=\""+phone+"\"  \n");     
		   html.append("                    class=\"form-control\" placeholder=\"연락처를 입력하세 요.\">  \n");     
		   html.append("                  </td>                                                      \n");     
		   html.append("              </tr>                                                          \n");     
		   html.append("                                                                             \n");     
		   html.append("              <tr class=\"text-left\">                                       \n");     
		   html.append("                 <td colspan=\"2\">이메일</td>                                 \n");            
		   html.append("                 <td  colspan=2>                                             \n");     
		   html.append("                    <input type=\"text\" name=\""+email+"\" id=\""+email+"\" \n");     
		   html.append("                    class=\"form-control\" placeholder=\"이메일을 입력하세요.\">   \n");     
		   html.append("                 </td>                                                       \n");     
		   html.append("              </tr>                                                          \n");     
		   html.append("           </tbody>                                                          \n");     
		   html.append("    </table>                                                                 \n");     
	  	   html.append("</div>                                                                       \n");
		   
		   
		   
	   return html.toString();
   }
 //------------------------------------------------------------------- 논멤버 끝 -------------------------------------------------------------------//   
   
   public static String noLoginNav() {
	   StringBuilder html=new StringBuilder();
	   html.append("<li class=\"dropdown\">																												\n");
	   html.append("<a href=\"#\" class=\"dropdown-toggle\"	data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">    \n");
	   html.append("로그인/회원가입                                                                                                                            \n");
	   html.append("</a>                                                                                                                                \n");
	   html.append("<ul class=\"dropdown-menu\">                                                                                                        \n");
	   html.append("<li><a href=\"LoginForm.jsp\">로그인</a></li>                                                                                         \n");
	   html.append("<li><a href=\"SignUpForm.jsp\">회원가입</a></li>                                                                                       \n");
	   html.append("</ul>                                                                                                                               \n");
	   html.append("</li>                                                                                                                               \n");                                                                                                                  
	   return html.toString();
   }
   public static String loginNav() {
	   StringBuilder html=new StringBuilder();
	   html.append("<li class=\"dropdown\">																												\n");
	   html.append("<a href=\"#\" class=\"dropdown-toggle\"	data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">    \n");
	   html.append("마이페이지                                                                                                                         \n");
	   html.append("</a>                                                                                                                                   \n");
	   html.append("<ul class=\"dropdown-menu\">                                                                                                           \n");
	   html.append("<li><a href=\"/SistAirLine/member/member.do?work_div=do_logout\">로그아웃</a></li>                                                                                       \n");
	   html.append("<li><a href=\"MypageForm.jsp\">마이 페이지</a></li>                                                                                       \n");
	   html.append("<li><a href=\"/SistAirLine/reservationtest/reservationtest.do?work_div=do_Res_Retrieve\">예약조회</a></li>                                                                                       \n");
	   html.append("</ul>                                                                                                                                  \n");
	   html.append("</li>                                                                                                                                  \n");
	   return html.toString();
   }
//------------------------------------------------------------------------ 사이드 바 -------------------------------------------------------------------------// 
   public static String sideBar(String scDcity, String scAcity, String scPrice,String tripType,String mileage, int adult, int child, int todd) {
	   StringBuilder html=new StringBuilder();
	   if(tripType.equals("1")) {
		   tripType="X";
	   }else {
		   tripType="O";
		   
	   }
	   html.append("<div>                                                              \n");
	   html.append("<h4>"+scDcity+" -> "+scAcity+"</h4>                                \n");
	   html.append("<div>                                                              \n");
	   html.append("<LABEL>기본 가격:&nbsp&nbsp</LABEL><LABEL>"+scPrice+"</LABEL><br/>    \n");
	   html.append("<LABEL>왕복여부:&nbsp&nbsp</LABEL><LABEL>"+tripType+"</LABEL><br/>    \n");
	   html.append("<LABEL>적립 마일리지:&nbsp&nbsp</LABEL><LABEL>"+mileage+"</LABEL><br/> \n");
	   html.append("</div>                                                             \n");
	   html.append("<div>                                                              \n");
	   html.append("<LABEL>성인:&nbsp&nbsp</LABEL><label>"+adult+"</label><br/>         \n");
	   html.append("<LABEL>소아:&nbsp&nbsp</LABEL><label>"+child+"</label><br/>         \n");
	   html.append("<LABEL>유아:&nbsp&nbsp</LABEL><label>"+todd+"</label><br/>          \n");
	   html.append("</div>                                                             \n");
	   html.append("<hr/>                                                              \n");
	   html.append("</div>															   \n");
	   
	   return html.toString();
   }
//------------------------------------------------------------------------ 사이드 바 끝 -----------------------------------------------------------------------//   

//------------------------------------------------------------------ 동승자(성인,소아,유아) --------------------------------------------------------------------//      
   public static String divPassenger(int adult, int child, int todd, String scCode,String scCodeR, String scCodeAdd
		   							,String scCodeRAdd, String scDcity, String scAcity, String scDcityR, String scAcityR
		   							) {
	     
	   StringBuilder html=new StringBuilder();
		  //왕복 인천 없는것
		  if (adult > 0) {
			for (int i = 0; i < adult; i++) {
				html.append(inputInfoAdd(i,scCode,"성인","gender_A","ename_A","scode_A","adultseatNum_A","passport_A",scDcity,scAcity,scDcityR,scAcityR,scCodeR,scCodeAdd,scCodeRAdd,"year_A","month_A","day_A"));	
			}
		  }
		  if (child > 0) {
			for (int i = 0; i < child; i++) {
				html.append(inputInfoAdd(i,scCode,"소아","gender_C","ename_C","scode_C","childseatNum_C","passport_C",scDcity,scAcity,scAcityR,scDcityR,scCodeR,scCodeAdd,scCodeRAdd,"year_C","month_C","day_C"));	
			}
		  }
		   
		  if (todd > 0) {
			for (int i = 0; i < todd; i++) {
				html.append(inputInfoAdd(i,scCode,"유아","gender_T","ename_T","scode_T","toddseatNum_T","passport_T",scAcity,scDcity,scAcityR,scDcityR,scCodeR,scCodeAdd,scCodeRAdd,"year_T","month_T","day_T"));	
			}
		  }
	  
	   return html.toString();	   
   }

   public static String inputInfoAdd(int i,String scCode,String pType,String gender,String ename,String sCode,String seatNum
		   								, String passport, String scDcity, String scAcity, String scDcityR, String scAcityR
		   								, String scCodeR,String scCodeAdd,String scCodeRAdd, String year, String month, String day) {
	   StringBuilder html=new StringBuilder();
		   html.append("<div class=\"container mt-5\" style=\"max-width: 800px;\">                                                         \n");
		   html.append("		<h1>"+pType+""+(i+1)+"</h1>                                                                                \n");
		   html.append("                                                                                                                   \n");
		   html.append("		<table class=\"table table-hover  table-bordered\">                                                        \n");
		   html.append("                                                                                                                   \n");
		   html.append("			<thead class=\"bg-primary text-center\">                                                               \n");
		   html.append("				<!-- 테이블 header -->                                                                               \n");
		   html.append("				<th class=\"text-center\" colspan=\"2\">정보</th>                                                   \n");
		   html.append("			</thead>                                                                                               \n");
		   html.append("                                                                                                                   \n");
		   html.append("			<tbody class=\"text-center\">                                                                          \n");
		   html.append("				<!-- data영역 -->                                                                                    \n");
		   html.append("				<tr class=\"text-center\">                                                                         \n");
		   html.append("				  <td><b>승객구분</b></td>                                                                           \n");
		   html.append("				  <td>                                                                                             \n");
		   html.append("				    <b>                                                                                            \n");
		   html.append("				      <input type=\"radio\" name=\""+gender+""+i+"\" id=\""+gender+""+i+"\" value=\"1\" checked>남   \n");
		   html.append("					  <input type=\"radio\" name=\""+gender+""+i+"\" id=\""+gender+""+i+"\" value=\"2\">여                 \n");
		   html.append("					</b>                                                                                           \n");
		   html.append("				  </td>                                                                                            \n");
		   html.append("				</tr>                                                                                              \n");
		   html.append("				                                                                                                   \n");
		   html.append("				<tr>                                                                                               \n");
		   html.append("					<td>영어이름</td>                                                                                \n");
		   html.append("					<td>                                                                                           \n");
		   html.append("				      <input type=\"text\" name=\""+ename+"\" name=\""+ename+""+i+"\"                              \n");
		   html.append("					  class=\"form-control mr-sm-2\" placeholder=\"영어이름을 입력하세요.\">                             \n");
		   html.append("					</td>                                                                                          \n");
		   html.append("				</tr>                                                                                              \n");
		   html.append("                                                                                                                   \n");
		   html.append("				<tr>                                                                                               \n");
		   html.append("					<td>["+scDcity+"-"+scAcity+"행] 좌석선택"+"</td>                                                  \n");
		   html.append("					<td>                                                                                           \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"1"+i+"\"                     \n");
		   html.append("						class=\"form-control mr-sm-2\">                                                            \n");
		   html.append("					                                                                                               \n");
		   html.append("					                                                                                               \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                                   \n");
		   html.append("							onclick=\"openChild('"+scCode+"','"+seatNum+"1"+i+"')\">좌석선택1</label>         	       \n");
		   html.append("					</td>                                                                                          \n");
		   html.append("				</tr>                                                                                              \n");
	 
	   if(scCodeR!=null && scAcityR==null) {//왕복 인천있는
		   html.append("				<tr>                                                                                               \n");
		   html.append("					<td>["+scAcity+"-"+scDcity+"행] 좌석선택"+"</td>                                                  \n");
		   html.append("					<td>                                                                                           \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"2"+i+"\"                     \n");
		   html.append("						class=\"form-control mr-sm-2\">                                                            \n");
		   html.append("					                                                                                               \n");
		   html.append("					                                                                                               \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                                   \n");
		   html.append("							onclick=\"openChild('"+scCodeR+"','"+seatNum+"2"+i+"')\">좌석선택2</label>         	   \n");
		   html.append("					</td>                                                                                          \n");
		   html.append("				</tr>                                                                                              \n");
		  }
	   if(scCodeR!=null && scAcityR!=null) {//편도 인천없는
		   html.append("				<tr>                                                                                               \n");
		   html.append("					<td>["+scDcityR+"-"+scAcityR+"행] 좌석선택"+"</td>                                                \n");
		   html.append("					<td>                                                                                           \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"2"+i+"\"                     \n");
		   html.append("						class=\"form-control mr-sm-2\">                                                            \n");
		   html.append("					                                                                                               \n");
		   html.append("					                                                                                               \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                                   \n");
		   html.append("							onclick=\"openChild('"+scCodeR+"','"+seatNum+"2"+i+"')\">좌석선택2</label>   	           \n");
		   html.append("					</td>                                                                                          \n");
		   html.append("				</tr>                                                                                              \n");
		  }
	   if(scCodeAdd!=null) {//왕복 인천없는
		   html.append("				<tr>                                                                                               \n");
		   html.append("					<td>["+scDcityR+"-"+scAcityR+"행] 좌석선택"+"</td>                                                \n");
		   html.append("					<td>                                                                                           \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"3"+i+"\"                     \n");
		   html.append("						class=\"form-control mr-sm-2\">                                                            \n");
		   html.append("					                                                                                               \n");
		   html.append("					                                                                                               \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                                   \n");
		   html.append("							onclick=\"openChild('"+scCodeAdd+"','"+seatNum+"3"+i+"')\">좌석선택3</label>             \n");
		   html.append("					</td>                                                                                          \n");
		   html.append("				</tr>                                                                                              \n");
	   	  }
	   if(scCodeRAdd!=null) {
		   html.append("				<tr>                                                                                               \n");
		   html.append("					<td>["+scAcityR+"-"+scDcityR+"행] 좌석선택"+"</td>                                                \n");
		   html.append("					<td>                                                                                           \n");
		   html.append("					    <input type=\"text\" name=\""+sCode+"\" id=\""+seatNum+"4"+i+"\"                     \n");
		   html.append("						class=\"form-control mr-sm-2\">                                                            \n");
		   html.append("					                                                                                               \n");
		   html.append("					                                                                                               \n");
		   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"  	                                   \n");
		   html.append("							onclick=\"openChild('"+scCodeRAdd+"','"+seatNum+"4"+i+"')\">좌석선택4</label>            \n");
		   html.append("					</td>                                                                                          \n");
		   html.append("				</tr>                                                                                              \n");
	      }
	      
		   html.append("				<tr>                                                                                               \n");
		   html.append("				  <td>여권번호</td>                                                                                  \n");
		   html.append("				  <td>                                                                                             \n");
		   html.append("				      <input type=\"text\" name=\""+passport+"\" id=\""+passport+""+i+"\"                          \n");
		   html.append("					  class=\"form-control mr-sm-2\" required=\"required\"                                         \n");
		   html.append("					  placeholder=\"여권번호을 입력하세요.\">                                                            \n");
		   html.append("				  </td>                                                                                            \n");
		   html.append("				</tr>                                                                                              \n");
		   html.append("				                                                                                                   \n");
		   html.append("				<tr>                                                                                               \n");
		   html.append("				  <td>생년월일</td>                                                                                  \n");
		   html.append("				  <td>                                                                                             \n");
		   html.append("					  <script language=\"javascript\">  		              								       \n");
		   html.append("				  Today(null,null,null,'"+i+"','"+year+"','"+month+"','"+day+"','"+year+"','"+month+"','"+day+"' );\n");
		   html.append("				      </script>																					   \n");
		   html.append("				  </td>                                                                                            \n");
		   html.append("				</tr>                                                                                              \n");
		   html.append("			</tbody>                                                                                               \n");
		   html.append("		 </table>                                                                                                  \n");
		   html.append("	   <hr/>                                                                                                       \n");
		   html.append("	</div>														                                                   \n");

	   return html.toString();
   }

   public static String inputInfoRoundTrip(int i,String scCode,String pType,String chk_info,String ename,String sCode,String seatNum, String passport) {
	   StringBuilder html=new StringBuilder();
	   

	   html.append("<div class=\"container mt-5\" style=\"max-width: 800px;\">                                                             \n");
	   html.append("		<h1>"+pType+""+(i+1)+"</h1>                                                                                    \n");
	   html.append("                                                                                                                       \n");
	   html.append("		<table class=\"table table-hover  table-bordered\">                                                            \n");
	   html.append("                                                                                                                       \n");
	   html.append("			<thead class=\"bg-primary text-center\">                                                                   \n");
	   html.append("				<!-- 테이블 header -->                                                                                   \n");
	   html.append("				<th class=\"text-center\" colspan=\"2\">정보</th>                                                       \n");
	   html.append("			</thead>                                                                                                   \n");
	   html.append("                                                                                                                       \n");
	   html.append("			<tbody class=\"text-center\">                                                                              \n");
	   html.append("				<!-- data영역 -->                                                                                        \n");
	   html.append("				<tr class=\"text-center\">                                                                             \n");
	   html.append("				  <td><b>승객구분</b></td>                                                                               \n");
	   html.append("				  <td>                                                                                                 \n");
	   html.append("				    <b>                                                                                                \n");
	   html.append("				      <input type=\"checkbox\" name=\""+chk_info+""+i+"\" value=\"1\" checked>남                         		   \n");
	   html.append("					  <input type=\"checkbox\" name=\""+chk_info+""+i+"\" value=\"2\">여                                 			   \n");
	   html.append("					</b>                                                                                               \n");
	   html.append("				  </td>                                                                                                \n");
	   html.append("				</tr>                                                                                                  \n");
	   html.append("				                                                                                                       \n");
	   html.append("				<tr>                                                                                                   \n");
	   html.append("					<td>영어이름</td>                                                                                    \n");
	   html.append("					<td>                                                                                               \n");
	   html.append("				      <input type=\"text\" name=\""+ename+""+i+"\"                                                     \n");
	   html.append("					  class=\"form-control mr-sm-2\" placeholder=\"영어이름을 입력하세요.\">                                 \n");
	   html.append("					</td>                                                                                              \n");
	   html.append("				</tr>                                                                                                  \n");
	   html.append("                                                                                                                       \n");
	   html.append("				<tr>                                                                                                   \n");
	   html.append("					<td>좌석번호</td>                                                                                    \n");
	   html.append("					<td>                                                                                               \n");
	   html.append("					    <input type=\"text\" name=\""+sCode+""+i+"\" id=\""+seatNum+""+i+"\"                           \n");
	   html.append("						class=\"form-control mr-sm-2\">                                                                \n");
	   html.append("					</td>                                                                                              \n");
	   html.append("				</tr>                                                                                                  \n");
	   html.append("                                                                                                                       \n");
	   html.append("				<tr>                                                                                                   \n");
	   html.append("				  <td>여권번호</td>                                                                                      \n");
	   html.append("				  <td>                                                                                                 \n");
	   html.append("				      <input type=\"text\" name=\""+passport+""+i+"\" id=\"passport\"                                  \n");
	   html.append("					  class=\"form-control mr-sm-2\" required=\"required\"                                             \n");
	   html.append("					  placeholder=\"여권번호을 입력하세요.\">                                                                \n");
	   html.append("				  </td>                                                                                                \n");
	   html.append("				</tr>                                                                                                  \n");
	   html.append("				                                                                                                       \n");
	   html.append("				<tr>                                                                                                   \n");
	   html.append("				  <td>생년월일</td>                                                                                      \n");
	   html.append("				  <td>                                                                                                 \n");
	   html.append("					  <script language=\"javascript\"> Today(null,null,null,'_A"+i+"'); </script>                      \n");
	   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"                                            \n");
	   html.append("							onclick=\"openChild('"+scCode+"','"+seatNum+""+i+"')\">좌석선택</label>                      \n");
	   html.append("				  </td>                                                                                                \n");
	   html.append("				</tr>                                                                                                  \n");
	   html.append("			</tbody>                                                                                                   \n");
	   html.append("		 </table>                                                                                                      \n");
	   html.append("	   <hr/>                                                                                                           \n");
	   html.append("	</div>																											   \n");


	   return html.toString();
   }
   
   public static String inputInfoNotIC(int i,String scCode,String pType,String chk_info,String ename,String sCode,String seatNum, String passport) {
	   StringBuilder html=new StringBuilder();
	   

	   html.append("<div class=\"container mt-5\" style=\"max-width: 800px;\">                                        \n");
	   html.append("		<h1>"+pType+""+(i+1)+"</h1>                                                               \n");
	   html.append("                                                                                                  \n");
	   html.append("		<table class=\"table table-hover  table-bordered\">                                       \n");
	   html.append("                                                                                                  \n");
	   html.append("			<thead class=\"bg-primary text-center\">                                              \n");
	   html.append("				<!-- 테이블 header -->                                                              \n");
	   html.append("				<th class=\"text-center\" colspan=\"2\">정보</th>                                  \n");
	   html.append("			</thead>                                                                              \n");
	   html.append("                                                                                                  \n");
	   html.append("			<tbody class=\"text-center\">                                                         \n");
	   html.append("				<!-- data영역 -->                                                                   \n");
	   html.append("				<tr class=\"text-center\">                                                        \n");
	   html.append("				  <td><b>승객구분</b></td>                                                          \n");
	   html.append("				  <td>                                                                            \n");
	   html.append("				    <b>                                                                           \n");
	   html.append("				      <input type=\"checkbox\" name=\""+chk_info+""+i+"\" value=\"1\" checked>남     \n");
	   html.append("					  <input type=\"checkbox\" name=\""+chk_info+""+i+"\" value=\"2\">여                   \n");
	   html.append("					</b>                                                                          \n");
	   html.append("				  </td>                                                                           \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("				                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("					<td>영어이름</td>                                                               \n");
	   html.append("					<td>                                                                          \n");
	   html.append("				      <input type=\"text\" name=\""+ename+""+i+"\"                                \n");
	   html.append("					  class=\"form-control mr-sm-2\" placeholder=\"영어이름을 입력하세요.\">            \n");
	   html.append("					</td>                                                                         \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("                                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("					<td>좌석번호</td>                                                               \n");
	   html.append("					<td>                                                                          \n");
	   html.append("					    <input type=\"text\" name=\""+sCode+""+i+"\" id=\""+seatNum+""+i+"\"      \n");
	   html.append("						class=\"form-control mr-sm-2\">                                           \n");
	   html.append("					</td>                                                                         \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("                                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("				  <td>여권번호</td>                                                                 \n");
	   html.append("				  <td>                                                                            \n");
	   html.append("				      <input type=\"text\" name=\""+passport+""+i+"\" id=\"passport\"             \n");
	   html.append("					  class=\"form-control mr-sm-2\" required=\"required\"                        \n");
	   html.append("					  placeholder=\"여권번호을 입력하세요.\">                                           \n");
	   html.append("				  </td>                                                                           \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("				                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("				  <td>생년월일</td>                                                                 \n");
	   html.append("				  <td>                                                                            \n");
	   html.append("					  <script language=\"javascript\"> Today(null,null,null,'_A"+i+"'); </script> \n");
	   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"                       \n");
	   html.append("							onclick=\"openChild('"+scCode+"','"+seatNum+""+i+"')\">좌석선택</label> \n");
	   html.append("				  </td>                                                                           \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("			</tbody>                                                                              \n");
	   html.append("		 </table>                                                                                 \n");
	   html.append("	   <hr/>                                                                                      \n");
	   html.append("	</div>																						  \n");


	   return html.toString();
   }
   
   public static String inputInfoRoundTripNotIC(int i,String scCode,String pType,String chk_info,String ename,String sCode,String seatNum, String passport) {
	   StringBuilder html=new StringBuilder();
	   

	   html.append("<div class=\"container mt-5\" style=\"max-width: 800px;\">                                        \n");
	   html.append("		<h1>"+pType+""+(i+1)+"</h1>                                                               \n");
	   html.append("                                                                                                  \n");
	   html.append("		<table class=\"table table-hover  table-bordered\">                                       \n");
	   html.append("                                                                                                  \n");
	   html.append("			<thead class=\"bg-primary text-center\">                                              \n");
	   html.append("				<!-- 테이블 header -->                                                              \n");
	   html.append("				<th class=\"text-center\" colspan=\"2\">정보</th>                                  \n");
	   html.append("			</thead>                                                                              \n");
	   html.append("                                                                                                  \n");
	   html.append("			<tbody class=\"text-center\">                                                         \n");
	   html.append("				<!-- data영역 -->                                                                  \n");
	   html.append("				<tr class=\"text-center\">                                                        \n");
	   html.append("				  <td><b>승객구분</b></td>                                                          \n");
	   html.append("				  <td>                                                                            \n");
	   html.append("				    <b>                                                                           \n");
	   html.append("				      <input type=\"checkbox\" name=\""+chk_info+""+i+"\" value=\"1\" checked>남     \n");
	   html.append("					  <input type=\"checkbox\" name=\""+chk_info+""+i+"\" value=\"2\">여                   \n");
	   html.append("					</b>                                                                          \n");
	   html.append("				  </td>                                                                           \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("				                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("					<td>영어이름</td>                                                               \n");
	   html.append("					<td>                                                                          \n");
	   html.append("				      <input type=\"text\" name=\""+ename+""+i+"\"                                \n");
	   html.append("					  class=\"form-control mr-sm-2\" placeholder=\"영어이름을 입력하세요.\">            \n");
	   html.append("					</td>                                                                         \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("                                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("					<td>좌석번호</td>                                                               \n");
	   html.append("					<td>                                                                          \n");
	   html.append("					    <input type=\"text\" name=\""+sCode+""+i+"\" id=\""+seatNum+""+i+"\"      \n");
	   html.append("						class=\"form-control mr-sm-2\">                                           \n");
	   html.append("					</td>                                                                         \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("                                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("				  <td>여권번호</td>                                                                 \n");
	   html.append("				  <td>                                                                            \n");
	   html.append("				      <input type=\"text\" name=\""+passport+""+i+"\" id=\"passport\"             \n");
	   html.append("					  class=\"form-control mr-sm-2\" required=\"required\"                        \n");
	   html.append("					  placeholder=\"여권번호을 입력하세요.\">                                           \n");
	   html.append("				  </td>                                                                           \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("				                                                                                  \n");
	   html.append("				<tr>                                                                              \n");
	   html.append("				  <td>생년월일</td>                                                                 \n");
	   html.append("				  <td>                                                                            \n");
	   html.append("					  <script language=\"javascript\"> Today(null,null,null,'_A"+i+"'); </script> \n");
	   html.append("                      <lable class=\"btn btn-outline-success my-2 my-sm-0\"                       \n");
	   html.append("							onclick=\"openChild('"+scCode+"','"+seatNum+""+i+"')\">좌석선택</label> \n");
	   html.append("				  </td>                                                                           \n");
	   html.append("				</tr>                                                                             \n");
	   html.append("			</tbody>                                                                              \n");
	   html.append("		 </table>                                                                                 \n");
	   html.append("	   <hr/>                                                                                      \n");
	   html.append("	</div>																						  \n");


	   return html.toString();
   }
     
   
   public static String inputInfoTodd(int i, String scCode) {
	   
	   StringBuilder html=new StringBuilder();
	   
	   html.append("<div class=\"container mt-7 \" style=\"max-width: 800px;\">                                         \n");
	   html.append("	<h1>유아"+(i+1)+"</h1>                                                                           \n");
	   html.append("                                                                                                    \n");
	   html.append("	<table class=\"table table-hover  table-bordered\">                                             \n");
	   html.append("		                                                                                            \n");
	   html.append("	  <thead class=\"bg-primary text-center\">                                                      \n");
	   html.append("			<!-- 테이블 header -->                                                                    \n");
	   html.append("			<th class=\"text-center\" colspan=\"2\">정보</th>                                        \n");
	   html.append("	  </thead>                                                                                      \n");
	   html.append("	                                                                                                \n");
	   html.append("	  <tbody class=\"text-center\">	                                                                \n");
	   html.append("		<!-- data영역 -->                                                                             \n");
	   html.append("		<tr class=\"text-center\">                                                                  \n");
	   html.append("		  <td><b>승객구분</b></td>                                                                    \n");
	   html.append("		  <td>                                                                                      \n");
	   html.append("			<b>                                                                                     \n");
	   html.append("			 	<input type=\"checkbox\" name=\"chk_info_C"+ i +"\" value=\"1\" checked>남                   \n");
	   html.append("				<input type=\"checkbox\" value=\"2\">여                                                                                \n");
	   html.append("			</b>                                                                                    \n");
	   html.append("		  </td>                                                                                     \n");
	   html.append("		</tr>                                                                                       \n");
	   html.append("		                                                                                            \n");
	   html.append("		<tr>                                                                                        \n");
	   html.append("			<td>영어이름</td>                                                                         \n");
	   html.append("			<td>                                                                                    \n");
	   html.append("			   <input type=\"text\" name=\"ename_C"+ i +"\"                                         \n");
	   html.append("			          class=\"form-control mr-sm-2\" placeholder=\"영어이름을 입력하세요.\">              \n");
	   html.append("			</td>                                                                                   \n");
	   html.append("	    </tr>                                                                                       \n");
	   html.append("                                                                                                    \n");
	   html.append("		<tr>                                                                                        \n");
	   html.append("			<td>좌석번호</td>                                                                         \n");
	   html.append("			<td>                                                                                    \n");
	   html.append("				<input type=\"text\" name=\"Scode_C"+ i +"\" id=\"toddseatNum"+ i +"\"              \n");
	   html.append("                       class=\"form-control mr-sm-2\">                                              \n");
	   html.append("			</td>                                                                                   \n");
	   html.append("		</tr>                                                                                       \n");
	   html.append("                                                                                                    \n");
	   html.append("		<tr>                                                                                        \n");
	   html.append("			<td>여권번호</td>                                                                         \n");
	   html.append("			<td>                                                                                    \n");
	   html.append("			  <input                                                                                \n");
	   html.append("				type=\"text\" name=\"passport_C"+ i +"\" id=\"passport\"                            \n");
	   html.append("				class=\"form-control mr-sm-2\" required=\"required\"                                \n");
	   html.append("				placeholder=\"여권번호을 입력하세요.\" >                                                  \n");
	   html.append("			</td>                                                                                   \n");
	   html.append("		</tr>                                                                                       \n");
	   html.append("                                                                                                    \n");
	   html.append("		<tr>                                                                                        \n");
	   html.append("			<td>생년월일</td>                                                                         \n");
	   html.append("			<td>                                                                                    \n");
	   html.append("			    <script language=\"javascript\"> Today(null,null,null, '_C"+ i +"'); </script>      \n");
	   html.append("                                                                                                    \n");
	   html.append("				<lable class=\"btn btn-outline-success my-2 my-sm-0\"                               \n");
	   html.append("					   onclick=\"openChild('"+scCode+"','toddseatNum"+ i +"')\">좌석선택</label>      \n");
	   html.append("			</td>                                                                                   \n");
	   html.append("		</tr>                                                                                       \n");
	   html.append("	  </tbody>                                                                                      \n");
	   html.append("	</table>                                                                                        \n");
	   html.append("  <hr/>                                                                                             \n");
	   html.append("</div>																								\n");

	   return html.toString();
   }
   
   
   
   public static String inputInfoChild(int i, String scCode) {
	   StringBuilder html=new StringBuilder();
	   
	   
	   html.append("<div class=\"container mt-6\" style=\"max-width: 800px;\">                                                \n");
	   html.append("				<h1>                                                                                      \n");
	   html.append("					소아"+(i+1)+"</h1>                                                                     \n");
	   html.append("                                                                                                          \n");
	   html.append("				<table class=\"table table-hover  table-bordered\">                                       \n");
	   html.append("					                                                                                      \n");
	   html.append("				  <thead class=\"bg-primary text-center\">                                                \n");
	   html.append("						<!-- 테이블 header -->                                                              \n");
	   html.append("						<th class=\"text-center\" colspan=\"2\">정보</th>                                  \n");
	   html.append("				  </thead>                                                                                \n");
	   html.append("					                                                                                      \n");
	   html.append("					                                                                                      \n");
	   html.append("				  <tbody class=\"text-center\">	                                                          \n");
	   html.append("					<!-- data영역 -->                                                                       \n");
	   html.append("					<tr class=\"text-center\">                                                            \n");
	   html.append("						<td><b>승객구분</b></td>                                                            \n");
	   html.append("						<td>                                                                              \n");
	   html.append("							<b>                                                                           \n");
	   html.append("						       <input type=\"checkbox\" name=\"chk_info_B"+i+"\" value=\"1\" checked>남       \n");
	   html.append("							   <input type=\"checkbox\" value=\"2\">여                                                                 \n");
	   html.append("							</b>                                                                          \n");
	   html.append("						</td>                                                                             \n");
	   html.append("					</tr>                                                                                 \n");
	   html.append("					                                                                                      \n");
	   html.append("					<tr>                                                                                  \n");
	   html.append("						<td>영어이름</td>                                                                   \n");
	   html.append("						<td>                                                                              \n");
	   html.append("						   <input type=\"text\" name=\"ename_B"+i+"\"                                     \n");
	   html.append("						          class=\"form-control mr-sm-2\" placeholder=\"영어이름을 입력하세요.\">        \n");
	   html.append("						</td>                                                                             \n");
	   html.append("				    </tr>                                                                                 \n");
	   html.append("                                                                                                          \n");
	   html.append("                                                                                                          \n");
	   html.append("					<tr>                                                                                  \n");
	   html.append("						<td>좌석번호</td>                                                                   \n");
	   html.append("						<td>                                                                              \n");
	   html.append("							<input type=\"text\" name=\"Scode_"+i+"\" id=\"youngseatNum"+i+"\"            \n");
	   html.append("                                   class=\"form-control mr-sm-2\">                                        \n");
	   html.append("						</td>                                                                             \n");
	   html.append("					</tr>                                                                                 \n");
	   html.append("                                                                                                          \n");
	   html.append("					<tr>                                                                                  \n");
	   html.append("						<td>여권번호</td>                                                                   \n");
	   html.append("						<td>                                                                              \n");
	   html.append("						  <input                                                                          \n");
	   html.append("							type=\"text\" name=\"passport_B"+i+"\" id=\"passport\"                        \n");
	   html.append("							class=\"form-control mr-sm-2\" required=\"required\"                          \n");
	   html.append("							placeholder=\"여권번호을 입력하세요.\">                                             \n");
	   html.append("						</td>                                                                             \n");
	   html.append("					</tr>                                                                                 \n");
	   html.append("                                                                                                          \n");
	   html.append("					<tr>                                                                                  \n");
	   html.append("						<td>생년월일</td>                                                                   \n");
	   html.append("						<td>                                                                              \n");
	   html.append("						    <script language=\"javascript\"> Today(null,null,null, '_B"+i+"'); </script>  \n");
	   html.append("                                                                                                          \n");
	   html.append("							<lable class=\"btn btn-outline-success my-2 my-sm-0\"                         \n");
	   html.append("								   onclick=\"openChild('"+scCode+"','youngseatNum"+i+"')\">좌석선택</label> \n");
	   html.append("						</td>                                                                             \n");
	   html.append("					</tr>                                                                                 \n");
	   html.append("				  </tbody>                                                                                \n");
	   html.append("				</table>                                                                                  \n");
	   html.append("			   <hr/>                                                                                      \n");
	   html.append("			</div>																						  \n");																								   

	   return html.toString();
   }
   
   
 //----------------------------------------------------------------- 동승자(성인,소아,유아) 끝 -------------------------------------------------------------------//   
   
    /**
      * Paging처리 
      * @param maxNum_i
      * @param currPageNoIn_i
      * @param rowsPerPage_i
      * @param bottomCount_i
      * @param url_i
      * @param scriptName_i
      * @return
      */
   public static String renderPaging(int maxNum_i, int currPageNoIn_i, int rowsPerPage_i, int bottomCount_i,
		    String url_i, String scriptName_i) {
		   int maxNum = 0; // 총 갯수
		   int currPageNo = 1; // 현재 페이지 번호 : page_num
		   int rowPerPage = 10; // 한페이지에 보여질 행수 : page_size
		   int bottomCount = 10; // 바닥에 보여질 페이지 수: 10
		   
		   /** 총글수: 21
		    *  현재페이지: 1
		    *  한 페이지에 보여질 행수: 10
		    *  바닥에 보여질 페이지 수 :10
		    * 		<<	 < 	1 2 3 4 5 6 7 8 9 10	>	 >>
		    */

		   maxNum = maxNum_i;
		   currPageNo = currPageNoIn_i;
		   rowPerPage = rowsPerPage_i;
		   bottomCount = bottomCount_i;

		   String url = url_i; // 호출 URL
		   String scriptName = scriptName_i; // 호출 자바스크립트

		   int maxPageNo = ((maxNum - 1) / rowPerPage) + 1;
		   int startPageNo = ((currPageNo - 1) / bottomCount) * bottomCount + 1;//
		   int endPageNo = ((currPageNo - 1) / bottomCount + 1) * bottomCount;
		   int nowBlockNo = ((currPageNo - 1) / bottomCount) + 1;
		   int maxBlockNo = ((maxNum - 1) / bottomCount) + 1;

		   int inx = 0;
		   StringBuilder html = new StringBuilder();
		   if (currPageNo > maxPageNo) {
		    return "";
		   }

		   html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">   \n");
		   html.append("<tr>                       \n");
		   html.append("<td align=\"center\">                                                                    \n");
		   //html.append("<ul class=\"pagination pagination-sm\">                                                  \n");
		   // <<
		   if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
		    html.append("<a href=\"javascript:" + scriptName + "( '" + url+ "', 1 );\">  \n");
		    html.append("&laquo;   \n");
		    html.append("</a>      \n");
		   }

		   // <
		   if (startPageNo > bottomCount) {
		    html.append("<a href=\"javascript:" + scriptName + "( '" + url + "'," + (startPageNo - 1)+ ");\"> \n");
		    html.append("<        \n");
		    html.append("</a>     \n");
		   }


		   // 1 2 3 ... 10 (숫자보여주기)
		   for (inx = startPageNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
		    
		    if (inx == currPageNo) {
		     html.append("<b>" + inx + "</b> &nbsp;&nbsp; \n");
		    } else {
		     html.append("<a href=\"javascript:" + scriptName + "('" + url + "'," + inx+ ");\" >" + inx + "</a> &nbsp;&nbsp; \n");
		    }
		   }
		   
		   // >
		   if (maxPageNo >= inx) {
		    html.append("<a href=\"javascript:" + scriptName + "('" + url + "',"+ ((nowBlockNo * bottomCount) + 1) + ");\"> \n");
		    html.append(">                       \n");
		    html.append("</a>              \n");
		   }

		   // >>
		   if (maxPageNo >= inx) {
		    html.append("<a href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo+ ");\">      \n");
		    html.append("&raquo;     \n");
		    html.append("</a>    \n");
		   }

		   html.append("</td>   \n");
		   html.append("</tr>   \n");
		   html.append("</table>   \n");

		   return html.toString();
		  }
   
   /**
    * 
   *@Method Name:nvl
   *@작성일: 2020. 2. 24.
   *@작성자: sist
   *@설명: null -> ""
   *@param val String
   *@return String
    */
   public static String nvl(String val) {
      return nvl(val,"");
   }
   
   
   public static String replace(String val) {
	   if(val=="1") {
		  return val.replace(val, "");
		  
	   }else {
		   return val;
	   }
   }
   /**
    * 
   *@Method Name:nvl
   *@작성일: 2020. 2. 24.
   *@작성자: sist
   *@설명: request param null to ""
   *@param val 원본 String
   *@param rep 치환 String
   *@return String
    */
   public static String nvl(String val, String rep) {
      if(null == val || "".equals(val)) {
         val = rep;
      }
      
      return val;
   }

}