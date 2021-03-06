package com.example.jorge.desafiohotelurbano.models

import android.os.Parcel
import android.os.Parcelable

/**
Model Hotel with Parcelable
 */

data class  Hotels(var mainTitle: String, val sku: String, val isPackage: Boolean, val isHotel: Boolean, val name: String, val url: String, val smallDescription: String, val description: String, val stars: Int, val gallery: List<Gallery>?, val price: Price?, val amenities: List<Amenities>?, val address: Address?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte()!! != 0.toByte(),
        parcel.readByte()!! != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!,
        parcel.createTypedArrayList(Gallery)!!,
        parcel.readParcelable(Price!!::class.java.classLoader),
        parcel.createTypedArrayList(Amenities!!),
        parcel.readParcelable(Address!!::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(if (mainTitle == null) "" else mainTitle)
        parcel.writeString(if (sku == null) "" else sku)
        parcel.writeByte(if (isPackage) 1 else 0)
        parcel.writeByte(if (isHotel) 1 else 0)
        parcel.writeString(if (name == null) "" else name)
        parcel.writeString(if (url == null) "" else url)
        parcel.writeString(if (smallDescription == null) "" else smallDescription)
        parcel.writeString(if (description == null) "" else description)
        parcel.writeInt(stars)
        parcel.writeTypedList(gallery)
        parcel.writeParcelable(price, flags)
        parcel.writeTypedList(amenities)
        parcel.writeParcelable(address, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hotels> {
        override fun createFromParcel(parcel: Parcel): Hotels {
            return Hotels(parcel)
        }

        override fun newArray(size: Int): Array<Hotels?> {
            return arrayOfNulls(size)
        }
    }
}

