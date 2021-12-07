package com.example.nagwa_task.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwa_task.R
import com.example.nagwa_task.data.models.Attachment
import com.example.nagwa_task.databinding.AttechmentItemBinding
import com.example.nagwa_task.di.DaggerAppComponent
import com.example.nagwa_task.view.activities.MainViewModel

class AttachmentAdapter(private val attachments: ArrayList<Attachment>) : RecyclerView.Adapter<AttachmentViewHolder>() {

    private var mainViewModel: MainViewModel = MainViewModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachmentViewHolder {
        val attachmentItemBinding: AttechmentItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.attechment_item,
            parent, false
        )

        return AttachmentViewHolder(attachmentItemBinding)
    }

    override fun onBindViewHolder(holder: AttachmentViewHolder, position: Int) {
        holder.attachmentItemBinding.attachment = attachments[position]

        holder.attachmentItemBinding.downloadBTN.setOnClickListener {
            mainViewModel.downloadFile(attachments[position])
        }
    }

    override fun getItemCount(): Int = attachments.size

    fun setAttachmentList(attachmentList: List<Attachment>){
        attachments.clear()
        attachments.addAll(attachmentList)
        notifyDataSetChanged()
    }
}

class AttachmentViewHolder(val attachmentItemBinding: AttechmentItemBinding) :
    RecyclerView.ViewHolder(attachmentItemBinding.root)