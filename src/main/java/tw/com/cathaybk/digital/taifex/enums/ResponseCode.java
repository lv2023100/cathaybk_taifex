package tw.com.cathaybk.digital.taifex.enums;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCode {
	
	SUCCESS("0000","成功"), DATE_RANGE_DOES_NOT_MATCH("E001","日期區間不符"), FIELD_MISSING("E099", "輸入欄位有誤");
	
	ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static Map<String, Object> getSuccess() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(mapper.writeValueAsString(ResponseCode.SUCCESS), new TypeReference<Map<String,Object>>() {});
	}
	public static Map<String, Object> getDateRangeDoesNotMathch() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(mapper.writeValueAsString(ResponseCode.DATE_RANGE_DOES_NOT_MATCH), new TypeReference<Map<String,Object>>() {});
	}
	
	public static Map<String, Object> getFieldMissing() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(mapper.writeValueAsString(ResponseCode.FIELD_MISSING), new TypeReference<Map<String,Object>>() {});
	}

}
