package cat.prv.services;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.rtc.gw.rs.api.SubscribersResource;
import cat.rtc.gw.rs.domain.subscriber.SubscriberInfo;

@Service
public class C1rtGwService {

	private static Logger logger = LoggerFactory.getLogger(C1rtGwService.class);
	
	@Autowired
	@Resource(name="subscribersResource")
	private SubscribersResource subscribersResource;
	
	public void setSubscribersResource(SubscribersResource subscribersResource) {
		this.subscribersResource = subscribersResource;
	}

	public SubscriberInfo info(String msisdn){
		
		SubscriberInfo info = null;
		try {
			info = subscribersResource.subscriberInfo(msisdn).summary(Short.valueOf("2"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Format Language [{}]",msisdn,e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Error GetInfo [{}]",msisdn,e);
		}
		
		return info;
	}
}
