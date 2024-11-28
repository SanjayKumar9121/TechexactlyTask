package com.saveetha.techexactlytask.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saveetha.techexactlytask.Adapter.ApplicationsRecyclerAdapter
import com.saveetha.techexactlytask.Model.AppList
import com.saveetha.techexactlytask.Model.ApplicationModel
import com.saveetha.techexactlytask.ViewModel.ApplicationViewModel
import com.saveetha.techexactlytask.databinding.FragmentApplicationBinding

class ApplicationsFragment : Fragment() {

    lateinit var binding: FragmentApplicationBinding
    lateinit var mviewmodel: ApplicationViewModel
    lateinit var appListsData: ArrayList<AppList>
    lateinit var adapter: ApplicationsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApplicationBinding.inflate(inflater, container, false)

        binding.appsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        appListsData = ArrayList()
        adapter = ApplicationsRecyclerAdapter(appListsData, requireContext())
        binding.appsRecyclerView.adapter = adapter
        mviewmodel = ViewModelProvider(this)[ApplicationViewModel::class.java]
        binding.progressLoader.visibility = View.VISIBLE

        mviewmodel.getAllApplicationsApi()
        mviewmodel.apiresponses.observe(viewLifecycleOwner) { response ->
            // Check if the response is not null and update the list
            val appListData = response.data?.appList
            binding.progressLoader.visibility = View.GONE
            if (appListData != null) {
                appListsData.clear()
                appListsData.addAll(appListData)
                adapter.updateData(appListsData)
            }
        }

        binding.appsSearchBar.queryHint = "Search"
        binding.appsSearchBar.setQueryHint("Search")
        binding.appsSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = appListsData.filter {
                    it.appName?.startsWith(newText.orEmpty(), ignoreCase = true) == true
                }
                adapter.updateData(ArrayList(filteredList))
                return true
            }
        })


        return binding.root
    }

}