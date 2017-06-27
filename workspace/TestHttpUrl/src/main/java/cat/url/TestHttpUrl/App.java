package cat.url.TestHttpUrl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        String url = args[0];
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
        	CloseableHttpResponse response = httpclient.execute(httpGet);
        	HttpEntity entity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            Header[] headers = response.getAllHeaders();
            for (int i = 0; i < headers.length; i++) {
              System.out.println(headers[i]);
            }
            System.out.println("----------------------------------------");

            if (entity != null) {
              System.out.println(EntityUtils.toString(entity));
            }
            response.close();
            httpclient.close();
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
        	try {
        		httpclient.close();
            } catch (Exception ignore) {
            	ignore.printStackTrace();
            }
        	
        }
    }
}
