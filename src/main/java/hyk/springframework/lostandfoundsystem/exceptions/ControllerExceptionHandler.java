package hyk.springframework.lostandfoundsystem.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Htoo Yanant Khin
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    public ModelAndView handleBadRequest(HttpServletRequest req, Exception exception) {
        log.error("Handling Bad Request Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/400badrequest");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(HttpServletRequest req, Exception exception) {
        log.error("Handling Resource Not Found Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404notfound");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("status", HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(HttpServletRequest req, Exception exception) {
        log.error("Handling Access Denied Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/403forbidden");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("status", HttpStatus.FORBIDDEN);
        return modelAndView;
    }
}
