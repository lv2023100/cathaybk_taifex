package tw.com.cathaybk.digital.taifex.acl.taifex.adaper;

import java.util.List;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;

public interface TaifexAdaper {
	List<Forex> dailyForeginExchangeRates() throws Exception;
}
