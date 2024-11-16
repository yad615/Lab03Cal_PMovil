package com.alcantara.yadhira.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.alcantara.yadhira.laboratoriocalificado03.databinding.ItemTeacherBinding


class TeacherAdapter(private val teachers: List<TeacherResponse>) :
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)
    }

    override fun getItemCount(): Int = teachers.size

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: TeacherResponse) {
            binding.apply {
                txtFullName.text = teacher.getFullName()
                txtPhone.text = teacher.getPhoneNumber()
                txtEmail.text = teacher.email

                Glide.with(itemView.context)
                    .load(teacher.getProfileImage())
                    .into(imgTeacher)

                root.setOnClickListener {
                    val phoneNumber = teacher.getPhoneNumber()
                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                    itemView.context.startActivity(dialIntent)
                }

                root.setOnLongClickListener {
                    val email = teacher.email
                    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                        itemView.context.getString(R.string.email_subject))
                    itemView.context.startActivity(Intent.createChooser(emailIntent,
                        itemView.context.getString(R.string.send_email)))
                    true
                }
            }
        }
    }
}
