package com.dtvn.foodorderbackend.service;

import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

@Service
public class BIDVService {
    String sandboxLink = "https://openapi.bidv.com.vn/bidv/sandbox/open-banking/ibank/chase-message";
    final String accessCode = "AAKqJPm_Wwoefa5wVAa-hm1iN8S9b0vDYSnZCFPaLDu3yXTilBPhAxhiI0UcHBrTlDG6-3gU6KciuyjRP7EieGfy8EaI0NhiVITGpJU5BeN_MKLOy15FNfvz2UU3VMC-wXeU8eRqQ0bGHsblQ8Q_GK8E";

    static class HTTPGetWithBIDVHeader extends HttpGet{
        HTTPGetWithBIDVHeader(String url){

        }
    }
}
