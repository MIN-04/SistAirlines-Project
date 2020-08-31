package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.DTO;
import com.hr.cmn.JDBCResClose;
import com.hr.cmn.SearchVO;
import com.hr.cmn.WorkDiv;

public class UserDao extends WorkDiv {

	private ConnectionMaker connectionmaker;

	public UserDao() {
		connectionmaker = new ConnectionMaker();

	}
	private static UserDao instance = new UserDao();
	public static UserDao getInstance() {
		return instance;
	}
	


	
	
	public int confirmId(UserVO inVO){
		int flag= 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		
		
		try {
			connection = connectionmaker.getConnection();
			LOG.debug("1.Connecetion" + connection);
			
			// 2.1. pstmt
			// QUERY
			sb.append(" SELECT              \n");
			sb.append("     u_id          \n");
			sb.append(" FROM                \n");
			sb.append("     USERS          \n");
			sb.append(" WHERE u_id = ?     \n");
			LOG.debug("2.query=\n" + sb.toString());
			LOG.debug("2.1.param=\n" + inVO);
			
			//pstmt.setString(1,inVO.getuId());
			
			pstmt = connection.prepareStatement(sb.toString());
			pstmt.setString(1,inVO.getuId());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				flag= 1; //해당 아이디 있음
			}else {
				flag=2; //해당아이디 없음
			}
		}catch(Exception e) {
			LOG.debug("===================");
			LOG.debug("=SQLException=" + e.getMessage());
			LOG.debug("===================");
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
			if(connection!=null)try {connection.close();} catch(SQLException e) {}
		}
		return flag;
	}

	@Override
	public UserVO doSelectOne(DTO dto) {
		UserVO inVO = (UserVO) dto;
		UserVO outVO = null;
		LOG.debug("inVO: "+inVO);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();

		try {
			// 1.Connection
			connection = connectionmaker.getConnection();
			LOG.debug("1.Connecetion" + connection);

			// QUERY
			sb.append(" SELECT              \n");
			sb.append("     u_num,          \n");
			sb.append("     u_id,           \n");
			sb.append("     u_pw,           \n");
			sb.append("     u_kname,        \n");
			sb.append("     u_ename,        \n");
			sb.append("     u_bir,          \n");
			sb.append("     u_phone,        \n");
			sb.append("     u_email,        \n");
			sb.append("     u_gender,       \n");
			sb.append("     u_address,      \n");
			sb.append("     u_country,      \n");
			sb.append("     u_mileage,      \n");
			sb.append("     u_maindepart,   \n");
			sb.append("     authority       \n");
			sb.append(" FROM                \n");
			sb.append("     USERS          \n");
			sb.append(" WHERE u_num = ?     \n");

			LOG.debug("2.query=\n" + sb.toString());
			// 2.1. pstmt
			pstmt = connection.prepareStatement(sb.toString());

			LOG.debug("3.param=\n" + inVO);

			// 3.param
			pstmt.setInt(1, inVO.getuNum());

			// 4. query 占쎈땾占쎈뻬

			rs = pstmt.executeQuery();

			if (rs.next()) {
				outVO = new UserVO();
				outVO.setuNum(rs.getInt("u_num"));
				outVO.setuId(rs.getString("u_id"));
				outVO.setuPw(rs.getString("u_pw"));
				outVO.setuKname(rs.getString("u_kname"));
				outVO.setuEname(rs.getString("u_ename"));
				outVO.setuBir(rs.getString("u_bir"));
				outVO.setuPhone(rs.getString("u_phone"));
				outVO.setuEmail(rs.getString("u_email"));
				outVO.setuGender(rs.getString("u_gender"));
				outVO.setuAddress(rs.getString("u_address"));
				outVO.setuCountry(rs.getString("u_country"));
				outVO.setuMileage(rs.getInt("u_mileage"));
				outVO.setuMaindepart(rs.getString("u_maindepart"));
				outVO.setAuthority(rs.getInt("authority"));

				LOG.debug("5.outVO=\n" + outVO);
			}

		} catch (SQLException e) {
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
	/**
	 * 
	 *@Method Name:passLookup
	 *@占쎌삂占쎄쉐占쎌뵬: 2020. 2. 13.
	 *@占쎌삂占쎄쉐占쎌쁽: sist128
	 *@占쎄퐬筌륅옙: �뜮袁⑨옙甕곕뜇�깈 筌≪뼐由�. (占쎌뵠�뵳占�,占쎌뵠筌롫뗄�뵬)
	 *@param dto
	 *@return
	 */
	public UserVO passLookup(DTO dto) {
		UserVO inVO = (UserVO) dto;
		UserVO outVO = null;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionMaker().getConnection();
			LOG.debug("conn:" + connection);
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT U_PW,					\n");
			sb.append(" U_EMAIL					\n");
			sb.append(" FROM USERS                 \n");
			sb.append(" WHERE U_KNAME = ?      			\n");
			sb.append(" AND U_EMAIL = ?      			\n");
			
			LOG.debug("1.conn:" + connection);
			LOG.debug("2.sql:" + sb.toString());

			pstmt = connection.prepareStatement(sb.toString());
			pstmt.setString(1, inVO.getuKname());
			pstmt.setString(2, inVO.getuEmail());
			LOG.debug("3.param:" + inVO);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				outVO = new UserVO();
				outVO.setuPw(rs.getString("U_PW"));
				outVO.setuEmail(rs.getString("U_EMAIL"));
				LOG.debug("5.outVO=\n" + outVO);
				
				return outVO;
				
			}else {
				outVO = new UserVO();
				return outVO;
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}

		return outVO;
	}


	
	/**
	 * 
	 *@Method Name:idLookup
	 *@占쎌삂占쎄쉐占쎌뵬: 2020. 2. 13.
	 *@占쎌삂占쎄쉐占쎌쁽: sist128
	 *@占쎄퐬筌륅옙: 占쎈툡占쎌뵠占쎈탵 筌≪뼐由�.
	 *@param dto
	 *@return
	 */
	public UserVO idLookup(DTO dto) {
		UserVO inVO = (UserVO) dto;
		UserVO outVO = null;

		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionMaker().getConnection();
			LOG.debug("conn:" + connection);
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT U_ID,					\n");
			sb.append(" U_EMAIL					\n");
			sb.append(" FROM USERS                 \n");
			sb.append(" WHERE U_KNAME = ?      			\n");
			sb.append(" AND U_PHONE = ?      			\n");
			
			LOG.debug("1.conn:" + connection);
			LOG.debug("2.sql:" + sb.toString());

			pstmt = connection.prepareStatement(sb.toString());
			pstmt.setString(1, inVO.getuKname());
			pstmt.setString(2, inVO.getuPhone());
			LOG.debug("3.param:" + inVO);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				outVO = new UserVO();
				outVO.setuId(rs.getString("U_ID"));
				outVO.setuEmail(rs.getString("U_EMAIL"));
				LOG.debug("5.outVO=\n" + outVO);
				
				return outVO;
				
			}else {
				outVO = new UserVO();
				return outVO;
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}

		return outVO;
	}
	
	
	
	
	
	/**
	 * 
	 * @Method Name:idCheck
	 * @占쎌삂占쎄쉐占쎌뵬: 2020. 2. 9.
	 * @占쎌삂占쎄쉐占쎌쁽: User
	 * @占쎄퐬筌륅옙: 占쎈툡占쎌뵠占쎈탵 鈺곕똻�삺 占쎌��눧占� 占쎌넇占쎌뵥.
	 * @param dto
	 * @return
	 */
	public DTO idCheck(DTO dto) {
		UserVO inVO = (UserVO) dto;
		UserVO outVO = new UserVO();

		int flag = 0;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionMaker().getConnection();
			LOG.debug("conn:" + connection);
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT U_ID					\n");
			sb.append(" FROM USERS                 \n");
			sb.append(" WHERE U_ID = ?      			\n");

			LOG.debug("2.sql:\n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			pstmt.setString(1, inVO.getuId());

			LOG.debug("3.param:" + inVO.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
					outVO.setuId("U_ID");
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}

		return outVO;
	}

	public UserVO loginMethod(DTO dto) {
		UserVO inVO = (UserVO) dto;
		UserVO outVO = new UserVO();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		
		try {
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
			sb.append(" WHERE U_ID = ?   \n");
			sb.append(" AND U_PW = ?      \n");

			connection = new ConnectionMaker().getConnection();
			LOG.debug("1.conn:" + connection);
			LOG.debug("2.sql:" + sb.toString());

			pstmt = connection.prepareStatement(sb.toString());
			pstmt.setString(1, inVO.getuId());
			pstmt.setString(2, inVO.getuPw());
			LOG.debug("3.param:" + inVO);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				outVO.setuNum(rs.getInt("U_NUM"));
				outVO.setuId(rs.getString("U_ID"));
				outVO.setuKname(rs.getString("U_KNAME"));
				outVO.setAuthority(rs.getInt("AUTHORITY"));
				
			}
		} catch (SQLException e) {
			LOG.debug("=========================");
			LOG.debug("SQLException:" + e.getMessage());
			LOG.debug("=========================");
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		LOG.debug(outVO);
		return outVO;
	}
	@Override
	public int doUpdate(DTO dto) {

		int flag = 0;
		UserVO inVO = (UserVO) dto; // param

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			// 1.Connection 占쎈염野껓옙
			connection = connectionmaker.getConnection();
			LOG.debug("1. Connection=" + connection);

			// query
			StringBuilder sb = new StringBuilder();

			sb.append(" UPDATE USERS      \n");
			sb.append(" SET                \n");
			sb.append("     u_pw = 	?      \n");
			sb.append("     ,u_kname =  ?   \n");
			sb.append("     ,u_ename =  ?   \n");
			sb.append("     ,u_bir =    ?   \n");
			sb.append("     ,u_phone =  ?   \n");
			sb.append("     ,u_email =  ?   \n");
			sb.append("     ,u_gender = ?   \n");
			sb.append("     ,u_address =?   \n");
			sb.append("     ,u_country =?   \n");
			sb.append("     ,u_mileage =?   \n");
			sb.append("     ,u_maindepart =?\n");
			sb.append("     ,authority =   ?\n");
			sb.append(" WHERE u_id = ?      \n");
			LOG.debug("2. query=\n" + sb.toString());

			// 2.1. pstmt
			pstmt = connection.prepareStatement(sb.toString());
			// 3.param
			LOG.debug("3. param=" + inVO);

			pstmt.setString(1, inVO.getuPw());
			pstmt.setString(2, inVO.getuKname());
			pstmt.setString(3, inVO.getuEname());
			pstmt.setString(4, inVO.getuBir());
			pstmt.setString(5, inVO.getuPhone());
			pstmt.setString(6, inVO.getuEmail());
			pstmt.setString(7, inVO.getuGender());
			pstmt.setString(8, inVO.getuAddress());
			pstmt.setString(9, inVO.getuCountry());
			pstmt.setInt(10, inVO.getuMileage());
			pstmt.setString(11, inVO.getuMaindepart());
			pstmt.setInt(12, inVO.getAuthority());
			pstmt.setString(13, inVO.getuId());

			// 4.query占쎈땾占쎈뻬
			flag = pstmt.executeUpdate();
			LOG.debug("4. flag=" + flag);

		} catch (SQLException e) {
			LOG.debug("===========================");
			LOG.debug("=SQLException=" + e.getMessage());
			LOG.debug("===========================");
			e.printStackTrace();
		} finally { // 占쎌쁽占쎌뜚獄쏆꼶沅�
			// 占쎄텢占쎌뒠 占쎈열占쎈떄占쎌몵嚥∽옙 close
			JDBCResClose.close(pstmt);// prepareStatement
			JDBCResClose.close(connection);// connection
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
		int flag = 0;
		BasicConfigurator.configure();
	
		Connection connection = connectionmaker.getConnection();

		// 3. query�닔�뻾�쓣 �쐞�븳 PreparedStatement
		StringBuilder sb = new StringBuilder();
		PreparedStatement pstmt = null;

		UserVO inVO = (UserVO) dto;

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

		try {

			LOG.debug("2.sql\n=" + sb.toString());
			LOG.debug("2.1.param=\n" + inVO);
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3.pstmt=" + pstmt);

			// 4.query �떎�뻾
			// 4.1 Bind 蹂��닔�뿉 媛� �꽕�젙
			pstmt.setString(1, inVO.getuId());// �젣紐�
			pstmt.setString(2, inVO.getuPw());// �궡�슜
			pstmt.setString(3, inVO.getuKname());
			pstmt.setString(4, inVO.getuEname());
			pstmt.setString(5, inVO.getuBir());
			pstmt.setString(6, inVO.getuPhone());
			pstmt.setString(7, inVO.getuEmail());
			pstmt.setString(8, inVO.getuGender());
			pstmt.setString(9, inVO.getuAddress());
			pstmt.setString(10, inVO.getuCountry());
			pstmt.setInt(11, inVO.getuMileage());
			pstmt.setString(12, inVO.getuMaindepart());
			pstmt.setInt(13, inVO.getAuthority());

			// 4.2. query �닔�뻾:
			flag = pstmt.executeUpdate();
			LOG.debug("4.flag=" + flag);

		} catch (SQLException e) {
			LOG.debug("===================");
			LOG.debug("SQLException=" + e.getMessage());
			LOG.debug("===================");
			e.printStackTrace();
			// * 6. PreparedStatement,ResultSet
			// * 7. Connection醫낅즺
		} finally {
			// �궗�슜 �뿭�닚�쑝濡� �옄�썝諛섎궔(close)
			JDBCResClose.close(pstmt);// preparedStatement
			JDBCResClose.close(connection);// connection

		}

		return flag;
	}


	@Override
	public List<?> doRetrieve(DTO dto) {
		BasicConfigurator.configure();

		
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
			connection = this.connectionmaker.getConnection();
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
