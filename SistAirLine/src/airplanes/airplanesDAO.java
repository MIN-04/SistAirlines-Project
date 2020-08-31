package airplanes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.DTO;
import com.hr.cmn.JDBCResClose;
import com.hr.cmn.WorkDiv;

import scheduleDTO.ScheduleVO;

public class airplanesDAO extends WorkDiv{
	private ConnectionMaker connectionMaker;

	public airplanesDAO() {
		connectionMaker = new ConnectionMaker();
	}
	
		
	@Override
	public DTO doSelectOne(DTO dto) {
		airplanesVO inVO = (airplanesVO) dto;
		airplanesVO outVO = new airplanesVO();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();

		try {
			// 1. Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.Connection=" + connection);
			
			LOG.debug("2.Airplane=" + inVO);
			
			// 2. Query


			sb.append("SELECT				");
			sb.append("    DISTINCT(a_type) a_type");
			sb.append("FROM                 ");
			sb.append("    airplanes        ");
			sb.append("WHERE city=?  ");



			LOG.debug("2.Query=" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			// 3.param
			LOG.debug("3.param=\n" + inVO);

			pstmt.setString(1, inVO.getCity());

			// 4. Query 수행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				outVO.setaType(rs.getString("a_type"));
					
				
				LOG.debug("5.outVO=\n" + outVO);
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

	@Override
	public int doUpdate(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
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
	public List<?> doRetrive(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
}
