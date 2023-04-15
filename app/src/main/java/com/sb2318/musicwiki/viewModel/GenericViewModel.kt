package com.sb2318.musicwiki.viewModel

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.sb2318.musicwiki.model.Track
import com.sb2318.musicwiki.model.TrackResponse
import com.sb2318.musicwiki.model.album.Album
import com.sb2318.musicwiki.model.album.AlbumInfo
import com.sb2318.musicwiki.model.album.AlbumInfoResponse
import com.sb2318.musicwiki.model.album.AlbumResponse
import com.sb2318.musicwiki.model.artist.Artist
import com.sb2318.musicwiki.model.artist.ArtistInfoResponse
import com.sb2318.musicwiki.model.artist.ArtistResponse
import com.sb2318.musicwiki.model.tag.Tag
import com.sb2318.musicwiki.model.tag.TagInfo
import com.sb2318.musicwiki.model.tag.TagInfoResponse
import com.sb2318.musicwiki.model.tag.TagResponse
import com.sb2318.musicwiki.services.DataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenericViewModel(application: Application): AndroidViewModel(application) {

    private var _listTopTags= MutableLiveData<List<Tag>>()
    private var _tagInfo = MutableLiveData<TagInfo>()
    private var _listOfAlbums = MutableLiveData<List<Album>>()
    private var _listOfTracks = MutableLiveData<List<Track>>()
    private var _listOfArtist= MutableLiveData<List<Artist>>()
    private var _artistInfo = MutableLiveData<Artist>()
    private var _albumInfo= MutableLiveData<AlbumInfo>()

    val listTopTags: LiveData<List<Tag>>
      get()= _listTopTags

    val tagInfo:LiveData<TagInfo>
    get()= _tagInfo

    val listOfAlbums:LiveData<List<Album>>
        get()=_listOfAlbums

    val listOfArtists:LiveData<List<Artist>>
        get()=_listOfArtist

    val listOfTracks:LiveData<List<Track>>
        get()=_listOfTracks

    val albumInfo:LiveData<AlbumInfo>
        get()=_albumInfo

   val artistInfo:LiveData<Artist>
       get()=_artistInfo


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
    fun getTagInfo(tagName:String, apiKey:String){

        val tagInfoResponse= DataService.lastFmService.getTagInfo("tag.getinfo",tagName,apiKey,"json")

        tagInfoResponse.enqueue(object:Callback<TagInfoResponse>{
            override fun onResponse(
                call: Call<TagInfoResponse>,
                response: Response<TagInfoResponse>
            ) {

                if(response.isSuccessful){
                    _tagInfo.value= response.body()?.tag
                }
            }

            override fun onFailure(call: Call<TagInfoResponse>, t: Throwable) {

                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }

        })
    }

   fun getTopAlbums(tagName:String, apiKey:String){

       val albumResponse= DataService.lastFmService.getTopAlbums("tag.gettopalbums",tagName,apiKey,"json")
       albumResponse.enqueue(object:Callback<AlbumResponse>{
           override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {

               if(response.isSuccessful){

                   _listOfAlbums.value= response.body()?.albums?.album
               }
           }

           override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
               Log.d("TagResponse",t.localizedMessage)
               Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
           }

       })
   }

    fun getTopArtists(tagName:String, apiKey:String){

        val artistResponse= DataService.lastFmService.getTopArtists("tag.gettopartists",tagName,apiKey,"json")

        artistResponse.enqueue(object:Callback<ArtistResponse>{

            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {

                if(response.isSuccessful){

                    _listOfArtist.value= response.body()?.topartists?.artist
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getTopTracks(tagName:String, apiKey:String){

        val trackResponse= DataService.lastFmService.getTopTracks("tag.gettoptracks",tagName,apiKey,"json")

        trackResponse.enqueue(object:Callback<TrackResponse>{
            override fun onResponse(call: Call<TrackResponse>, response: Response<TrackResponse>) {

                if(response.isSuccessful){
                    _listOfTracks.value= response.body()?.tracks?.track
                }
            }

            override fun onFailure(call: Call<TrackResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getAlbumInfo(albumName:String, artistName:String,apiKey:String){

        val infoResponse = DataService.lastFmService.getAlbumInfo("album.getinfo",
                  apiKey,artistName,albumName,"json")

        infoResponse.enqueue(object:Callback<AlbumInfoResponse>{
            override fun onResponse(
                call: Call<AlbumInfoResponse>,
                response: Response<AlbumInfoResponse>
            ) {
                if(response.isSuccessful) {
                    _albumInfo.value = response.body()?.album
                }
            }

            override fun onFailure(call: Call<AlbumInfoResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getTopTagsByAlbum(artistName:String, albumName: String,apiKey:String){

        val tagResponse = DataService.lastFmService.getAlbumTags("album.gettoptags",
               artistName,albumName,apiKey,"json")

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

    //  Get Artist Info
    fun getArtistInfo(artistName:String,apiKey:String){

        val infoResponse = DataService.lastFmService.getArtistInfo("artist.getinfo",artistName,apiKey,"json")

        infoResponse.enqueue(object:Callback<ArtistInfoResponse>{
            override fun onResponse(
                call: Call<ArtistInfoResponse>,
                response: Response<ArtistInfoResponse>
            ) {
               if(response.isSuccessful){
                   _artistInfo.value= response.body()?.artist
               }
            }

            override fun onFailure(call: Call<ArtistInfoResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }

        })
    }

    // Get artist top tags
    fun getArtistTopTags(artistName: String,apiKey: String){

        val tagResponse= DataService.lastFmService.getArtistTags("artist.gettoptags",artistName,apiKey,"json")

        tagResponse.enqueue(object:Callback<TagResponse>{
            override fun onResponse(call: Call<TagResponse>, response: Response<TagResponse>) {

                if(response.isSuccessful){
                    _listTopTags.value = response.body()?.toptags?.tag
                }
            }

            override fun onFailure(call: Call<TagResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }

        })
    }

    // Get Artist Tracks

    fun getArtistTopTracks(artistName:String,apiKey:String){

        val trackResponse= DataService.lastFmService.getArtistTrack("artist.gettoptracks",artistName,apiKey,"json")

        trackResponse.enqueue(object:Callback<TrackResponse>{

            override fun onResponse(call: Call<TrackResponse>, response: Response<TrackResponse>) {

                if(response.isSuccessful){
                    _listOfTracks.value= response.body()?.tracks?.track
                }
            }

            override fun onFailure(call: Call<TrackResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }

        })
    }

    //Get artist albums

    fun getArtistTopAlbums(artistName: String,apiKey: String){

        val albumResponse= DataService.lastFmService.getArtistAlbums("artist.gettopalbums",artistName,apiKey,"json")

        albumResponse.enqueue(object:Callback<AlbumResponse>{

            override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {

                 if(response.isSuccessful){
                     _listOfAlbums.value= response.body()?.albums?.album
                 }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                Log.d("TagResponse",t.localizedMessage)
                Toast.makeText(getApplication(),"Something went wrong,try again",Toast.LENGTH_SHORT).show()
            }
        })
    }

}

fun getModifiedTextFromHTML(text:String): Spanned? {
   return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }
}

