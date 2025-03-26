package com.example.cusCom.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "db-string")
class DBStringConfig {
    lateinit var mysql: MysqlProperties
    lateinit var mongodb: MongodbProperties
    lateinit var blob: BlobProperties
    lateinit var redis: RedisProperties

    class MysqlProperties {
        lateinit var id: String
        lateinit var name: String
        lateinit var parts: PartsProperties

        class PartsProperties {
            lateinit var case: String
            lateinit var cpu: String
            lateinit var cpuCooler: String
            lateinit var dataStorage: String
            lateinit var graphicsCard: String
            lateinit var memory: String
            lateinit var motherBoard: String
            lateinit var powerSupply: String
            lateinit var user: String
        }
    }

    class MongodbProperties {
        lateinit var id: String
        lateinit var userName: String
        lateinit var likeCount: String
        lateinit var mongoClass: String
        lateinit var collection: CollectionProperties

        class CollectionProperties {
            lateinit var post: String
            lateinit var comment: String
            lateinit var estimate: String
        }
    }

    class BlobProperties {
        lateinit var fileFormat: String
        lateinit var containerName: String
    }

    class RedisProperties {
        lateinit var refreshTokenKey: String
        lateinit var blacklistTokenKey: String
    }
}