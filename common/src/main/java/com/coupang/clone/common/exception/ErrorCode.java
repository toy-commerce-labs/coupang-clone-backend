package com.coupang.clone.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_EMAIL_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "?대찓???먮뒗 鍮꾨?踰덊샇媛 ?щ컮瑜댁? ?딆뒿?덈떎."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "?뚯썝??李얠쓣 ???놁뒿?덈떎."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "?대? ?ъ슜 以묒씤 ?대찓?쇱엥?덈떎."),
    REQUIRED_TERMS_NOT_AGREED(HttpStatus.BAD_REQUEST, "?꾩닔 ?쎄???紐⑤몢 ?숈쓽?댁빞 ?⑸땲??"),
    TERMS_NOT_FOUND(HttpStatus.NOT_FOUND, "議댁옱?섏? ?딅뒗 ?쎄??낅땲??"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "?좏슚?섏? ?딆? ?좏겙?낅땲??"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "留뚮즺???좏겙?낅땲??"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "?묎렐 沅뚰븳???놁뒿?덈떎."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "?낅젰媛믪씠 ?щ컮瑜댁? ?딆뒿?덈떎.");

    private final HttpStatus httpStatus;
    private final String message;
}

