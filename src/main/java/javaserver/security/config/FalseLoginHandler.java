//package javaserver.security.config;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FalseLoginHandler implements AuthenticationFailureHandler{
////	PrintWriter writer;
//	
//	@Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//        AuthenticationException exception) throws IOException, ServletException {
//		System.out.println("錯誤驗證");
//		
//
////        super.onAuthenticationFailure(request, response, exception);
//		response.setContentType("application/json");
//		JSONObject returnObj = new JSONObject();
//		 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		 response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//		 response.setCharacterEncoding("UTF-8");
//
////		 writer = response.getWriter();
//		 PrintWriter out = response.getWriter();
//		 try {
//			returnObj.put("resultStatus", false);
//			returnObj.put("message", "登入失敗");
//			returnObj.put("model", null);
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 out.print(returnObj.toString());
//		 out.flush();
////		 out.close();
//    }
//}
