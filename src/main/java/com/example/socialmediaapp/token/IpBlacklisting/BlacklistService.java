package com.example.socialmediaapp.token.IpBlacklisting;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BlacklistService {

    private Set<String> blacklist = new HashSet<>();

    public boolean isBlacklisted(String ipAddress) {
        return blacklist.contains(ipAddress);
    }

    public void addToBlacklist(String ipAddress) {
        blacklist.add(ipAddress);
    }

    public void removeFromBlacklist(String ipAddress) {
        blacklist.remove(ipAddress);
    }

}