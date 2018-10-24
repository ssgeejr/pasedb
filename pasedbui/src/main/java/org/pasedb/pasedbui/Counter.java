package org.pasedb.pasedbui;

import javax.servlet.http.HttpServletRequest;

public class Counter {
	
	public Counter(HttpServletRequest request) {
		System.out.println(new java.util.Date().toString());
		try {
			System.out.println("REMOTE_ADDRESS: " + request.getRemoteAddr());
//			System.out.println(request.getHeader("X_FORWARDED_FOR"));
//			System.out.println(request.getHeader("HTTP_CLIENT_IP"));
//			System.out.println(request.getHeader("WL-Proxy-Client-IP"));
//			System.out.println(request.getHeader("Proxy-Client-IP"));
//			System.out.println(request.getHeader("REMOTE_ADDR"));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
