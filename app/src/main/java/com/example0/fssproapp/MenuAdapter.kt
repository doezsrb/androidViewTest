package com.example0.fssproapp

import android.accounts.AccountManager
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.sip.SipSession
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import com.example0.fssproapp.auth.LoginActivity
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.logout_popup.view.*
import org.w3c.dom.Text
import kotlin.math.log

class MenuAdapter(private val myDataset:List<MenuItemModel>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    var row_index = -1;
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.menu_item,parent,false)

        return MenuViewHolder(textView,parent.context)
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
        val accManager = AccountManager.get(holder.ctx)
        val sharedPreferences = holder.ctx.getSharedPreferences(holder.ctx.resources.getString(R.string.shared_preferences_name),Context.MODE_PRIVATE)
        holder.textView1.text = myDataset[position].name

        holder.textView1.setOnClickListener {
            val tv = it as TextView

            if(tv.text == "Switch Account"){
                val accounts = accManager.getAccountsByType(holder.ctx.resources.getString(R.string.type_account))
                var accName = mutableListOf<String>()
                for((i,acc) in accounts.withIndex()){
                    accName.add(i,acc.name)
                }
                val builder = AlertDialog.Builder(holder.ctx,R.style.AlertDialogTheme)
               builder
                   .setItems(accName.toTypedArray(),object :DialogInterface.OnClickListener{
                   override fun onClick(p0: DialogInterface?, p1: Int) {


                       with(sharedPreferences.edit()){
                           putInt("selected_account",p1)
                           apply()
                       }
                   }
               })
                val alert = builder.create()
                alert.show()
            }else if(tv.text == "Log out"){
                val accounts = accManager.getAccountsByType(holder.ctx.resources.getString(R.string.type_account))
                val account_id = sharedPreferences.getInt(holder.ctx.resources.getString(R.string.shared_preferences_name),accounts.size-1)
                accManager.removeAccount(accounts[account_id],null,null,null)
                val act = holder.ctx as Activity
                val li = act.layoutInflater
                val view = li.inflate(R.layout.logout_popup,null)
                val builder = AlertDialog.Builder(holder.ctx)
                var createBuilder:AlertDialog? = null
                view.lgtbtn.setOnClickListener {
                    act.startActivity(Intent(holder.ctx,LoginActivity::class.java))
                    createBuilder?.dismiss()
                }


                act.drawer1.closeDrawer(Gravity.LEFT)
                createBuilder = builder.setView(view).setCancelable(false).create()
                createBuilder.show()
                //holder.ctx.startActivity(Intent(holder.ctx,MainActivity::class.java))
            }

            row_index = position
            notifyDataSetChanged()
        }
        if(row_index == position){
            holder.textView1.setBackgroundColor(Color.parseColor("#80bfff"))
        }else{
            holder.textView1.setBackgroundResource(android.R.color.holo_blue_dark)
        }
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }
    class MenuViewHolder(val itemView: View,context:Context): RecyclerView.ViewHolder(itemView){
        lateinit var textView1:TextView
        lateinit var ctx: Context
        init {
            ctx = context
            textView1 = itemView.findViewById(R.id.textView17)
        }
    }
}