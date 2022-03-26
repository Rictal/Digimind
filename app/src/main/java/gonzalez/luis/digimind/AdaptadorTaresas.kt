package gonzalez.luis.digimind

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import gonzalez.luis.digimind.ui.Task
import gonzalez.luis.digimind.ui.home.HomeFragment

class AdaptadorTaresas : BaseAdapter {

    lateinit var conext: Context
    var tasks: ArrayList<Task> = ArrayList<Task>()

    constructor(context: Context, tasks: ArrayList<Task>) {
        this.conext = context
        this.tasks = tasks
    }

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(p0: Int): Any {
        return tasks[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflador = LayoutInflater.from(conext)
        var vista = inflador.inflate(R.layout.task_view, null)

        var task = tasks[p0]

        val tv_titulo: TextView = vista.findViewById(R.id.tv_title)
        val tv_time: TextView = vista.findViewById(R.id.tv_time)
        val tv_dia: TextView = vista.findViewById(R.id.tv_days)

        tv_titulo.setText(task.title)
        tv_dia.setText(task.day)
        tv_time.setText(task.time)

        vista.setOnClickListener{
            eliminar(task)
        }

        return vista
    }

    fun eliminar(task: Task) {

        val alertDialog: AlertDialog? = conext.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok_button,
                    DialogInterface.OnClickListener{ dialog, id->
                        //User clicked on OK button
                        HomeFragment.tasks.remove(task)
                        HomeFragment.adaptador.notifyDataSetChanged()
                        Toast.makeText(context, R.string.msg_deleted, Toast.LENGTH_SHORT).show()
                    })
                setNegativeButton(R.string.cancel_button,
                DialogInterface.OnClickListener{ dialog, id->
                })
            }
            builder?.setMessage(R.string.msg)
                .setTitle(R.string.title)
            builder.create()
        }
        alertDialog?.show()

    }
}