package com.etpl.demoencora.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.etpl.demoencora.utils.Converters
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RoomRes(
        @SerializedName("response")  val response: Response
)

data class Response (
        @SerializedName("numFound") val numFound : Int,
        @SerializedName("start") val start : Int,
        @SerializedName("maxScore") val maxScore : Double,
        @SerializedName("docs") val docs : List<Docs>
)
@Entity(tableName = "Docs")
open class Docs(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "did")
        var did: Int,
        @ColumnInfo(name = "id")
        var id: String,
        @ColumnInfo(name = "journal") var journal: String,
        @ColumnInfo(name = "eissn") var eissn: String,
        @ColumnInfo(name = "publication_date") var publication_date: String,
        @ColumnInfo(name = "article_type") var article_type: String,

        @TypeConverters(Converters::class)
        @ColumnInfo(name = "author_display")
        var author_display: List<String>,

        @TypeConverters(Converters::class)
        @ColumnInfo(name = "abstract")
        @SerializedName("abstract")
        var abstractName: MutableList<String>,

        @ColumnInfo(name = "title_display") var title_display: String,
        @ColumnInfo(name = "score") var score: String):Serializable{

        constructor() : this(0,"","","","","", listOf(""),
                mutableListOf(""),"","")
}
