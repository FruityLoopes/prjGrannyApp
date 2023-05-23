package com.example.prjgrannyapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Welcome : AppCompatActivity()
{
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val feed : RecyclerView = findViewById(R.id.Feed)
        userAdapter = UserAdapter()
        feed.apply {
            layoutManager = LinearLayoutManager(this@Welcome)
            adapter = userAdapter
        }
        val progressBar = findViewById<ProgressBar>(R.id.progress)
        progressBar.visibility = View.VISIBLE

        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val url = URL("https://wordapidata.000webhostapp.com/?getuserdb")
            val json = url.readText()
            val userList = Gson().fromJson(json, Array<User>::class.java).toList()



            Handler(Looper.getMainLooper()).post {
                Log.d("AddNewUser", "Plain Json Vars :" + json.toString())
                Log.d("AddNewUser", "Converted Json:" + userList.toString())
                userAdapter.submitList(userList)
            }
        }






//        val Items = mutableListOf<User>()
//        for(i in 0 .. 40){
//            Items.add(
//                User(
//                    Name = "Name test $i",
//                    Password = "Password $i",
//                    imageURL = "https://picsum.photos/200/300"
//                )
//            )
//        }
//
//        userAdapter.submitList(Items)


//        //declaring and setting the imageview
//        val imageView = findViewById<ImageView>(R.id.imageView)
//
//        //Declaring executor to parse the url
//        val executor = Executors.newSingleThreadExecutor()
//
//        val handler = Handler(Looper.getMainLooper())
//
//        //initializing the image
//        var image: Bitmap?= null
//
//        executor.execute{
//            //image url
//            val imageUrl= "https://picsum.photos/200/300"
//            //Tries to get the image and post
//            // it in the imageview with the help of the handler
//            try {
//                    val `in` = java.net.URL(imageUrl).openStream()
//                    image = BitmapFactory.decodeStream(`in`)
//                    Log.d("AddNewUser","Image in text"+image.toString())
//                    //only for making changes in UI
//                    handler.post {
//                        Log.d("AddNewUser","Image added")
//                        imageView.setImageBitmap(image)
//                    }
//
//                }
//            catch (e:java.lang.Exception)
//            {
//                Log.d("AddNewUser","Error happened "+e.toString())
//                e.printStackTrace()
//            }
//        }

    }
}