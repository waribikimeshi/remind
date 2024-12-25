package com.example.rest.common;

/**
 * 固定値クラス
 * @author hanada
 *
 */
public class Consts {

	/******************************************************
	 * RESTベースURL
	 * http://localhost:8080/remind/v20241209/authentication/list
	 * server.servlet.context-path=/api
	 ******************************************************/
    public static final String REST_URL_BASE = "v20241209";

	/******************************************************
	 * RESTURLパスパラメータ
	 ******************************************************/
    public static final String REST_URL_PASSPARAMETER_ID = "/{id}";
	/******************************************************
	 * REST個別URL
	 ******************************************************/
    public static final String REST_URL_AUTHENTICATION = "/authentication";
    public static final String REST_URL_AUTHENTICATION_GET = REST_URL_AUTHENTICATION +  "/get";
    public static final String REST_URL_AUTHENTICATION_LIST = REST_URL_AUTHENTICATION + "/list";
    public static final String REST_URL_AUTHENTICATION_POST = REST_URL_AUTHENTICATION +  "/post";
    public static final String REST_URL_AUTHENTICATION_PUT = REST_URL_AUTHENTICATION +  "/put";
    public static final String REST_URL_AUTHENTICATION_PATCH = REST_URL_AUTHENTICATION +  "/patch";
    public static final String REST_URL_AUTHENTICATION_DELETE = REST_URL_AUTHENTICATION +  "/delete";

    
	/******************************************************
	 * SPA COR 許可
	 ******************************************************/
    public static final String SPA_CORS_ORGINS = "http://localhost:3000";

}
