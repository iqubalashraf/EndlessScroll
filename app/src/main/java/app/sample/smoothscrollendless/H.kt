package app.sample.smoothscrollendless

import android.view.View
import androidx.annotation.WorkerThread

object H {
    @WorkerThread
    fun getMoreData(totalItemsCount: Int): List<Models> {
        val list: ArrayList<Models> = ArrayList()
        Thread.sleep(1000) // network call timing
        for (i in 1..100) {
            list.add(Models(String.format("Name %d", totalItemsCount+i), "Designation Place Holder"))
        }
        return list
    }
}