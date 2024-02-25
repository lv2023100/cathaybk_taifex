package tw.com.cathaybk.digital.taifex.application.service.commandservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.commandservice.ForexCommandService;
import tw.com.cathaybk.digital.taifex.domain.service.repository.ForexRepository;

@Service
public class ForexCommandServiceImpl implements ForexCommandService{
	
	@Autowired
	private ForexRepository forexRepository;

	@Override
	public void saveForexs(List<Forex> forexs) {
		forexRepository.save(forexs);
		
	}

}
