package com.devtests.pruebatecnica

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devtests.pruebatecnica.databinding.FragmentListBinding
import com.devtests.pruebatecnica.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentListBinding

    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<DataModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.reciclerView
        list = ArrayList()

        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerAdapter(list, requireContext())

        recyclerView.layoutManager = layoutManager

        adapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putString("clave", it.name)
            bundle.putString("donut", it.toString())


            findNavController().navigate(R.id.detailsListFragment, bundle)
            Log.e("pRUEBA", it.toString())
        }


        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://mocki.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api:ApiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<DataModel> = api.getData()

        call.enqueue(object: Callback<DataModel?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
                if(response.isSuccessful) {
                    list.clear()

                    for(myData in response.body()!!) {
                        list.add(myData)
                    }

                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter

                }
            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater,container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}