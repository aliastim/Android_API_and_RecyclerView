package com.example.timotheecorrado.app1

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_commits.*
import kotlinx.android.synthetic.main.fragment_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by timotheecorrado on 17/10/2018.
 */
class CommitsFragment : Fragment() {


/*
    companion object {
        fun create(repo: FullRepo): CommitsFragment {
            val fragment = CommitsFragment()
            val args = Bundle()
            args.putString("name", repo.name)
            args.putString("avatarUrl", repo.owner.avatar_url)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments!!.getString("name")

        title.text = name
    }*/

    companion object {
        fun create(repo: FullRepo): CommitsFragment {
            val fragment = CommitsFragment()
            val args = Bundle()
            args.putString("name", repo.name)
            //args.putString("avatarUrl", repo.owner.avatar_url)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments!!.getString("name")

        titleFrag.text = name

        val lm = LinearLayoutManager(this.context)
        recyclerView2.layoutManager = lm
        recyclerView2.addItemDecoration(DividerItemDecoration(this.context, lm.orientation))

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(API::class.java)

        val call = api.getRepoCommits(name)

        call.enqueue(object: Callback<List<RepoCommits>> {
            override fun onFailure(call: Call<List<RepoCommits>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<List<RepoCommits>>, response: Response<List<RepoCommits>>)
            {
                val repos = response.body()!!
                for (repo in repos)
                    Log.i("REPO", repo.toString())
                recyclerView2.adapter = CommitsAdapter(repos)
            }
        })
    }


}
