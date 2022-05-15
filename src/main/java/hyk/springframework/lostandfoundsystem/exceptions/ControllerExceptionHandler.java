package hyk.springframework.lostandfoundsystem.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
//@ControllerAdvice
public class ControllerExceptionHandler {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception exception) {
        log.error("Handling ControllerExceptionHandler");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/400badrequest");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView handleNotFoundException(Exception exception) {
//        log.error("Handling ControllerExceptionHandler");
//        log.error(exception.getMessage());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error/404error");
//        modelAndView.addObject("exception", exception);
//        return modelAndView;
//    }
}
