package com.example.jorge.desafiohotelurbano.models

data class  Hotels(var mainTitle : String, val sku : String, val isPackage : Boolean, val isHotel : Boolean, val name : String, val url : String, val smallDescription : String, val description : String, val stars : Int, val gallery: List<Gallery>, val price: Price, val amenities: List<Amenities>, val address: Address)
