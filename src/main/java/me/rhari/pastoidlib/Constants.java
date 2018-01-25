package me.rhari.pastoidlib;

final class Constants {

    private Constants() {

    }

    static final String RESPONSE_PREFIX_BAD_API_REQUEST = "Bad API request";

    static final class Urls {

        private Urls() {

        }

        static final String BASE_URL = "http://pastebin.com/";
        static final String PATH_API_LOGIN = "api/api_login.php";
        static final String PATH_URL_API_POST = "api/api_post.php";
        static final String PATH_URL_API_RAW = "api/api_raw.php";
        static final String PATH_URL_RAW_PASTE = "raw/{pasteKey}";
    }

    static final class ApiOptions {

        private ApiOptions() {

        }

        static final String PASTE = "paste";
        static final String LIST = "list";
        static final String TRENDS = "trends";
        static final String DELETE = "delete";
        static final String USER_DETAILS = "userdetails";
        static final String SHOW_PASTE = "show_paste";
    }

    static final class Headers {

        private Headers() {

        }

        static final String FIELD_NAME_API_OPTION = "api-option";
        static final String FIELD_NAME_AUTHENTICATION_REQUIRED = "authentication-required";

        static final String API_OPTION_PASTE = FIELD_NAME_API_OPTION + ": " + ApiOptions.PASTE;
        static final String API_OPTION_LIST = FIELD_NAME_API_OPTION + ": " + ApiOptions.LIST;
        static final String API_OPTION_TRENDS = FIELD_NAME_API_OPTION + ": " + ApiOptions.TRENDS;
        static final String API_OPTION_DELETE = FIELD_NAME_API_OPTION + ": " + ApiOptions.DELETE;
        static final String API_OPTION_USER_DETAILS = FIELD_NAME_API_OPTION + ": "
                + ApiOptions.USER_DETAILS;
        static final String API_OPTION_SHOW_PASTE = FIELD_NAME_API_OPTION + ": "
                + ApiOptions.SHOW_PASTE;

        static final String AUTHENTICATION_REQUIRED_TRUE = FIELD_NAME_AUTHENTICATION_REQUIRED
                + ": true";
    }

    static final class Fields {

        private Fields() {

        }

        static final String API_DEV_KEY = "api_dev_key";
        static final String API_USER_KEY = "api_user_key";
        static final String API_OPTION = "api_option";
        static final String API_USER_NAME = "api_user_name";
        static final String API_USER_PASSWORD = "api_user_password";
        static final String API_PASTE_CODE = "api_paste_code";
        static final String API_PASTE_NAME = "api_paste_name";
        static final String API_PASTE_FORMAT = "api_paste_format";
        static final String API_PASTE_PRIVATE = "api_paste_private";
        static final String API_PASTE_EXPIRE_DATE = "api_paste_expire_date";
        static final String API_RESULTS_LIMIT = "api_results_limit";
        static final String API_PASTE_KEY = "api_paste_key";
        static final String RAW_OUTPUT_PASTE_KEY = "pasteKey";
    }
}
