package com.saveetha.techexactlytask.Model

import com.google.gson.annotations.SerializedName

data class ApplicationModel(
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("data"    ) var data    : Data?    = Data(),
    @SerializedName("message" ) var message : String?  = null
)

data class AppList (

    @SerializedName("app_id"            ) var appId           : Int?    = null,
    @SerializedName("fk_kid_id"         ) var fkKidId         : Int?    = null,
    @SerializedName("kid_profile_image" ) var kidProfileImage : String? = null,
    @SerializedName("app_name"          ) var appName         : String? = null,
    @SerializedName("app_icon"          ) var appIcon         : String? = null,
    @SerializedName("app_package_name"  ) var appPackageName  : String? = null,
    @SerializedName("status"            ) var status          : String? = null

)

data class Data (

    @SerializedName("app_list"     ) var appList     : ArrayList<AppList> = arrayListOf(),
    @SerializedName("usage_access" ) var usageAccess : Int?               = null

)
