package cat.prv.listen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Qualifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import cat.prv.om.entity.TransHdr;
import cat.prv.services.OrderService;
import cat.prv.services.TransService;
import cat.prv.util.OrderType;

public class ProvisioningListener implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(ProvisioningListener.class);
	private static List<OrderType> allowList ;
	private static List<OrderType> multipleList ;
	
	@Autowired
	private Jackson2JsonMessageConverter jsonConvertor;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TransService transService;
	
	
	public void setJsonConvertor(Jackson2JsonMessageConverter jsonConvertor) {
		this.jsonConvertor = jsonConvertor;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setTransService(TransService transService) {
		this.transService = transService;
	}

	@PostConstruct
	public void init(){
		allowList = new ArrayList<>();
		allowList.add(OrderType.NEW_POSTPAID);
		allowList.add(OrderType.NEW_PREPAID);
		allowList.add(OrderType.SUSPEND);
		allowList.add(OrderType.RECONNECT);
		allowList.add(OrderType.TERMINATE);
		allowList.add(OrderType.CHG_PO);
		allowList.add(OrderType.ADD_SO2);
		allowList.add(OrderType.DEL_SO2);
		allowList.add(OrderType.PRE_TO_POST1);
		allowList.add(OrderType.PRE_TO_POST2);
		allowList.add(OrderType.POST_TO_PRE);
		allowList.add(OrderType.ACTIVATE_SO);
		
		multipleList = new ArrayList<>();
		multipleList.add(OrderType.PRE_TO_POST1);
		multipleList.add(OrderType.PRE_TO_POST2);
		multipleList.add(OrderType.POST_TO_PRE);
		multipleList.add(OrderType.CHG_SO1);
	}
	
	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		try {
			HashMap<String, String> map = (HashMap<String, String>)jsonConvertor.fromMessage(msg);
			String transId = map.get("transId");
			logger.debug("Consuming transId [{}]",transId);
			
			
			TransHdr transHdr = transService.getTransHdr(transId);
			
			logger.debug("Consuming TransHdr {}",transHdr == null);
			if(allowList.stream().anyMatch(p -> p == transHdr.getOrderType())){
				orderService.makeProvisioning4G(transId);
			}
			
		} catch (Exception e) {
			logger.error("Error Consuming {}",msg,e);
		}
	}

}
