package com.winter.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 一些常量
 */
public class Const {

    public static  final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String PHONE = "phone";

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DSC = Sets.newHashSet("price_desc","price_asc");
    }


    public enum ProductStatusEnum {
        ON_SALE(1,"在线");

        private String value;
        private int code;
        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
