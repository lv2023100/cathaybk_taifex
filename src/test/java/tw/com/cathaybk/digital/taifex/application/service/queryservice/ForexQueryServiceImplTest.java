package tw.com.cathaybk.digital.taifex.application.service.queryservice;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.repository.ForexRepository;
import tw.com.cathaybk.digital.taifex.enums.ForexType;

public class ForexQueryServiceImplTest {
	
	@InjectMocks
	private ForexQueryServiceImpl forexQueryService;
	
	@Mock
	private ForexRepository forexRepository;
	
	@BeforeEach
    public void setUp() {
		MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void findByTypeAndDateRange(){
		LocalDate startDate = LocalDate.of(2023, 5, 1);
		LocalDate endDate = LocalDate.of(2023, 6, 1);
		ForexType type = ForexType.USD_TO_NTD;
		forexQueryService.findByTypeAndDateRange(startDate, endDate, type);
		
		List<Forex> forexs = new ArrayList<Forex>();
		when(forexRepository.findByTypeAndDateRange(startDate, endDate, type)).thenReturn(forexs);
	}

}
