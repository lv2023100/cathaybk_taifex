package tw.com.cathaybk.digital.taifex.infrastructure.rest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public class ForexQueryRequest {

	@Schema( type="string" , example = "2024/01/01")
	@JsonFormat(pattern="yyyy/MM/dd")
	private LocalDate startDate;
	@Schema( type="string" , example = "2024/02/01")
	@JsonFormat(pattern="yyyy/MM/dd")
	private LocalDate endDate;
	@Schema( example = "usd")
	private String currency;


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


}
