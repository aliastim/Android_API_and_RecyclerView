package com.example.timotheecorrado.app1

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_commits.view.*

class CommitsAdapter (private val repos: List<RepoCommits>) : RecyclerView.Adapter<CommitsAdapter.MyViewHolder>() {

    //Transforme le item_commits.xml en objet
    //Inflater = récupère le layout (xml) et le transforme en objet
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item_commits, parent, false)

        return MyViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return repos.count()
    }

    // Par rapport au nombre d'informations appelées, multiplie les textview de item_commits.xml et remplace le texte du textview par la valeur appelée
    // Bind = lié
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repo = repos[position]
        holder.itemView.textView3.text = repo.commit.author.name
        holder.itemView.textView4.text = repo.commit.message

        /*holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, CommitsFragment::class.java)

            intent.putExtra("name", repo.sha)

            holder.itemView.context.startActivity(intent)
        }*/
    }


    class MyViewHolder(val textView: View) : RecyclerView.ViewHolder(textView)


}
