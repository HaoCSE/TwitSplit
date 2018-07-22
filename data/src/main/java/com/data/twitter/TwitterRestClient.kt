package com.data.twitter

import android.content.Context

import com.codepath.oauth.OAuthBaseClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams

import org.scribe.builder.api.Api
import org.scribe.builder.api.TwitterApi

/*
 *
 * This is the object responsible for communicating with a REST API.
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes:
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 *
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 *
 */
class TwitterRestClient(context: Context) : OAuthBaseClient(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL) {

    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    fun getInterestingnessList(handler: AsyncHttpResponseHandler) {
        val apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList")
        // Can specify query string params directly or through RequestParams.
        val params = RequestParams()
        params.put("format", "json")
        getClient().get(apiUrl, params, handler)
    }

    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */

    fun getHomeTimeline(page: Int, handler: AsyncHttpResponseHandler) {
        val apiUrl = getApiUrl("statuses/home_timeline.json")
        val params = RequestParams()
        params.put("page", page.toString())
        getClient().get(apiUrl, params, handler)
    }

    fun postTweet(body: String, handler: AsyncHttpResponseHandler) {
        val apiUrl = getApiUrl("statuses/update.json")
        val params = RequestParams()
        params.put("status", body)
        getClient().post(apiUrl, params, handler)
    }

    companion object {
        val REST_API_CLASS: Class<out Api> = TwitterApi::class.java // Change this
        val REST_URL = "https://api.twitter.com/1.1" // Change this, base API URL
        val REST_CONSUMER_KEY = "UauwHDkOGImrNdaWOEbLvnYYh"       // Change this
        val REST_CONSUMER_SECRET = "yejL6pzKUGYgoCJwfc6enSxMr2GwoqGwYjvtgFu28krb1nLGmV" // Change this
        val REST_CALLBACK_URL = "http://mytwitter" // Change this (here and in manifest)
    }
}
