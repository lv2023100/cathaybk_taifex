package tw.com.cathaybk.digital.taifex.domain.service.commandservice;

import java.util.List;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;

public interface ForexCommandService {
	
	void saveForexs(List<Forex> forexs);


}
