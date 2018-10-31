package com.example.jorge.desafiohotelurbano.models

import android.os.Parcel
import android.os.Parcelable


data class  Amenities(val category : String, val name : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(if (category == null) "" else category)
        parcel.writeString(if (name == null) "" else name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Amenities> {
        override fun createFromParcel(parcel: Parcel): Amenities {
            return Amenities(parcel)
        }

        override fun newArray(size: Int): Array<Amenities?> {
            return arrayOfNulls(size)
        }
    }
}