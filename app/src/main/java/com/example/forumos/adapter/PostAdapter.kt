package estsetubal.ips.demontracaoForumosPosts.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forumos.R
import com.example.forumos.model.ForumosPost

class ForumosPostsAdapter(val listaForumosPosts : List<ForumosPost>, private val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<ForumosPostsAdapter.ForumosPostViewHolder>() {

    class ForumosPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tv_tituloPost)
        val mensagem : TextView = itemView.findViewById(R.id.tv_mensagemPost)
        val nomeUtilizador : TextView = itemView.findViewById(R.id.nomeUtilizador)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumosPostViewHolder {
        val ForumosPost = LayoutInflater.from(parent.context).inflate(R.layout.cv_post, parent, false)
        return ForumosPostViewHolder(ForumosPost)
    }

    override fun getItemCount(): Int {
        return listaForumosPosts.size
    }

    override fun onBindViewHolder(holder: ForumosPostViewHolder, position: Int) {
        val forumosPost : ForumosPost = listaForumosPosts[position]
        holder.titulo.text = forumosPost.titulo
        holder.mensagem.text = forumosPost.mensagem.toString()
        holder.nomeUtilizador.text = forumosPost.autor;
        holder.itemView.setOnClickListener {
            onItemClick(forumosPost.id_post)
        }
    }
}