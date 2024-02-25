package tw.com.cathaybk.digital.taifex.enums;

import org.apache.commons.lang3.StringUtils;

public enum ForexType {
	
	USD_TO_NTD("usd");
	
	ForexType(String name){
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static ForexType getForexType(String name) {
		if(StringUtils.isBlank(name)) {
			return null;
		}
		switch (name) {
		case "usd":
			return ForexType.USD_TO_NTD;
		}
		return null;
	}

}
