package org.krpano.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CLoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("-----我是拦截器sasa，Action前置拦截----");
		Object loginUserName = ActionContext.getContext().getSession()
				.get("username");
		// System.out.println(loginUserName);
		if (loginUserName == null) {
			String failResult = "false";
			ActionContext.getContext().put("failResult", failResult);
			// System.out.println(failResult);
			return "fail";
		} else {
			// System.out.println("----我是一个拦截器,Action后置拦截----");
			// String sucResult =loginUserName;
			ActionContext.getContext().put("successResult", loginUserName);
			return "success";
		}
	}
}
