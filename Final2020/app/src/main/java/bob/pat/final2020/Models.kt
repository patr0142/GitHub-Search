package bob.pat.final2020

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable

/*
 * Katelyn Patrick December 7th 2020
*/


// region Returned JSON
/*
{
  "total_count": 1,
  "incomplete_results": false,
  "items": [
    {
      "login": "crazybob1215",
      "id": 45743617,
      "node_id": "MDQ6VXNlcjQ1NzQzNjE3",
      "avatar_url": "https://avatars0.githubusercontent.com/u/45743617?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/crazybob1215",
      "html_url": "https://github.com/crazybob1215",
      "followers_url": "https://api.github.com/users/crazybob1215/followers",
      "following_url": "https://api.github.com/users/crazybob1215/following{/other_user}",
      "gists_url": "https://api.github.com/users/crazybob1215/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/crazybob1215/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/crazybob1215/subscriptions",
      "organizations_url": "https://api.github.com/users/crazybob1215/orgs",
      "repos_url": "https://api.github.com/users/crazybob1215/repos",
      "events_url": "https://api.github.com/users/crazybob1215/events{/privacy}",
      "received_events_url": "https://api.github.com/users/crazybob1215/received_events",
      "type": "User",
      "site_admin": false,
      "score": 1.0
    }
  ]
}

// user details:

{
  "login": "crazybob1215",
  "id": 45743617,
  "node_id": "MDQ6VXNlcjQ1NzQzNjE3",
  "avatar_url": "https://avatars0.githubusercontent.com/u/45743617?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/crazybob1215",
  "html_url": "https://github.com/crazybob1215",
  "followers_url": "https://api.github.com/users/crazybob1215/followers",
  "following_url": "https://api.github.com/users/crazybob1215/following{/other_user}",
  "gists_url": "https://api.github.com/users/crazybob1215/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/crazybob1215/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/crazybob1215/subscriptions",
  "organizations_url": "https://api.github.com/users/crazybob1215/orgs",
  "repos_url": "https://api.github.com/users/crazybob1215/repos",
  "events_url": "https://api.github.com/users/crazybob1215/events{/privacy}",
  "received_events_url": "https://api.github.com/users/crazybob1215/received_events",
  "type": "User",
  "site_admin": false,
  "name": null,
  "company": null, 
  "blog": "",
  "location": null,
  "email": null,
  "hireable": null,
  "bio": null,
  "twitter_username": null,
  "public_repos": 0,
  "public_gists": 0,
  "followers": 0,
  "following": 0,
  "created_at": "2018-12-09T23:35:04Z",
  "updated_at": "2020-09-27T20:09:47Z"
}


 */
// endregion




// region REST API Interface
interface RestApi {
    @GET("users?")
    fun getUserData(@Query("q") searchName: String, @Query("per_page")perPage: Int = 30 ):
            Call<ResponseDataClass>
}

// endregion

// region response data
data class ResponseDataClass(
    val incomplete_results: Boolean = false,
    val items: ArrayList<Users>,
    val total_count: Int = 0
) : Serializable

data class Users(
    val avatar_url: String = "",
    val events_url: String = "",
    val followers_url: String = "",
    val following_url: String = "",
    val gists_url: String = "",
    val gravatar_id: String = "",
    val html_url: String = "",
    val id: Int = 0,
    val login: String = "",
    val node_id: String = "",
    val organizations_url: String = "",
    val received_events_url: String = "",
    val repos_url: String = "",
    val score: Double = 0.0,
    val site_admin: Boolean = false,
    val starred_url: String = "",
    val subscriptions_url: String = "",
    val type: String = "",
    val url: String = ""
) : Serializable

data class UserDetails(
    val avatar_url: String? = "",
    val bio: String? = "",
    val blog: String? = "",
    val company: String? = "",
    val created_at: String? = "",
    val email: String? = "",
    val events_url: String? = "",
    val followers: Int? = 0,
    val followers_url: String? = "",
    val following: Int? = 0,
    val following_url: String? = "",
    val gists_url: String? = "",
    val gravatar_id: String? = "",
    val hireable: String? = "",
    val html_url: String? = "",
    val id: Int? = 0,
    val location: String? = "",
    val login: String? = "",
    val name: String? = "",
    val node_id: String? = "",
    val organizations_url: String? = "",
    val public_gists: Int? = 0,
    val public_repos: Int? = 0,
    val received_events_url: String? = "",
    val repos_url: String? = "",
    val site_admin: Boolean? = false,
    val starred_url: String? = "",
    val subscriptions_url: String? = "",
    val twitter_username: String? = "",
    val type: String? = "",
    val updated_at: String? = "",
    val url: String? = ""
)

//endregion