package com.example.timotheecorrado.app1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_repo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val param1 = this.getIntent().getStringExtra("valeurstring")
        //val param2 = this.getIntent().getIntExtra("valeurint", 0)

      //  textView2.text = param1

        setContentView(R.layout.activity_repo)

        val name = intent.getStringExtra("name")

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(API::class.java)

        val call = api.getFullRepo(name)

        call.enqueue(object : Callback<FullRepo> {
            override fun onFailure(call: Call<FullRepo>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<FullRepo>, response: Response<FullRepo>)
            {
                val repo = response.body()!!
                Log.i("FULL REPO", repo.toString())

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame, InfoFragment.create(repo))
                        .commit()

                navigation.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId)
                    {
                        R.id.navigation_info ->{
                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.frame, InfoFragment.create(repo))
                                    .commit()
                        }

                        R.id.navigation_commits -> {
                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.frame, CommitsFragment.create(repo))
                                    .commit()
                        }
                    }
                    true
                }
            }
        })

    }



}
