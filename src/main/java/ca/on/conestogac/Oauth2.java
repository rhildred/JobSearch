package ca.on.conestogac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.*;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.http.*;

public class Oauth2 {

	private String sClientId = null, sSecretToken = null, sRedirect = null;

	public Oauth2(){
	}

	public void redirect(HttpServletResponse res) throws IOException {
		if (!this.sRedirect.contains("localhost")) {
			String sPattern = ":\\d\\d*";
			this.sRedirect = this.sRedirect.replaceAll(sPattern, "");
		}
		String sAuthUrl = "https://accounts.google.com/o/oauth2/auth?redirect_uri=%s&client_id=%s&scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email&response_type=code&max_auth_age=0";
		String sRedirectToGoogle = String.format(sAuthUrl, this.sRedirect,
				this.sClientId);
		res.sendRedirect(sRedirectToGoogle);

	}
	private WebClient conn = null;
	private String sGoogleId = null, sName = null, sEmail = null;
	public void handleCode(String sCode) throws IOException, ParseException
    {
		if(conn == null){
			this.conn = new WebClient();			
		}
        if (this.sGoogleId == null)
        {
            //step 5
            // then google has redirected to us so build up query for 2nd phase of authentication
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
            nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
            nameValuePairs.add(new BasicNameValuePair("client_id", this.sClientId));
            nameValuePairs.add(new BasicNameValuePair("client_secret", this.sSecretToken));
            nameValuePairs.add(new BasicNameValuePair("code", sCode));
            nameValuePairs.add(new BasicNameValuePair("redirect_uri", this.sRedirect));

            JSONObject oResult = (JSONObject) this.conn.downloadJson("https://accounts.google.com/o/oauth2/token", nameValuePairs);
            String sAccessToken = (String)oResult.get("access_token");

            // step 7
            // now we can get the user info
            String sUserInfoUrl = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=%s";
            JSONObject oInfo = (JSONObject) conn.downloadJson(String.format(sUserInfoUrl, sAccessToken));
            sName = (String)oInfo.get("name");
            sGoogleId = (String)oInfo.get("id");
            sEmail = (String)oInfo.get("email");
  
        }
                  
    }
	

	public void close() {
		this.conn.dispose();
	}

	public String getsName() {
		return sName;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsClientId(String sClientId) {
		this.sClientId = sClientId;
	}

	public void setsSecretToken(String sSecretToken) {
		this.sSecretToken = sSecretToken;
	}

	public void setsRedirect(String sRedirect) {
		this.sRedirect = sRedirect;
	}

	public String getsRedirect() {
		return sRedirect;
	}

}