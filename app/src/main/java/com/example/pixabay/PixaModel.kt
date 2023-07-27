package com.example.pixabay

data class PixaModel (
    var hits: ArrayList<ImageModel>
        )

data class ImageModel (
        var largeImageURL : String
        )