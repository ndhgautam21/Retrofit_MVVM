package com.example.retrofitmvvm.api

import com.example.retrofitmvvm.models.Users
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.Objects

interface UsersRequest {

    @GET(value = "/users/get_all")
    suspend fun getUsers() : Response<List<Users>>

    @GET(value = "/users/get_id/{id}")
    suspend fun getUserById(@Path("id") id : Int) : Response<Users>

    @POST(value = "/users/create")
    suspend fun createUser(@Body users: Users) : Response<Users>

    @PUT(value = "/users/update/{id}")
    suspend fun updateUser(@Body users: Users, @Path("id") int: Int) : Response<Users>

    @DELETE(value = "users/{id}")
    suspend fun deleteUser(@Path("id") int: Int) : Response<Objects>
}