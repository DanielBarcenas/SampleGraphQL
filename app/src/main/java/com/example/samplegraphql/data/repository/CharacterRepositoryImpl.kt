package com.example.samplegraphql.data.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.example.samplegraphql.data.network.RickAndMortyApi
import com.example.samplegraphql.queries.CharacterQuery
import com.example.samplegraphql.queries.CharactersListQuery
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val webService: RickAndMortyApi
) : CharacterRepository {

    override suspend fun queryCharactersList(): ApolloResponse<CharactersListQuery.Data> {
        return webService.getApolloClient().query(CharactersListQuery()).execute()
    }

    override suspend fun queryCharacter(id: String): ApolloResponse<CharacterQuery.Data> {
        return webService.getApolloClient().query(CharacterQuery(id)).execute()
    }

}