package com.senijoshua.picsrus.data.models

data class Photos (var id: String,
                   var width: Int,
                   var height: Int,
                   var color: String,
                   var likes: Int,
                   var user: User,
                   var urls: Urls){

}