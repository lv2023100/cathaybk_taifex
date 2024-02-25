package tw.com.cathaybk.digital.taifex.infrastructure.repository.mapper;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;
import tw.com.cathaybk.digital.taifex.enums.ForexType;
@Repository
public interface ForexMapper extends MongoRepository<Forex, String> {
	
	 @Query("{ 'date' : { $gte: ?0, $lte: ?1 } }")
	 List<Forex> findByTypeAndDateRange(LocalDate start, LocalDate end, ForexType type);

}
