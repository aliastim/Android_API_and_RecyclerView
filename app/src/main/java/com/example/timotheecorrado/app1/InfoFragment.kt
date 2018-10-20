package com.example.timotheecorrado.app1

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.fragment_info.*

/**
 * Created by timotheecorrado on 17/10/2018.
 */

class InfoFragment : Fragment() {

    companion object {
        fun create(repo: FullRepo): InfoFragment {
            val fragment = InfoFragment()
            val args = Bundle()
            args.putString("name", repo.name)
            args.putLong("id", repo.id)
            args.putString("avatarUrl", repo.owner.avatar_url)
            args.putInt("ownerId", repo.owner.id)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getLong("id")

        val ownerId = arguments!!.getInt("ownerId")

        val avatarUrl = arguments?.getString("avatarUrl")

        val name = arguments!!.getString("name")

        title0.text = "id : " + id.toString()

        title.text = name

        Glide.with(this).load(avatarUrl).into(image)

        title2.text= "Owner id : " + ownerId.toString()

    }


}
