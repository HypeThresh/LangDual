package com.example.langdual;

import com.example.langdual.models.api_response;

public interface OnFetchDataListener {
    void onFetchDataSuccess(api_response apiResponse, String message);
    void onError(String message);
}
