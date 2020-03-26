package com.ssm.bbc.controller;



import com.ssm.bbc.util.BussinessException;
import com.ssm.bbc.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



//@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 数据校验
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        }else {
            msg = "系统繁忙，请稍后重试...";
        }
       return ResponseUtil.badArgument(msg);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //返回一个指定的http response状态码
    @ResponseBody
    public Object notFount(MissingServletRequestParameterException e) {
        log.error("输入参数错误:", e);
        return ResponseUtil.badArgument("输入参数错误");
    }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BussinessException.class)   //此处为自定义业务异常类
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //返回一个指定的http response状态码
    @ResponseBody
    public Object notFount(BussinessException e) {
        log.error("业务异常:", e);
        return ResponseUtil.unsupport(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        return ResponseUtil.unsupport();
    }


}
