package com.example.githubusers.ui.features.usersList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubusers.R
import com.example.githubusers.common.Resource
import com.example.githubusers.common.gone
import com.example.githubusers.common.show
import com.example.githubusers.data.model.UsersResponseItem
import com.example.githubusers.databinding.FragmentUsersBinding
import com.example.githubusers.domain.searchQuery
import com.example.githubusers.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UsersFragment : Fragment(), UsersAdapter.Interaction ,SearchView.OnQueryTextListener {


    private lateinit var  binding  :FragmentUsersBinding
    private val viewModel: UsersViewModel by viewModels()
    private val userAdapter by lazy { UsersAdapter(this) }
    private lateinit var responseList: MutableList<UsersResponseItem>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding=FragmentUsersBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).title = "Home"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // navController = Navigation.findNavController(view)

        viewModel.getUsers()
        responseList = mutableListOf()
        setupRecyclerView()
        observeToUsersLiveData()
        observeToErrorLiveData(view)
    }


    private fun observeToErrorLiveData(view: View) {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if(it){
                viewModel.error.postValue(false)
                Snackbar.make(
                    view,
                    ("No Data Saved "),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(("retry")) {
                        viewModel.getUsers()
                    }
                    .show()
            }
        })
    }


    private fun setupRecyclerView() {
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                responseList.clear()
                viewModel.getUsers()
                observeToUsersLiveData()

            }
        }
        binding.newsRecycler.apply {
            adapter = userAdapter
        }
    }
    private fun observeToUsersLiveData() {
        viewModel.getUsersLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Error -> {
                    binding.ProgressBar.gone()
                }
                is Resource.Loading -> binding.ProgressBar.show()
                is Resource.Success -> {
                    if (it.data != null) {
                        responseList.clear()
                        binding.ProgressBar.gone()
                        binding.swipeRefresh.isRefreshing = false
                        userAdapter.differ.submitList(it.data)
                        responseList.addAll(it.data)  // add the call from api to list in memory to search
                    }
                }
            }
        })
    }
    override fun onItemSelected(position: Int, item: UsersResponseItem) {
        val action = UsersFragmentDirections.actionUsersFragmentToUserWebViewFragment(item.url,item.login)
        findNavController().navigate(action)
    }

    override fun onFavSelected(position: Int, item: UsersResponseItem) {

        item.isFav = !item.isFav
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.updateFavorite(item.isFav, item.login)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryTextChange(query)
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        userAdapter.differ.submitList(searchQuery(newText, responseList))
        return true
    }

}