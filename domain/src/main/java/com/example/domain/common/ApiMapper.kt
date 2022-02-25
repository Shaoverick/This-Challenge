package com.example.domain.common

interface ApiMapper<Model, ApiModel> {

    fun toModel(entity: ApiModel?): Model?

    fun toApiModel(model: Model?): ApiModel?

    fun toModelList(entityList: List<ApiModel>): List<Model> {
        val modelList = ArrayList<Model>()

        entityList.forEach { entity: ApiModel ->
            val model: Model? = toModel(entity)
            if (model != null)
                modelList.add(model)
        }

        return modelList
    }

    fun toApiModelList(apiModelList: List<Model>): List<ApiModel> {
        val roomEntityList = ArrayList<ApiModel>()

        apiModelList.forEach { model: Model ->
            val entity: ApiModel? = toApiModel(model)
            if (entity != null)
                roomEntityList.add(entity)
        }

        return roomEntityList
    }

}
