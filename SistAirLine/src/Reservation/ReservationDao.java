package Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.DTO;
import com.hr.cmn.JDBCResClose;
import com.hr.cmn.SearchVO;

public class ReservationDao {
   private final Logger LOG = Logger.getLogger(ReservationDao.class);
   private ConnectionMaker connectionMaker;

   public ReservationDao() {
      connectionMaker = new ConnectionMaker();
   }
   
   public String doSelectResNo() {
      ReservationVO outVO = null;
      Connection connection = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         connection=connectionMaker.getConnection();
         LOG.debug("1. connection:" + connection);
   
      
         StringBuilder sb = new StringBuilder();
         
         sb.append("select                                                                              \n");                 
         sb.append("TO_CHAR(sysdate,'YYYYMMDD')||LPAD(TO_CHAR(resno_seq.NEXTVAL),7,'0000000') as RES_NUM \n");
         sb.append("from dual                                                                           \n");
         
         pstmt = connection.prepareStatement(sb.toString());
         LOG.debug("2. PreparedStatement:" + pstmt);
         LOG.debug("2.1. sql:\n" + sb.toString());

         rs = pstmt.executeQuery();
         if (rs.next()) {
            
            outVO = new ReservationVO();

            outVO.setResNum(rs.getString("RES_NUM"));
            LOG.debug("4. return :" + outVO);
         }

      } catch (SQLException e) {
         LOG.debug("===================");
         LOG.debug("=  SQL Exception  =" + e.getMessage());
         LOG.debug("===================");
         e.printStackTrace();
      } finally {
         JDBCResClose.close(rs);
         JDBCResClose.close(pstmt);
         JDBCResClose.close(connection);
      }

      return outVO.getResNum();

   }
   
   public DTO doSelectOne(DTO dto) {
      ReservationVO inVO = (ReservationVO) dto;
      ReservationVO outVO = null;
      LOG.debug("inVO: "+inVO);
      Connection connection = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         
         connection=connectionMaker.getConnection();
         LOG.debug("1. connection:" + connection);

      
         StringBuilder sb = new StringBuilder();
         
         sb.append("SELECT              \n");
         sb.append("    RES_NUM,        \n");
         sb.append("    RES_PASSPORT,   \n");
         sb.append("    RES_ENAME,      \n");
         sb.append("    RES_BIR,        \n");
         sb.append("    RES_PHONE,      \n");
         sb.append("    RES_EMAIL,      \n");
         sb.append("    RES_WAY,        \n");
         sb.append("    RES_PTYPE,      \n");
         sb.append("    RES_GENDER,     \n");
         sb.append("    RES_STATE,       \n");
         sb.append("    RES_State,       \n");
         sb.append("    RES_Pay,           \n");
         sb.append("    RES_Mileage,     \n");
         sb.append("    RES_SCODE,       \n");
         sb.append("    U_NUM           \n");
         sb.append("FROM RESERVATION    \n");
         sb.append("WHERE RES_NUM = '202003160000002'   \n");
         sb.append("AND RES_PASSPORT = 'M153A4567'\n");
               
         pstmt = connection.prepareStatement(sb.toString());
         LOG.debug("2. PreparedStatement:" + pstmt);
         LOG.debug("2.1. sql:\n" + sb.toString());

         
         LOG.debug("3. Param:" + inVO);
       
         
         rs = pstmt.executeQuery();
			if(rs.next()) {
            
            outVO = new ReservationVO();

            outVO.setResNum(rs.getString("RES_NUM"));
            outVO.setResPassport(rs.getString("RES_PASSPORT"));            
            outVO.setResEname(rs.getString("RES_ENAME"));
            outVO.setResBir(rs.getString("RES_BIR"));
            outVO.setResphone(rs.getString("RES_PHONE"));
            outVO.setResEmail(rs.getString("RES_EMAIL"));
            outVO.setResWay(rs.getInt("RES_WAY"));
            outVO.setResPtype(rs.getInt("RES_PTYPE"));
            outVO.setResGender(rs.getInt("RES_GENDER"));
            outVO.setResState(rs.getInt("RES_STATE"));
            outVO.setResPay(rs.getInt("RES_PAY"));
            outVO.setResMileage(rs.getInt("RES_MILEAGE"));
            outVO.setResScode(rs.getString("RES_STATE"));   
            outVO.setuNum(rs.getInt("U_NUM"));
            LOG.debug("4. return :" + outVO);
         }

      } catch (SQLException e) {
         LOG.debug("===================");
         LOG.debug("=  SQL Exception  =" + e.getMessage());
         LOG.debug("===================");
         e.printStackTrace();
      } finally {
         JDBCResClose.close(rs);
         JDBCResClose.close(pstmt);
         JDBCResClose.close(connection);
      }

      return outVO;

   }

   public int doChangeReservation(DTO dto) {
		int flag = 0;
		ReservationVO inVO = (ReservationVO) dto; //param
		
		Connection connection = null; //DB Connection
		PreparedStatement pstmt = null; //VS Statement 해킹에 취약
		
		try {
			//1.Connection 생성
			connection = connectionMaker.getConnection();
			connection.setAutoCommit(false); //transaction을 개발자가 관리
			
			//2.PreparedStatement pstmt
			//2.1.SQL-Update query
			StringBuilder sb = new StringBuilder(); //속도 향상
			sb.append("UPDATE reservation  \n");
	         sb.append("SET RES_STATE = ?  \n");
	         sb.append("WHERE RES_NUM = ?   \n");
	         sb.append("AND  RES_PASSPORT =?\n");

	         LOG.debug("2.SQL(Query) :\\n"+sb.toString());
			//2.2.pstmt
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1.PreparedStatement : "+pstmt);
			
			//3.Param setting
			LOG.debug("3.param : \n"+inVO);
			//3.1.Param binding
			pstmt.setInt(1, inVO.getNum()); //예약 상태 변경하기
			pstmt.setString(2, inVO.getResNum()); //내용
			pstmt.setString(3, inVO.getResPassport()); //수정아이디
			
			//4.Query 수행
			flag = pstmt.executeUpdate(); //DML 발생에 사용
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

   public int doChangeState(DTO dto) {
	      int flag = 0;
	      ReservationVO inVO = (ReservationVO) dto;

	      Connection connection = null;// DB Connection
	      PreparedStatement pstmt = null;// VS Statement

	      try {
	         // 1.Connection 
	         connection = connectionMaker.getConnection();
	         connection.setAutoCommit(false);// transaction 

	         LOG.debug("1. Connection: " + connection);
	         // 2.PreparedStatement
	         // 2.1 SQL
	         StringBuilder sb = new StringBuilder();

	         sb.append("UPDATE reservation  \n");
	         sb.append("SET   RES_STATE = ?      \n");
	         sb.append("WHERE RES_NUM = ?   \n");
	         sb.append("AND  RES_PASSPORT =?\n");
	         // ------------------------------------------

	         pstmt = connection.prepareStatement(sb.toString());
	         LOG.debug("2. PreparedStatement: " + pstmt);
	         LOG.debug("2.1. query:\n" + sb.toString());
	         // 3.param
	         LOG.debug("3. param: " + inVO);
	         // 3.1 param binding
	         pstmt.setInt(1, inVO.getResState());
	         pstmt.setString(2, inVO.getResNum());
	         pstmt.setString(3, inVO.getResPassport());
	         
	         flag = pstmt.executeUpdate();
	         connection.commit();
	         LOG.debug("4. flag: " + flag);
	      } catch (SQLException e) {
	         LOG.debug("===========================");
	         LOG.debug("=SQLException=" + e.getMessage());
	         LOG.debug("===========================");
	         e.printStackTrace();

	         // Rollback
	         JDBCResClose.rollBack(connection);

	      } finally { 
	         
	         JDBCResClose.close(pstmt);// prepareStatement
	         JDBCResClose.close(connection);// connection
	      }
	      return flag;
	   }

   
   
   
   public int doUpdate(DTO dto) {
      int flag = 0;
      ReservationVO inVO = (ReservationVO) dto;

      Connection connection = null;// DB Connection
      PreparedStatement pstmt = null;// VS Statement

      try {
         // 1.Connection 
         connection = connectionMaker.getConnection();
         connection.setAutoCommit(false);// transaction 

         LOG.debug("1. Connection: " + connection);
         // 2.PreparedStatement
         // 2.1 SQL
         StringBuilder sb = new StringBuilder();

         sb.append("UPDATE reservation  \n");
         sb.append("SET RES_ENAME = ?,  \n");
         sb.append("    RES_KNAME = ?,  \n");
         sb.append("   RES_PTYPE = ?,     \n");
         sb.append("   RES_WAY = ?,       \n");
         sb.append("   RES_PHONE = ?,     \n");
         sb.append("   RES_EMAIL = ?,     \n");
         sb.append("   RES_STATE = ?      \n");
         sb.append("WHERE RES_NUM = ?   \n");
         sb.append("AND  RES_PASSPORT =?\n");
         // ------------------------------------------

         pstmt = connection.prepareStatement(sb.toString());
         LOG.debug("2. PreparedStatement: " + pstmt);
         LOG.debug("2.1. query:\n" + sb.toString());
         // 3.param
         LOG.debug("3. param: " + inVO);
         // 3.1 param binding
//         pstmt.setString(1, inVO.getRESENAME());
//         pstmt.setString(2, inVO.getRESKNAME());
//         pstmt.setInt(3, inVO.getRESPTYPE());
//         pstmt.setInt(4, inVO.getRESWAY());
//         pstmt.setString(5, inVO.getRESPHONE());
//         pstmt.setString(6, inVO.getRESEMAIL());
//         pstmt.setString(7, inVO.getRESSTATE());
//         pstmt.setString(8, inVO.getRESNUM());
//         pstmt.setString(9, inVO.getRESPASSPORT());

         flag = pstmt.executeUpdate();
         connection.commit();
         LOG.debug("4. flag: " + flag);
      } catch (SQLException e) {
         LOG.debug("===========================");
         LOG.debug("=SQLException=" + e.getMessage());
         LOG.debug("===========================");
         e.printStackTrace();

         // Rollback
         JDBCResClose.rollBack(connection);

      } finally { 
         
         JDBCResClose.close(pstmt);// prepareStatement
         JDBCResClose.close(connection);// connection
      }
      return flag;
   }

   
   public int doDelete(DTO dto) {
      int flag = 0;

      Connection connection = connectionMaker.getConnection();
      PreparedStatement pstmt = null;
      ReservationVO inVO = (ReservationVO) dto;

   
      StringBuilder sb = new StringBuilder();
      sb.append(" DELETE from reservation \n");
      sb.append(" WHERE RES_NUM = ?    \n");

      try {

         LOG.debug("2.sql=\n" + sb.toString());
         LOG.debug("2.1 param=\n" + inVO);
         pstmt = connection.prepareStatement(sb.toString());
         LOG.debug("3.pstmt=" + pstmt);
      
         pstmt.setString(1, inVO.getResNum());

         
         flag = pstmt.executeUpdate();
         LOG.debug("4.flag=" + flag);

      } catch (SQLException e) {
         LOG.debug("===================");
         LOG.debug("SQLException=" + e.getMessage());
         LOG.debug("===================");
         e.printStackTrace();
         
      } finally {
         
         JDBCResClose.close(pstmt);// prepareStatement
         JDBCResClose.close(connection);// connection
      }
      return flag;
   }

   public int doInsert(DTO dto) {
      int flag = 0;


      Connection connection = connectionMaker.getConnection();
      PreparedStatement pstmt = null;
      ReservationVO inVO = (ReservationVO) dto;

      try {

         // 3. query PreparedStatement
         StringBuilder sb = new StringBuilder();

         // ----------------Query---------------------

         sb.append("    INSERT INTO Reservation (                                                              \n");
         sb.append("    res_num,                                                                     \n");
         sb.append("    res_passport,                                                             \n");
         sb.append("    res_ename,                                                                           \n");
         sb.append("    res_bir,                                                                           \n");
         sb.append("    res_phone,                                                                             \n");
         sb.append("    res_email,                                                                             \n");
         sb.append("    res_way,                                                                               \n");
         sb.append("    res_ptype,                                                                             \n");
         sb.append("    res_gender,                                                                            \n");
         sb.append("    res_state,                                                                             \n");
         sb.append("    res_pay,                                                                               \n");
         sb.append("    res_mileage,                                                                           \n");
         sb.append("    res_scode,                                                                             \n");
         sb.append("    u_num                      )                                                       \n");
         sb.append("     VALUES(     ?,                                                          \n");
         sb.append("              ?,                                                                        \n");
         sb.append("             ?,                                                                        \n");
         sb.append("             ?,                                                                       \n");
         sb.append("             ?,                                                                        \n");
         sb.append("             ?,                                                                       \n");
         sb.append("             ?,                                                                      \n");
         sb.append("             ?,                                                                       \n");
         sb.append("             ?,                                                                           \n");
         sb.append("             1,                                                                           \n");
         sb.append("             ?,                                                                           \n");
         sb.append("             ?,                                                                           \n");
         sb.append("             ?,                                                                           \n");
         sb.append("             ?                                                                           \n");
         sb.append(" )                                                                                          \n");
         

         LOG.debug("2.sql=\n" + sb.toString());
         LOG.debug("2.1 param=\n" + inVO);
         pstmt = connection.prepareStatement(sb.toString());
         LOG.debug("3.pstmt=" + pstmt);

         // -----------------Bind설정-----------------
         pstmt.setString(1, inVO.getResNum());
         pstmt.setString(2, inVO.getResPassport());
         pstmt.setString(3, inVO.getResEname());
          pstmt.setString(4, inVO.getResBir());
          pstmt.setString(5, inVO.getResphone());
          pstmt.setString(6, inVO.getResEmail());
          pstmt.setInt   (7, inVO.getResWay());
          pstmt.setDouble(8, inVO.getResPtype());      
          pstmt.setInt   (9, inVO.getResGender());      
          pstmt.setDouble(10,inVO.getResPay());
          pstmt.setDouble(11,inVO.getResMileage());      
          pstmt.setString(12,inVO.getResScode());
          pstmt.setInt(13,inVO.getuNum());
          
      
                
         flag = pstmt.executeUpdate();
         LOG.debug("4.flag=" + flag);

      } catch (SQLException e) {
         LOG.debug("===================");
         LOG.debug("SQLException=" + e.getMessage());
         LOG.debug("===================");
         e.printStackTrace();
      
         
      } finally {
      
         JDBCResClose.close(pstmt);// prepareStatement
         JDBCResClose.close(connection);// connection
      }
      return flag;

   }
   
 
   public List<?> doRetrieve(DTO dto) {
      SearchVO inVO = (SearchVO)dto;
      List<ReservationVO> outList = new ArrayList<ReservationVO>();
      
      Connection connection = null;
      PreparedStatement pstmt = null;
      ResultSet  rs = null;
      
      StringBuilder sbWhere = new StringBuilder();
      StringBuilder sb = new StringBuilder();
      
      
      if(null != inVO.getSearchDiv()) {
         if("10".equals(inVO.getSearchDiv())) {
            sbWhere.append("WHERE t1.RES_NUM like ?||'%' \n");      
         }else if("20".equals(inVO.getSearchDiv())) {
            sbWhere.append("WHERE t1.RES_PASSPORT like ?||'%' \n");
         }else if("30".equals(inVO.getSearchDiv())) {
            sbWhere.append("WHERE t1.RES_PHONE like ?||'%' \n");
          }else if("40".equals(inVO.getSearchDiv())) {
            sbWhere.append("WHERE t1.RES_EMAIL like ?||'%' \n");
          }
      }
      //---------------------------------------------------------------------
      sb.append("SELECT *                                                     \n");
      sb.append("FROM(                                                        \n");
      sb.append("    SELECT B.RES_NUM                                         \n");
      sb.append("          ,B.RES_PASSPORT                                    \n");
      sb.append("          ,b.RES_PHONE                                       \n");
      sb.append("          ,b.RES_EMAIL                                       \n");
      sb.append("    FROM (                                                   \n");
      sb.append("        SELECT ROWNUM as rnum,A.*                            \n");
      sb.append("        FROM(                                                \n");
      sb.append("            SELECT t1.*                                      \n");
      sb.append("            FROM RESERVATION t1                              \n");
   
      //--------------------------------------------------------------------------
      if(null != inVO.getSearchDiv() ) {
         if(null != inVO.getSearchWord() ) {
            sb.append(sbWhere.toString());
         }
      }
      //--------------------------------------------------------------------------
      sb.append("            ORDER BY t1.RES_NUM DESC                         \n");
      sb.append("        )A                                                   \n");
      sb.append("        WHERE rownum <=(?*(?-1)+?)                      \n");
      sb.append("    )B                                                       \n");
      sb.append("    WHERE rnum >=(?*(?-1)+1) )                             \n");
      sb.append("CROSS JOIN                                                   \n");
      sb.append("(                                                            \n");
      sb.append("    SELECT COUNT(*) TOTAL                                    \n");
      sb.append("    FROM RESERVATION t1                                      \n");
      sb.append("    --검색조건                                                                \n");
      //--------------------------------------------------------------------------
      if(null != inVO.getSearchDiv() ) {
         if(null != inVO.getSearchWord() ) {
            sb.append(sbWhere.toString());
         }
      }
      //--------------------------------------------------------------------------
      sb.append(")                                                            \n");
      //---------------------------------------------------------------------
      try {
         //1.connection
         connection = this.connectionMaker.getConnection();
         LOG.debug("1. connection="+connection);
         
         //2.pstmt
         //2.1.
         LOG.debug("2. qurey=\n"+sb.toString());
         pstmt = connection.prepareStatement(sb.toString());
         LOG.debug("2.1. pstmt="+pstmt);
         LOG.debug("3. param="+inVO);
      
         
         if(null != inVO.getSearchDiv() && null != inVO.getSearchWord() ) {
            
            pstmt.setString(1, inVO.getSearchWord());
            pstmt.setInt(2, inVO.getPageSize());
            pstmt.setInt(3, inVO.getPageNum());
            pstmt.setInt(4, inVO.getPageSize());
            pstmt.setInt(5, inVO.getPageSize());
            pstmt.setInt(6, inVO.getPageNum());
            pstmt.setString(7, inVO.getSearchWord());
         
      
         }else {
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
      
         //4.query
         rs = pstmt.executeQuery();
         while(rs.next()) {
               
            ReservationVO outVO = new ReservationVO();
              
               outVO.setTotal(rs.getInt("total"));
               outList.add(outVO);
               
            }//while
            LOG.debug("4. return :"+outList);
         
         
         
      } catch(SQLException e){
         LOG.debug("=================");
         LOG.debug("=SQLException="+e.getMessage());
         LOG.debug("=================");
         e.printStackTrace();
      }finally {
         JDBCResClose.close(rs);
         JDBCResClose.close(pstmt);
         JDBCResClose.close(connection);
         
      }
      
      
      return outList;
   }
   public List<?> doMemberRetrieve(DTO dto) {
	      ReservationVO inVO = (ReservationVO)dto;
	      List<ReservationVO> outList = new ArrayList<ReservationVO>();
	      
	      Connection connection = null;
	      PreparedStatement pstmt = null;
	      ResultSet  rs = null;
	      
	      StringBuilder sb = new StringBuilder();
	      
	      LOG.debug("resNum: "+inVO.getResNum());
	      sb.append("SELECT                                                \n");
	      sb.append("              res_num,                                \n");
	      sb.append("              res_passport,                           \n");
	      sb.append("              res_ename,                              \n");
	      sb.append("              TO_CHAR(res_bir,'YYYY-MM-DD') res_bir,    \n");
	      sb.append("              res_phone,                              \n");
	      sb.append("              res_email,                              \n");
	      sb.append("              res_way,                                \n");
	      sb.append("              res_ptype,                              \n");
	      sb.append("              res_gender,                             \n");
	      sb.append("              res_state,                              \n");
	      sb.append("              res_pay,                                \n");
	      sb.append("              res_mileage,                            \n");
	      sb.append("              res_scode,                              \n");
	      sb.append("              u_num			                          \n");
	      sb.append("FROM reservation                                     \n");
	      sb.append("WHERE res_num=TO_CHAR(?)                       				 \n");
	      
				
	      	 try {
	         //1.connection
	         connection = this.connectionMaker.getConnection();
	         LOG.debug("1. connection="+connection);
	         
	         //2.pstmt
	         //2.1.
	         LOG.debug("2. qurey=\n"+sb.toString());
	         pstmt = connection.prepareStatement(sb.toString());
	         LOG.debug("2.1. pstmt="+pstmt);
	         LOG.debug("3. param="+inVO);
	         //202003160000002
	         //202003160000002
	         //pstmt.setString(1, inVO.getResNum());
	         pstmt.setString(1, inVO.getResNum().trim());
		       
	         
	         //4.query
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	               
	             ReservationVO outVO = new ReservationVO();
	             
	             outVO.setResNum(rs.getString("res_num"));  
	             outVO.setResPassport(rs.getString("res_passport"));  
	             outVO.setResEname(rs.getString("res_ename"));  
	             outVO.setResBir(rs.getString("res_bir"));
	             outVO.setResphone(rs.getString("res_phone"));
	             outVO.setResEmail(rs.getString("res_email"));
	             outVO.setResWay(rs.getInt("res_way"));
	             outVO.setResPtype(rs.getInt("res_ptype"));
	             outVO.setResGender(rs.getInt("res_gender"));  
	             outVO.setResState(rs.getInt("res_state"));  
	             outVO.setResPay(rs.getInt("res_pay"));  
	             outVO.setResMileage(rs.getInt("res_mileage"));  
	             outVO.setResScode(rs.getString("res_scode"));  
	             outVO.setuNum(rs.getInt("u_num"));  
	             
	             LOG.debug("outVO:"+outVO);
	             outList.add(outVO);
	                
	             }//while
	          
	          
	            LOG.debug("4. return :"+outList);
	         
	         
	         
	      } catch(SQLException e){
	         LOG.debug("=================");
	         LOG.debug("=SQLException="+e.getMessage());
	         LOG.debug("=================");
	         e.printStackTrace();
	      }finally {
	         JDBCResClose.close(rs);
	         JDBCResClose.close(pstmt);
	         JDBCResClose.close(connection);
	         
	      }
	      
	      
	      return outList;
	   }
   public List<?> doResRetrieve(DTO dto) {
	      ReservationVO inVO = (ReservationVO)dto;
	      List<ReservationVO> outList = new ArrayList<ReservationVO>();
	      
	      Connection connection = null;
	      PreparedStatement pstmt = null;
	      ResultSet  rs = null;
	      
	      StringBuilder sbWhere = new StringBuilder();
	      StringBuilder sb = new StringBuilder();
	      

	      sb.append("SELECT                                                \n");
	      sb.append("              res_num,                                \n");
	      sb.append("              res_passport,                           \n");
	      sb.append("              res_ename,                              \n");
	      sb.append("              TO_CHAR(res_bir,'YYYY-MM-DD') res_bir,    \n");
	      sb.append("              res_phone,                              \n");
	      sb.append("              res_email,                              \n");
	      sb.append("              res_way,                                \n");
	      sb.append("              res_ptype,                              \n");
	      sb.append("              res_gender,                             \n");
	      sb.append("              res_state,                              \n");
	      sb.append("              res_pay,                                \n");
	      sb.append("              res_mileage,                            \n");
	      sb.append("              res_scode,                              \n");
	      sb.append("              u_num			                        \n");
	      sb.append("FROM reservation                                     	\n");
	      sb.append("WHERE res_email= (SELECT u_email                      	\n");
	      sb.append("					FROM users          					\n");
	      sb.append("					WHERE u_num=? )							\n");
	      
				
	      	 try {
	         //1.connection
	         connection = this.connectionMaker.getConnection();
	         LOG.debug("1. connection="+connection);
	         
	         //2.pstmt
	         //2.1.
	         LOG.debug("2. qurey=\n"+sb.toString());
	         pstmt = connection.prepareStatement(sb.toString());
	         LOG.debug("2.1. pstmt="+pstmt);
	         LOG.debug("3. param="+inVO);
	     
	         pstmt.setInt(1, inVO.getuNum());
	       
	         
	         //4.query
	         rs = pstmt.executeQuery();
	         
	         List<ReservationVO> list=new ArrayList<ReservationVO>();
	         
	         while(rs.next()) {
	               
	             ReservationVO outVO = new ReservationVO();
	             
	             outVO.setResNum(rs.getString("res_num"));  
	             outVO.setResPassport(rs.getString("res_passport"));  
	             outVO.setResEname(rs.getString("res_ename"));  
	             outVO.setResBir(rs.getString("res_bir"));
	             outVO.setResphone(rs.getString("res_phone"));
	             outVO.setResEmail(rs.getString("res_email"));
	             outVO.setResWay(rs.getInt("res_way"));
	             outVO.setResPtype(rs.getInt("res_ptype"));
	             outVO.setResGender(rs.getInt("res_gender"));  
	             outVO.setResState(rs.getInt("res_state"));  
	             outVO.setResPay(rs.getInt("res_pay"));  
	             outVO.setResMileage(rs.getInt("res_mileage"));  
	             outVO.setResScode(rs.getString("res_scode"));  
	             outVO.setuNum(rs.getInt("u_num"));  
	             
	             
	             outList.add(outVO);
	                
	             }//while
	          
	          
	            LOG.debug("4. return :"+outList);
	         
	         
	         
	      } catch(SQLException e){
	         LOG.debug("=================");
	         LOG.debug("=SQLException="+e.getMessage());
	         LOG.debug("=================");
	         e.printStackTrace();
	      }finally {
	         JDBCResClose.close(rs);
	         JDBCResClose.close(pstmt);
	         JDBCResClose.close(connection);
	         
	      }
	      
	      
	      return outList;
	   }

   
}