package org.nvijaykarthik.fccclient.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpService {

	private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

	public HttpService() {
	}
	
	/**
	 * Encodes Parameters, Path variables. URL encoder follows HTML 
	 * encoding so need to replace + with %20 to ensure we follow URI spec rfc3986 
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeUrlParameters(String value) throws UnsupportedEncodingException {
	    return URLEncoder.encode(value, StandardCharsets.US_ASCII.toString()).replace("+", "%20");
	}

	public <T> T sendGetRequest(String url, Class<T> responseType) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).header("Accept", "application/json")
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		System.out.println(response.body());
		return response.body()!=null && !("").equals(response.body()) ? objectMapper.readValue(response.body(), responseType) : null;
	}

	public <T> List<T> sendGetListRequest(String url, Class<T> responseType) throws IOException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).header("Accept", "application/json")
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return response.body()!=null && !("").equals(response.body()) ? objectMapper.readValue(response.body(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, responseType)) : null;
	}
}
