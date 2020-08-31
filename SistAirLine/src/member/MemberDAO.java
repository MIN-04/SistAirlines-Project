/**
 *<pre>
 * com.hr.member
 * Class Name : MemberDao.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-05           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-05 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.DTO;
import com.hr.cmn.JDBCResClose;
import com.hr.cmn.MessageVO;
import com.hr.cmn.SearchVO;
import com.hr.cmn.WorkDiv;

import User.UserVO;

public class MemberDAO extends WorkDiv {
	private ConnectionMaker connectionMaker;
	
	public MemberDAO() {
		connectionMaker = new ConnectionMaker();
	}
	
	/**
	 * 
	 *Method Name:passCheck
	 *작성일: 2020. 3. 12.
	 *작성자: sist
	 *설명: 비번 체크
	 *@param dto
	 *@return
	 */
	public DTO passCheck(DTO dto) {
		MemberVO inVO = (MemberVO)dto; //param
		MessageVO outVO = null; //return
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement 해킹에 취약
		ResultSet rs = null; //결과 값 처리 class
		
		try {
			//1.Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.connection : "+connection);
			
			//2.PreparedStatement
			//2.1.SQL-단건 조회 query
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT count(*) cnt  \n");
			sb.append(" FROM users       \n");
			sb.append(" WHERE u_id = ?  \n");
			sb.append(" AND  u_pw   = ?  \n");
			
			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting 
			LOG.debug("3.param :\n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getuID());
			pstmt.setString(2, inVO.getuPW());
			
			//4.Query 수행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//데이터를 1건씩 outVO에 담기
				outVO = new MessageVO();
				int result = rs.getInt("cnt"); //아이디
				//ID/비번 있으면 1 그렇치 않으면 0
				if(0 == result) {
					outVO.setMsgId("20");//ID/비번가 없는 경우
					outVO.setMsgContents("비번을 확인 하세요.");//ID/비번가 없는 경우
				}else {
					outVO.setMsgId("1");//ID/비번가 있는 경우
					outVO.setMsgContents("ID/비번OK");//ID/비번가 있는 경우
				}
				LOG.debug("4.return :\n"+outVO);
			}
		} catch(SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=======SQLException======="+e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally {
			//5.preparedStatement, ResultSet 자원 반납 - 사용하는 역순으로 close
			//6.Connection 종료
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outVO;
	}	
	
	
	/**
	 * 
	 *Method Name:passCheck
	 *작성일: 2020. 3. 12.
	 *작성자: sist
	 *설명: 비번 체크
	 *@param dto
	 *@return
	 */
	public DTO adminLogin(DTO dto) {
		MemberVO inVO = (MemberVO)dto; //param
		MessageVO outVO = null; //return
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement 해킹에 취약
		ResultSet rs = null; //결과 값 처리 class
		
		try {
			//1.Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.connection : "+connection);
			
			//2.PreparedStatement
			//2.1.SQL-단건 조회 query
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT authority \n");
			sb.append(" FROM users       \n");
			sb.append(" WHERE u_id = ?  \n");
		
			
			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting 
			LOG.debug("3.param :\n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getuID());
			
			//4.Query 수행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//데이터를 1건씩 outVO에 담기
				outVO = new MessageVO();
				int result = rs.getInt("authority"); //권한 체크 1:일반회원 9:관리자
				//ID/비번 있으면 1 그렇치 않으면 0
				if(9 == result) {
					outVO.setMsgId("30");//관리자
					outVO.setMsgContents("관리자로 로그인합니다.");
				}else {
					outVO.setMsgId("40");//일반회원
					outVO.setMsgContents("메인페이지로 이동합니다.");
				}
				LOG.debug("4.return :\n"+outVO);
			}
		} catch(SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=======SQLException======="+e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally {
			//5.preparedStatement, ResultSet 자원 반납 - 사용하는 역순으로 close
			//6.Connection 종료
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outVO;
	}	
	/**
	 * 
	 *Method Name:idCheck
	 *작성일: 2020. 3. 12.
	 *작성자: sist
	 *설명: 로그인ID Check
	 *@param dto
	 *@return
	 */
	public DTO idCheck(DTO dto) {
		MemberVO inVO = (MemberVO) dto; //param
		MessageVO outVO = null; //return
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement 해킹에 취약
		ResultSet rs = null; //결과 값 처리 class
		
		try {
			//1.Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.connection : "+connection);
			
			//2.PreparedStatement
			//2.1.SQL-단건 조회 query
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT count(*) cnt  \n");
			sb.append(" FROM users       \n");
			sb.append(" WHERE U_ID = ?  \n");
			
			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting 
			LOG.debug("3.param :\n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getuID());
			
			//4.Query 수행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//데이터를 1건씩 outVO에 담기
				outVO = new MessageVO();
				int result = rs.getInt("cnt"); //아이디
				//ID 있으면 1 그렇치 않으면 0
				if(0 == result) {
					outVO.setMsgId("10");//ID가 없는 경우
					outVO.setMsgContents("ID를 확인 하세요.");//ID가 없는 경우
				}else {
					outVO.setMsgId("1");//ID가 있는 경우
					outVO.setMsgId("ID가 있습니다.");//ID가 있는 경우
				}
				
				
				LOG.debug("4.return :\n"+outVO);
			}
		} catch(SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=======SQLException======="+e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally {
			//5.preparedStatement, ResultSet 자원 반납 - 사용하는 역순으로 close
			//6.Connection 종료
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outVO;
	}
	
	
	/**
	 * 
	 *@Method Name:doSelectOne
	 *@작성일: 2020. 2. 5.
	 *@작성자: sist
	 *@설명: 단건 조회
	 *@param dto
	 *@return MemberVO
	 */
	@Override
	public DTO doSelectOne(DTO dto) {
		MemberVO inVO = (MemberVO) dto; //param
		MemberVO outVO = null; //return
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement 해킹에 취약
		ResultSet rs = null; //결과 값 처리 class
		
		try {
			//1.Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.connection : "+connection);
			
			//2.PreparedStatement
			//2.1.SQL-단건 조회 query
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT               \n");
			sb.append("    u_num,           \n");
			sb.append("    u_id,            \n");
			sb.append("    u_pw,            \n");
			sb.append("    u_kname,         \n");
			sb.append("    u_ename,         \n");
			sb.append("    u_bir,           \n");
			sb.append("    u_phone,         \n");
			sb.append("    u_email,         \n");
			sb.append("    u_gender,        \n");
			sb.append("    u_address,       \n");
			sb.append("    u_country,       \n");
			sb.append("    u_mileage,       \n");
			sb.append("    u_maindepart,    \n");
			sb.append("    authority        \n");
			sb.append("FROM                 \n");
			sb.append("    users			\n");
			sb.append("WHERE u_id=?			\n");

			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting 
			LOG.debug("3.param :\n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getuID());
			
			//4.Query 수행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//데이터를 1건씩 outVO에 담기
				outVO = new MemberVO();
				outVO.setuNum(rs.getInt("u_num"));
				LOG.debug("3.u_num :\n"+rs.getInt("u_num"));
				outVO.setuID(rs.getString("u_id"));
				outVO.setuPW(rs.getString("u_pw"));
				outVO.setuKname(rs.getString("u_kname"));
				outVO.setuEname(rs.getString("u_ename"));
				outVO.setuBir(rs.getString("u_bir"));
				outVO.setuPhone(rs.getString("u_phone"));
				outVO.setuEmail(rs.getString("u_email"));
				outVO.setuGender(rs.getInt("u_gender"));
				outVO.setuAddress(rs.getString("u_address"));
				outVO.setuCountry(rs.getString("u_country"));
				outVO.setuMileage(rs.getInt("u_mileage"));
				outVO.setuMaindepart(rs.getString("u_maindepart"));
				outVO.setAuthority(rs.getInt("authority"));
				
				LOG.debug("4.return :\n"+outVO);
			}
		} catch(SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=======SQLException======="+e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally {
			//5.preparedStatement, ResultSet 자원 반납 - 사용하는 역순으로 close
			//6.Connection 종료
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outVO;
	}

	@Override
	public int doUpdate(DTO dto) {
		int flag = 0;
		MemberVO inVO = (MemberVO) dto; //param
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement 해킹에 취약
		
		try {
			//1.Connection 생성
			connection = connectionMaker.getConnection();
			connection.setAutoCommit(false); //transaction을 개발자가 관리
			
			//2.PreparedStatement pstmt
			//2.1.SQL-Update query
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE users			\n");
			sb.append("SET                  \n");
			sb.append("    u_pw = ?        \n");
			sb.append("    ,u_kname = ?     \n");
			sb.append("    ,u_ename = ?     \n");
			sb.append("    ,u_bir = ?       \n");
			sb.append("    ,u_phone = ?     \n");
			sb.append("    ,u_email = ?     \n");
			sb.append("    ,u_gender = ?    \n");
			sb.append("    ,u_address = ?   \n");
			sb.append("    ,u_country = ?   \n");
			sb.append("    ,u_mileage = ?   \n");
			sb.append("    ,u_maindepart= ?  \n");
			sb.append("     ,authority =   ?\n");
			sb.append("WHERE                \n");
			sb.append("	u_num=?             \n");
			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting
			LOG.debug("3.param : \n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getuPW()); //비밀번호
			pstmt.setString(2, inVO.getuKname()); //이름
			pstmt.setString(3, inVO.getuEname()); 
			pstmt.setString(4, inVO.getuBir()); 
			pstmt.setString(5, inVO.getuPhone());
			pstmt.setString(6, inVO.getuEmail()); 
			pstmt.setInt(7, inVO.getuGender()); 
			pstmt.setString(8, inVO.getuAddress()); 
			pstmt.setString(9, inVO.getuCountry()); 
			pstmt.setInt(10, inVO.getuMileage()); 
			pstmt.setString(11, inVO.getuMaindepart());
			pstmt.setInt(12, inVO.getAuthority());
			pstmt.setInt(13, inVO.getuNum()); 
			
			
//			pstmt.setString(1, inVO.getuPW()); //비밀번호
//			pstmt.setString(2, inVO.getuKname()); //이름
//			pstmt.setString(3, inVO.getuEname()); 
//			pstmt.setString(4, inVO.getuPhone());
//			pstmt.setString(5, inVO.getuEmail()); 
//			pstmt.setInt(6, inVO.getuGender()); 
//			pstmt.setString(7, inVO.getuAddress()); 
//			pstmt.setString(8, inVO.getuCountry()); 
//			pstmt.setString(9, inVO.getuMaindepart());
//			pstmt.setInt(10, inVO.getuNum()); 
			
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

	@Override
	public int doDelete(DTO dto) {
		int flag = 0;
		//param
		MemberVO inVO = (MemberVO) dto;
				
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement
		
		try {
			//1.Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.Connection : "+connection);
			
			//2.PreparedStatement pstmt
			//2.1.SQL-Delete query
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM hr_member \n");
			sb.append("WHERE member_id = ?   \n");
			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting
			LOG.debug("3.Param :\n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getuID()); //아이디
						
			//4.Query 수행(1 성공, 0 실패)
			flag = pstmt.executeUpdate();
			LOG.debug("4.flag : "+flag);
		} catch (SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=======SQLException======="+e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally {
			//5.preparedStatement 자원 반납 - 사용하는 역순으로 close
			//6.Connection 종료
			JDBCResClose.close(pstmt); //PreparedStatement
			JDBCResClose.close(connection); //Connection
		}
		return flag;
	}

	@Override
	public int doInsert(DTO dto) {
		int flag = 0;
		MemberVO inVO = (MemberVO) dto; //param
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement
				
		try {
			//1.Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.Connection : "+connection);
			
			//2.PreparedStatement pstmt
			//2.1.SQL-Insert query
			StringBuilder sb = new StringBuilder();
			sb.append(" INSERT INTO users		 (			\n");
			sb.append("     U_NUM,                        \n");
			sb.append("     U_ID,                         \n");
			sb.append("     U_PW,                         \n");
			sb.append("     U_KNAME,                    \n");
			sb.append("     U_ENAME,                    \n");
			sb.append("     U_BIR,                      \n");
			sb.append("     U_PHONE,                      \n");
			sb.append("     U_EMAIL,                      \n");
			sb.append("     U_GENDER,                     \n");
			sb.append("     U_ADDRESS,                    \n");
			sb.append("     U_COUNTRY,                    \n");
			sb.append("     U_MILEAGE,                    \n");
			sb.append("     U_MAINDEPART,                     \n");
			sb.append("     AUTHORITY                       \n");
			sb.append(" ) VALUES (                          \n");
			sb.append("     USERS_SEQ.NEXTVAL,       \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?,                              \n");
			sb.append("     ?                               \n");
			sb.append(" 	)                               \n");

			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting
			LOG.debug("3.param :\n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getuID());// �젣紐�
			pstmt.setString(2, inVO.getuPW());// �궡�슜
			pstmt.setString(3, inVO.getuKname());
			pstmt.setString(4, inVO.getuEname());
			pstmt.setString(5, inVO.getuBir());
			pstmt.setString(6, inVO.getuPhone());
			pstmt.setString(7, inVO.getuEmail());
			pstmt.setInt(8, inVO.getuGender());
			pstmt.setString(9, inVO.getuAddress());
			pstmt.setString(10, inVO.getuCountry());
			pstmt.setInt(11, inVO.getuMileage());
			pstmt.setString(12, inVO.getuMaindepart());
			pstmt.setInt(13, inVO.getAuthority());

			//4.Query 수행(1 성공, 0 실패)
			flag = pstmt.executeUpdate(); //트랜잭션 발생
			LOG.debug("4.flag : "+flag);
		} catch (SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=======SQLException======="+e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally {
			//5.preparedStatement 자원 반납 - 사용하는 역순으로 close
			//6.Connection 종료
			JDBCResClose.close(pstmt); //PreparedStatement
			JDBCResClose.close(connection); //Connection
		}
		return flag;
	}
	

	
	public List<?> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<UserVO> outList = new ArrayList<UserVO>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sbWhere = new StringBuilder();// 寃��깋議곌굔
		StringBuilder sb = new StringBuilder();// 寃��깋query

		if (null != inVO.getSearchDiv()) {
			if ("10".equals(inVO.getSearchDiv())) {
				sbWhere.append("WHERE U_NUM like ?||'%'\n");
			}
		}

		// main query
		sb.append(" SELECT *															\n");
		sb.append(" FROM(                                                               \n");
		sb.append("     SELECT				                                            \n");
		sb.append("          U_NUM,                                                       \n");
		sb.append("          U_ID,                                                        \n");
		sb.append("          U_PW,                                                        \n");
		sb.append("          U_KNAME,                                                     \n");
		sb.append("          U_ENAME,                                                     \n");
		sb.append("          U_BIR,                                                       \n");
		sb.append("          U_PHONE,                                                     \n");
		sb.append("          U_EMAIL,                                                     \n");
		sb.append("          U_GENDER,                                                    \n");
		sb.append("          U_ADDRESS,                                                   \n");
		sb.append("          U_COUNTRY,                                                   \n");
		sb.append("          U_MILEAGE,                                                   \n");
		sb.append("          U_MAINDEPART,                                                \n");
		sb.append("          AUTHORITY                   					              \n");
		sb.append("     FROM (                                                          \n");
		sb.append("         SELECT ROWNUM as rnum,A.*                                   \n");
		sb.append("         FROM(                                                       \n");
		sb.append("             SELECT t1.*                                             \n");
		sb.append("             FROM users t1                                     \n");
		sb.append("                                                           \n");
		// ---------------------------------where-------------------------------------
		if (null != inVO.getSearchDiv()) {
			if (null != inVO.getSearchWord()) {
				sb.append(sbWhere.toString());
			}
		}
		// ---------------------------------where-------------------------------------
		//sb.append("             ORDER BY t1.usernum                                  \n");
		sb.append("         )A                                                          \n");
		sb.append("         WHERE rownum <=(?*(?-1)+?)        \n");
		sb.append("     )B                                                              \n");
		sb.append("     WHERE rnum >=(?*(?-1)+1) )                     \n");
		sb.append(" CROSS JOIN                                                          \n");
		sb.append(" (                                                                   \n");
		sb.append("     SELECT COUNT(*) TOTAL                                           \n");
		sb.append("     FROM users t1                                               \n");
		sb.append("     --寃��깋議곌굔                                                        \n");
		// ---------------------------------where-------------------------------------
		if (null != inVO.getSearchDiv()) {
			if (null != inVO.getSearchWord()) {
				sb.append(sbWhere.toString());
			}
		}
		// ---------------------------------where-------------------------------------
		sb.append(" )                                                                   \n");

		try {
			
			// 1.
			connection = this.connectionMaker.getConnection();
			LOG.debug("1.connection=" + connection);

			// 2.pstmt

			LOG.debug("2.sql=\n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1. pstmt=" + pstmt);
			LOG.debug("2.2. param=" + inVO);
			// 寃��깋�뼱 �엳�뒗 寃쎌슦
			if (null != inVO.getSearchDiv() && null != inVO.getSearchWord()) {
				// 寃��깋�뼱
				// &PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE
				// &PAGE_SIZE*(&PAGE_NUM-1)+1)

				// 1.寃��깋�뼱
				// 2.PAGE_SIZE
				// 3.PAGE_NUM
				// 4.PAGE_SIZE
				// 5.PAGE_SIZE
				// 6.PAGE_NUM
				// 7.寃��깋�뼱

				pstmt.setString(1, inVO.getSearchWord());
				pstmt.setInt(2, inVO.getPageSize());
				pstmt.setInt(3, inVO.getPageNum());
				pstmt.setInt(4, inVO.getPageSize());
				pstmt.setInt(5, inVO.getPageSize());
				pstmt.setInt(6, inVO.getPageNum());
				pstmt.setString(7, inVO.getSearchWord());

				// 寃��깋�뼱�뾾�쓣�뻹
			} else {
				// 1.寃��깋�뼱
				// 2.PAGE_SIZE
				// 3.PAGE_NUM
				// 4.PAGE_SIZE
				// 5.PAGE_SIZE
				pstmt.setInt(1, inVO.getPageSize());
				pstmt.setInt(2, inVO.getPageNum());
				pstmt.setInt(3, inVO.getPageSize());
				pstmt.setInt(4, inVO.getPageSize());
				pstmt.setInt(5, inVO.getPageNum());	
			}
			
			
			// 4.query �닔�뻾

			rs = pstmt.executeQuery();
			while (rs.next()) {// Moves the cursor forward one row from its current position
				// Data 1嫄댁쓣 outVO�뿉 �떞湲�
				UserVO outVO = new UserVO();

				outVO.setuNum(rs.getInt("U_NUM"));
				outVO.setuId(rs.getString("U_ID"));
				outVO.setuPw(rs.getString("U_PW"));
				outVO.setuPw(rs.getString("U_KNAME"));
				outVO.setuEname(rs.getString("U_ENAME"));
				outVO.setuBir(rs.getString("U_BIR"));
				outVO.setuPhone(rs.getString("U_PHONE"));
				outVO.setuEmail(rs.getString("U_EMAIL"));
				outVO.setuGender(rs.getString("U_GENDER"));
				outVO.setuAddress(rs.getString("U_ADDRESS"));
				outVO.setuCountry(rs.getString("U_COUNTRY"));
				outVO.setuMileage(rs.getInt("U_MILEAGE"));
				outVO.setuMaindepart(rs.getString("U_MAINDEPART"));
				outVO.setAuthority(rs.getInt("AUTHORITY"));

				outList.add(outVO);

			}
			LOG.debug("4. return:" + outList);


		} catch (SQLException e) {
			LOG.debug("===================");
			LOG.debug("SQLException=" + e.getMessage());
			LOG.debug("===================");
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);// ResultSet
			JDBCResClose.close(pstmt);// preparedStatement
			JDBCResClose.close(connection);// connection
		}


		return outList;

	}


}
