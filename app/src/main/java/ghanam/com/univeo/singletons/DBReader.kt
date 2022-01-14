package ghanam.com.univeo.singletons

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ghanam.com.univeo.adapters.UniversitiesAdapter
import ghanam.com.univeo.dataclasses.NewsGeneral
import ghanam.com.univeo.dataclasses.University
import ghanam.com.univeo.dataclasses.UniversityGeneral

object DBReader {
    private lateinit var db: FirebaseFirestore
    val universitiesList : MutableList<University> = mutableListOf()
    val universitiesHomeList: MutableList<UniversityGeneral> = mutableListOf()
    val newsHomeList: MutableList<NewsGeneral> = mutableListOf()
    var dataRead=false
    var currentUni:University?=null
    var currentFaculty:HashMap<String, Any>?=null
    init {

        db = Firebase.firestore
    }

    fun readData(universitiesAdapter: UniversitiesAdapter) {
        if (dataRead==false){
            db.collection("universities")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val json=document.data
                        val faculties:MutableList<HashMap<String,Any>> = json["faculties"] as MutableList<HashMap<String, Any>>
                        Log.d("before success",faculties.toString())
                        val uni=University(json["name"] as String,json["city"] as String, json["rank"] as String
                            ,json["videoID"] as String,json["brief"] as String,
                            faculties,json["longitude"] as String, json["latitude"] as String,
                            json["imageURL"] as String, json["logoURL"] as String,document.id)
                        universitiesList.add(uni)
                        universitiesAdapter.addUniversity(UniversityGeneral(uni.name,uni.rank,uni.city,uni.id,uni.imageURL))
                        Log.d("Success", "${document.id} => ${document.data}")

                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("Failure", "Error getting documents.", exception)
                }

            db.collection("news")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val json=document.data
                        newsHomeList.add(NewsGeneral(json["title"] as String,json["newsURL"] as String, json["imageURL"] as String))
                        Log.d("Success", "${document.id} => ${document.data}")

                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("Failure", "Error getting documents.", exception)
                }

            dataRead=true
        }

    }

    fun getUniversityById(id:String):University?{
        for (uni in universitiesList){
            if (uni.id==id){
                currentUni=uni
                return uni
            }
        }
        currentUni=null
        return null
    }

}