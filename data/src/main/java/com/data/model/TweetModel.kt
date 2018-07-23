package com.data.model

import com.google.gson.annotations.SerializedName

class TweetModel {
    @SerializedName("created_at")
    var createdAt: String? = null
    var id: Long = 0
    @SerializedName("id_str")
    var idStr: String? = null
    var text: String? = null
    var entities: EntitiesModel? = null
    var user: UserModel? = null
    @SerializedName("retweet_count")
    var retweetCount: Int = 0
    @SerializedName("favorite_count")
    var favoriteCount: Int = 0

    inner class EntitiesModel {
        internal var owner: TweetModel? = null
        var id: Int = 0
        var urls: List<UrlModel>? = null
        var media: List<MediaModel>? = null

        inner class MediaModel {
            var id: Long = 0
            @SerializedName("id_str")
            var idStr: String? = null
            @SerializedName("media_url")
            var mediaUrl: String? = null
            @SerializedName("media_url_https")
            var mediaUrlHttps: String? = null
            var url: String? = null
            @SerializedName("display_url")
            var displayUrl: String? = null
            @SerializedName("expanded_url")
            var expandedUrl: String? = null
            var type: String? = null
            var sizes: SizesModel? = null

            inner class SizesModel {
                var id: Int = 0
                var small: SizeModel? = null
                var large: SizeModel? = null
                var medium: SizeModel? = null
                var thumb: SizeModel? = null
            }


            inner class SizeModel {
                var id: Int = 0
                var w: Int = 0
                var h: Int = 0
            }
        }

        inner class UrlModel {
            var id: Int = 0
            var url: String? = null
            @SerializedName("expanded_url")
            var expandUrl: String? = null
            @SerializedName("display_url")
            var displayUrl: String? = null
        }
    }

    inner class UserModel {
        internal var owner: TweetModel? = null
        var id: Long = 0
        @SerializedName("id_str")
        var idStr: String? = null
        var name: String? = null
        @SerializedName("screen_name")
        var screenName: String? = null
        var location: String? = null
        var description: String? = null
        var url: String? = null
        @SerializedName("followers_count")
        var followersCount: Long = 0
        @SerializedName("friends_count")
        var friendsCount: Long = 0
        @SerializedName("listed_count")
        var listedCount: Long = 0
        @SerializedName("created_at")
        var createdAt: String? = null
        @SerializedName("favourites_count")
        var favouritesCount: Long = 0
        var verified: Boolean = false
        @SerializedName("statuses_count")
        var statusesCount: Long = 0
        @SerializedName("profile_background_image_url")
        var profileBgImgUrl: String? = null
        @SerializedName("profile_background_image_url_https")
        var profileBgImgUrlHttps: String? = null
        @SerializedName("profile_image_url")
        var profileImgUrl: String? = null
        @SerializedName("profile_image_url_https")
        var profileImgUrlHttps: String? = null
        @SerializedName("profile_banner_url")
        var profileBannerUrl: String? = null
    }
}
