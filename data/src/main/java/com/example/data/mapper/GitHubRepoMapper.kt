package com.example.data.mapper

import com.example.client.exception.ParseException
import com.example.domain.common.ApiMapper
import com.example.domain.model.apiModel.Owner
import com.example.domain.model.apiModel.RepoApiModel
import com.example.domain.model.entity.OwnerEntity
import com.example.domain.model.entity.RepoEntity
import javax.inject.Inject

class GitHubRepoMapper @Inject constructor(): ApiMapper<RepoEntity, RepoApiModel> {

    //region PROPERTIES ----------------------------------------------------------------------------
    @Inject lateinit var ownerMapper: OwnerMapper
    //endregion


    //region PUBLIC METHODS ------------------------------------------------------------------------
    override fun toEntity(apiModel: RepoApiModel?): RepoEntity? {
        return if (apiModel != null)
            RepoEntity(
                name = apiModel.name,
                description = apiModel.description,
                ownerEntity = ownerMapper.toEntity(apiModel.owner) ?: throw ParseException(),
                fork = apiModel.fork,
                htmlUrl = apiModel.htmlUrl
            )
        else
            null
    }

    override fun toApiModel(entity: RepoEntity?): RepoApiModel? {
        //Not used
        TODO("Not yet implemented")
    }
    //endregion

}


class OwnerMapper @Inject constructor(): ApiMapper<OwnerEntity, Owner> {

    //region PUBLIC METHODS ------------------------------------------------------------------------
    override fun toEntity(apiModel: Owner?): OwnerEntity? {
        return if (apiModel != null)
            OwnerEntity(
                login = apiModel.login,
                avatarUrl = apiModel.avatarUrl,
                htmlUrl = apiModel.htmlUrl
            )
        else
            null
    }

    override fun toApiModel(entity: OwnerEntity?): Owner? {
        return if (entity != null)
            Owner(
                login = entity.login,
                avatarUrl = entity.avatarUrl,
                htmlUrl = entity.htmlUrl
            )
        else
            null
    }
    //endregion

}