package com.common.http.okhttp.builder;

import com.common.http.okhttp.request.PutJsonRequest;
import com.common.http.okhttp.request.RequestCall;

import okhttp3.RequestBody;

/**
 * DELETE、PUT、PATCH等其他方法的Json请求
 */
public class PutJsonBuilder extends OkHttpRequestBuilder<PutJsonBuilder>
{
    private RequestBody requestBody;
    private String method;
    private String content;

    public PutJsonBuilder(String method)
    {
        this.method = method;
    }

    public PutJsonBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PutJsonRequest(requestBody, content, method, url, tag, params, headers,id).build();
    }

    public PutJsonBuilder requestBody(RequestBody requestBody)
    {
        this.requestBody = requestBody;
        return this;
    }

    public PutJsonBuilder requestBody(String content)
    {
        this.content = content;
        return this;
    }


}
