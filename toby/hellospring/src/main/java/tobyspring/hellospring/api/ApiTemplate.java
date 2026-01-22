package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
	private final ApiExecutor apiExecutor;
	private final ExRateExtractor exRateExTractor;

	public ApiTemplate() {
		this.apiExecutor = new HttpClientApiExecutor();
		this.exRateExTractor = new ErApiExtractor();
	}

	public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExTractor) {
		this.apiExecutor = apiExecutor;
		this.exRateExTractor = exRateExTractor;
	}

	public BigDecimal getForExRate(String url){
		return this.getForExRate(url, this.apiExecutor, this.exRateExTractor);
	}
	public BigDecimal getForExRate(String url, ApiExecutor apiExecutor){
		return this.getForExRate(url, apiExecutor, this.exRateExTractor);
	}

	public BigDecimal getForExRate(String url, ExRateExtractor exRateExtractor){
		return this.getForExRate(url, this.apiExecutor, exRateExtractor);
	}

	public BigDecimal getForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = apiExecutor.execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return exRateExtractor.extract(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
