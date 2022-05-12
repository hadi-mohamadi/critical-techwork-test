package com.critical_techworks.article_domain.util

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T

    fun mapToDomainList(modelList: List<T>): List<DomainModel>

    fun mapFromDomainList(domainModelList: List<DomainModel>): List<T>
}