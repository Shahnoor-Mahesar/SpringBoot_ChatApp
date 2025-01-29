package com.learnspring.communication.service;

import com.learnspring.communication.entity.UserStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
@Service
public class UserStatusService {
    private final Map<String, UserStatus> userStatusMap = new ConcurrentHashMap<>();

    public void setStatus(String username, UserStatus status) {
        userStatusMap.put(username, status);
    }

    public UserStatus getStatus(String username) {
        return userStatusMap.getOrDefault(username, UserStatus.OFFLINE);
    }

    public void removeStatus(String username) {
        userStatusMap.remove(username);
    }
}

