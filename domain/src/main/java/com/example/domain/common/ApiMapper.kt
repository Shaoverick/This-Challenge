package com.example.domain.common

interface ApiMapper<Entity, ApiModel> {

    fun toEntity(apiModel: ApiModel?): Entity?

    fun toApiModel(entity: Entity?): ApiModel?

    fun toEntityList(apiModelList: List<ApiModel>): List<Entity> {
        val entityList = ArrayList<Entity>()

        apiModelList.forEach { apiModel: ApiModel ->
            val entity: Entity? = toEntity(apiModel)
            if (entity != null)
                entityList.add(entity)
        }

        return entityList
    }

    fun toApiModelList(entityList: List<Entity>): List<ApiModel> {
        val apiModelList = ArrayList<ApiModel>()

        entityList.forEach { entity: Entity ->
            val apiModel: ApiModel? = toApiModel(entity)
            if (apiModel != null)
                apiModelList.add(apiModel)
        }

        return apiModelList
    }

}
