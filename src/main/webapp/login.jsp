<%@page contentType="application/json; charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<jsp:useBean id="oAuth2" class="ca.on.conestogac.Oauth2" scope="session" />
<%

try{	
	String sUrl = request.getRequestURL().toString();
	if(oAuth2.getsEmail() == null && request.getParameter("code") == null){
		oAuth2.setsRedirect(sUrl);
		oAuth2.setsClientId(application.getInitParameter("ClientID"));
		oAuth2.setsSecretToken(application.getInitParameter("ClientSecret"));
		oAuth2.redirect(response);
	}else if(request.getParameter("code") != null){
		oAuth2.handleCode(request.getParameter("code"));
		List<String> aUrl = new ArrayList<String>(Arrays.asList(sUrl.split("/")));
		aUrl.remove(aUrl.size() - 1);
		String sNewUrl = StringUtils.join(aUrl.toArray(), "/");
		response.sendRedirect(sNewUrl);
	}else{
		out.print("{\"name\":\"" + oAuth2.getsName() + "\", \"email\":\"" + oAuth2.getsEmail() + "\"}" );
	}
}catch(Exception e){
	response.sendError(500, "Exception " + e.toString());
}

%>
