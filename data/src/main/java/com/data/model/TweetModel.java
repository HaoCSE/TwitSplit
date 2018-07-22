package com.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TweetModel {
    @SerializedName("created_at")
    private String createdAt;
    private long id;
    @SerializedName("id_str")
    private String idStr;
    private String text;
    private EntitiesModel entities;
    private UserModel user;
    @SerializedName("retweet_count")
    private int retweetCount;
    @SerializedName("favorite_count")
    private int favoriteCount;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EntitiesModel getEntities() {
        return entities;
    }

    public void setEntities(EntitiesModel entities) {
        this.entities = entities;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public class EntitiesModel {
        TweetModel owner;
        private int id;
        private List<UrlModel> urls;
        private List<MediaModel> media;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<UrlModel> getUrls() {
            return urls;
        }

        public void setUrls(List<UrlModel> urls) {
            this.urls = urls;
        }

        public List<MediaModel> getMedia() {
            return media;
        }

        public void setMedia(List<MediaModel> media) {
            this.media = media;
        }

        public class MediaModel {
            private long id;
            @SerializedName("id_str")
            private String idStr;
            @SerializedName("media_url")
            private String mediaUrl;
            @SerializedName("media_url_https")
            private String mediaUrlHttps;
            private String url;
            @SerializedName("display_url")
            private String displayUrl;
            @SerializedName("expanded_url")
            private String expandedUrl;
            private String type;
            private SizesModel sizes;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getIdStr() {
                return idStr;
            }

            public void setIdStr(String idStr) {
                this.idStr = idStr;
            }

            public String getMediaUrl() {
                return mediaUrl;
            }

            public void setMediaUrl(String mediaUrl) {
                this.mediaUrl = mediaUrl;
            }

            public String getMediaUrlHttps() {
                return mediaUrlHttps;
            }

            public void setMediaUrlHttps(String mediaUrlHttps) {
                this.mediaUrlHttps = mediaUrlHttps;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDisplayUrl() {
                return displayUrl;
            }

            public void setDisplayUrl(String displayUrl) {
                this.displayUrl = displayUrl;
            }

            public String getExpandedUrl() {
                return expandedUrl;
            }

            public void setExpandedUrl(String expandedUrl) {
                this.expandedUrl = expandedUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public SizesModel getSizes() {
                return sizes;
            }

            public void setSizes(SizesModel sizes) {
                this.sizes = sizes;
            }

            public class SizesModel {
                private int id;
                private SizeModel small;
                private SizeModel large;
                private SizeModel medium;
                private SizeModel thumb;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public SizeModel getSmall() {
                    return small;
                }

                public void setSmall(SizeModel small) {
                    this.small = small;
                }

                public SizeModel getLarge() {
                    return large;
                }

                public void setLarge(SizeModel large) {
                    this.large = large;
                }

                public SizeModel getMedium() {
                    return medium;
                }

                public void setMedium(SizeModel medium) {
                    this.medium = medium;
                }

                public SizeModel getThumb() {
                    return thumb;
                }

                public void setThumb(SizeModel thumb) {
                    this.thumb = thumb;
                }
            }


            public class SizeModel {
                private int id;
                private int w;
                private int h;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getW() {
                    return w;
                }

                public void setW(int w) {
                    this.w = w;
                }

                public int getH() {
                    return h;
                }

                public void setH(int h) {
                    this.h = h;
                }
            }
        }

        public class UrlModel {
            private int id;
            private String url;
            @SerializedName("expanded_url")
            private String expandUrl;
            @SerializedName("display_url")
            private String displayUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getExpandUrl() {
                return expandUrl;
            }

            public void setExpandUrl(String expandUrl) {
                this.expandUrl = expandUrl;
            }

            public String getDisplayUrl() {
                return displayUrl;
            }

            public void setDisplayUrl(String displayUrl) {
                this.displayUrl = displayUrl;
            }
        }
    }

    public class UserModel {
        TweetModel owner;
        private long id;
        @SerializedName("id_str")
        private String idStr;
        private String name;
        @SerializedName("screen_name")
        private String screenName;
        private String location;
        private String description;
        private String url;
        @SerializedName("followers_count")
        private long followersCount;
        @SerializedName("friends_count")
        private long friendsCount;
        @SerializedName("listed_count")
        private long listedCount;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("favourites_count")
        private long favouritesCount;
        private boolean verified;
        @SerializedName("statuses_count")
        private long statusesCount;
        @SerializedName("profile_background_image_url")
        private String profileBgImgUrl;
        @SerializedName("profile_background_image_url_https")
        private String profileBgImgUrlHttps;
        @SerializedName("profile_image_url")
        private String profileImgUrl;
        @SerializedName("profile_image_url_https")
        private String profileImgUrlHttps;
        @SerializedName("profile_banner_url")
        private String profileBannerUrl;

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIdStr() {
            return idStr;
        }

        public void setIdStr(String idStr) {
            this.idStr = idStr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScreenName() {
            return screenName;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getFollowersCount() {
            return followersCount;
        }

        public void setFollowersCount(long followersCount) {
            this.followersCount = followersCount;
        }

        public long getFriendsCount() {
            return friendsCount;
        }

        public void setFriendsCount(long friendsCount) {
            this.friendsCount = friendsCount;
        }

        public long getListedCount() {
            return listedCount;
        }

        public void setListedCount(long listedCount) {
            this.listedCount = listedCount;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public long getFavouritesCount() {
            return favouritesCount;
        }

        public void setFavouritesCount(long favouritesCount) {
            this.favouritesCount = favouritesCount;
        }

        public long getStatusesCount() {
            return statusesCount;
        }

        public void setStatusesCount(long statusesCount) {
            this.statusesCount = statusesCount;
        }

        public String getProfileBgImgUrl() {
            return profileBgImgUrl;
        }

        public void setProfileBgImgUrl(String profileBgImgUrl) {
            this.profileBgImgUrl = profileBgImgUrl;
        }

        public String getProfileBgImgUrlHttps() {
            return profileBgImgUrlHttps;
        }

        public void setProfileBgImgUrlHttps(String profileBgImgUrlHttps) {
            this.profileBgImgUrlHttps = profileBgImgUrlHttps;
        }

        public String getProfileImgUrl() {
            return profileImgUrl;
        }

        public void setProfileImgUrl(String profileImgUrl) {
            this.profileImgUrl = profileImgUrl;
        }

        public String getProfileImgUrlHttps() {
            return profileImgUrlHttps;
        }

        public void setProfileImgUrlHttps(String profileImgUrlHttps) {
            this.profileImgUrlHttps = profileImgUrlHttps;
        }

        public String getProfileBannerUrl() {
            return profileBannerUrl;
        }

        public void setProfileBannerUrl(String profileBannerUrl) {
            this.profileBannerUrl = profileBannerUrl;
        }
    }
}
