package com.chen.handler;

import com.chen.domain.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
//    /**
//     * 参数校验异常统一处理，拦截 MethodArgumentNotValidException 异常
//     */
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    public ResponseResult handleValidException(MethodArgumentNotValidException e) {
//        log.error("数据校验异常，{}，异常类型：{}", e.getMessage(), e.getClass());
//        BindingResult bindingResult = e.getBindingResult();
//        Map<String, String> errorMap = ErrorResultUtil.getErrorMap(bindingResult);
//        return ResponseResult.error(400,"参数校验失败").put("data", errorMap);
//    }
//    /**
//     * 参数绑定异常统一处理，拦截 BindException 异常
//     */
//    @ExceptionHandler(value = {BindException.class})
//    public R handleValidException(BindException e) {
//        log.error("数据校验异常，{}，异常类型：{}", e.getMessage(), e.getClass());
//        BindingResult bindingResult = e.getBindingResult();
//        Map<String, String> errorMap = ErrorResultUtil.getErrorMap(bindingResult);
//        return R.error(400,"参数校验失败").put("data", errorMap);
//    }
//    /**
//     * 约束校验异常统一处理
//     */
//    @ExceptionHandler(value = {ConstraintViolationException.class})
//    public R handleValidException(ConstraintViolationException e) {
//        log.error("数据校验异常，{}，异常类型：{}", e.getMessage(), e.getClass());
//        List<String> violations = e.getConstraintViolations().stream()
//                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
//        String error = violations.get(0);
//        return R.error(400, error);
//    }


    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeException(RuntimeException t) {
        log.error("未知异常，{}，异常类型：{}", t.getMessage(), t.getClass());
        return ResponseResult.error(t.getMessage());
    }

    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult handleException(Throwable t) {
        log.error("未知异常，{}，异常类型：{}", t.getMessage(), t.getClass());
        return ResponseResult.error("网络出小差");
    }
}