package Reservation;

import java.util.List;

import org.apache.log4j.Logger;

import com.hr.cmn.DTO;

import Reservation.ReservationVO;




public class ReservationService {
   private final Logger LOG = Logger.getLogger(ReservationService.class);
   private ReservationDao resDao;

   public ReservationService() {
      resDao = new ReservationDao();
   }
   
   public String doSelectResNo() {
      // 단건 조회
      return resDao.doSelectResNo();

   }
   public int doChangeReservation(DTO dto) {
	      // 예약상태 변경
	      return resDao.doChangeReservation(dto);

	   }
   
   public DTO doSelectOne(DTO dto) {
      // 단건 조회
      return resDao.doSelectOne(dto);

   }

   public int doInsert(DTO dto) {

      return resDao.doInsert(dto);
   }
   
   public int doInsertList(List<ReservationVO> voList) {
         int iSuccessCnt = 0;
         int iSuccessYn = 0;
         for(ReservationVO vo : voList) {
            iSuccessYn = resDao.doInsert(vo);
//            if(gg == 1) {
//               return gg;
//            }else {
//               gg = 0;
//               break;
//            }
            if(iSuccessYn == 1) {
               iSuccessCnt++;
            }
         }
         return iSuccessCnt;
      }
   
   public int doUpdate(DTO dto) {

      return resDao.doUpdate(dto);
   }
   public int doChangeState(DTO dto) {

	      return resDao.doChangeState(dto);
	   }

   public int doDelete(DTO dto) {

      return resDao.doDelete(dto);
   }

   public List<?> doRetrieve(DTO dto) {

      return resDao.doRetrieve(dto);
   }
   public List<?> doMemberRetrieve(DTO dto) {

	      return resDao.doMemberRetrieve(dto);
  }
   public List<?> doResRetrieve(DTO dto) {

	      return resDao.doResRetrieve(dto);
}

}