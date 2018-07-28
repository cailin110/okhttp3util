package com.common.http.okhttp.builder;

import com.common.http.okhttp.OkHttpUtils;
import com.common.http.okhttp.request.OtherRequest;
import com.common.http.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
