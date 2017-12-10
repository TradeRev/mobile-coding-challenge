//
//  Settings.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-08.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit

class Settings {
  static let shared = Settings()
  
  let applicationID = "bfd715955275ab1cb898a4b5841009491fbc9d24e46f0cdcd5252b694b2e426f"
  let secret = "cce2077b319979bfe8f50c584da2e6e4390df39f2719b64a515be4771d1f78cd"
  var token: String?
  
  let apiEndpoint = "https://api.unsplash.com"
  
  let authorizationURL = "https://unsplash.com/oauth/authorize"
  let tokenURL = "https://unsplash.com/oauth/token"
  
  let redirectURL = "https://zs-imageviewer.com/unsplash/callback"
}
