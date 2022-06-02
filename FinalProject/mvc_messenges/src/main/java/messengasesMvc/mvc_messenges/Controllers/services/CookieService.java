package messengasesMvc.mvc_messenges.Controllers.Services;

import javax.servlet.http.Cookie;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
@Service
public class CookieService {


    public void createCoockie(String token){
        /*ResponseCookie uiTokenCookie = ResponseCookie.from("TokenCookie", token)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(60)
            .domain("example.com")
            .build();
        */
        Cookie uiTokenCookie = new Cookie("TokenCookie", token);
            uiTokenCookie.setMaxAge(60 * 60);
            uiTokenCookie.setSecure(true);
            uiTokenCookie.setHttpOnly(true);
            uiTokenCookie.setPath("/");

        

            ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, uiTokenCookie.toString())
                .build();

            System.out.println(uiTokenCookie);
    }

    public String readCookie(@CookieValue(name = "TokenCookie") String userToken){      //To be use in a try catch for java.lang.IllegalStateException
        return userToken;
    }

    public void deleteCookie(){
        ResponseCookie uiTokenCookie = ResponseCookie
        .from("TokenCookie", null)
        .build();

        ResponseEntity
        .ok()
        .header(HttpHeaders.SET_COOKIE, uiTokenCookie.toString())
        .build();
    }
}
