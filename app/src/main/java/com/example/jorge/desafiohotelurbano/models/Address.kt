package com.example.jorge.desafiohotelurbano.models

import android.os.Parcel
import android.os.Parcelable

/**
Model Address with Parcelable
 */

data class  Address(val city : String, val state : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(if (city == null) "" else city)
        parcel.writeString(if (state == null) "" else state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}
