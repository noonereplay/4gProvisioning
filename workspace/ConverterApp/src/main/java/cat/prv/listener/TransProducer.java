package cat.prv.listener;



import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import cat.prv.conv.services.TransService;



public class TransProducer implements MessageListener{
	
	private final static Logger logger = LoggerFactory.getLogger(TransProducer.class);


	private TransService transService;

	private RabbitTemplate amqpTemplate;


	public void setTransService(TransService transService) {
		this.transService = transService;
	}

	public void setAmqpTemplate(RabbitTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	@Override
	public void onMessage(Message message) {
		
		logger.debug("onMessage!!!!");		

		
		if (message instanceof TextMessage) {
            try {
            	
            	/*
            	for (Enumeration e = message.getPropertyNames(); e.hasMoreElements();) {
                    
            		
            		Object ee = e.nextElement();
            		
					
            		String key = (String) ee;
                    Object obj = message.getObjectProperty(key);
                    
                    
                    if (obj != null) {
                        logger.debug("key {} value {} ",key,obj);
                        logger.debug("key decode {} value {} ",key,obj);
                        
                        
                    }
                }*/
            	
            	
            	
            	TextMessage tm = (TextMessage)message;
                String msg = tm.getText();
                logger.debug("Msg {} ",msg);
                String transId = message.getStringProperty("TRANS_ID");
                logger.debug(msg);
                logger.debug("transId Id : {} ",transId);
                
                
                
                HashMap<String, String> map = new HashMap<>();
                map.put("transId", transId);
                
                logger.debug("Sending to RabbitMq : {} ",map);
                amqpTemplate.convertAndSend(map);
                
                
                logger.debug("Retrive : {} ",map);
            }
            catch (JMSException ex) {
            	logger.error("Error JMSException",ex);
            } 
        }
        else {
        	logger.error("Error ",new IllegalArgumentException("Message must be of type TextMessage"));
        }
		
	}

}
