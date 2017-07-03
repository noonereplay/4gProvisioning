package cat.prv.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import cat.prv.hlr.dto.HlrRequest;
import cat.prv.hlr.dto.HlrResponse;


@Service
public class Provisioning4GService {
	
	private static Logger logger = LoggerFactory.getLogger(Provisioning4GService.class);
	
	private URI endPoint;
    private ClientHttpRequestFactory requestFactory ;
    private static final HttpMethod method = HttpMethod.POST;
    public static final String SUB_TYPE = "2";
    
    @Value("${hlr.endpoint}")
    private String hlrEndPoint;
    
    @Autowired
    @Resource(name="objectMapper")
    private ObjectMapper mapper;
    
    @Autowired
    @Resource(name="hlrRestTemplate")
    private RestTemplate restTemplate;
    
    @Autowired
    private UriTemplate uriTemplate;
    
    @Autowired
    private TransService transService;
	
	public void Enable4G(String msisdn, String imsi, String serviceType, String transId){
		HlrRequest request = new HlrRequest();
		request.setAction("Enable4G");
		request.setImsi(imsi);
		request.setMsisdn(msisdn);
		request.setOmtransId("FG"+transId);
		request.setSubType(serviceType);
		
		HlrResponse response = null;
		
		try {
			response = callHlrRest(request);	
		} catch (Exception e) {
			logger.error("Error Request HLR : {}",request);
		}
		
		transService.saveHlrTrans(request, response);
	}
	
	public void Disable4G(String msisdn, String imsi, String serviceType, String transId){
		HlrRequest request = new HlrRequest();
		request.setAction("Disable4G");
		request.setImsi(imsi);
		request.setMsisdn(msisdn);
		request.setOmtransId("FG"+transId);
		request.setSubType(serviceType);
		
		HlrResponse response = null;
		
		try {
			response = callHlrRest(request);
		} catch (Exception e) {
			logger.error("Error Request HLR : {}",request);
		}
		
		transService.saveHlrTrans(request, response);
	}
	
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public void setUriTemplate(UriTemplate uriTemplate) {
		this.uriTemplate = uriTemplate;
	}

	public void setHlrEndPoint(String hlrEndPoint) {
		this.hlrEndPoint = hlrEndPoint;
	}

	public void setTransService(TransService transService) {
		this.transService = transService;
	}

	public HlrResponse callHlrRest(HlrRequest hlrRequest){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_XML}));
		
		HttpEntity<HlrRequest> request = new HttpEntity<>(hlrRequest,headers);

		HlrResponse response = null ;
		try {
			response = restTemplate.postForObject(hlrEndPoint, request, HlrResponse.class);
			/*ResponseEntity<HlrResponse> entity = restTemplate
					  .exchange(hlrEndPoint, HttpMethod.POST, request, HlrResponse.class);
			response  = entity.getBody();*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error HlrRestTemplate",e);
		}
		
		
		return response;
	}
	
	public void callHlr(String msisdn, String imsi, String transId,String subType,String action) throws Exception{
		ClientHttpRequest request = requestFactory.createRequest(endPoint, method); 
	    
	    String xmldata = "<HLR_IN><ACTION>" + 
	    action + "</ACTION>" + 
	    "<MSISDN>" + msisdn + "</MSISDN>" + 
	    "<IMSI>" + imsi + "</IMSI>" + 
	    "<OMTRANS_ID>" + transId + "</OMTRANS_ID>" + 
	    "<SUB_TYPE>" + subType + "</SUB_TYPE>" + 
	    "</HLR_IN>";
	    
	    OutputStream os = request.getBody();
	    OutputStreamWriter wr = new OutputStreamWriter(os);
	    wr.write(xmldata);
	    wr.flush();
	    
	    request.getHeaders().set("Content-type", "text/xml; charset=utf-8");
	    
	    ClientHttpResponse resp = null;
	    try {
	        logger.debug("Request Text [{}] ",xmldata);
	        resp = request.execute();
	    } catch (Exception e) {
	        logger.error("Call Hlr Error : {} ",msisdn,e);
	    }
	    
	    
	    BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getBody()));
	    StringBuilder sb = new StringBuilder();
	    String contentLine = rd.readLine();
	    
		while (contentLine != null) {
		        sb.append(contentLine);
		    contentLine = rd.readLine();
		}
		rd.close();
	    
	    try{
	        String res_work = sb.toString();
	        logger.debug("Response [{}] ",res_work);
	        String response = res_work.substring(res_work.indexOf("<STATUS>") + 8, res_work.indexOf("</STATUS>")) + "|" + res_work.substring(res_work.indexOf("<RES_MSG>") + 9, res_work.indexOf("</RES_MSG>"));
	        logger.debug("Response Text [{}] ",response);
	    }catch(Exception e){
	        logger.error("Call Hlr Error : msisdn[{}] action[{}] ",msisdn,action,e);
	    }
		
	}
	
	
	
}
