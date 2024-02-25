package tw.com.cathaybk.digital.taifex.application.service.commandservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.commandservice.ForexCommandService;
import tw.com.cathaybk.digital.taifex.domain.service.repository.ForexRepository;
import tw.com.cathaybk.digital.taifex.enums.ForexType;

@ExtendWith(MockitoExtension.class)
public class ForexCommandServiceImplTest {
	
	@Mock
	private ForexRepository forexRepository;
	
	@InjectMocks
    private ForexCommandServiceImpl forexCommandService;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void saveForexs() {
		Forex forex1 = new Forex();
		forex1.setConvertedAmount(new BigDecimal(3.333));
		forex1.setDate(LocalDate.now().minusDays(1));
		forex1.setType(ForexType.USD_TO_NTD);
		
		Forex forex2 = new Forex();
		forex2.setConvertedAmount(new BigDecimal(3.333));
		forex2.setDate(LocalDate.now().minusDays(2));
		forex2.setType(ForexType.USD_TO_NTD);
		List<Forex> forexs = Arrays.asList(forex1, forex2);
		forexCommandService.saveForexs(forexs);
		verify(forexRepository, times(1)).save(forexs);
	}

}
