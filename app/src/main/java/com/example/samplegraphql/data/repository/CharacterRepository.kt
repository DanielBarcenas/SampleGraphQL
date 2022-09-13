package com.example.samplegraphql.data.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.example.samplegraphql.queries.CharacterQuery
import com.example.samplegraphql.queries.CharactersListQuery

interface CharacterRepository {

    suspend fun queryCharactersList(): ApolloResponse<CharactersListQuery.Data>

    suspend fun queryCharacter(id: String): ApolloResponse<CharacterQuery.Data>

}