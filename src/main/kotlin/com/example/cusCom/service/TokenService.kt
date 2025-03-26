package com.example.cusCom.service

import com.example.cusCom.config.DBStringConfig
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class TokenService(@Qualifier("blacklistRedisTemplate")
                   private val blacklistRedisTemplate: StringRedisTemplate,
                   @Qualifier("refreshRedisTemplate")
                   private val refreshRedisTemplate: StringRedisTemplate,
                   private val dbStringConfig: DBStringConfig) {

    private val blacklistOps: ValueOperations<String, String> = blacklistRedisTemplate.opsForValue()
    private val refreshOps: ValueOperations<String, String> = refreshRedisTemplate.opsForValue()

    fun saveRefreshToken(tokenValue:String,accountId:String) {
        val key = "${dbStringConfig.redis.refreshTokenKey}:$tokenValue"
        refreshOps.set(key, accountId, 7, TimeUnit.DAYS)
    }

    fun getAccountIdFromRefreshToken(tokenValue:String): String? {
        return refreshOps.get("${dbStringConfig.redis.refreshTokenKey}:$tokenValue")
    }

    fun removeRefreshToken(tokenValue:String) {
        refreshRedisTemplate.delete("${dbStringConfig.redis.refreshTokenKey}:$tokenValue")
    }

    fun saveBlacklistToken(tokenValue:String) {
        val key = "${dbStringConfig.redis.blacklistTokenKey}:$tokenValue"
        blacklistOps.set(key, System.currentTimeMillis().toString(), 86400, TimeUnit.SECONDS)
    }

    fun isTokenBlacklisted(tokenValue:String): Boolean {
        return blacklistRedisTemplate.hasKey("${dbStringConfig.redis.blacklistTokenKey}:$tokenValue")
    }

    fun removeBlacklistToken(tokenValue:String) {
        blacklistRedisTemplate.delete("${dbStringConfig.redis.blacklistTokenKey}:$tokenValue")
    }
}