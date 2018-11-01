package com.example.jorge.desafiohotelurbano.models

import android.os.Parcel
import android.os.Parcelable

/**
Model Main Results with Parcelable
 */

data class  Results(val results : List<Hotels>) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Hotels)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Results> {
        override fun createFromParcel(parcel: Parcel): Results {
            return Results(parcel)
        }

        override fun newArray(size: Int): Array<Results?> {
            return arrayOfNulls(size)
        }
    }
}