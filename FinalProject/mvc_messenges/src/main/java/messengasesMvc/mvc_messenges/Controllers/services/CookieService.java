package messengasesMvc.mvc_messenges.Controllers.Services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
@Service
public class CookieService {

    @Autowired
    HttpServletResponse response;

    public void createCoockie(String token){
		
        Cookie uiTokenCookie = new Cookie("TokenCookie", token);
            uiTokenCookie.setMaxAge(60 * 60);
            uiTokenCookie.setSecure(true);
            uiTokenCookie.setHttpOnly(true);
            uiTokenCookie.setPath("/");

		response.addCookie(uiTokenCookie);
    }

    public void deleteCookie(){
        Cookie uiTokenCookie = new Cookie("TokenCookie",null);
            uiTokenCookie.setMaxAge(0);
            uiTokenCookie.setSecure(true);
            uiTokenCookie.setHttpOnly(true);
            uiTokenCookie.setPath("/");

		response.addCookie(uiTokenCookie);
    }
}
