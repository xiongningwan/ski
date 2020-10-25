package com.yb.core.net;

public interface HttpCode {
    /**
     * HTTP状态码
     */
     int BAD_REQUEST = 400;
     int UNAUTHORIZED = 401;
     int FORBIDDEN = 403;
     int NOT_FOUND = 404;
     int METHOD_NOT_ALLOWED = 405;
     int REQUEST_TIMEOUT = 408;
     int CONFLICT = 409;
     int PRECONDITION_FAILED = 412;
     int INTERNAL_SERVER_ERROR = 500;
     int BAD_GATEWAY = 502;
     int SERVICE_UNAVAILABLE = 503;
     int GATEWAY_TIMEOUT = 504;
}
