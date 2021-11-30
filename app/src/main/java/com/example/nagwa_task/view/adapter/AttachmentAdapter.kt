package com.example.nagwa_task.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwa_task.R
import com.example.nagwa_task.data.models.Attachment
import com.example.nagwa_task.databinding.AttechmentItemBinding

class AttachmentAdapter(private val attachments: ArrayList<Attachment>) : RecyclerView.Adapter<AttachmentViewHolder>() {

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