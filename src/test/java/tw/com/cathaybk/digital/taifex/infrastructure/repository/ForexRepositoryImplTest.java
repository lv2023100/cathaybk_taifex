package tw.com.cathaybk.digital.taifex.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.domain.service.repository.ForexRepository;
import tw.com.cathaybk.digital.taifex.enums.ForexType;
import tw.com.cathaybk.digital.taifex.infrastructure.repository.ForexRepositoryImpl;

@DataMongoTest
@Import(ForexRepositoryImpl.class)
public class ForexRepositoryImplTest {
	
	@Autowired
	private ForexRepository forexRepository;
	
	List<Forex> forexs = new ArrayList<Forex>();
	
	@BeforeEach
	public void setUp() throws Exception{
		Forex forex1 = new Forex();
		forex1.setConvertedAmount(new BigDecimal(3.333));
		forex1.setDate(LocalDate.now().minusDays(1));
		forex1.setType(ForexType.USD_TO_NTD);
		
		Forex forex2 = new Forex();
		forex2.setConvertedAmount(new BigDecimal(3.333));
		forex2.setDate(LocalDate.now().minusDays(2));
		forex2.setType(ForexType.USD_TO_NTD);
		forexs.add(forex1);
		forexs.add(forex2);
		forexRepository.save(forexs);
	}
	
	@Test
	public void dateRangeDoesNotMathch(@Autowired MongoTemplate mongoTemplate) {
		List<Forex> forexs = forexRepository.findByTypeAndDateRange(LocalDate.of(2024,01,1), LocalDate.now().minusDays(1), ForexType.USD_TO_NTD);
		assertTrue(!CollectionUtils.isEmpty(forexs));
		assertThat(forexs.size()).isEqualTo(this.forexs.size());
		assertThat(forexs.get(0).getConvertedAmount()).isEqualTo(this.forexs.get(0).getConvertedAmount());
		assertThat(forexs.get(0).getDate()).isEqualTo(this.forexs.get(0).getDate());
		assertThat(( forexs.get(0)).getType()).isEqualTo((this.forexs.get(0)).getType());
	}

}
