package com.raqtpie.springsecuritydemo.service;

public interface TokenBlackListService {
    void addTokenToBlackList(String token);

    boolean isTokenBlacklisted(String token);
}
