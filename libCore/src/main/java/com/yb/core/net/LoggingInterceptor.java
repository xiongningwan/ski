package com.yb.core.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor {

    private Map mHeaderMap;

    public LoggingInterceptor(Map headerMap) {
        this.mHeaderMap = headerMap;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        if(!TextUtils.isEmpty(mToken)) {
//            builder.addHeader("token", mToken);
//        }
//        if(!TextUtils.isEmpty(mAuthorization)) {
//            builder.addHeader("authorization", mAuthorization);
//        }
//        builder.addHeader("device", "3");
        if(mHeaderMap != null) {
            Iterator<Map.Entry<String, String>> entries = mHeaderMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        injectPublicParams(request, builder);
        request = builder.build();
        return processResponse(chain.proceed(request));
    }

    private void injectPublicParams(Request request, Request.Builder builder) {
        Map<String, String> paramsMap = getParamMap();
        switch (request.method()) {
            case "GET":
                injectParamsIntoUrl(request, builder, paramsMap);
                break;
            case "POST":
                injectParamsToBody(request, builder, paramsMap);
                break;
        }
    }


    private void injectParamsToBody(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }

        RequestBody formBody = formBodyBuilder.build();
        String postBodyString = bodyToString(request.body());
        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
        MediaType mediaType = request.body().contentType();
        requestBuilder.post(RequestBody.create(postBodyString, mediaType));
    }


    //访问网络之后，处理Response(这里没有做特别处理)
    private Response processResponse(Response response) {
        doHttpCode(response);
        return response;
    }

    /**
     * 处理http code
     *
     * @param response
     */
    private void doHttpCode(Response response) {
        // 处理http code
        int statusCode = response.code();
        if (statusCode != 200) {
//            throw new CustomHttpException(mContext, statusCode, iDoHttpExceptionListener);
            ResponseBody body = response.body();
            String s = "";
            try {
                assert body != null;
                s = body.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new CustomHttpException(statusCode, s);
        }
    }


    private Map<String, String> getParamMap() {
        Map paraMap = new HashMap();

//        paraMap.put("app_version", PackageInfoUtil.getVersionName(mContext));
//        paraMap.put("app_type", ConstantValue.APP_TYPE);

        return paraMap;
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }


    // func to inject params into url
    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        requestBuilder.url(httpUrlBuilder.build());
    }

}