package cat.prv.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum OrderType implements Serializable{

	NEW_POSTPAID("OM101"),NEW_PREPAID("OM102"),
	EDIT_PROFILE("OM302"), CHG_PO("OM401"), CHG_SO1("OM402"),
	ADD_SO2("OM403"), DEL_SO2("OM404"), ACTIVATE_SO("OM405"),
	RECONNECT("OM501"),TERMINATE("OM502"), SUSPEND("OM503"), 
	CHG_OWNER1("OM601"),CHG_OWNER2("OM602"),CHG_MSISDN("OM701"),
	CHG_ICCID("OM801"), PRE_TO_POST1("OM901"), PRE_TO_POST2("OM902"),
	POST_TO_PRE("OM903");
	
	private String transGroupId;
	
	private static final Map<String, OrderType> orderTypeMap = Arrays.asList(OrderType.values()).stream().collect(Collectors.toMap(OrderType::getTransGroupId, Function.identity()));

	
	private OrderType(String transGroupId){
		this.transGroupId = transGroupId;
	}
	
	public String getTransGroupId(){
		return this.transGroupId;
	}
	public static OrderType getByGroupId(String transGroupId) {
        return orderTypeMap.get(transGroupId);
    }
}