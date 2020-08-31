package User;

import java.util.List;

import com.hr.cmn.DTO;

public class UserService {

	private UserDao userDao;
	public UserService() {
		userDao =new UserDao();
		
	}
	public DTO loginMethod(DTO dto) {
		return userDao.loginMethod(dto);
	}
	
	public DTO doSelecteOne(DTO dto) {
		return userDao.doSelectOne(dto);
	}
	public int doInsert(DTO dto) {
		return userDao.doInsert(dto);
	}
	public int doUpdate(DTO dto) {
		return userDao.doUpdate(dto);
	}
	public int doDelete(DTO dto) {
		return userDao.doDelete(dto);
	}
	public List<?> doRetrieve(DTO dto) {
		return userDao.doRetrieve(dto);
	}
	
	
}
