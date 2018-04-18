package com.whl.blog.web.common;

/**
 * Created by whling on 2017/6/8.
 */
public class Constants {

    public final static String MDC_URI = "mdc_uri";

    public final static String MDC_REQ_ID = "mdc_req_id";

    public final static String MDC_USR_OPR_LOG_ID = "mdc_opr_id";

    public final static String MDC_START_TIME = "mdc_start_time";

    public final static String VIEW_URI_HEAD = "/view";


    /**
     * ReCode
     */
    public final static String HEADER_RESPONSE_CODE = "Header-Response-Code";

    /**
     * ReMessage
     */
    public static final String SERVER_ERROR = "服务繁忙，请稍后再试！";

    public static final String ARGUMENT_ERROR = "参数异常！";

    /**
     * 批量代付
     */
    public static final String BATCHPAYFOR = "19";

    /**
     * 批量代收
     */
    public static final String BATCHPAYRECEIPT = "18";

    /**
     * 0 对公0对应TB
     */
    public static final String CARDFLAGPUB = "TB";
    /**
     * 1 对私 对应TC
     */
    public static final String CARDFLAGPRI = "TC";


    public static final String ABOUT = "about";
    public static final String MAIN_PAGE = "mainPage";
    public static final String WEB_APP_DTO = "webAppDto";
    public static final String ARTICLES = "articles";
    public static final String ARTICLE = "article";
    public static final String PAGER = "pager";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String CURRENT_USER = "currentUser";
    public static final String RETURN_INFO = "info";
    public static final Integer DAY_TIME = 1 * 60 * 60 * 24;
    public static final String CATEGORIES = "categories";
    public static final String CATEGORY = "category";
    public static final String ERROR = "error";

}
