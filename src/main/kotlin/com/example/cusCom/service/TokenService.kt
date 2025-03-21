package com.example.cusCom.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class TokenService(@Qualifier("blacklistRedisTemplate")
                   private val blacklistRedisTemplate: StringRedisTemplate,
                   @Qualifier("refreshRedisTemplate")
                   private val refreshRedisTemplate: StringRedisTemplate) {

    private val blacklistOps: ValueOperations<String, String> = blacklistRedisTemplate.opsForValue()
    private val refreshOps: ValueOperations<String, String> = refreshRedisTemplate.opsForValue()

    fun saveRefreshToken(tokenValue:String,accountId:String) {
        val key = "refresh:$tokenValue"
        refreshOps.set(key, accountId, 7, TimeUnit.DAYS)
    }

    fun getAccountIdFromRefreshToken(tokenValue:String): String? {
        return refreshOps.get("refresh:$tokenValue")
    }

    fun removeRefreshToken(tokenValue:String) {
        refreshRedisTemplate.delete("refresh:$tokenValue")
    }

    fun saveBlacklistToken(tokenValue:String) {
        val key = "blacklist:$tokenValue"
        blacklistOps.set(key, System.currentTimeMillis().toString(), 86400, TimeUnit.SECONDS)
    }

    fun isTokenBlacklisted(tokenValue:String): Boolean {
        return blacklistRedisTemplate.hasKey("blacklist:$tokenValue")
    }

    fun removeBlacklistToken(tokenValue:String) {
        blacklistRedisTemplate.delete("blacklist:$tokenValue")
    }
}