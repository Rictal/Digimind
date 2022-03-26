package gonzalez.luis.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gonzalez.luis.digimind.AdaptadorTaresas
import gonzalez.luis.digimind.databinding.FragmentHomeBinding
import gonzalez.luis.digimind.ui.Task

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    companion object {
        var tasks: ArrayList<Task> = ArrayList<Task>()
        var first = true
        lateinit var adaptador : AdaptadorTaresas
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gridView: GridView = binding.gridview

        /*if (first) {
            fill_tasks()
            first = false
        }*/

        cargar_tareas()

        adaptador = AdaptadorTaresas(root.context, tasks)

        gridView.adapter = adaptador

        return root
    }

    fun fill_tasks() {
        tasks.add(Task("tarea 1", "lunes", "5:00"))
        tasks.add(Task("tarea 2", "lunes", "18:00"))
        tasks.add(Task("tarea 3", "lunes", "15:00"))
        tasks.add(Task("tarea 4", "lunes", "9:00"))
        tasks.add(Task("tarea 5", "lunes", "11:00"))

    }

    fun cargar_tareas(){
        val preferencias = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val gson : Gson = Gson()

        var json = preferencias?.getString("tareas", null)

        var type = object : TypeToken<ArrayList<Task>?>() {}.type

        if (json == null){
            tasks = ArrayList<Task>()
        }else{
            tasks = gson.fromJson(json, type)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}