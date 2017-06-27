package cat.prv.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cat.prv.hlr.dto.HlrRequest;
import cat.prv.hlr.dto.HlrResponse;
import cat.prv.om.dao.TblRtOffersDao;
import cat.prv.om.dao.TblRtOffersDelDao;
import cat.prv.om.dao.TblServicesDao;
import cat.prv.om.dao.TblServicesHistoryDao;
import cat.prv.om.dao.TransDtlDao;
import cat.prv.om.dao.TransHdrDao;
import cat.prv.om.entity.TblRtOffers;
import cat.prv.om.entity.TblRtOffersDel;
import cat.prv.om.entity.TblServices;
import cat.prv.om.entity.TblServicesHistory;
import cat.prv.om.entity.TransDtl;
import cat.prv.om.entity.TransHdr;
import cat.prv.services.OrderService;
import cat.prv.services.Provisioning4GService;
import cat.prv.services.TransService;

/*
@TestPropertySource(properties = { "spring.profiles.active=dev","jboss.server.log.dir=C:\\Development\\Application Server\\wildfly-10.1.0.Final\\standalone\\log"})
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/ApplicationContext.xml",
		"file:src/main/webapp/WEB-INF/spring-servlet.xml",})
@WebAppConfiguration
*/
public class ApplicationContextTest {

	@Autowired
	TransHdrDao transHdrDao;
	
	@Autowired
	TransDtlDao transDtlDao;
	
	@Autowired
	TblServicesDao tblServicesDao;
	
	@Autowired
	TblRtOffersDao tblRtOffersDao;
	
	@Autowired
	TblRtOffersDelDao tblRtOffersDelDao;
	
	@Autowired
	TblServicesHistoryDao tblServicesHistoryDao;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	Provisioning4GService provisioningService;
	
	@Autowired
	TransService transService;

	
	//@Test
    public void testTrans() {
		
		/*List<String> testList = null;
		if(!testList.stream().anyMatch(a -> a.equals("String"))){
			System.out.println("Nullable");
		}*/
		

		testTransHdr("U1610190000018865313");
		testTransHdr("U1610200000018902794");
		testTransDtl("U1610190000018865313","D1610190000041466173");
		testTransDtl("U1610200000018902794","D1610200000041545198");
        testTblServices();
        testTblServicesHistory();
        testTblRtOffers();
        testTblRtOffersDel();
        
        
    }
	
	
    public void testTransHdr(String transId) {
    	
		System.out.println("Test TranHdr Started");
        TransHdr transHdr = transHdrDao.getTransHdr(transId);
        List<TransDtl> transDtls = transHdr.getTransDtl();
        assertEquals(transHdr.getTransId(), transId);
        System.out.println("TransHdr "+transHdr.getTransId()+" Type : "+transHdr.getOrderType());
        transDtls.stream().forEach(d -> System.out.println(d.getTransDtlId()));
        System.out.println("Test TranHdr Completed ");
        System.out.println();
    }
	
	
	public void testTransDtl(String transId, String transDtlId){
		System.out.println("Test TranDtl Started");
		TransDtl transDtl = transDtlDao.getTransDtl(transDtlId);
		assertEquals(transDtl.getTransDtlId(), transDtlId);
		assertEquals(transDtl.getTransHdr().getTransId(), transId);
		
		List<TransDtl> transDtls = transDtlDao.getTransDtlByTransId(transId);
		
		transDtls.stream().forEach(d -> {
			System.out.println(d);
			assertEquals(transDtl.getTransHdr().getTransId(), transId);
		});
		
        System.out.println("Test TranDtl Completed ");
        System.out.println();
	}
	
	public void testTblServices(){
		System.out.println("Test TblServices Started");
		TblServices tblServices = tblServicesDao.getTblServiceByMsisdn("66887998168");
		assertEquals(tblServices.getMsisdn(), "66887998168");
		assertEquals(tblServicesDao.getTblServices(tblServices.getServicesId()).getServicesId(), tblServices.getServicesId());
		System.out.println(tblServices);
        System.out.println("Test TblServices Completed ");
        System.out.println();
		
	}
	
	public void testTblServicesHistory(){
		System.out.println("Test TblServicesHistory Started");
		List<TblServicesHistory> list = tblServicesHistoryDao.getTblServicesHistoryByMsisdn("66864496265");
		TblServicesHistory history = tblServicesHistoryDao.getTblServices(Integer.valueOf("873448"));
		list.stream().forEach( s-> System.out.println(s));
		assertEquals(history.getMsisdn(),"66864496265");
		System.out.println("Test TblServicesHistory Completed ");
        System.out.println();
		
	}
	
	
	public void testTblRtOffers(){
		System.out.println("Test TblRtOffers Started");
		TblRtOffers tblRtOffers = tblRtOffersDao.getTblRtOffer( Integer.valueOf("20637011"));
		assertEquals(tblRtOffers.getOfferId(), Integer.valueOf("51005425"));
		System.out.println(tblRtOffers);
		System.out.println("Test TblRtOffers Completed ");
		System.out.println();
	} 
	
	public void testTblRtOffersDel(){
		System.out.println("Test TblRtOffersDel Started");
		TblRtOffersDel tblRtOffers = tblRtOffersDelDao.getTblRtOffer(Integer.valueOf("20673393"));
		assertEquals(tblRtOffers.getOfferId(), Integer.valueOf("51005503"));
		System.out.println(tblRtOffers);
		System.out.println("Test TblRtOffersDel Completed ");
		System.out.println();
	} 
	
	//@Test
	public void testOrderService(){
		testAddSo2();
		testDelSo2();
	}
	
	public void testAddSo2(){
		System.out.println("Test Add SO2 Service Started");
		try {
			assertFalse(orderService.makeProvisioning4G("U1610250000019086648"));
			assertTrue(orderService.makeProvisioning4G("U1610220000018979434"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test Add SO2 Service Completed");
	}

	public void testDelSo2(){
		System.out.println("Test Delete SO2 Service Started");
		try {
			assertFalse(orderService.makeProvisioning4G("T1610220000018993441"));
			assertFalse(orderService.makeProvisioning4G("U1610220000019001626"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test Delete SO2 Service Completed");
	}
	
	//@Test
	public void testProvisioning(){
		
		System.out.println();
		System.out.println("Test Provisioning Service Started");
		HlrRequest request = new HlrRequest();
		request.setAction("FirstActivate");
		request.setImsi("520001910248562");
		request.setMsisdn("66882753351");
		request.setOmtransId("T1609210000000044006");
		request.setSubType("1");
		
		System.out.println("Request : "+request);

		try {
			HlrResponse response = provisioningService.callHlrRest(request);
			
			transService.saveHlrTrans(request, response);
			System.out.println("Response : "+response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		
		System.out.println("Test Provisioning Service Completed");
	}
}
