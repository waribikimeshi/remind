package com.example.rest.aop;

import java.util.Arrays;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.rest.exception.BadRequestException;
import com.example.rest.exception.DataNotFoundException;
import com.example.rest.model.ExceptionResponseModel;

@ControllerAdvice
public class ExceptionAOP {

	public static final Logger logger = LoggerFactory.getLogger(ExceptionAOP.class);

	@Autowired
	private MessageSource messageSource;

    /**
     * リクエスト不正エラー
     * @param exception
     * @return
     */
    @ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="リクエスト不正エラー")
    @ExceptionHandler({ BadRequestException.class })
    @ResponseBody
    public ExceptionResponseModel handleBadRequestException(BadRequestException exception) {
    	logger.warn(exception.toString());

    	ExceptionResponseModel result =
    			new ExceptionResponseModel(
    					HttpStatus.BAD_REQUEST.value()
    					,HttpStatus.BAD_REQUEST.getReasonPhrase()
    					,exception.getMessageList());
        return result;
    }

    /**
     * 対象なしエラー
     * @param exception
     * @return
     */
    @ResponseStatus(value=HttpStatus.NOT_FOUND,reason="対象なしエラー")
    @ExceptionHandler({ DataNotFoundException.class})
    @ResponseBody
    public ExceptionResponseModel handleDataNotFoundException(DataNotFoundException exception) {
    	logger.warn(exception.toString());

    	ExceptionResponseModel result =
    			new ExceptionResponseModel(
    					HttpStatus.NOT_FOUND.value()
    					,HttpStatus.NOT_FOUND.getReasonPhrase()
    					,exception.getMessageList());
        return result;
    }

	/**
	 * 排他エラー例外
	 * @param exception
	 * @return
	 */
    @ResponseStatus(value=HttpStatus.CONFLICT,reason="楽観排他エラー")
    @ExceptionHandler({ OptimisticLockingFailureException.class })
    @ResponseBody
    public ExceptionResponseModel handleOptimisticLockingFailureException(OptimisticLockingFailureException exception) {
    	logger.error(exception.toString());

    	ExceptionResponseModel result =
    			new ExceptionResponseModel(
    					HttpStatus.CONFLICT.value()
    					,HttpStatus.CONFLICT.getReasonPhrase()
    					,Arrays.asList(exception.toString()));

        return result;
    }
    
    /**
     * ＤＢ定義制約エラー
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ DataIntegrityViolationException.class })
    @ResponseBody
    public ExceptionResponseModel handle404_3(DataIntegrityViolationException exception, Locale locale) {
    	logger.error(exception.toString());


    	ExceptionResponseModel result =
    			new ExceptionResponseModel(
    					HttpStatus.CONFLICT.value()
    					,HttpStatus.CONFLICT.getReasonPhrase()
    					,Arrays.asList(exception.toString()));
    	
        return result;
    }


    /**
     * ＤＢアクセス例外
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ DataAccessException.class })
    @ResponseBody
    public ExceptionResponseModel handle500_1(DataAccessException exception, Locale locale) {
    	logger.error(exception.toString());


    	ExceptionResponseModel result =
    			new ExceptionResponseModel(
    					HttpStatus.INTERNAL_SERVER_ERROR.value()
    					,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
    					,Arrays.asList(exception.toString()));
        return result;
    }

    
    
    /**
     * 予期せぬ例外エラー
     * @param exception
     * @return
     */
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="予期せぬエラー")
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ExceptionResponseModel handle500(Exception exception, Locale locale) {
    	logger.error(exception.toString());

    	//TODO:メッセージリソース
    	//String msg = messageSource.getMessage("message.error.99999", null, locale);
    	String msg = "予期せぬ例外が発生しました";
    	ExceptionResponseModel result =
    			new ExceptionResponseModel(
    					HttpStatus.INTERNAL_SERVER_ERROR.value()
    					,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
    					,Arrays.asList(msg));
        return result;
    }
}
