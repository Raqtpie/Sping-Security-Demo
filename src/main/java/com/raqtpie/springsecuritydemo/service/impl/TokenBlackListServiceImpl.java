package com.raqtpie.springsecuritydemo.service.impl;

import com.raqtpie.springsecuritydemo.service.TokenBlackListService;
import com.raqtpie.springsecuritydemo.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class TokenBlackListServiceImpl implements TokenBlackListService {

    private final List<String> tokenBlackList;

    @PostConstruct
    private void init() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new tokenBlackListHandler(), 0, 1000 * 60 * 60 * 12, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    private class tokenBlackListHandler implements Runnable {
        @Override
        public void run() {
            try {
                tokenBlackList.removeIf(JwtUtil::isTokenExpired);
                Thread.sleep(1000 * 60 * 60 * 12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tokenBlackList.clear();
        }
    }

    public TokenBlackListServiceImpl(List<String> tokenBlackList) {
        this.tokenBlackList = tokenBlackList;
    }

    @Override
    public void addTokenToBlackList(String token) {
        tokenBlackList.add(token);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return tokenBlackList.contains(token);
    }
}
