package io.pine.cloud.service.user.interfaces;

import io.pine.cloud.service.user.interfaces.vo.Resp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	public final static String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Resp<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		return Resp.fail(500, e.getMessage(), "Internal Error");
	}
}
