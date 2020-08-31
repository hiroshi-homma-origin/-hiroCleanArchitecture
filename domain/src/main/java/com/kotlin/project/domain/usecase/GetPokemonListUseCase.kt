package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.response.PokeList
import com.kotlin.project.domain.repository.GetPokemonListRepository
import javax.inject.Inject

interface GetPokemonListUseCase {
    suspend fun getList(jsonName: String): List<PokeList>
}

class GetPokemonListUseCaseImpl @Inject constructor(
    private val getPokemonListRepository: GetPokemonListRepository
) : GetPokemonListUseCase {
    override suspend fun getList(jsonName: String): List<PokeList> {
        runCatching {
            getPokemonListRepository.getPokemonList(jsonName)
        }.fold(
            onSuccess = {
                return it
            },
            onFailure = {
                return listOf()
            }
        )
    }
}
