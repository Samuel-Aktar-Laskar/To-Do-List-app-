package com.example.todolistappforwillingsgalk.util

interface EntityMapper<Entity, DomainModel> {
    fun mapToEntity(domainModel: DomainModel): Entity
    fun mapFromEntity(entity: Entity) : DomainModel
}

/*
1- 2774  pixe 747
        2- 3655  pixel per cm sq = 1008

        24inch  2268 sq cm
        pixel per cm sq = 914*/
