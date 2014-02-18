package com.app.yakstore.functional.rest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StockControllerTest 
{

	public void testGetStock()
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	    RestTemplate template = new RestTemplate();
	    
	    ResponseEntity<Map> entity = template.getForEntity("http://localhost:8080/stock/13",Map.class);

        String path = entity.getHeaders().getLocation().getPath();

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertTrue(path.startsWith("/aggregators/orders/"));

	}
}
