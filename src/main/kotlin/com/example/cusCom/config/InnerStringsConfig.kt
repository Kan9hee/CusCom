package com.example.cusCom.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "inner-string")
class InnerStringsConfig {
    lateinit var request: RequestConfig
    lateinit var parts: PartsConfig
    lateinit var property: PropertyConfig
    lateinit var postListMapper: PostListMapperConfig

    class RequestConfig {
        lateinit var estimate: EstimateConfig
        lateinit var post: PostConfig
        lateinit var search: SearchConfig
        lateinit var partsType: String
        lateinit var commentData: String
        lateinit var targetName: String
        lateinit var encoding: String

        class EstimateConfig {
            lateinit var updated: String
            lateinit var data: String
            lateinit var id: String
        }

        class PostConfig {
            lateinit var createdAt: String
            lateinit var data: String
            lateinit var id: String
        }

        class SearchConfig {
            lateinit var data: String
            lateinit var maxNumContent: String
            lateinit var currentPage: String
        }
    }

    class PartsConfig {
        lateinit var case: String
        lateinit var cpu: String
        lateinit var cpuCooler: String
        lateinit var dataStorage: String
        lateinit var graphicsCard: String
        lateinit var memory: String
        lateinit var motherBoard: String
        lateinit var powerSupply: String
        lateinit var cpuSocket: String
        lateinit var name: String
        var memoryInterval: Int = 0
    }

    class PropertyConfig {
        lateinit var responseOk: String
        lateinit var defaultImageUrl: String
        lateinit var imageUrl: String
        var imageWidth: Int = 0
        var imageHeight: Int = 0
        lateinit var userName: String
        lateinit var searchOption: String
        lateinit var searchKeyword: String
        lateinit var viewCount: String
        lateinit var commentCount: String
        var changeValue: Int = 0
        lateinit var postCheck: String
        lateinit var postSearchOption: PostSearchOptionConfig
        lateinit var like: LikeConfig
        lateinit var findOption: OptionConfig

        class PostSearchOptionConfig{
            lateinit var title: String
            lateinit var tags: String
            lateinit var parts: String
        }

        class LikeConfig {
            lateinit var increase: String
            lateinit var decrease: String
            var value: Int = 0
        }

        class OptionConfig{
            lateinit var name: String
            lateinit var id: String
        }
    }

    class PostListMapperConfig {
        lateinit var post: String
        lateinit var postList: String
        lateinit var pageCount: String
        lateinit var currentPage: String
        lateinit var postEstimate: String
        lateinit var commentList: String
    }

    class PostSearchOptionConfig{
        lateinit var title: String
        lateinit var tags: String
        lateinit var userName: String
    }
}