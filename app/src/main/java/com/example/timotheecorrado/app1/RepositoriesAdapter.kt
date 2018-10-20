package com.example.timotheecorrado.app1

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*

class RepositoriesAdapter(private val repos: List<Repo>) : RecyclerView.Adapter<RepositoriesAdapter.MyViewHolder>() {

    //Transforme le item.xml en objet
    //Inflater = récupère le layout (xml) et le transforme en objet
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return MyViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return repos.count()
    }

    // Par rapport au nombre d'informations appelées, multiplie les textview de item.xml et remplace le texte du textview par la valeur appelée
    // Bind = lié
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repo = repos[position]
        holder.itemView.textView.text = repo.name

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, RepoActivity::class.java)

            //intent.putExtra("valeurstring", "test")
            //intent.putExtra("valeurint", "7")

            //intent.putExtra("info", repo.full_name)
            //intent.putExtra("commits", repo.commits)

            intent.putExtra("name", repo.name)

            holder.itemView.context.startActivity(intent)
        }
    }


    class MyViewHolder(val textView: View) : RecyclerView.ViewHolder(textView)


}
