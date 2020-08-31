package cityDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.JDBCResClose;

public class CityDAO {
	private ConnectionMaker connectionMaker;

	public CityDAO() {
		connectionMaker = new ConnectionMaker();
	}
	
	public List<?> cityList() {
		List<CityVO> outList = new ArrayList<CityVO>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder(); // 寃��깋 query

		// 2. Query
		sb.append("SELECT C_CONTINENT 		 ");
		sb.append("		,C_CONTINENT_KOR     ");
		sb.append("		,C_KOR      		 ");
		sb.append("		,C_ENG 		  		 ");
		sb.append("FROM CITY          		 ");

		try {
			// 1. connection
			connection = connectionMaker.getConnection();
			System.out.println("1.connection=" + connection);

			// 2.query
			System.out.println("2.Query=" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			// 4.query�닔�뻾
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CityVO outVO = new CityVO();

				outVO.setContinent(rs.getString("C_CONTINENT"));
				outVO.setContinentKor(rs.getString("C_CONTINENT_KOR"));
				outVO.setCityKor(rs.getString("C_KOR"));
				outVO.setCityEng(rs.getString("C_ENG"));
				
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
}
