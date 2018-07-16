package com.optimus.basketbrawl

import android.os.Parcel
import android.os.Parcelable

class CourtModel() : Parcelable{

    var courtName: String = ""
    var imageResourceId: Int = 0
    var courtAddress: String = ""
    var courtHours: List<String> = ArrayList() 

    constructor(parcel: Parcel) : this() {
        courtHours = ArrayList()
        courtName = parcel.readString()
        imageResourceId = parcel.readInt()
        courtAddress = parcel.readString()
        courtHours = readArrayList() as ArrayList<String>
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courtName)
        parcel.writeInt(imageResourceId)
        parcel.writeString(courtAddress)
        parcel.writeStringList(courtHours)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourtModel> {
        override fun createFromParcel(parcel: Parcel): CourtModel {
            return CourtModel(parcel)
        }

        override fun newArray(size: Int): Array<CourtModel?> {
            return arrayOfNulls(size)
        }
    }
}

private fun readArrayList() {

}
