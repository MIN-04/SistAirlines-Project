package cityDTO;

import com.hr.cmn.DTO;

public class CityVO extends DTO {
	String continent;
	String continentKor;
	String cityKor;
	String cityEng;
	
	public CityVO() {}

	public CityVO(String continent, String continentKor, String cityKor, String cityEng) {
		super();
		this.continent = continent;
		this.continentKor = continentKor;
		this.cityKor = cityKor;
		this.cityEng = cityEng;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getContinentKor() {
		return continentKor;
	}

	public void setContinentKor(String continentKor) {
		this.continentKor = continentKor;
	}

	public String getCityKor() {
		return cityKor;
	}

	public void setCityKor(String cityKor) {
		this.cityKor = cityKor;
	}

	public String getCityEng() {
		return cityEng;
	}

	public void setCityEng(String cityEng) {
		this.cityEng = cityEng;
	}

	@Override
	public String toString() {
		return "CityVO [continent=" + continent + ", continentKor=" + continentKor + ", cityKor=" + cityKor
				+ ", cityEng=" + cityEng + ", toString()=" + super.toString() + "]";
	}

	
	
	
	
	
}
