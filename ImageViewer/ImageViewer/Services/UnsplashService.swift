//
//  UnsplashService.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-08.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit
import UnsplashKit

// https://unsplash.com/oauth/applications/18009
// Application ID bfd715955275ab1cb898a4b5841009491fbc9d24e46f0cdcd5252b694b2e426f
// Secret cce2077b319979bfe8f50c584da2e6e4390df39f2719b64a515be4771d1f78cd
// https://zs-imageviewer.com/unsplash/callback?code=44a3b16c0af9c7dea182d72e0c3ab580b553d17c0d105e5d9cdb14d5688b3d0f
class UnsplashService: NSObject {
  override init() {
    var token = "xxxx"
    var client = UnsplashClient { request -> [String: String] in
      return [ "Authorization": "Bearer \(token)"]
    }
  }
  
}
