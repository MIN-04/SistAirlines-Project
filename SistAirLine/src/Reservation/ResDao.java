package Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.DTO;
import com.hr.cmn.JDBCResClose;
import com.hr.cmn.WorkDiv;



public class ResDao extends WorkDiv {
	
	
	private ConnectionMaker connectionMaker;
	
	public ResDao() {
		
		connectionMaker = new ConnectionMaker();
		
		
	}
	/**
	 * 
	 *@Method Name:readCount
	 *@작성일: 2020. 2. 6.
	 *@작성자: sist
	 *@설명: 조회수 증가
	 *@return
	 */
	public int readCount(DTO dto) {
		
		return 0;
		}
	
	
	//@Override
	public DTO doSelectOne(DTO dto) {
	
		ResVO inVO = (ResVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ResVO outVO=null;
		
		StringBuilder sb = new StringBuilder(); // 검색 query

		// main query
		sb.append(" SELECT               \n");
		sb.append("     res_num,         \n");
		sb.append("     res_passport,    \n");
		sb.append("     res_ename,       \n");
		sb.append("     TO_CHAR(res_bir,'YYYY-MM-DD') res_bir,  \n");
		sb.append("     res_phone,       \n");
		sb.append("     res_email,       \n");
//		sb.append("     res_way,         \n");
//		sb.append("     res_ptype,       \n");
		sb.append("     res_gender,      \n");
		sb.append("     res_state,       \n");
		sb.append("     res_pay,         \n");
		sb.append("     res_mileage,     \n");
		sb.append("     res_scode,       \n");
		sb.append("     u_num            \n");
		sb.append(" FROM                 \n");
		sb.append("     reservation      \n");
		sb.append("WHERE res_num= ?		 \n");
		sb.append(" AND   res_passport =?   "); 

		try {
			// 1. connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.connection=" + connection);

			// 2.query
			LOG.debug("2.Query=" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			// 3.param
			LOG.debug("3.param=\n" + inVO);

			pstmt.setString(1, inVO.getResNum());
			pstmt.setString(2, inVO.getResPassport());
			
			// 4.query수행
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// Data 1건을 outVO담기.
				outVO = new ResVO();

				outVO.setResNum(rs.getString("res_num"));
				outVO.setResPassport(rs.getString("res_passport"));
				outVO.setResEname(rs.getString("res_ename"));
				outVO.setResBir(rs.getString("res_bir"));
				outVO.setResphone(rs.getString("res_phone"));
				outVO.setResEmail(rs.getString("res_email"));
				outVO.setResGender(rs.getInt("res_gender"));
				outVO.setResState(rs.getInt("res_state"));
				outVO.setResPay(rs.getInt("res_pay"));
				outVO.setResMileage(rs.getInt("res_mileage"));
				outVO.setResScode(rs.getString("res_scode"));
				outVO.setuNum(rs.getString("u_num"));
				
				
			}
			LOG.debug("4.outVO=\n" + outVO);
			

		} catch (Exception e) {
			LOG.debug("===================");
			LOG.debug("=SQLException=" + e.getMessage());
			LOG.debug("===================");
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outVO;
		}

	//@Override
	public int doUpdate(DTO dto) {
		 int flag = 0;
		 ResVO inVO = (ResVO) dto; //param
			
			Connection connection = null; //DB Connection
			PreparedStatement pstmt = null; //VS Statement 해킹에 취약
			
			try {
				//1.Connection 생성
				connection = connectionMaker.getConnection();
				connection.setAutoCommit(false); //transaction을 개발자가 관리
				
				//2.PreparedStatement pstmt 
				//2.1.SQL-Update query
				StringBuilder sb = new StringBuilder(); //속도 향상
				
				sb.append (" UPDATE reservation            \n");
				sb.append (" SET                           \n");
				sb.append ("      res_num = ?              \n");
				sb.append ("      ,res_ename =      ?     \n");
				sb.append ("      ,res_bir =		? \n");
				sb.append ("      ,res_phone =      ?     \n");
				sb.append ("      ,res_email =      ?     \n");
				sb.append ("      ,res_gender =     ?     \n");
				sb.append ("      ,res_state =      ?     \n");
				sb.append ("      ,res_pay =        ?     \n");
				sb.append ("      ,res_mileage =    ?     \n");
				sb.append ("      ,res_scode =      ?     \n");
				sb.append ("      ,u_num =          ?     \n");
				sb.append (" WHERE   res_num = ?     	 \n");
				sb.append (" AND   res_passport = ?      \n");
								
				
				
				LOG.debug("2.SQL(Query) :\n"+sb.toString());
				//2.2.pstmt
				pstmt = connection.prepareStatement(sb.toString());
				LOG.debug("2.1.PreparedStatement : "+pstmt);
				
				//3.Param setting
				LOG.debug("3.param : \n"+inVO);
				//3.1.Param binding
				pstmt.setString(1, inVO.getResNum()); 
				pstmt.setString(2, inVO.getResEname()); 
				pstmt.setString(3, inVO.getResBir()); 
				pstmt.setString(4, inVO.getResphone()); 
				pstmt.setString(5, inVO.getResEmail()); 
				pstmt.setInt(6, inVO.getResGender()); 
				pstmt.setInt(7, inVO.getResState()); 
				pstmt.setInt(8, inVO.getResPay()); 
				pstmt.setInt(9, inVO.getResMileage()); 
				pstmt.setString(10, inVO.getResScode()); 
				pstmt.setString(11, inVO.getuNum()); 
				pstmt.setString(12, inVO.getResNum());
				pstmt.setString(13, inVO.getResPassport()); 
				
				
				//4.Query 수행
				flag = pstmt.executeUpdate();
				LOG.debug("4.flag : "+flag);
				connection.commit(); //성공 commit
			} catch (SQLException e) {
				LOG.debug("===========================");
				LOG.debug("=======SQLException======="+e.getMessage());
				LOG.debug("===========================");
				e.printStackTrace();
				//Rollback
				JDBCResClose.rollBack(connection);
			} finally {
				//5.preparedStatement 자원 반납 - 사용하는 역순으로 close
				//6.Connection 종료
				JDBCResClose.close(pstmt); //PreparedStatement
				JDBCResClose.close(connection); //Connection
			}
			return flag;
	   }

	//@Override
	public int doDelete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	//@Override
	public int doInsert(DTO dto) {
		return 0;
	}
     /**
      * 둘다 null 일때 , 새로고침과 동일한 효과
      */
	@Override
	public List<?> doRetrieve(DTO dto) {
//		 SearchVO inVO = (SearchVO) dto; //param
		ResVO inVO = (ResVO) dto; //return
		ResVO outVO = null; //return
		List<ResVO> outList = new ArrayList<ResVO>(); //return
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement
		ResultSet rs = null;
				
		try {
			//1.Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1.Connection : "+connection);
			
			//2.PreparedStatement pstmt
			//검색 조건
//			StringBuilder sbWhere = new StringBuilder();
			//검색 Query
			StringBuilder sb = new StringBuilder();
			
			//검색 조건
//					sbWhere.append("WHERE SUBSTR(t1.res_num,0,8) like ? \n");
//		
//					sbWhere.append(" AND RES_STATE like ? \n");
			
			
		
			
			//2.1.SQL-목록조회 query
					sb.append(" SELECT *                                                   ");   
					sb.append(" FROM(                                                      ");
					sb.append("       SELECT b.RES_NUM,                                    ");
					sb.append("       b.RES_PASSPORT,                                      ");
					sb.append("       b.RES_ENAME,                                         ");
					sb.append("       b.RES_BIR,                                           ");
					sb.append("       b.RES_PHONE,                                         ");
					sb.append("       b.RES_EMAIL,                                         ");
					sb.append("       b.RES_PTYPE,                                         ");
					sb.append("       b.RES_GENDER,                                        ");
					sb.append("       b.RES_STATE,                                        \n ");
					sb.append("       b.RES_PAY,                                          \n ");
					sb.append("       b.RES_MILEAGE,                                      \n ");
					sb.append("       b.RES_SCODE,                                        \n ");
					sb.append("        b.U_NUM                                            \n ");
					sb.append(" FROM(	                                                  \n ");
					sb.append(" 	SELECT ROWNUM as rnum,A.*  	                          \n ");
					sb.append(" 	FROM(	                                              \n ");
					sb.append(" 		SELECT t1.*                                       \n ");
					sb.append(" 		FROM reservation t1                               \n ");
					//sb.append(" 		--?????                                           \n ");
				//	sb.append(" 	                                                      \n ");
			//sb.append("             --검색어                                                                             \n");
			//-where----------------------------------------------------------------------
//			if(inVO.getResNum() != null || !inVO.getResNum().equals("")) {
//				if(inVO.getResNum() != null) {
//					sb.append(sbWhere.toString());
//				}
//			}
			//-where----------------------------------------------------------------------
			sb.append(" 		ORDER BY SUBSTR(t1.res_num,0,8) DESC              \n ");
			sb.append(" 		)A                                                \n ");
		//	sb.append(" 		--page 끝번호                                       \n ");
			sb.append(" 	WHERE rownum <=(?*(?-1)+?)  \n ");
			sb.append(" 	)B                                                    \n ");
			sb.append(" WHERE rnum >=(?*(?-1)+1))                \n ");
			sb.append(" CROSS JOIN                                                \n ");   
			sb.append(" (                                                         \n ");   
			sb.append("     SELECT COUNT(*) TOTAL                                 \n ");   
			sb.append("     FROM reservation t1                                   \n ");         
			//sb.append("     --?????                                               \n ");   
			//sb.append("     --검색어                                                                                           \n");
			//-where----------------------------------------------------------------------
//			if(inVO.getResNum() != null || !inVO.getResNum().equals("")) {
//				if(inVO.getResNum() != null) {
//					sb.append(sbWhere.toString());
//				}
//			}
			//-where----------------------------------------------------------------------
			
			sb.append(" )                                                        \n ");
		
			LOG.debug("2.query : \n"+sb.toString());
			
			
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting
			LOG.debug("3.param : "+inVO);
			//3.1.Param binding
			//검색어가 있는 경우
		
				//2.PAGE_SIZE 3.PAGE_NUM 4.PAGE_SIZE
				//5.PAGE_SIZE 6.PAGE_NUM
//				pstmt.setString(1, inVO.getResNum());
//				pstmt.setInt(2, inVO.getResState());
				pstmt.setInt(1, inVO.getPageSize());
				pstmt.setInt(2, inVO.getPageNum());
				pstmt.setInt(3, inVO.getPageSize());
				pstmt.setInt(4, inVO.getPageSize());
				pstmt.setInt(5	, inVO.getPageNum());
//				pstmt.setString(8, inVO.getResNum());
//				pstmt.setInt(9, inVO.getResState());
//		
			
			//4.Query 수행
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//데이터를 1건씩 outVO에 담기
				outVO = new ResVO();
				
				
				
				//outVO.setSeq(Integer.parseInt(rs.getString("seq")));
				outVO.setResNum(rs.getString("RES_NUM"));
				outVO.setResPassport(rs.getString("RES_PASSPORT"));
				outVO.setResEname(rs.getString("RES_ENAME"));
				outVO.setResBir(rs.getString("RES_BIR"));
				outVO.setResphone(rs.getString("RES_PHONE"));
				outVO.setResEmail(rs.getString("RES_EMAIL"));
				outVO.setResPtype(rs.getInt("RES_PTYPE"));
				outVO.setResGender(rs.getInt("RES_GENDER"));
				outVO.setResState(rs.getInt("RES_STATE"));
				outVO.setResPay(rs.getInt("RES_PAY"));
				outVO.setResMileage(rs.getInt("RES_MILEAGE"));
				outVO.setResScode(rs.getString("RES_SCODE"));
				outVO.setuNum(rs.getString("U_NUM"));
				outVO.setTotal(rs.getInt("TOTAL"));
				
				outList.add(outVO);
			}
	        LOG.debug("4.return : "+outList);
		} catch(SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=======SQLException======="+e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outList;
	}
	/**
	 *  둘중의 하나  널이거나 / 둘다 널이 아닐때
	 */

public List<?> doRetriev(DTO dto) {
// SearchVO inVO = (SearchVO) dto; //param
ResVO inVO = (ResVO) dto; //return
ResVO outVO = null; //return
List<ResVO> outList = new ArrayList<ResVO>(); //return

Connection connection = null; //DB Connection
PreparedStatement pstmt = null; //VS Statement
ResultSet rs = null;
		
try {
	//1.Connection
	connection = this.connectionMaker.getConnection();
	LOG.debug("1.Connection : "+connection);
	
	//2.PreparedStatement pstmt
	//검색 조건
	StringBuilder sbWhere = new StringBuilder();
	//검색 Query
	StringBuilder sb = new StringBuilder();
	
	//검색 조건
//			sbWhere.append("WHERE SUBSTR(t1.res_num,0,8) like ? \n");
//
//			sbWhere.append(" AND RES_STATE like ? \n");
	//---------------검색조건-------------------------------------------------------
	
			int div =0;

			if(!("".equals(inVO.getResState())) && (null !=inVO.getResNum() && !("".equals(inVO.getResNum()) ) && inVO.getResState()!=0)) {
				div=10;
				sbWhere.append("WHERE SUBSTR(t1.res_num,0,8) like ? \n");

				sbWhere.append(" AND RES_STATE like ? \n");
				//}else if( (inVO.getScDate()==null || inVO.getScDate().equals(""))  && (null !=inVO.getScDcity() && !("".equals(inVO.getScDcity()) ))) {
				}else if((inVO.getResState()==0||("".equals(inVO.getResState())))   && (null !=inVO.getResNum() || !("".equals(inVO.getResNum())))) {
					div=20;
					sbWhere.append("WHERE SUBSTR(t1.res_num,0,8) like ? \n");
			
				}else if(!("".equals(inVO.getResState()) && (inVO.getResNum()==null ||inVO.getResNum().equals("")))) {
					div=30;
					sbWhere.append(" WHERE RES_STATE like ? \n");
				
				}
			
			
			
			

	//---------------------------------------------------------------------------
	//2.1.SQL-목록조회 query
			sb.append(" SELECT *                                                   ");   
			sb.append(" FROM(                                                      ");
			sb.append("       SELECT b.RES_NUM,                                    ");
			sb.append("       b.RES_PASSPORT,                                      ");
			sb.append("       b.RES_ENAME,                                         ");
			sb.append("       b.RES_BIR,                                           ");
			sb.append("       b.RES_PHONE,                                         ");
			sb.append("       b.RES_EMAIL,                                         ");
			sb.append("       b.RES_PTYPE,                                         ");
			sb.append("       b.RES_GENDER,                                        ");
			sb.append("       b.RES_STATE,                                        \n ");
			sb.append("       b.RES_PAY,                                          \n ");
			sb.append("       b.RES_MILEAGE,                                      \n ");
			sb.append("       b.RES_SCODE,                                        \n ");
			sb.append("        b.U_NUM                                            \n ");
			sb.append(" FROM(	                                                  \n ");
			sb.append(" 	SELECT ROWNUM as rnum,A.*  	                          \n ");
			sb.append(" 	FROM(	                                              \n ");
			sb.append(" 		SELECT t1.*                                       \n ");
			sb.append(" 		FROM reservation t1                               \n ");
			//sb.append(" 		--?????                                           \n ");
		//	sb.append(" 	                                                      \n ");
	//sb.append("             --검색어                                                                             \n");
	//-where----------------------------------------------------------------------
	if(inVO.getResNum() != null || !inVO.getResNum().equals("")) {
		if(inVO.getResNum() != null) {
			sb.append(sbWhere.toString());
		}
	}
	//-where----------------------------------------------------------------------
	sb.append(" 		ORDER BY SUBSTR(t1.res_num,0,8) DESC              \n ");
	sb.append(" 		)A                                                \n ");
//	sb.append(" 		--page 끝번호                                       \n ");
	sb.append(" 	WHERE rownum <=(?*(?-1)+?)  \n ");
	sb.append(" 	)B                                                    \n ");
	sb.append(" WHERE rnum >=(?*(?-1)+1))                \n ");
	sb.append(" CROSS JOIN                                                \n ");   
	sb.append(" (                                                         \n ");   
	sb.append("     SELECT COUNT(*) TOTAL                                 \n ");   
	sb.append("     FROM reservation t1                                   \n ");         
	//sb.append("     --?????                                               \n ");   
	//sb.append("     --검색어                                                                                           \n");
	//-where----------------------------------------------------------------------
	if(inVO.getResNum() != null || !inVO.getResNum().equals("")) {
		if(inVO.getResNum() != null) {
			sb.append(sbWhere.toString());
		}
	}
	//-where----------------------------------------------------------------------
	
	sb.append(" )                                                        \n ");

	LOG.debug("2.query : \n"+sb.toString());
	
	
	//2.2.pstmt
	pstmt = connection.prepareStatement(sb.toString());
	LOG.debug("2.1.PreparedStatement : "+pstmt);
	
	//3.Param setting
	LOG.debug("3.param : "+inVO);
	//3.1.Param binding
	//검색어가 있는 경우

		//2.PAGE_SIZE 3.PAGE_NUM 4.PAGE_SIZE
		//5.PAGE_SIZE 6.PAGE_NUM
	if(div==10) {
	
		pstmt.setString(1, inVO.getResNum());
		pstmt.setInt(2, inVO.getResState());
		pstmt.setInt(3, inVO.getPageSize());
		pstmt.setInt(4, inVO.getPageNum());
		pstmt.setInt(5, inVO.getPageSize());
		pstmt.setInt(6, inVO.getPageSize());
		pstmt.setInt(7, inVO.getPageNum());
		pstmt.setString(8, inVO.getResNum());
		pstmt.setInt(9, inVO.getResState());

	}else if(div==20) {
		
		pstmt.setString(1, inVO.getResNum());
		pstmt.setInt(2, inVO.getPageSize());
		pstmt.setInt(3, inVO.getPageNum());
		pstmt.setInt(4, inVO.getPageSize());
		pstmt.setInt(5, inVO.getPageSize());
		pstmt.setInt(6, inVO.getPageNum());
		pstmt.setString(7, inVO.getResNum());
		
		
		
		
	}else if(div==30) {
		
		pstmt.setInt(1, inVO.getResState());
		pstmt.setInt(2, inVO.getPageSize());
		pstmt.setInt(3, inVO.getPageNum());
		pstmt.setInt(4, inVO.getPageSize());
		pstmt.setInt(5, inVO.getPageSize());
		pstmt.setInt(6, inVO.getPageNum());
		pstmt.setInt(7, inVO.getResState());
		
		
		
		
	}
	
	
	
	
	
	
	//4.Query 수행
	rs = pstmt.executeQuery();
	while(rs.next()) {
		//데이터를 1건씩 outVO에 담기
		outVO = new ResVO();
		
		
		
		//outVO.setSeq(Integer.parseInt(rs.getString("seq")));
		outVO.setResNum(rs.getString("RES_NUM"));
		outVO.setResPassport(rs.getString("RES_PASSPORT"));
		outVO.setResEname(rs.getString("RES_ENAME"));
		outVO.setResBir(rs.getString("RES_BIR"));
		outVO.setResphone(rs.getString("RES_PHONE"));
		outVO.setResEmail(rs.getString("RES_EMAIL"));
		
		
		outVO.setResPtype(rs.getInt("RES_PTYPE"));
		outVO.setResGender(rs.getInt("RES_GENDER"));
		outVO.setResState(rs.getInt("RES_STATE"));
		outVO.setResPay(rs.getInt("RES_PAY"));
		outVO.setResMileage(rs.getInt("RES_MILEAGE"));
		outVO.setResScode(rs.getString("RES_SCODE"));
		outVO.setuNum(rs.getString("U_NUM"));
		outVO.setTotal(rs.getInt("TOTAL"));
		
		outList.add(outVO);
	}
    LOG.debug("4.return : "+outList);
} catch(SQLException e) {
	LOG.debug("===========================");
	LOG.debug("=======SQLException======="+e.getMessage());
	LOG.debug("===========================");
	e.printStackTrace();
} finally {
	JDBCResClose.close(rs);
	JDBCResClose.close(pstmt);
	JDBCResClose.close(connection);
}
return outList;
}
}
