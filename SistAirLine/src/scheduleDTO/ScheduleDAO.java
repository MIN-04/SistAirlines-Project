package scheduleDTO;

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

public class ScheduleDAO extends WorkDiv {
   private ConnectionMaker connectionMaker;

   public ScheduleDAO() {
      connectionMaker = new ConnectionMaker();
   }

   @Override
   public DTO doSelectOne(DTO dto) {   
      ScheduleVO inVO = (ScheduleVO) dto;
   Connection connection = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   ScheduleVO outVO=null;
   StringBuilder sb = new StringBuilder(); // 검색 query

   // main query
   sb.append("SELECT                                                 ");
   sb.append("    TO_CHAR(sc_date,'YYYY-MM-DD') sc_date,             ");
   sb.append("    sc_dtime,                                          ");
   sb.append("    sc_stime,                                          ");
   sb.append("    (SELECT C_KOR FROM CITY WHERE C_ENG = SC_DCITY ) SC_DCITY,                                          ");
   sb.append("    (SELECT C_KOR FROM CITY WHERE C_ENG = SC_ACITY ) SC_ACITY,                                          ");
   sb.append("    sc_price,                                          ");
   sb.append("    sc_mileage,                                        ");
   sb.append("    sc_aname,                                          ");
   sb.append("    sc_capacity,                                       ");
   sb.append("    sc_unoccupied,                                     ");
   sb.append("    sc.sc_code sc_code,                                        ");
   sb.append("    sc_state                                           ");
   sb.append("FROM                                                   ");
   sb.append("    f_schedule sc                                      ");
   sb.append("WHERE sc.sc_code=(SELECT DISTINCT(se.sc_code)          ");
   sb.append("                FROM sc_seats se                       ");
   sb.append("                WHERE se.se_code=?)");


   try {
      // 1. connection
      connection = connectionMaker.getConnection();
      LOG.debug("1.connection=" + connection);

      // 2.query
      LOG.debug("2.Query=" + sb.toString());
      pstmt = connection.prepareStatement(sb.toString());

      // 3.param
      LOG.debug("3.param=\n" + inVO);

      pstmt.setString(1, inVO.getScCode());
   
      // 4.query수행
      rs = pstmt.executeQuery();
      while (rs.next()) {
         // Data 1건을 outVO담기.
         outVO = new ScheduleVO();

         outVO.setScDate(rs.getString("sc_date"));
         outVO.setScDtime(rs.getString("sc_dtime"));
         outVO.setScStime(rs.getString("sc_stime"));
         outVO.setScDcity(rs.getString("sc_dcity"));
         outVO.setScAcity(rs.getString("sc_acity"));
         outVO.setScPrice(rs.getString("sc_price"));
         outVO.setScMileage(rs.getInt("sc_mileage"));
         outVO.setScAname(rs.getString("sc_aname"));
         outVO.setScCapacity(rs.getString("sc_capacity"));
         outVO.setScUnoccupied(rs.getString("sc_unoccupied"));
         outVO.setScCode(rs.getString("sc_code"));
         outVO.setScState(rs.getInt("sc_state")); 
         
         
      }
      

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
   
   
   public DTO admindoSelectOne(DTO dto) {	
		ScheduleVO inVO = (ScheduleVO) dto;
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ScheduleVO outVO=null;
	StringBuilder sb = new StringBuilder(); // 검색 query

	// main query
	sb.append("SELECT                                                 ");
	sb.append("    TO_CHAR(SC_DATE,'YYYYMMDD')  sc_date,          ");
	sb.append("    sc_dtime,                                          ");
	sb.append("    sc_stime,                                          ");
	sb.append("    sc_dcity,                                          ");
	sb.append("    sc_acity,                                          ");
	sb.append("    sc_price,                                          ");
	sb.append("    sc_mileage,                                        ");
	sb.append("    sc_aname,                                          ");
	sb.append("    sc_capacity,                                       ");
	sb.append("    sc_unoccupied,                                     ");
	sb.append("    sc_code,                                        ");
	sb.append("    sc_state                                           ");
	sb.append("FROM                                                   ");
	sb.append("    f_schedule                                    ");
	sb.append("WHERE sc_code=?						"				);


	try {
		// 1. connection
		connection = connectionMaker.getConnection();
		LOG.debug("1.connection=" + connection);

		// 2.query
		LOG.debug("2.Query=" + sb.toString());
		pstmt = connection.prepareStatement(sb.toString());

		// 3.param
		LOG.debug("3.param=\n" + inVO);

		pstmt.setString(1, inVO.getScCode());
	
		// 4.query수행
		rs = pstmt.executeQuery();
		while (rs.next()) {
			// Data 1건을 outVO담기.
			outVO = new ScheduleVO();

			outVO.setScDate(rs.getString("sc_date"));
			outVO.setScDtime(rs.getString("sc_dtime"));
			outVO.setScStime(rs.getString("sc_stime"));
			outVO.setScDcity(rs.getString("sc_dcity"));
			outVO.setScAcity(rs.getString("sc_acity"));
			outVO.setScPrice(rs.getString("sc_price"));
			outVO.setScMileage(rs.getInt("sc_mileage"));
			outVO.setScAname(rs.getString("sc_aname"));
			outVO.setScCapacity(rs.getString("sc_capacity"));
			outVO.setScUnoccupied(rs.getString("sc_unoccupied"));
			outVO.setScCode(rs.getString("sc_code"));
			outVO.setScState(rs.getInt("sc_state")); 
			
			
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

	

   @Override
   public int doUpdate(DTO dto) {
	   int flag = 0;
		ScheduleVO inVO = (ScheduleVO) dto; //param
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement 해킹에 취약
		
		try {
			//1.Connection 생성
			connection = connectionMaker.getConnection();
			connection.setAutoCommit(false); //transaction을 개발자가 관리
			
			//2.PreparedStatement pstmt
			//2.1.SQL-Update query
			StringBuilder sb = new StringBuilder(); //속도 향상
			sb.append("UPDATE f_schedule          \n");
			sb.append("SET     sc_date= ?      ,                 \n");
			sb.append("     sc_dtime= ?        ,              \n");
			sb.append("     sc_stime= ?        ,              \n");
			sb.append("     sc_dcity= ?        ,              \n");
			sb.append("     sc_acity= ?        ,              \n");
			sb.append("     sc_price= ?        ,              \n");
			sb.append("     sc_mileage= ?      ,              \n");
			sb.append("     sc_aname      = ?  ,             \n");
			sb.append("     sc_capacity   = ?  ,              \n");
			sb.append("     sc_unoccupied = ?  ,             \n");
			sb.append("     sc_code       = ?  ,              \n");
			sb.append("     sc_state      = ?                    \n");
			sb.append(" WHERE sc_code=?	    \n");
			
			LOG.debug("2.SQL(Query) :\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting
			LOG.debug("3.param : \n"+inVO);
			//3.1.Param binding
			pstmt.setString(1, inVO.getScDate()); 
			pstmt.setString(2, inVO.getScDtime()); 
			pstmt.setString(3, inVO.getScStime()); 
			pstmt.setString(4, inVO.getScDcity()); 
			pstmt.setString(5, inVO.getScAcity()); 
			pstmt.setString(6, inVO.getScPrice()); 
			pstmt.setInt(7, inVO.getScMileage()); 
			pstmt.setString(8, inVO.getScAname()); 
			pstmt.setString(9, inVO.getScCapacity()); //내용
			pstmt.setString(10, inVO.getScUnoccupied()); //수정아이디
			pstmt.setString(11, inVO.getScCode()); //순서
			pstmt.setInt(12, inVO.getScState()); //순서
			pstmt.setString(13, inVO.getScCode()); //순서
			
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
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int doInsert(DTO dto) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
	public List<ScheduleVO> doRetrieve(DTO dto) {
		ScheduleVO inVO = (ScheduleVO) dto;
		List<ScheduleVO> outList = new ArrayList<ScheduleVO>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder(); // 검색 query

		// main query
		sb.append("SELECT TO_CHAR(SC_DATE,'YYYY.MM.DD') SC_DATE,			\n");
		sb.append("		SC_DTIME,											\n");
		sb.append("		SC_STIME,											\n");
		sb.append("		(SELECT C_KOR FROM CITY WHERE C_ENG = SC_DCITY ) SC_DCITY,\n");
		sb.append("		(SELECT C_KOR FROM CITY WHERE C_ENG = SC_ACITY ) SC_ACITY,\n");
		sb.append("		TO_CHAR(SC_PRICE,'999,999') \"PRICE\",				\n");
		sb.append("		SC_MILEAGE,											\n");
		sb.append("    	SC_CAPACITY,     									\n");
		sb.append("    	SC_UNOCCUPIED,   									\n");
		sb.append("		SC_CODE 											\n");
		sb.append("FROM F_SCHEDULE      									\n");
		sb.append("WHERE TO_CHAR(SC_DATE,'YYYYMMDD') = ?					\n");
		sb.append("AND SC_DCITY = (SELECT C_ENG FROM CITY WHERE C_KOR = ? ) \n");
		sb.append("AND SC_ACITY = (SELECT C_ENG FROM CITY WHERE C_KOR = ? )	\n");
		sb.append("ORDER BY SC_DTIME 										\n");

		try {
			// 1. connection
			connection = connectionMaker.getConnection();
			System.out.println("1.connection=" + connection);

			// 2.query
			System.out.println("2.Query=" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			// 3.param
			System.out.println("3.param=\n" + inVO);

			pstmt.setString(1, inVO.getScDate());
			pstmt.setString(2, inVO.getScDcity());
			pstmt.setString(3, inVO.getScAcity());

			// 4.query수행
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// Data 1건을 outVO담기.
				ScheduleVO outVO = new ScheduleVO();

				outVO.setScDate(rs.getString("SC_DATE"));
				outVO.setScDtime(rs.getString("SC_DTIME"));
				outVO.setScStime(rs.getString("SC_STIME"));
				outVO.setScDcity(rs.getString("SC_DCITY"));
				outVO.setScAcity(rs.getString("SC_ACITY"));
				outVO.setScPrice(rs.getString("PRICE"));
				outVO.setScMileage(rs.getInt("SC_MILEAGE"));
				outVO.setScCapacity(rs.getString("SC_CAPACITY"));
				outVO.setScUnoccupied(rs.getString("SC_UNOCCUPIED"));
				outVO.setScCode(rs.getString("SC_CODE"));

				outList.add(outVO);
			}
			System.out.println("4 return :" + outList);

		} catch (Exception e) {
			System.out.println("===================");
			System.out.println("=SQLException=" + e.getMessage());
			System.out.println("===================");
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outList;
	}

	public List<ScheduleVO> doRetriveNotIN(DTO dto, List<ScheduleVO> list) { //인천 없을때 중간 역할하는 스케쥴 출력 메소드
		ScheduleVO inVO = (ScheduleVO) dto;
		List<ScheduleVO> inList = list;
		List<ScheduleVO> outList = new ArrayList<ScheduleVO>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//while(!inList.isEmpty()){//배열 요소가 있으면 false 없으면 true
		for(ScheduleVO vo:inList) {	
			StringBuilder sb = new StringBuilder(); // 검색 query
			System.out.println("vo"+vo);
			// main query
			sb.append("SELECT TO_CHAR(SC_DATE,'YYYY.MM.DD') SC_DATE,				\n");
			sb.append("		SC_DTIME,											\n");
			sb.append("		SC_STIME,											\n");
			sb.append("		(SELECT C_KOR FROM CITY WHERE C_ENG = SC_DCITY ) SC_DCITY,  											\n");
			sb.append("		(SELECT C_KOR FROM CITY WHERE C_ENG = SC_ACITY ) SC_ACITY,  											\n");
			sb.append("		TO_CHAR(SC_PRICE,'999,999') \"PRICE\",				\n");
			sb.append("		SC_MILEAGE,											\n");
			sb.append("    	SC_CAPACITY,     									\n");
			sb.append("    	SC_UNOCCUPIED,   									\n");
			sb.append("		SC_CODE 											\n");
			sb.append("FROM F_SCHEDULE      									\n");
			sb.append("WHERE TO_CHAR(SC_DATE,'YYYYMMDD') = ?					\n");
			sb.append("AND SC_DTIME > TO_CHAR(TO_DATE(SUBSTR(?,1,2)||SUBSTR(?,4,5), 'HH24:MI') + TO_NUMBER(SUBSTR(?,1,2))/24+ TO_NUMBER(SUBSTR(?,4,5))/(24*60),'HH24:MI')\n");
			sb.append("AND SC_DCITY = (SELECT C_ENG FROM CITY WHERE C_KOR = ? ) \n");
			sb.append("AND SC_ACITY = (SELECT C_ENG FROM CITY WHERE C_KOR = ? )	\n");
			sb.append("AND rownum = 1											\n");
			sb.append("ORDER BY SC_DTIME 										\n");
			

			try {
				// 1. connection
				connection = connectionMaker.getConnection();
				System.out.println("1.connection=" + connection);

				// 2.query
				System.out.println("2.Query=" + sb.toString());
				pstmt = connection.prepareStatement(sb.toString());

				// 3.param
				System.out.println("3.1.param list(=vo)=\n" + vo);
				System.out.println("3.2.param inVO=\n" + inVO);
				
				pstmt.setString(1, inVO.getScDate());
				pstmt.setString(2, vo.getScDtime());
				pstmt.setString(3, vo.getScDtime());
				pstmt.setString(4, vo.getScStime());
				pstmt.setString(5, vo.getScStime());
				pstmt.setString(6, inVO.getScDcity());
				pstmt.setString(7, inVO.getScAcity());
				
				// 4.query수행
				rs = pstmt.executeQuery();
				while(rs.next()) {
					// Data 1건을 outVO담기.
					ScheduleVO outVO = new ScheduleVO();

					outVO.setScDate(rs.getString("SC_DATE"));
					outVO.setScDtime(rs.getString("SC_DTIME"));
					outVO.setScStime(rs.getString("SC_STIME"));
					outVO.setScDcity(rs.getString("SC_DCITY"));
					outVO.setScAcity(rs.getString("SC_ACITY"));
					outVO.setScPrice(rs.getString("PRICE"));
					outVO.setScMileage(rs.getInt("SC_MILEAGE"));
					outVO.setScCapacity(rs.getString("SC_CAPACITY"));
					outVO.setScUnoccupied(rs.getString("SC_UNOCCUPIED"));
					outVO.setScCode(rs.getString("SC_CODE"));
					
					System.out.println("outVO="+outVO.toString());
					outList.add(vo);
					outList.add(outVO);
					
				}
				System.out.println("4 return :" + outList);

			} catch (Exception e) {
				System.out.println("===================");
				System.out.println("=SQLException=" + e.getMessage());
				System.out.println("===================");
				e.printStackTrace();
			} finally {
				JDBCResClose.close(rs);
				JDBCResClose.close(pstmt);
				JDBCResClose.close(connection);
			}
		}
				return outList;
	}
   
   
   public List<?> adminRetrive(DTO dto) {
      
      
		ScheduleVO inVO= (ScheduleVO) dto;//param
		List<ScheduleVO> outList = new ArrayList<ScheduleVO>();//return

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
	try {
		// 1. connection
		connection = connectionMaker.getConnection();
		LOG.debug("1.connection!=" + connection);

		//2.pstmt
		//검색조건
		StringBuilder sbWhere = new StringBuilder();
		//검색쿼리
		StringBuilder sb = new StringBuilder(); // 검색 query
		int div =0;

		//검색구분			
		//if( (null != inVO.getScDate()) && (null !=inVO.getScDcity())) {
		//if( (null != inVO.getScDate() || !("".equals(inVO.getScDate()))) && (null !=inVO.getScDcity() ||!("".equals(inVO.getScDcity())))) {
		if( (null != inVO.getScDate() && !("".equals(inVO.getScDate()))) && (null !=inVO.getScDcity() && !("".equals(inVO.getScDcity()) ))) {
			div=10;
			sbWhere.append("WHERE TO_CHAR(t1.SC_DATE,'YYYYMMDD') = ? \n");
			sbWhere.append("AND t1.sc_dcity = (SELECT C_ENG FROM CITY WHERE C_KOR = ? )\n");
			//}else if( (inVO.getScDate()==null || inVO.getScDate().equals(""))  && (null !=inVO.getScDcity() && !("".equals(inVO.getScDcity()) ))) {
			}else if( (inVO.getScDate()==null || inVO.getScDate().equals(""))  && null !=inVO.getScDcity()&& !("".equals(inVO.getScDcity()))) {
				div=20;
				sbWhere.append("WHERE t1.sc_dcity = (SELECT C_ENG FROM CITY WHERE C_KOR = ?) \n");
				//sbWhere.append("AND t1.sc_date = ? \n");
			}else if(null != inVO.getScDate()&&!("".equals(inVO.getScDate())) && (inVO.getScDcity()==null ||inVO.getScDcity().equals(""))) {
				div=30;
				sbWhere.append("WHERE TO_CHAR(t1.SC_DATE,'YYYYMMDD'	) = ? \n");
			}


		
		
		//2.1. main query
		sb.append("SELECT *                                                     \n");
		sb.append("FROM(                                                        \n");
		sb.append("    SELECT TO_CHAR(B.SC_DATE,'YYYYMMDD')    SC_DATE    \n");
		sb.append("          ,B.sc_dtime                                    \n");
		sb.append("          ,b.sc_stime                                    \n");
		sb.append("          ,b.sc_dcity                                    \n");
		sb.append("          ,b.sc_acity                                    \n");
		sb.append("          ,TO_CHAR(b.SC_PRICE,'999,999')  PRICE         \n");
		sb.append("          ,b.sc_mileage                                    \n");
		sb.append("          ,b.sc_aname                                    \n");
		sb.append("          ,b.sc_capacity                                    \n");
		sb.append("          ,b.sc_unoccupied                                    \n");
		sb.append("          ,b.sc_code                                    \n");
		sb.append("          ,b.sc_state                                    \n");
		sb.append("    FROM (                                                   \n");
		sb.append("        SELECT ROWNUM as rnum,A.*                            \n");
		sb.append("        FROM(                                                \n");
		sb.append("            SELECT t1.*                                      \n");
		sb.append("            FROM F_SCHEDULE t1                              \n");
		//sb.append("             --검색어                                                                             \n");
		//-where----------------------------------------------------------------------
		if(null != inVO.getScDate() || null !=inVO.getScDcity()) {
			sb.append(sbWhere.toString());
		}
		//--------------------------------------------------------------------------
		sb.append("            ORDER BY t1.sc_dtime DESC                         \n");
		sb.append("        )A                                                   \n");
		sb.append("        WHERE rownum <=(?*(?-1)+?)						    \n");
		sb.append("    )B                                                       \n");
		sb.append("    WHERE rnum >=(?*(?-1)+1) )              					\n");
		sb.append("CROSS JOIN                                                   \n");
		sb.append("(                                                            \n");
		sb.append("    SELECT COUNT(*) TOTAL                                    \n");
		sb.append("    FROM F_SCHEDULE t1                                     \n");
		//sb.append("             --검색어                                                                             \n");
		//-where----------------------------------------------------------------------
		if(null != inVO.getScDate() || null !=inVO.getScDcity()) {
			sb.append(sbWhere.toString());
		}
		//--------------------------------------------------------------------------
		sb.append(")                                                            \n");
		LOG.debug("2.query : \n"+sb.toString());
		//2.2.pstmt
		pstmt = connection.prepareStatement(sb.toString());
		LOG.debug("2.1.PreparedStatement : "+pstmt);
		
		//3.Param setting
		LOG.debug("3.param : "+inVO);	
		
		if(div==10) {
			
			//1.검색어
			//(&PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE)
			//(&PAGE_SIZE*(&PAGE_NUM-1)+1)
			//2.PAGE_SIZE 3.PAGE_NUM 4.PAGE_SIZE
			//5.PAGE_SIZE 6.PAGE_NUM
			//7.검색어

			pstmt.setString(1, inVO.getScDate());
			pstmt.setString(2, inVO.getScDcity());
			pstmt.setInt(3, inVO.getPageSize());
			pstmt.setInt(4, inVO.getPageNum());
			pstmt.setInt(5, inVO.getPageSize());
			pstmt.setInt(6, inVO.getPageSize());
			pstmt.setInt(7, inVO.getPageNum());
			pstmt.setString(8, inVO.getScDate());
			pstmt.setString(9, inVO.getScDcity());
		
	
		}else if(div==20) {
			pstmt.setString(1, inVO.getScDcity());
			pstmt.setInt(2, inVO.getPageSize());       
			pstmt.setInt(3, inVO.getPageNum());        
			pstmt.setInt(4, inVO.getPageSize());       
			pstmt.setInt(5, inVO.getPageSize());       
			pstmt.setInt(6, inVO.getPageNum());        
			pstmt.setString(7, inVO.getScDcity());  
		}else if(div==30) {
			pstmt.setString(1, inVO.getScDate());
			pstmt.setInt(2, inVO.getPageSize());       
			pstmt.setInt(3, inVO.getPageNum());        
			pstmt.setInt(4, inVO.getPageSize());       
			pstmt.setInt(5, inVO.getPageSize());       
			pstmt.setInt(6, inVO.getPageNum());        
			pstmt.setString(7, inVO.getScDate());  
		
		}else {
			//(&PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE)
			//(&PAGE_SIZE*(&PAGE_NUM-1)+1)
			//1.PAGE_SIZE
			//2.PAGE_NUM
			//3.PAGE_SIZE
			//4.PAGE_SIZE
			//5.PAGE_NUM
			
			pstmt.setInt(1, inVO.getPageSize());
			pstmt.setInt(2, inVO.getPageNum());
			pstmt.setInt(3, inVO.getPageSize());
			pstmt.setInt(4, inVO.getPageSize());
			pstmt.setInt(5, inVO.getPageNum());
			
		}
	


		// 4.query수행
		rs = pstmt.executeQuery();
		while (rs.next()) {
			// Data 1건을 outVO담기.
			ScheduleVO outVO = new ScheduleVO();
			
			outVO.setScDate(rs.getString("SC_DATE"));
			outVO.setScDtime(rs.getString("sc_dtime"));
			outVO.setScStime(rs.getString("sc_stime"));
			outVO.setScDcity(rs.getString("sc_dcity"));
			outVO.setScAcity(rs.getString("sc_acity"));
			outVO.setScPrice(rs.getString("PRICE"));
			outVO.setScMileage(rs.getInt("sc_mileage"));
			outVO.setScAname(rs.getString("sc_aname"));
			outVO.setScCapacity(rs.getString("sc_capacity"));
			outVO.setScUnoccupied(rs.getString("sc_unoccupied"));
			outVO.setScCode(rs.getString("sc_code"));
			outVO.setScState(rs.getInt("sc_state"));
			//outVO.setSeq(Integer.parseInt(rs.getString("seq")));
			
			outVO.setTotal(rs.getInt("total"));
			outList.add(outVO);
			LOG.debug("4.return : "+outList);
		}
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
   

   
   

      public List<ScheduleDTO> doRetrive1(DTO dto) {
         Connection conn=null;
         PreparedStatement ppst=null;
         ResultSet rs=null;
         List<ScheduleDTO> list = new ArrayList<ScheduleDTO>();
         ScheduleDTO inVO=(ScheduleDTO)dto;
         LOG.debug("inVO:"+inVO);
         
         StringBuilder sb=new StringBuilder();
         try {
            conn=this.connectionMaker.getConnection();
            
            sb.append("SELECT            ");
            sb.append("TO_CHAR(sc_date,'YYYYMMDD') sc_date,");
            sb.append("    sc_dtime,        ");
            sb.append("    sc_stime,        ");
            sb.append("    sc_dcity,        ");
            sb.append("    sc_acity,        ");
            sb.append("TO_CHAR(sc_price,'999,999') sc_price, ");
            sb.append("    sc_mileage,      ");
            sb.append("    sc_aname,        ");
            sb.append("    sc_capacity,     ");
            sb.append("    sc_unoccupied,   ");
            sb.append("    sc_code          ");
            sb.append("FROM                 ");
            sb.append("    f_schedule       ");
            sb.append("WHERE TO_CHAR(sc_date,'YYYYMMDD')=?");
            sb.append("   AND sc_dcity=(SELECT C_ENG FROM CITY WHERE C_KOR = ? )      ");
            sb.append("   AND sc_acity=(SELECT C_ENG FROM CITY WHERE C_KOR = ? )      ");
            sb.append("   ORDER BY sc_dtime      ");
            
            
            ppst=conn.prepareStatement(sb.toString());
            
            ppst.setString(1, inVO.getScDate());
            ppst.setString(2, inVO.getScDcity());
            ppst.setString(3, inVO.getScAcity());
            
            rs=ppst.executeQuery();
            
            if(rs.next()) {
               do {
               ScheduleDTO outVO=new ScheduleDTO();
               
               outVO.setScDate(rs.getString("sc_date"));      
               outVO.setScDtime(rs.getString("sc_dtime"));    
               outVO.setScStime(rs.getString("sc_stime"));     
               outVO.setScDcity(rs.getString("sc_dcity"));     
               outVO.setScAcity(rs.getString("sc_acity"));     
               outVO.setScPrice(rs.getString("sc_price"));     
               outVO.setScMileage(rs.getString("sc_mileage"));   
               outVO.setScAname(rs.getString("sc_aname"));     
               outVO.setScCapacity(rs.getString("sc_capacity"));
               LOG.debug("sc_capacity: "+rs.getString("sc_capacity"));
               outVO.setScUnoccupied(rs.getString("sc_unoccupied"));
               LOG.debug("sc_unoccupied: "+rs.getString("sc_unoccupied"));
               outVO.setScCode(rs.getString("sc_code"));       
               LOG.debug("outVO:"+outVO);
               list.add(outVO);
               }while(rs.next());
            }
         }catch(SQLException e){

            e.printStackTrace();
         }finally {
            try {
               if(rs != null) rs.close();
               if(ppst != null) ppst.close();
               if(conn != null) conn.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }//finally
         
         
         return list;
      }
      
   
   
   
   public int doUpdateSeat(DTO dto) {
         int flag=0;
         ScheduleDTO inVO=(ScheduleDTO)dto;
         LOG.debug("OccupiedVO: "+inVO);
         
         StringBuilder sb=new StringBuilder();
         Connection conn=null;
         PreparedStatement ppst=null;
         try {
            conn=connectionMaker.getConnection();
            sb.append("UPDATE f_schedule               ");
            sb.append("SET                                  ");
            sb.append("    sc_unoccupied =sc_unoccupied-1   ");
            sb.append("WHERE                                ");
            sb.append("    sc_code = ?                     ");
            
            ppst=conn.prepareStatement(sb.toString());
            ppst.setString(1, inVO.getScCode());
            
            flag=ppst.executeUpdate();
            
         }catch(SQLException e) {
            LOG.debug("=========================");
            LOG.debug("SQLException: "+e.getMessage());
            LOG.debug("=========================");
         }finally {
            try {
               if(ppst != null) ppst.close();
               if(conn != null) conn.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }//finally
         
         return flag;
      }

}