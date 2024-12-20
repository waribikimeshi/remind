package com.example.rest.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.common.Consts;
import com.example.rest.exception.BadRequestException;
import com.example.rest.model.table.AuthenticationModel;
import com.example.rest.model.validated.group.PATCHRequired;
import com.example.rest.model.validated.group.POSTRequired;
import com.example.rest.model.validated.group.PUTRequired;
import com.example.rest.service.AuthenticationService;

import io.swagger.annotations.ApiOperation;

/**
 * コントローラクラス
 * @author hanada
 * @remark:バリデーションはMVCで行う
 * VSCode Rest Client
 * ※spring bootの内臓tomcatで起動する場合はURLにコンテキストパスは除外しないとアクセスできない
 *   以下の「/remind」を除外
 *  GET http://localhost:8080/remind/v20241209/authentication/get/2
 *  GET http://localhost:8080/remind/v20241209/authentication/list
 *  DELETE http://localhost:8080/remind/v20241209/authentication/delete/2
 *  POST http://localhost:8080/remind/v20241209/authentication/post HTTP/1.1
 *  content-type: application/json
 *  	
 *  {
 *      "mailAddress": "waribikimeshi@gmail.com",
 *      "password": "{bcrypt}$2a$10$BooaIiRno2t5XKmsroWHG.HC9QqIa8Z4BUahMLaI8vRj3Oo4Tfyx.",
 *      "role": "ROLE_PROVIDER_CONTRACT_2",
 *      "expirationDate": "9999-12-31",
 *      "lock": "False",
 *      "enabled": "True",
 *      "version": "8",
 *      "createdUser": "anonymousUser",
 *      "createDatetime": "2020-08-15 11:58:00.000000000",
 *      "lastModifiedUser": "waribikimeshi@gmail.com",
 *      "lastModifiedDatetime": "2021-01-25 12:39:44.000000000"
 *  }
 *  PUT http://localhost:8080/remind/v20241209/authentication/put HTTP/1.1
 *  content-type: application/json
 *  	
 *  {
 *      "id": "14",
 *      "mailAddress": "waribikimeshi@gmail.com",
 *      "password": "{bcrypt}$2a$10$BooaIiRno2t5XKmsroWHG.HC9QqIa8Z4BUahMLaI8vRj3Oo4Tfyx.",
 *      "role": "ROLE_PROVIDER_CONTRACT_2",
 *      "expirationDate": "9999-12-31",
 *      "lock": "False",
 *      "enabled": "True",
 *      "version": "8"
 *  }
 *  なんかjsonの最後カンマなしにしないとエラーになる
 *  PATCH http://localhost:8080/remind/v20241209/authentication/patch HTTP/1.1
 *  content-type: application/json
 *  
 *  {
 *      "id": "13",
 *      "mailAddress": "hogehoge@gmail.com",
 *      "version": "9"
 *  } *  
 *
 *
 */
@RestController
@RequestMapping(Consts.REST_URL_BASE)
public class AuthenticationRestController {

	public static final Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

	@Autowired
	private AuthenticationService authenticationService;

//	@GetMapping()
//	@ResponseStatus(HttpStatus.OK)
//	public String top() throws Exception {
//		logger.info("top開始");
//
//
//		logger.info("top終了");
//		return "hello";
//	}

	//remark:パスパラメータのバリデーションは404で返るので行っても通らない
	@ApiOperation(value = "認証マスタを１件検索する。")
	@GetMapping(Consts.REST_URL_AUTHENTICATION_GET + Consts.REST_URL_PASSPARAMETER_ID)
	@ResponseStatus(HttpStatus.OK)
	public AuthenticationModel get(@PathVariable Long id) throws Exception {
		logger.info("get開始");

		AuthenticationModel model = new AuthenticationModel();
		model.setId(id);
		
		 //サービス
		AuthenticationModel result = authenticationService.findById(model);

		logger.info("get終了");
		return result;
	}


	@ApiOperation(value = "認証マスタを全件検索する。")
	@GetMapping(Consts.REST_URL_AUTHENTICATION_LIST)
	@ResponseStatus(HttpStatus.OK)
	public List<AuthenticationModel> list() throws Exception {

		logger.info("list開始");

		List<AuthenticationModel> result = new ArrayList<AuthenticationModel>();

		 //サービス
		result = authenticationService.findAll();

		logger.info("list終了");
        return result;
	}

	@ApiOperation(value = "認証マスタへ登録する。")
	@PostMapping(Consts.REST_URL_AUTHENTICATION_POST)
	@ResponseStatus(HttpStatus.CREATED)
	public AuthenticationModel post(
			Locale locale
			, @Validated(POSTRequired.class) @RequestBody  AuthenticationModel model
			, BindingResult bindingResult) throws Exception {
		logger.info("post開始");
		
    	//バリデーション
	    if(bindingResult.hasErrors()) {
	    	throw new BadRequestException(bindingResult);
	    }
		
		AuthenticationModel result = new AuthenticationModel();

		 //サービス
		result = authenticationService.insert(model);

		logger.info("post終了");
		 return result;
	}

	@ApiOperation(value = "認証マスタを全体更新する。")
	@PutMapping(Consts.REST_URL_AUTHENTICATION_PUT)
	@ResponseStatus(HttpStatus.OK)
    public AuthenticationModel put(
    		Locale locale
    		, @Validated(value = {PUTRequired.class}) @RequestBody AuthenticationModel model
    		, BindingResult bindingResult) throws Exception {
		logger.info("put開始");
		
    	//バリデーション
	    if(bindingResult.hasErrors()) {
	    	throw new BadRequestException(bindingResult);
	    }
		AuthenticationModel result = new AuthenticationModel();

		 //サービス
		 result = authenticationService.update(model);
		 //サービスでトランザクション切ってるのでcommit後最新取得
		 result = authenticationService.findById(result);

		logger.info("put終了");
		return result;
    }

	@ApiOperation(value = "認証マスタを部分更新する。")
	@PatchMapping(Consts.REST_URL_AUTHENTICATION_PATCH)
	@ResponseStatus(HttpStatus.OK)
    public AuthenticationModel patch(
    		Locale locale
    		, @Validated(value = {PATCHRequired.class}) @RequestBody AuthenticationModel model
    		, BindingResult bindingResult) throws Exception {
		logger.info("patch開始");
		
    	//バリデーション
	    if(bindingResult.hasErrors()) {
	    	throw new BadRequestException(bindingResult);
	    }
		AuthenticationModel result = new AuthenticationModel();

		 //サービス
		 result = authenticationService.update(model);
		 //サービスでトランザクション切ってるのでcommit後最新取得
		 result = authenticationService.findById(result);


		logger.info("patch終了");
		return result;
    }

	//remark:パスパラメータのバリデーションは404で返るので行っても通らない
	@ApiOperation(value = "認証マスタから削除する。")
	@DeleteMapping(Consts.REST_URL_AUTHENTICATION_DELETE + Consts.REST_URL_PASSPARAMETER_ID)
	@ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable Long id) throws Exception {
		logger.info("delete開始");

		AuthenticationModel model = new AuthenticationModel();
		model.setId(id);

		 //ビジネス
		authenticationService.delete(model);

		return HttpStatus.OK.getReasonPhrase();
	}

}
