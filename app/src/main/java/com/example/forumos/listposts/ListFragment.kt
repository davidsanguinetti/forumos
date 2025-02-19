package com.example.forumos.listposts

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forumos.R
import com.example.forumos.databinding.FragmentFirstBinding
import com.example.forumos.postinsert.InsertPostFragment
import estsetubal.ips.demontracaoForumosPosts.fragments.adapters.ForumosPostsAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    private lateinit var vmodel: ListFragmentViewModel

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        vmodel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Mensagens"
        actionBar?.setDisplayHomeAsUpEnabled(true)


        _binding!!.rvlistPosts.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        _binding!!.rvlistPosts.addItemDecoration(dividerItemDecoration)

        vmodel.posts.observe(viewLifecycleOwner) { posts ->
            _binding!!.rvlistPosts.adapter = ForumosPostsAdapter(posts) { id ->
                run {
                    val idDir = ListFragmentDirections.actionFirstFragmentToPostDetailFragment();
                    idDir.setPostid(id)
                    findNavController().navigate(idDir)
                }
            }
            _binding!!.swipeRefreshLayout.isRefreshing = false
        }

        vmodel.getPosts()
        _binding!!.swipeRefreshLayout.setOnRefreshListener {
            vmodel.getPosts()
        }


        binding.fab.setOnClickListener { view ->
            findNavController().navigate(ListFragmentDirections.actionCreatepost("", 1))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            // Inflate the menu for this fragment
            menuInflater.inflate(R.menu.menu_fragment, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // Handle menu item selection
            return when (menuItem.itemId) {
                R.id.action_refresh -> {
                    // Handle refresh action
                    true
                }
                else -> false
            }
        }
    }
}