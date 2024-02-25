package tw.com.cathaybk.digital.taifex.application.service.queryservice;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.queryservice.ForexQueryService;
import tw.com.cathaybk.digital.taifex.domain.service.repository.ForexRepository;
import tw.com.cathaybk.digital.taifex.enums.ForexType;

@Service
public class ForexQueryServiceImpl implements ForexQueryService{
	
	@Autowired
	private ForexRepository forexRepository;
	
	@Override
	public List<Forex> findByTypeAndDateRange(LocalDate start, LocalDate end, ForexType type){
		return forexRepository.findByTypeAndDateRange(start, end, type);
	}

}
