package tw.com.cathaybk.digital.taifex.domain.service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.enums.ForexType;

@Repository
public interface ForexRepository {
	
	void save(List<Forex> forexs);
	List<Forex> findByTypeAndDateRange(LocalDate start, LocalDate end, ForexType type);

}
