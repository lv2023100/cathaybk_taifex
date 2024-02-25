package tw.com.cathaybk.digital.taifex.acl.taifex.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyStore;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.com.cathaybk.digital.taifex.acl.taifex.adaper.TaifexAdaper;
import tw.com.cathaybk.digital.taifex.acl.taifex.dto.UsdToNtdForexDto;
import tw.com.cathaybk.digital.taifex.domain.model.entity.Forex;

@Component
public class TaifexService implements TaifexAdaper {

	Logger log = LoggerFactory.getLogger(TaifexService.class);

	@Autowired
	private ObjectMapper mapper;
	
	@Value("classpath:taifex.jks")
    private Resource resourceFile;

	@Override
	public List<Forex> dailyForeginExchangeRates() throws Exception {
		
		
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(resourceFile.getInputStream(), "cathaybk888".toCharArray());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(keyStore);
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, tmf.getTrustManagers(), null);
		
		
		
		HttpClient client = HttpClient.newBuilder()
				.sslContext(sslContext)
		        .build();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates"))
				.header("Accept", "application/json")
				.GET().build();
		HttpResponse<String> responseBody = client.send(request, BodyHandlers.ofString());
		List<UsdToNtdForexDto> usdToNtdForexDtos = mapper.readValue(responseBody.body(), new TypeReference<List<UsdToNtdForexDto>>() {});
		DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyyMMdd");
		List<Forex> forexs= usdToNtdForexDtos.stream().map(usdToNtdForexDto->{
			Forex forex = new Forex();
			BeanUtils.copyProperties(usdToNtdForexDto, forex);
			forex.setId(usdToNtdForexDto.getType().getName()+forex.getDate().format(format));
			return forex;
		}).collect(Collectors.toList());
		
//		RestTemplate restTemplate = new RestTemplate();
//		String fooResourceUrl
//		  = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
//		ResponseEntity<String> response
//		  = restTemplate.getForEntity(fooResourceUrl, String.class);
//		log.info("===getBody========"+response.getBody());
//		List<Forex> forexs = new ArrayList<Forex>();
		return forexs;
	}

}
