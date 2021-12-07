package com.example.nagwa_task.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.nagwa_task.R
import com.example.nagwa_task.di.DaggerAppComponent
import com.example.nagwa_task.view.adapter.AttachmentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var attachmentAdapter : AttachmentAdapter

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.create().inject(this)

        setUpRecyclerView()
        observeLiveData()
    }

    private fun setUpRecyclerView(){
        attachmentsRV.apply {
             setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = attachmentAdapter
        }
    }

    private fun observeLiveData(){
        observeIsLoading()
        observeHasError()
        observeAttachmentsList()
    }

    private fun observeIsLoading(){
        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it){
                    empty_text.visibility = View.GONE
                    attachmentsRV.visibility = View.GONE
                    fetch_progress.visibility = View.VISIBLE
                }else{
                    fetch_progress.visibility = View.GONE
                }
            }
        })
    }

    private fun observeHasError(){
        mainViewModel.hasError.observe(this, Observer { hasError ->
            hasError.let {
                if (it){
                    disableViewsOnError()
                }else{
                    empty_text.visibility = View.GONE
                    fetch_progress.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun observeAttachmentsList(){
        mainViewModel.attachments.observe(this, Observer { attachments ->
            attachments.let {
                if (it != null && it .isNotEmpty()){
                    attachmentsRV.visibility = View.VISIBLE
                    attachmentAdapter.setAttachmentList(it)
                    empty_text.visibility = View.GONE
                    fetch_progress.visibility = View.GONE
                } else {
                    disableViewsOnError()
                }
            }
        })
    }

    private fun disableViewsOnError() {
        empty_text.visibility = View.VISIBLE
        attachmentsRV.visibility = View.GONE
        attachmentAdapter.setAttachmentList(emptyList())
        fetch_progress.visibility = View.GONE
    }
}