package com.sb2318.musicwiki.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sb2318.musicwiki.model.Tag
import com.sb2318.musicwiki.model.TagResponse
import com.sb2318.musicwiki.services.DataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenericViewModel(application: Application): AndroidViewModel(application) {

    private var _listTopTags= MutableLiveData<List<Tag>>()
    private var _isExpanded = MutableLiveData<Boolean>()

    val listTopTags: LiveData<List<Tag>>
      get()= _listTopTags



    fun getGeneres(apiKey:String){

         val tagResponse = DataService.lastFmService.getGenres("tag.getTopTags",apiKey,"json")

        tagResponse.enqueue(object:Callback<TagResponse>{

            override fun onResponse(call: Call<TagResponse>, response: Response<TagResponse>) {

                val body = response.body();

                Log.d("Response",body.toString())
                if(response.isSuccessful){
                    _listTopTags.value= body?.toptags?.tag

                }
            }

            override fun onFailure(call: Call<TagResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }
        })
    }

}