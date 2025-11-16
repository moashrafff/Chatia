package com.chatia.data.mapper

/**
 * Generic interface for mapping data layer models (DTOs) to domain layer models.
 * This interface ensures a consistent mapping pattern across all features.
 *
 * @param T The source type (typically a DTO from the data layer)
 * @param R The target type (typically a domain model)
 */
interface Mapper<in T, out R> {
    /**
     * Maps a data layer model to a domain model.
     *
     * @param from The source object to map from
     * @return The mapped domain model
     */
    fun toDomain(from: T): R
}

