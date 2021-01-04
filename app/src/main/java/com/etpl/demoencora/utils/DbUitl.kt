package com.etpl.demoencora.utils

import com.etpl.demoencora.model.Docs
import com.etpl.demoencora.room.ArticleDao
import com.etpl.demoencora.room.ArticleDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun rePopulateDb(database: ArticleDataBase?) {

    /*{
        "id": "10.1371/journal.pone.0106286",
        "journal": "PLoS ONE",
        "eissn": "1932-6203",
        "publication_date": "2014-09-30T00:00:00Z",
        "article_type": "Research Article",
        "author_display": [
        "Jennifer R. Spoor",
        "Justin J. Lehmiller"
        ],
        "abstract": [
        "\nDiversity awareness has enormous benefits, and universities in the United States increasingly require students to complete diversity-related courses. Prior research has demonstrated that students' initial attitudes toward these courses affect their subsequent engagement, as well as the quality of their learning experience; however, very little research has examined how these initial attitudes are formed. We conducted an experiment to examine this issue in the context of a women's and gender studies course in psychology. Participants read one of two identical course descriptions that varied only the course title (i.e., Psychology of Gender versus Psychology of Women) and instructor gender. Participants perceived a women-titled course to be narrowly focused compared to an identical gender-titled course and were more interested in taking the gender-titled course. Instructor gender had no effects on any of the variables. Additionally, female participants had more positive attitudes toward the course than male participants, regardless of title. Exploratory mediation analyses indicated that the main effects of course title and participant gender were mediated by perceptions of course content. Implications for improving student experiences and interest in diversity-related courses are discussed.\n"
        ],
        "title_display": "The Impact of Course Title and Instructor Gender on Student Perceptions and Interest in a Women's and Gender Studies Course",
        "score": 7.19762
    },*/

    database?.let { db ->
        withContext(Dispatchers.IO) {
            val articleDao: ArticleDao = db.articleDao()
            articleDao.deleteAll()
           var doc = Docs(0,"10.1371/journal.pone.0106286","","","2014-09-30T00:00:00Z","Research Article",listOf("Jennifer R. Spoor",
                   "Jennifer R. Spoor",
                   "Justin J. Lehmiller"), mutableListOf("Diversity awareness has enormous benefits, and universities in the United States increasingly " +
                   "require students to complete diversity-related courses. " +
                   "Prior research has demonstrated that students' initial attitudes " +
                   "toward these courses affect their subsequent engagement, as well as " +
                   "the quality of their learning experience; however, very little research " +
                   "has examined how these initial attitudes are formed. We conducted an experiment " +
                   "to examine this issue in the context of a women's and gender studies course in psychology." +
                   " Participants read one of two identical course descriptions that varied only the course title " +
                   "(i.e., Psychology of Gender versus Psychology of Women) and instructor gender. " +
                   "Participants perceived a women-titled course to be narrowly focused compared to an identical gender-titled course " +
                   "and were more interested in taking the gender-titled course." +
                   " Instructor gender had no effects on any of the variables. " +
                   "Additionally, female participants had more positive attitudes toward the course than male participants, regardless of title. " +
                   "Exploratory mediation analyses indicated that the main effects of " +
                   "course title and participant gender were mediated by perceptions of course content. " +
                   "Implications for improving student experiences and interest in diversity-related courses are discussed."),
                   "The Impact of Course Title and Instructor Gender on Student Perceptions and Interest in a Women's and Gender Studies Course",
           "7.19762")

            articleDao.insert(doc)
        }
    }
}

suspend fun insertAllData(database: ArticleDataBase?,resultModel: List<Docs?>?){
    database.let {
        withContext(Dispatchers.IO){
            var dao = it!!.articleDao()
            dao.deleteAll()
            dao.insertPosts(resultModel)
        }
    }
}

