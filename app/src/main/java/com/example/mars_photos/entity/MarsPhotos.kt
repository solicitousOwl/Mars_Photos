package com.example.mars_photos.entity

import com.example.mars_photos.data.CameraFieldDto
import com.example.mars_photos.data.MarsPhotosArrayDto
import com.example.mars_photos.data.RoverFieldDto


interface MarsPhotos{
    val photos: Array<MarsPhotosArrayDto>
}
interface MarsPhotosArray {
    var id:Int
    var sol:Int
    var camera: CameraFieldDto
    var img_src:String
    var earth_date:String
    var rover: RoverFieldDto
}

interface CameraField {
    var id:Int
    var name:String
    var rover_id:Int
    var full_name:String
}

interface RoverField{
    var id:Int
    var name:String
    var landing_date:String
    var launch_date:String
    var status:String
}