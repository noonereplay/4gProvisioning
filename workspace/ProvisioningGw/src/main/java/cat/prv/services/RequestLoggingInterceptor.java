package cat.prv.services;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;


public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        log.debug("request method: {}, request URI: {}, request headers: {}, request body: {}, response status code: {}, response headers: {}, response body: {}",
            request.getMethod(),
            request.getURI(),
            request.getHeaders(),
            new String(body, Charset.forName("UTF-8")),
            response.getStatusCode(),
            response.getHeaders(),
            response.getBody().toString());
        /*
        
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader( response.getBody()))) {
            log.debug("Response [{}] ",buffer.lines().collect(Collectors.joining("\n")));
        }catch(Exception e){
	    	log.error("Call Web Service",e);
	    }
        */
        return response;
    }


}