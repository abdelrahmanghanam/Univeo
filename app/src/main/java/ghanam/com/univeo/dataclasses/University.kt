package ghanam.com.univeo.dataclasses

data class University(
    val name:String,
    val city:String,
    val rank:String,
    val videoID:String,
    val brief:String,
    val faculties:MutableList<HashMap<String, Any>>,
    val longtidue:String,
    val latitude:String,
    val imageURL:String,
    val logoURL:String,
    val id:String
)
