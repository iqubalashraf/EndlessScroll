package app.sample.smoothscrollendless

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.WorkerThread
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rvList: RecyclerView
    lateinit var loader: ProgressBar

    val adapter: MyAdapter by lazy { MyAdapter(this) }
    val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

    var list: List<Models> = ArrayList()

    val append = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListener()
        loadMoreData(0)
    }

    private fun initViews() {
        rvList = findViewById(R.id.rv_list)
        rvList.layoutManager = layoutManager
        rvList.adapter = adapter
        loader = findViewById(R.id.loader)
        rvList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        rvList.addOnScrollListener(scrollListener)
    }

    private fun initListener() {

    }

    private fun loadMoreData(totalItemsCount: Int) {
        loader.visibility = View.VISIBLE
        App.executors.diskIO().execute {
            list = H.getMoreData(totalItemsCount)
            App.executors.mainThread().execute {
                adapter.list.addAll(ArrayList(list))
                adapter.notifyDataSetChanged()
                loader.visibility = View.GONE
            }
        }
    }

    val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            Log.d("AshrafLog", "onLoadMore $totalItemsCount")
            loadMoreData(totalItemsCount)
        }
    }


}