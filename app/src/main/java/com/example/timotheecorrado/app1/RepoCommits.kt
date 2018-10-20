package com.example.timotheecorrado.app1

/**
 * Created by timotheecorrado on 17/10/2018.
 */

data class RepoCommits (
       val sha : String,
       val commit : Commit
)

data class Commit (
        val name : String,
        val author: Author,
        val message : String
)

data class Author (
        val name : String
)