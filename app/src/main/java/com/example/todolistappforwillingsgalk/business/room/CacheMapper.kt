package com.example.todolistappforwillingsgalk.business.room

import com.example.todolistappforwillingsgalk.business.model.Task
import com.example.todolistappforwillingsgalk.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<CacheTask, Task>{
    override fun mapToEntity(domainModel: Task): CacheTask {
        TODO("Not yet implemented")

    }

    override fun mapFromEntity(entity: CacheTask): Task {
        return Task(
            task = entity.task,
            id = entity.serialNo
        )
    }

    fun mapFromEntityList(entities : List<CacheTask>): List<Task>{
        return entities.map {
            mapFromEntity(it)
        }
    }
}