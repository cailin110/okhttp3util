package com.common.http.okhttp.builder;

import android.net.Uri;
import android.util.Log;

import com.common.http.okhttp.request.GetJsonRequest;
import com.common.http.okhttp.request.RequestCall;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GetJsonBuilder extends OkHttpRequestBuilder<GetJsonBuilder> implements HasParamsable {
    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }

        return new GetJsonRequest(url, tag, params, headers, id).build();
    }

    protected String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        String str=builder.build().toString();
//        Log.e("xiaocaitest", "geturl="+str);
        return str;
    }


    @Override
    public GetJsonBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public GetJsonBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }


}
