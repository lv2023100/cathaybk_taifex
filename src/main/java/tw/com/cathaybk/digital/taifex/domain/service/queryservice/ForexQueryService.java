package tw.com.cathaybk.digital.taifex.domain.service.queryservice;

import java.time.LocalDate;
import java.util.List;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.enums.ForexType;

public interface ForexQueryService {
	
	List<Forex> findByTypeAndDateRange(LocalDate start, LocalDate end, ForexType type);

}
