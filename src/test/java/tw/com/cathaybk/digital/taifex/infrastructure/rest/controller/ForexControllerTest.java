package tw.com.cathaybk.digital.taifex.infrastructure.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import tw.com.cathaybk.digital.taifex.infrastructure.rest.dto.ForexQueryRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ForexControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void run() throws Exception {
		ResponseEntity<String> response= this.restTemplate.getForEntity("http://localhost:"+port+"/run", String.class);
		assertThat(response.getBody()).isEqualTo("success");
	}
	
	@Test
	public void success() throws Exception {
		ForexQueryRequest forexQueryRequest = new ForexQueryRequest();
		forexQueryRequest.setCurrency("usd");
		forexQueryRequest.setStartDate(LocalDate.of(2023, 3, 1));
		forexQueryRequest.setEndDate(LocalDate.of(2024, 2, 1));
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<ForexQueryRequest> requestEntity = new HttpEntity<>(forexQueryRequest, headers);
		
		Map<String, Map<String, Object>> result= this.restTemplate.postForObject("http://localhost:"+port+"/find",
				requestEntity,Map.class);
		assertThat(result).isNotNull();
		assertThat(result.get("error")).isNotNull();
		assertThat(result.get("error").get("code")).isEqualTo("0000");
		assertThat(result.get("error").get("message")).isEqualTo("成功");
		assertTrue(!CollectionUtils.isEmpty(((List)result.get("currency"))));

	}
	
	@Test
	public void dateRangeDoesNotMathch() throws Exception {
		ForexQueryRequest forexQueryRequest = new ForexQueryRequest();
		forexQueryRequest.setCurrency("usd");
		forexQueryRequest.setStartDate(LocalDate.of(2022, 2, 1));
		forexQueryRequest.setEndDate(LocalDate.of(2024, 2, 1));
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<ForexQueryRequest> requestEntity = new HttpEntity<>(forexQueryRequest, headers);
		
		Map<String, Map<String, Object>> result= this.restTemplate.postForObject("http://localhost:"+port+"/find",
				requestEntity,Map.class);
		assertThat(result).isNotNull();
		assertThat(result.get("error")).isNotNull();
		assertThat(result.get("error").get("code")).isEqualTo("E001");
		assertThat(result.get("error").get("message")).isEqualTo("日期區間不符");
		assertTrue(CollectionUtils.isEmpty(result.get("currency")));

	}

}
