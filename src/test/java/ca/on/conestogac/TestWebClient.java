package ca.on.conestogac;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestWebClient {
	String sUrl = "http://www.wowjobs.ca/wowrss.aspx?q=web+developer&l=N2T1G8&s=r&sr=25&t=30&f=r&e=&si=A&Dup=H";
	@Test
	public void testWowDownload()
	{
		try{
			WebClient oWebClient = new WebClient();
			String sRc = oWebClient.downloadString(sUrl);
			System.out.println(sRc);
			assertTrue(true);
		}catch(Exception e){
			
			assertTrue(false);
		}
	}
	@Test
	public void testXMLDownloadAsJSON()
	{
		try{
			WebClient oWebClient = new WebClient();
			oWebClient.downloadString(sUrl, System.out);
			assertTrue(true);
		}catch(Exception e){
			
			assertTrue(false);
		}
		
	}
}
