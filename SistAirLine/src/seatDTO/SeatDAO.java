package seatDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.hr.cmn.ConnectionMaker;
import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;
import cityDTO.CityDAO;


public class SeatDAO extends WorkDiv{
	private final Logger LOG=Logger.getLogger(SeatDAO.class);
	
	private ConnectionMaker connectionMaker;
	public SeatDAO() {
		connectionMaker = new ConnectionMaker();
	}
	

	
	public List<SeatDTO> selectSchedule(DTO dto){
//		ScheduleDTO invo=(ScheduleDTO)dto;
//		
//		Connection conn = null;
//		PreparedStatement ppst = null;
//		ResultSet rs = null; // ResultSet import!
//		List<ScheduleDTO> list = null;
//		StringBuilder sb= new StringBuilder();
//		list = new ArrayList<ScheduleDTO>(); // List ��ü ����
//		LOG.debug("param: "+dto);
//		
//		try {
//			conn=connectionMaker.getConnection();
//			// JDBC Driver �ε�
//			// ������ ���� ���� / Ư�� �����͸� �˻��ϰ��� �Ѵٸ� where �������� �Ű����� �߰� �ʿ�
//			sb.append("SELECT sc_date,");
//			sb.append(" 	sc_dtime,");
//			sb.append(" 	sc_stime,");
//			sb.append(" 	sc_dcity,");
//			sb.append(" 	sc_acity,");
//			sb.append(" 	sc_price,");
//			sb.append(" 	sc_mileage,");
//			sb.append(" 	sc_aname,");
//			sb.append(" 	sc_unoccupied,");
//			sb.append(" 	sc_code");
//			sb.append("FROM f_schedule");
//			sb.append("WHERE sc_dcity='INCHEON'");
//			sb.append("AND sc_acity='OSAKA'");
//			sb.append("AND sc_date=20200211");
//			LOG.debug("sb: "+sb);
//			
//			ppst = conn.prepareStatement(sb.toString());
//			LOG.debug("ppst: "+ppst);
//			
////			ppst.setString(1, invo.getScDcity());
////			ppst.setString(2, invo.getScAcity());
////			ppst.setString(3, invo.getScDate());
//			// ���� ����
//			rs = ppst.executeQuery();
//			LOG.debug("rs: "+rs);
//			
//			// ����� ����� List<MemberInfoDTO)�� ����
//			if(rs.next()){
//				do {
//					// �ݺ����� ����� �� ���� MemberInfoDTO ����(�������� �ٸ�)
//					ScheduleDTO outVO = new ScheduleDTO();
//					// ������ ��ü(data)�� ����� ������ ��(�ش� �÷�)�� ������� ����
//					outVO.setScDate(rs.getString("sc_date"));
//					outVO.setScDtime(rs.getString("sc_dtime"));
//					outVO.setScStime(rs.getString("sc_stime"));
//					outVO.setScDcity(rs.getString("sc_dcity"));
//					outVO.setScAcity(rs.getString("sc_acity"));
//					outVO.setScPrice(rs.getString("sc_price"));
//					outVO.setScMileage(rs.getString("sc_mileage"));
//					outVO.setScAname(rs.getString("sc_aname"));
//					outVO.setScCapacity(rs.getString("sc_capacity"));
//					outVO.setScCode(rs.getString("sc_code"));
//					// list�� 0�� index���� ������� data ��ü�� �������� ����
//					LOG.debug(outVO);
//					list.add(outVO);
//				} while (rs.next());
//			}//if��--> ������ ������ ������� ������ ���� while���� ����
//		} catch (Exception e) {
//			LOG.debug("SQL Exception: "+e.getMessage());
//		} finally {
//			try {
//				if(rs != null) rs.close();
//				if(ppst != null) ppst.close();
//				if(conn != null) conn.close();
//			} catch (Exception e2) {
//				e2.getStackTrace();
//			}
//		}
		return null; // list ����
	}
	@Override
	public DTO doSelectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public int doOccupied(DTO dto) {
		
		int flag=0;
		SeatDTO inVO=(SeatDTO)dto;
		LOG.debug("inVO:"+inVO);

		Connection conn=null;
		PreparedStatement ppst=null;
		StringBuilder sb=new StringBuilder();
		
		
		try {
			conn=connectionMaker.getConnection();

			sb.append("UPDATE sc_seats		");
			sb.append("SET                  ");
			sb.append("    se_occupied = 0  ");
			sb.append("WHERE                ");
			sb.append("    se_code = ?     ");

			
			ppst=conn.prepareStatement(sb.toString());
			ppst.setString(1, inVO.getSeCode());
			
			flag=ppst.executeUpdate();
			LOG.debug("flag: "+flag);

		}catch(SQLException e) {
			LOG.debug("========");
			LOG.debug("SQLException"+e.getMessage());
			LOG.debug("========");
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
	
	public int doCancleSeat(DTO dto) {
		
		int flag=0;
		
		SeatDTO inVO=(SeatDTO)dto;
		Connection conn=null;
		PreparedStatement ppst=null;
		StringBuilder sb=new StringBuilder();
		
		
		try {
			conn=connectionMaker.getConnection();

			sb.append("UPDATE sc_seats		");
			sb.append("SET                  ");
			sb.append("    se_occupied = 1  ");
			sb.append("WHERE                ");
			sb.append("    se_code = ?     ");

			
			ppst=conn.prepareStatement(sb.toString());
			ppst.setString(1, inVO.getSeCode());
			
			flag=ppst.executeUpdate();

		}catch(SQLException e) {
			LOG.debug("========");
			LOG.debug("SQLException"+e.getMessage());
			LOG.debug("========");
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
	public List<SeatDTO> doRetrieve(DTO dto) {
		Connection conn=null;
		PreparedStatement ppst=null;
		ResultSet rs=null;
		List<SeatDTO> list = new ArrayList<SeatDTO>();
		SeatDTO inVO=(SeatDTO)dto;
		StringBuilder sb=new StringBuilder();
		try {
			conn=this.connectionMaker.getConnection();
			
			sb.append("SELECT			");
			sb.append("    sc_code,     ");
			sb.append("    se_name,     ");
			sb.append("    se_occupied, ");
			sb.append("    se_passport, ");
			sb.append("    se_code      ");
			sb.append("FROM             ");
			sb.append("    sc_seats     ");
			sb.append("WHERE            ");
			sb.append("	sc_code=?	    ");
			sb.append("ORDER BY se_name	");
			
			
			ppst=conn.prepareStatement(sb.toString());
			
			ppst.setString(1, inVO.getScCode());
			
			rs=ppst.executeQuery();
			
			if(rs.next()) {
				do {
				SeatDTO outVO=new SeatDTO();
				
				outVO.setScCode(rs.getString("sc_code"));      
				outVO.setSeName(rs.getString("se_name"));    
				outVO.setSeOccupied(rs.getString("se_occupied"));     
				outVO.setSePassport(rs.getString("se_passport"));     
				outVO.setSeCode(rs.getString("se_code"));     
				LOG.debug("outVOTest:"+outVO);
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



	@Override
	public int doUpdate(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
