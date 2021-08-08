package com.example.familybrowser.models

import android.os.Parcel
import android.os.Parcelable

class TypeDetails(
    val typeName: String?,
    val familyName: String?,
    val description: String?,
    val typeId:String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        typeName = parcel.readString(),
        familyName = parcel.readString(),
        description = parcel.readString(),
        typeId = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(typeName)
        parcel.writeString(familyName)
        parcel.writeString(description)
        parcel.writeString(typeId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TypeDetails> {
        override fun createFromParcel(parcel: Parcel): TypeDetails {
            return TypeDetails(parcel)
        }

        override fun newArray(size: Int): Array<TypeDetails?> {
            return arrayOfNulls(size)
        }
    }
}