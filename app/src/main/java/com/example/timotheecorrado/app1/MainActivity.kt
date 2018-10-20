package com.example.timotheecorrado.app1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

//https://translate.googleusercontent.com/translate_c?depth=1&hl=fr&prev=search&rurl=translate.google.com&sl=en&sp=nmt4&u=https://stackoverflow.com/questions/2039964/how-to-connect-android-emulator-to-the-internet&xid=17259,15700022,15700124,15700149,15700186,15700191,15700201,15700214&usg=ALkJrhgoqLvGzRIto8rWz3uUVmVYoorIiw
//https://api.github.com/repos/aosp-mirror/kernel_common/commits?fbclid=IwAR2FHapZIg1b3fl_BLfBgMCJmlqA6Di5Q53XM6Mr_-drudBJckJdY5LPXNs
//https://api.github.com/users/aosp-mirror/repos?fbclid=IwAR1oDaZ2Z32s7RtZUszck64ksSnCUFfDfQ8wq41HuDHTxs6VooYwzkf2m20

    //cd ~/Library/Android/sdk/tools
    //./emulator -avd Nexus_5X_API_27 -dns-server 8.8.8.8

//option + entrer pour avoir des suggestions d'imports

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = RepositoriesAdapter()

        val lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        recyclerView.addItemDecoration(DividerItemDecoration(this, lm.orientation))

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(API::class.java)

        val call = api.getRepos()



        //le call.enqueue permet de ne pas être dans le mainthread, après on récupère tout ce qu'il y dans l'API et on les transmet à l'adapter
        //l'adapter = repository
        call.enqueue(object: Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>)
            {
                val repos = response.body()!!
                for (repo in repos)
                    Log.i("REPO", repo.toString())
                recyclerView.adapter = RepositoriesAdapter(repos)
            }
        })


        }

    }
