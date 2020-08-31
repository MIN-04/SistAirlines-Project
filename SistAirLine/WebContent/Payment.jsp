<%@page import="Reservation.ReservationVO"%>
<%@page import="Reservation.ReservationDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="java.sql.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PaymentPage</title>
</head>
<body>
<%

      String inPrice=(String)request.getParameter("totalPrice");
	  int price=Integer.parseInt(inPrice.replace(",","").trim());
	  
     
	 String name="홍승민";
      String tel="010-8227-1995";
      String addr="서울특별시";
      
      

%>

 <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" ></script>
<script type="text/javascript">
		function goPayment(){
			  window.location.href = '/SistAirLine/index.jsp';
		}
        var IMP = window.IMP; // 생략가능
        IMP.init('iamport'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        //onclick, onload 등 원하는 이벤트에 호출합니다
        IMP.request_pay({
            pg : 'inicis', // version 1.1.0부터 지원.
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : 'Sist Airlines',
            amount : <%=price%>,
            buyer_email : 'iamport@siot.do',
            buyer_name : '<%=name%>',
            buyer_tel : '<%=tel%>',
            buyer_addr : '<%=addr%>',
            buyer_postcode : '123-456',
            m_redirect_url : 'https://www.yourdomain.com/payments/complete',
            app_scheme : 'iamportapp'
              
        }, function(rsp) {
            if ( rsp.success ) {
                var msg = '결제가 완료되었습니다.';
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
                goPayment()
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                alert('결제를 실패하였습니다!');
                goPayment()
            }
            document.getElementById("result").innerHTML = msg;
            alert(msg);
        });
        
        
        
</scripT>
 <main>
</main>
</body>
</html>