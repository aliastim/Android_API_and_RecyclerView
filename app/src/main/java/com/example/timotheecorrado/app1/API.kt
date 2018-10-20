package com.example.timotheecorrado.app1

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by timotheecorrado on 16/10/2018.
 */
interface API {

    @GET("/users/aosp-mirror/repos")
    fun getRepos() : Call<List<Repo>>

    @GET("/repos/aosp-mirror/{name}")
    fun getFullRepo(@Path("name")name:String): Call<FullRepo>

    /*@GET("/repos/aosp-mirror/{name}/commits")
    fun getRepoCommits(@Path("name")name:String): Call<RepoCommits>*/

    @GET("/repos/aosp-mirror/{name}/commits")
    fun getRepoCommits(@Path("name")name:String): Call<List<RepoCommits>>
}