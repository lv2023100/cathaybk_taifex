package tw.com.cathaybk.digital.taifex.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.repository.ForexRepository;
import tw.com.cathaybk.digital.taifex.enums.ForexType;
import tw.com.cathaybk.digital.taifex.infrastructure.repository.mapper.ForexMapper;

@Repository
public class ForexRepositoryImpl implements ForexRepository{
	
	@Autowired
	private ForexMapper forexMapper;

	@Override
	public void save(List<Forex> forexs) {
		forexMapper.saveAll(forexs);
	}
	
	@Override
	public List<Forex> findByTypeAndDateRange(LocalDate start, LocalDate end, ForexType type){
		return forexMapper.findByTypeAndDateRange(start, end, type);
	}


}
