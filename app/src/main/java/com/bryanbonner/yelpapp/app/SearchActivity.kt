package com.bryanbonner.yelpapp.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bryanbonner.yelpapp.app.ui.BusinessesAdapter
import com.bryanbonner.yelpapp.app.ui.BusinessesViewModel
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity(), MaterialSearchBar.OnSearchActionListener {

    private val REQUEST_CODE_ASK_PERMISSIONS = 123
    private val viewModel: BusinessesViewModel by viewModel()
    private lateinit var adapter: BusinessesAdapter
    private lateinit var searchBar: MaterialSearchBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        initSearchBar()
        setupPermissions()
        initRecycler()
        initObserver()
    }

    private fun initSearchBar() {
        searchBar = findViewById(R.id.searchBar)
        searchBar.setOnSearchActionListener(this)
        searchBar.isSuggestionsEnabled = false
        searchBar.inflateMenu(R.menu.search_menu)
    }

    private fun initRecycler() {
        adapter = BusinessesAdapter()
        businesses_recycler_view.adapter = adapter
        businesses_recycler_view.layoutManager = GridLayoutManager(this@SearchActivity, 1)
    }

    private fun initObserver() {
        //TODO: Network Handling
//        viewModel.networkState?.observe(this, Observer { adapter.updateNetworkState(it) })
        viewModel.businesses.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onButtonClicked(buttonCode: Int) {
       //Do Nothing
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        //Do Nothing
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        viewModel.onSearchClicked(text.toString(), location = "irvine")
        searchBar.closeSearch()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_CODE_ASK_PERMISSIONS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //TODO
                }
            }
        }
    }
}
