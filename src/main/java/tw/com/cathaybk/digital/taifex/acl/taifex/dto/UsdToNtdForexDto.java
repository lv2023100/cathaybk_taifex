package tw.com.cathaybk.digital.taifex.acl.taifex.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import tw.com.cathaybk.digital.taifex.enums.ForexType;

public class UsdToNtdForexDto {
	@JsonProperty("Date")
	@JsonFormat(pattern="yyyyMMdd")
	private LocalDate date;
	
	@JsonProperty("USD/NTD")
	private BigDecimal convertedAmount;

	private ForexType type = ForexType.USD_TO_NTD;

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
