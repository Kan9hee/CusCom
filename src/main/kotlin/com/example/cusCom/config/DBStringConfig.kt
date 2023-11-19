package com.example.cusCom.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "dbString")
class DBStringConfig {
    lateinit var mysql: MysqlProperties
    lateinit var mongodb: MongodbProperties
    lateinit var blob: BlobProperties

    class MysqlProperties {
        lateinit var id: String
        lateinit var name: String
        lateinit var table: TableProperties

        class TableProperties {
            lateinit var case: String
            lateinit var cpu: String
            lateinit var cpuCooler: String
            lateinit var dataStorage: String
            lateinit var graphicsCard: String
            lateinit var memory: String
            lateinit var motherBoard: String
            lateinit var powerSupply: String
            lateinit var cpuSocket: String
            lateinit var motherboardFormFactor: String
            lateinit var user: String
        }
    }

    class MongodbProperties {
        lateinit var id: String
        lateinit var mongoClass: String
        var deleteFailValue: Long = 0L
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
}