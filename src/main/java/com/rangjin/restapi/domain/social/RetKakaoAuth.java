package com.rangjin.restapi.domain.social;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RetKakaoAuth {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;

}