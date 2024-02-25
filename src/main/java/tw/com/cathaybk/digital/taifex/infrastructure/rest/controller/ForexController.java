package tw.com.cathaybk.digital.taifex.infrastructure.rest.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.com.cathaybk.digital.taifex.acl.taifex.adaper.TaifexAdaper;
import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.commandservice.ForexCommandService;
import tw.com.cathaybk.digital.taifex.domain.service.queryservice.ForexQueryService;
import tw.com.cathaybk.digital.taifex.enums.ForexType;
import tw.com.cathaybk.digital.taifex.enums.ResponseCode;
import tw.com.cathaybk.digital.taifex.infrastructure.rest.dto.ForexQueryRequest;
import tw.com.cathaybk.digital.taifex.infrastructure.scheduler.ForesTask;

@RestController
public class ForexController {

	@Autowired
	private ForexCommandService forexCommandService;

	@Autowired
	private ForexQueryService forexQueryService;

	@Autowired
	private TaifexAdaper taifexAdaper;
	
	Logger log = LoggerFactory.getLogger(ForexController.class);

	@GetMapping("/run")
	public String run() throws Exception {

		List<Forex> forexs = taifexAdaper.dailyForeginExchangeRates();
		forexCommandService.saveForexs(forexs);
		return "success";
	}

	LocalDate minLocalDate = LocalDate.now().minusYears(1);
	LocalDate maxLocalDate = LocalDate.now();

	private boolean isBetween(LocalDate ld) {
		return (ld.isEqual(minLocalDate) || ld.isAfter(minLocalDate)) && ld.isBefore(maxLocalDate);
	}

	@PostMapping("/find")
	public Map<String, Object> find(@RequestBody ForexQueryRequest forexQueryRequest) throws JsonProcessingException {
		Map<String, Object> result = new HashMap<String, Object>();
		ForexType forexType = ForexType.getForexType(forexQueryRequest.getCurrency());
		if (forexQueryRequest.getStartDate() == null || forexQueryRequest.getEndDate() == null || forexType == null) {
			result.put("error", ResponseCode.getFieldMissing());
			return result;
		}
		if (!isBetween(forexQueryRequest.getStartDate()) || !isBetween(forexQueryRequest.getEndDate())) {
			result.put("error", ResponseCode.getDateRangeDoesNotMathch());
			return result;
		}

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		List<Map<String, String>> forexs = forexQueryService
				.findByTypeAndDateRange(forexQueryRequest.getStartDate(), forexQueryRequest.getEndDate(), forexType)
				.stream().map(forex -> {
					Map<String, String> forexMap = new HashMap<String, String>();
					forexMap.put("date", forex.getDate().format(format));
					if (ForexType.USD_TO_NTD.equals(ForexType.USD_TO_NTD)) {
						forexMap.put(ForexType.USD_TO_NTD.getName(), forex.getConvertedAmount().toString());
					}
					return forexMap;
				}).collect(Collectors.toList());

		result.put("currency", forexs);
		result.put("error", ResponseCode.getSuccess());
		return result;
	}

}
