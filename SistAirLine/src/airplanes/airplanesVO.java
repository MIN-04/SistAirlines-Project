package airplanes;

import com.hr.cmn.DTO;

public class airplanesVO extends DTO{
	
	String aName;
	String aType;
	int capacity;
	String city;
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getaType() {
		return aType;
	}
	public void setaType(String aType) {
		this.aType = aType;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	@Override
	public String toString() {
		return "airplanesVO [aName=" + aName + ", aType=" + aType + ", capacity=" + capacity + ", city=" + city
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
}
