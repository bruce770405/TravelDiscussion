package javaserver.security.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * @author BruceHsu
 *  登入失敗調用的方法
 */
@Component
public class RESTAuthenticationEntryPoint  implements AuthenticationEntryPoint,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7390984499666171537L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.setContentType("application/json");
		JSONObject returnObj = new JSONObject();
		 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		 response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		 response.setCharacterEncoding("UTF-8");

//		 writer = response.getWriter();
		 PrintWriter out = response.getWriter();
		 try {
			returnObj.put("resultStatus", false);
			returnObj.put("message", "登入失敗");
			returnObj.put("model", "");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 
		 out.print(returnObj.toString());
		 out.flush();
//	
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}