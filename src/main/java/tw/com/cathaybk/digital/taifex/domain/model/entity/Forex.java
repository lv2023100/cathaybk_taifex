package tw.com.cathaybk.digital.taifex.domain.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import tw.com.cathaybk.digital.taifex.enums.ForexType;
@Document(collection = "forex")
public class Forex {
	@Id
	private String  id;
	
	private LocalDate date;
	
	private BigDecimal convertedAmount;
	
	private ForexType type = ForexType.USD_TO_NTD;

	public String  getId() {
		return id;
	}

	public void setId(String  id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(BigDecimal convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	public ForexType getType() {
		return type;
	}

	public void setType(ForexType type) {
		this.type = type;
	}


}
