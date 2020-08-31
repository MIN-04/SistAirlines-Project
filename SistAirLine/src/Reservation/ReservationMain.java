package Reservation;

public class ReservationMain {

	public static void main(String[] args) {
		ReservationDao dao=new ReservationDao();
		ReservationVO inVO=new ReservationVO();
		inVO.setuNum(123);
		
		dao.doMemberRetrieve(inVO);

	}

}
