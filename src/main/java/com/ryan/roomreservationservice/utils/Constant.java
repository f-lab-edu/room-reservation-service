package com.ryan.roomreservationservice.utils;


public class Constant {
    public final static String REGEXP_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*\\d).{8,16}$";
    public final static int MIN_SIZE_PASSWORD = 8;
    public final static int MAX_SIZE_PASSWORD = 16;
    public final static String REGEXP_PHONE = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
}
