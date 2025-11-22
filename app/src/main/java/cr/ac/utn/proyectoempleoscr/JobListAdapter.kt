package cr.ac.utn.proyectoempleoscr

import Entity.Job
import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobListAdapter(
    private var itemList: List<Job>,
    private val itemClickListener: OnJobClickListener
) : RecyclerView.Adapter<JobListAdapter.CustomViewHolder>() {

    interface OnJobClickListener {
        fun onItemClicked(job: Job)
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imgLogo: ImageView = view.findViewById(R.id.imgLogo_recycler)
        var txtPosition: TextView = view.findViewById(R.id.txtPosition_recycler)
        var txtJobID: TextView = view.findViewById(R.id.txtID_recycler)
        var txtModalidadJornada: TextView = view.findViewById(R.id.txtModalidadJornada)
        var txtResumen: TextView = view.findViewById(R.id.txtResumen)

        fun bind(item: Job, clickListener: OnJobClickListener) {
            txtPosition.text = item.Puesto
            txtJobID.text = item.ID
            txtModalidadJornada.text = "${item.Modalidad} · ${item.JornadaLaboral}"
            txtResumen.text = item.Descripcion
            imgLogo.setImageBitmap(item.LogoEmpresa)

            itemView.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_job, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, itemClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
