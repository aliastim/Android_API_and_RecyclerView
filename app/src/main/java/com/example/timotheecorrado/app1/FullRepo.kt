package com.example.timotheecorrado.app1

/**
 * Created by timotheecorrado on 17/10/2018.
 */

data class FullRepo (
        val id: Long,
        val name: String,
        val owner: Owner
        //val repoCommits: RepoCommits
)

data class Owner(
        val login: String,
        val avatar_url: String,
        val id : Int
)
/*
data class RepoCommits (
        val sha : Long
)*/