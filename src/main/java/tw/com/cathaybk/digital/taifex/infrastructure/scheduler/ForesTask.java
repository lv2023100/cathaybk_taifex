package tw.com.cathaybk.digital.taifex.infrastructure.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tw.com.cathaybk.digital.taifex.acl.taifex.adaper.TaifexAdaper;
import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.commandservice.ForexCommandService;

@Component
public class ForesTask {
	
	@Autowired
	private ForexCommandService forexCommandService;
	@Autowired
	private TaifexAdaper taifexAdaper;
	
	Logger log = LoggerFactory.getLogger(ForesTask.class);
	
	@Scheduled(cron = "0 0 18 * * ?", zone = "${timezone}")
	public void run() throws Exception {
		log.info("Start fetching foreign exchange rates ...");
		List<Forex> forexs = taifexAdaper.dailyForeginExchangeRates();
		forexCommandService.saveForexs(forexs);
		log.info("Finished fetching foreign exchange rates ...");
	}

}
