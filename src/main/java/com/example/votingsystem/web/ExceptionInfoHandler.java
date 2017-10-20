package com.example.votingsystem.web;

import com.example.votingsystem.util.MessageUtil;
import com.example.votingsystem.util.ValidationUtil;
import com.example.votingsystem.util.exception.ErrorInfo;
import com.example.votingsystem.util.exception.ErrorType;
import com.example.votingsystem.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.*;
import org.zalando.problem.spring.web.advice.AdviceTrait;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Brad on 19.10.2017.
 */

@ControllerAdvice
public class ExceptionInfoHandler implements ProblemHandling {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";

    private static final Map<String, String> CONSTRAINS_I18N_MAP = Collections.unmodifiableMap(
            new HashMap<String, String>() {
                {
                    put("users_unique_email_idx", EXCEPTION_DUPLICATE_EMAIL);
                }
            });

    @Autowired
    private MessageUtil messageUtil;

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    ResponseEntity<Problem> handleDataIntegrityViolationException(Exception exception, NativeWebRequest request) {
//
////        final ThrowableProblem problem;
////
////        problem = Problem.valueOf(Status.CONFLICT);
//
//        //ResponseEntity<Problem> retval = create(problem, request);
//
//        ThrowableProblem problem = Problem.builder()
//                .withType(URI.create("https://example.org/order-failed"))
//                .withTitle("Order failed")
//                .withStatus(Status.BAD_REQUEST)
//                .withCause(Problem.builder()
//                        .withType(URI.create("about:blank"))
//                        .withTitle("Out of Stock")
//                        .withStatus(Status.BAD_REQUEST)
//                        .build())
//                .build();
//
//        problem.getCause(); // standard API of java.lang.Throwable
//
//
//        ResponseEntity<Problem> retval = create(problem, request);
//
//        return retval;
//    }
//
//    @Override
//    public boolean isCausalChainsEnabled() {
//        return false;
//    }

    //    @Override
//    public ThrowableProblem toProblem(Throwable throwable, StatusType status, URI type) {
//
//        final ThrowableProblem problem = prepare(throwable, status, type).build();
//        final StackTraceElement[] stackTrace = createStackTrace(throwable);
//        problem.setStackTrace(stackTrace);
//        return problem;
//    }


//    @Override
//    public ProblemBuilder prepare(Throwable throwable, StatusType status, URI type) {
//
//        ProblemBuilder builder = Problem.builder();
//
//        if (throwable instanceof DataIntegrityViolationException) {
//            String rootMsg = ValidationUtil.getRootCause(throwable).getMessage();
//            if (rootMsg != null) {
//                String lowerCaseMsg = rootMsg.toLowerCase();
//                Optional<Map.Entry<String, String>> entry = CONSTRAINS_I18N_MAP.entrySet().stream()
//                        .filter(it -> lowerCaseMsg.contains(it.getKey()))
//                        .findAny();
//                if (entry.isPresent()) {
//
//                    builder
//                            .withType(type)
////                            .withTitle(status.getReasonPhrase())
////                            .withStatus(status)
////                            .withTitle(ErrorType.DATA_ERROR.toString())
//                            .withTitle(Status.CONFLICT.getReasonPhrase())
//                            .withStatus(Status.CONFLICT)
////                            .withDetail(messageUtil.getMessage(entry.get().getValue()))
//                            .withDetail(EXCEPTION_DUPLICATE_EMAIL);
////                            .withCause(Optional.ofNullable(throwable.getCause())
////                                    .filter(cause -> isCausalChainsEnabled())
////                                    .map(this::toProblem)
////                                    .orElse(null));
//                }
//            }
//        } else {
//
//            builder
//                    .withType(type)
//                    .withTitle(status.getReasonPhrase())
//                    .withStatus(status)
//                    .withDetail(throwable.getMessage())
//                    .withCause(Optional.ofNullable(throwable.getCause())
//                            .filter(cause -> isCausalChainsEnabled())
//                            .map(this::toProblem)
//                            .orElse(null));
//
//        }
//
//        return builder;
//    }


    //    private static final Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);
//
//    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";
//
//
//    private static final Map<String, String> CONSTRAINS_I18N_MAP = Collections.unmodifiableMap(
//            new HashMap<String, String>() {                {
//                    put("users_unique_email_idx", EXCEPTION_DUPLICATE_EMAIL);
//                }
//            });
//
//    @Autowired
//    private MessageUtil messageUtil;
//
//    //  http://stackoverflow.com/a/22358422/548473
//    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
//    @ExceptionHandler(NotFoundException.class)
//    public ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
//        return logAndGetErrorInfo(req, e, false, ErrorType.DATA_NOT_FOUND);
//    }
//
//    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
//        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
//        if (rootMsg != null) {
//            String lowerCaseMsg = rootMsg.toLowerCase();
//            Optional<Map.Entry<String, String>> entry = CONSTRAINS_I18N_MAP.entrySet().stream()
//                    .filter(it -> lowerCaseMsg.contains(it.getKey()))
//                    .findAny();
//            if (entry.isPresent()) {
//                return logAndGetErrorInfo(req, e, false, ErrorType.DATA_ERROR, messageUtil.getMessage(entry.get().getValue()));
//            }
//        }
//        return logAndGetErrorInfo(req, e, true, ErrorType.DATA_ERROR);
//    }
//
//    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
//    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
//    public ErrorInfo bindValidationError(HttpServletRequest req, Exception e) {
//        BindingResult result = e instanceof BindException ?
//                ((BindException) e).getBindingResult() : ((MethodArgumentNotValidException) e).getBindingResult();
//
//        String[] details = result.getFieldErrors().stream()
//                .map(fe -> {
//                    String msg = fe.getDefaultMessage();
//                    return (msg.startsWith(fe.getField())) ? msg : fe.getField() + ' ' + msg;
//                })
//                .toArray(String[]::new);
//
//        return logAndGetErrorInfo(req, e, false, ErrorType.VALIDATION_ERROR, details);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
//        return logAndGetErrorInfo(req, e, true, ErrorType.APP_ERROR);
//    }
//
//    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
//        Throwable rootCause = ValidationUtil.getRootCause(e);
//        if (logException) {
//            LOG.error(errorType + " at request " + req.getRequestURL(), rootCause);
//        } else {
//            LOG.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
//        }
//        return new ErrorInfo(req.getRequestURL(), errorType,
//                details.length != 0 ? details : new String[]{rootCause.toString()});
//    }


}
