package egovframework.com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.mtpms.dto.loginDto;

public class EgovAuthenticInterceptor extends WebContentInterceptor {

	protected static Logger logger = Logger.getLogger(Main.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

		//logger.info("== URL : "+request.getRequestURI());

		loginDto loginDto = (loginDto) EgovUserDetailsHelper.getAuthenticatedUser();

		if (loginDto.getUserid() != null) {
			return true;
		} else {
			ModelAndView modelAndView = new ModelAndView("forward:/login.do");
			modelAndView.addObject("message", "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}

}
