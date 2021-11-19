# Youtube Data API

| 메소드                                             | HTTP 요청              | 설명                                                         |
| :------------------------------------------------- | :--------------------- | :----------------------------------------------------------- |
| `https://www.googleapis.com/youtube/v3`의 상대 URI |                        |                                                              |
| `list`                                             | `GET /activities`      | 요청 기준과 일치하는 채널 활동 이벤트의 목록을 반환합니다. 예를 들어 특정 채널과 관련된 이벤트, 사용자의 구독정보 및 Google+ 친구 또는 사용자별로 맞춤 설정되는 YouTube 홈페이지 피드와 관련된 이벤트를 검색할 수 있습니다. |
| list                                               | `GET /channel`         | 요청 기준과 일치하는 0개 이상의 `channel` 리소스 집합을 반환합니다. |
|                                                    |                        |                                                              |
| list                                               | `GET /search`          | API 요청에 지정된 쿼리 매개변수와 일치하는 검색 결과의 모음을 반환합니다. 기본적으로 검색 결과의 집합은 쿼리 매개변수와 일치하는 `video`, `channel`, `playlist` 리소스를 식별하지만, 특정 유형의 리소스만 검색하도록 쿼리를 구성할 수도 있습니다. |
| list                                               | `GET /videoCategories` | YouTube 동영상과 연결할 수 있는 카테고리의 목록을 반환합니다. |
|                                                    |                        |                                                              |
|                                                    |                        |                                                              |
|                                                    |                        |                                                              |

활동 또는 이벤트? 사용 용도는 딱히 없을 것 같습니다.

재생목록 관련 데이터도 수집 가능

### activities

```json
{
  "kind": "youtube#activity",
  "etag": etag,
  "id": string,
  "snippet": {
    "publishedAt": datetime,
    "channelId": string,
    "title": string,
    "description": string,
    "thumbnails": {
      (key): {
        "url": string,
        "width": unsigned integer,
        "height": unsigned integer
      }
    },
    "channelTitle": string,
    "type": string,
    "groupId": string
  },
  "contentDetails": {
    "upload": {
      "videoId": string
    },
    "like": {
      "resourceId": {
        "kind": string,
        "videoId": string,
      }
    },
    "favorite": {
      "resourceId": {
        "kind": string,
        "videoId": string,
      }
    },
    "comment": {
      "resourceId": {
        "kind": string,
        "videoId": string,
        "channelId": string,
      }
    },
    "subscription": {
      "resourceId": {
        "kind": string,
        "channelId": string,
      }
    },
    "playlistItem": {
      "resourceId": {
        "kind": string,
        "videoId": string,
      },
      "playlistId": string,
      "playlistItemId": string
    },
    "recommendation": {
      "resourceId": {
        "kind": string,
        "videoId": string,
        "channelId": string,
      },
      "reason": string,
      "seedResourceId": {
        "kind": string,
        "videoId": string,
        "channelId": string,
        "playlistId": string
      }
    },
    "bulletin": {
      "resourceId": {
        "kind": string,
        "videoId": string,
        "channelId": string,
        "playlistId": string
      }
    },
    "social": {
      "type": string,
      "resourceId": {
        "kind": string,
        "videoId": string,
        "channelId": string,
        "playlistId": string
      },
      "author": string,
      "referenceUrl": string,
      "imageUrl": string
    },
    "channelItem": {
      "resourceId": {
      }
    },
  }
}
```

### channel

```json
{
  "kind": "youtube#channel",
  "etag": etag,
  "id": string,
  "snippet": {
    "title": string,
    "description": string,
    "publishedAt": datetime,
    "thumbnails": {
      (key): {
        "url": string,
        "width": unsigned integer,
        "height": unsigned integer
      }
    }
  },
  "contentDetails": {
    "relatedPlaylists": {
      "likes": string,
      "favorites": string,
      "uploads": string,
      "watchHistory": string,
      "watchLater": string
    },
    "googlePlusUserId": string
  },
  "statistics": {
    "viewCount": unsigned long,
    "commentCount": unsigned long,
    "subscriberCount": unsigned long,
    "hiddenSubscriberCount": boolean,
    "videoCount": unsigned long
  },
  "topicDetails": {
    "topicIds": [
      string
    ]
  },
  "status": {
    "privacyStatus": string,
    "isLinked": boolean
  },
  "brandingSettings": {
    "channel": {
      "title": string,
      "description": string,
      "keywords": string,
      "defaultTab": string,
      "trackingAnalyticsAccountId": string,
      "moderateComments": boolean,
      "showRelatedChannels": boolean,
      "showBrowseView": boolean,
      "featuredChannelsTitle": string,
      "featuredChannelsUrls": [
        string
      ],
      "unsubscribedTrailer": string,
      "profileColor": string
    },
    "watch": {
      "textColor": string,
      "backgroundColor": string,
      "featuredPlaylistId": string
    },
    "image": {
      "bannerImageUrl": string,
      "bannerMobileImageUrl": string,
      "backgroundImageUrl": {
        "default": string,
        "localized": [
          {
            "value": string,
            "language": string
          }
        ]
      },
      "largeBrandedBannerImageImapScript": {
        "default": string,
        "localized": [
          {
            "value": string,
            "language": string
          }
        ]
      },
      "largeBrandedBannerImageUrl": {
        "default": string,
        "localized": [
          {
            "value": string,
            "language": string
          }
        ]
      },
      "smallBrandedBannerImageImapScript": {
        "default": string,
        "localized": [
          {
            "value": string,
            "language": string
          }
        ]
      },
      "smallBrandedBannerImageUrl": {
        "default": string,
        "localized": [
          {
            "value": string,
            "language": string
          }
        ]
      },
      "watchIconImageUrl": string,
      "trackingImageUrl": string,
      "bannerTabletLowImageUrl": string,
      "bannerTabletImageUrl": string,
      "bannerTabletHdImageUrl": string,
      "bannerTabletExtraHdImageUrl": string,
      "bannerMobileLowImageUrl": string,
      "bannerMobileMediumHdImageUrl": string,
      "bannerMobileHdImageUrl": string,
      "bannerMobileExtraHdImageUrl": string,
      "bannerTvImageUrl": string,
      "bannerExternalUrl": string
    },
    "hints": [
      {
        "property": string,
        "value": string
      }
    ]
  },
  "invideoPromotion": {
    "defaultTiming": {
      "type": string,
      "offsetMs": unsigned long,
      "durationMs": unsigned long
    },
    "position": {
      "type": string,
      "cornerPosition": string
    },
    "items": [
      {
        "id": {
          "type": string,
          "videoId": string,
          "websiteUrl": string
        },
        "timing": {
          "type": string,
          "offsetMs": unsigned long,
          "durationMs": unsigned long
        },
        "customMessage": string
      }
    ]
  }
}
```

### VideoCategories

```json
{
  "kind": "youtube#videoCategory",
  "etag": etag,
  "id": string,
  "snippet": {
    "channelId": "UCBR8-60-B28hp2BmDPdntcQ",
    "title": string,
    "assignable": boolean
  }
}
```

### Search

```json
{
  "kind": "youtube#searchResult",
  "etag": etag,
  "id": {
    "kind": string,
    "videoId": string,
    "channelId": string,
    "playlistId": string
  },
  "snippet": {
    "publishedAt": datetime,
    "channelId": string,
    "title": string,
    "description": string,
    "thumbnails": {
      (key): {
        "url": string,
        "width": unsigned integer,
        "height": unsigned integer
      }
    },
    "channelTitle": string
  }
}
```

