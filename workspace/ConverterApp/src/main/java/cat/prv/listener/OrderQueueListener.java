package cat.prv.listener;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cat.prv.conv.services.TransService;
import cat.prv.om.entity.TransHdr;
import cat.prv.util.OrderType;




public class OrderQueueListener implements MessageListener, BeanNameAware{

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(OrderQueueListener.class);
	
	private String beanName;
	
	@Autowired
	@Qualifier("jsonMessageConverter")
	private Jackson2JsonMessageConverter jsonConvertor;
	

	private TransService transService;
	
	private RabbitTemplate newAmqpTemplate;
	private RabbitTemplate suspendAmqpTemplate;
	private RabbitTemplate reconnectAmqpTemplate;
	private RabbitTemplate terminateAmqpTemplate;
	private RabbitTemplate addOfferAmqpTemplate;
	private RabbitTemplate delOfferTemplate;
	private RabbitTemplate chgOfferAmqpTemplate;
	private RabbitTemplate preToPostAmqpTemplate;
	private RabbitTemplate postToPreAmqpTemplate;
	private RabbitTemplate actSoAmqpTemplate;

	public Jackson2JsonMessageConverter getJsonConvertor() {
		return jsonConvertor;
	}
	
	public void setJsonConvertor(Jackson2JsonMessageConverter jsonConvertor) {
		this.jsonConvertor = jsonConvertor;
	}
		
	public void setTransService(TransService transService) {
		this.transService = transService;
	}


	public void setNewAmqpTemplate(RabbitTemplate newAmqpTemplate) {
		this.newAmqpTemplate = newAmqpTemplate;
	}

	public void setSuspendAmqpTemplate(RabbitTemplate suspendAmqpTemplate) {
		this.suspendAmqpTemplate = suspendAmqpTemplate;
	}

	public void setReconnectAmqpTemplate(RabbitTemplate reconnectAmqpTemplate) {
		this.reconnectAmqpTemplate = reconnectAmqpTemplate;
	}

	public void setTerminateAmqpTemplate(RabbitTemplate terminateAmqpTemplate) {
		this.terminateAmqpTemplate = terminateAmqpTemplate;
	}

	public void setAddOfferAmqpTemplate(RabbitTemplate addOfferAmqpTemplate) {
		this.addOfferAmqpTemplate = addOfferAmqpTemplate;
	}

	public void setDelOfferTemplate(RabbitTemplate delOfferTemplate) {
		this.delOfferTemplate = delOfferTemplate;
	}

	public void setChgOfferAmqpTemplate(RabbitTemplate chgOfferAmqpTemplate) {
		this.chgOfferAmqpTemplate = chgOfferAmqpTemplate;
	}

	public void setPreToPostAmqpTemplate(RabbitTemplate preToPostAmqpTemplate) {
		this.preToPostAmqpTemplate = preToPostAmqpTemplate;
	}

	public void setPostToPreAmqpTemplate(RabbitTemplate postToPreAmqpTemplate) {
		this.postToPreAmqpTemplate = postToPreAmqpTemplate;
	}

	public void setActSoAmqpTemplate(RabbitTemplate actSoAmqpTemplate) {
		this.actSoAmqpTemplate = actSoAmqpTemplate;
	}

	@Override
	public void onMessage(Message msg) {
		
		try{
			HashMap<String, String> map = (HashMap<String, String>)jsonConvertor.fromMessage(msg);
			String transId = map.get("transId");
			
			TransHdr transHdr = transService.getTransHdr(transId);
			
			RabbitTemplate rabbitTemplate = null;
			if(transHdr.getOrderType() == OrderType.NEW_POSTPAID || transHdr.getOrderType() == OrderType.NEW_PREPAID ){
				rabbitTemplate = newAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.SUSPEND){
				rabbitTemplate = suspendAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.RECONNECT){
				rabbitTemplate = reconnectAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.TERMINATE){
				rabbitTemplate = terminateAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.ADD_SO2){
				rabbitTemplate = addOfferAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.DEL_SO2){
				rabbitTemplate = delOfferTemplate;
			}else if(transHdr.getOrderType() == OrderType.CHG_SO1){
				rabbitTemplate = chgOfferAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.PRE_TO_POST1 || transHdr.getOrderType() == OrderType.PRE_TO_POST2){
				rabbitTemplate = preToPostAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.POST_TO_PRE){
				rabbitTemplate = postToPreAmqpTemplate;
			}else if(transHdr.getOrderType() == OrderType.ACTIVATE_SO){
				rabbitTemplate = actSoAmqpTemplate;
			}
				
			if(rabbitTemplate != null){
				rabbitTemplate.convertAndSend(map);
			}	
			
		}catch(Exception e){
			logger.error("Error getTransHdr DB : {} ",e);
		}
		logger.info("{} onMessage Body {} ", beanName, new String(msg.getBody()) );
		/*
		if(!result.isSuccess() && sms.getRetryNum() > 0){
			//msg.getMessageProperties().getRedelivered();
			logger.error("Error Result [{}] : {} ",sms,result.getThrowable());
			//throw new ListenerExecutionFailedException("Error occur while sending message to SMSC",result.getThrowable());
			amqpTemplate.convertAndSend(sms);
		}*/
	}
	
	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;		
	}

}