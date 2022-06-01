package hyk.springframework.lostandfoundsystem.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler { 

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

     if(exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            setDefaultFailureUrl("/url1");
      }
      else if (exception.getClass().isAssignableFrom(DisabledException.class)) {         
          setDefaultFailureUrl("/url2");
      }
      else if (exception.getClass().isAssignableFrom(SessionAuthenticationException.class)) {       
          setDefaultFailureUrl("/url3");    
      }

      super.onAuthenticationFailure(request, response, exception);  

    }

}