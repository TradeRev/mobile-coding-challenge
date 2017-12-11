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
  
  private init() {}
  
  // ImageViewer
  let applicationID = "bfd715955275ab1cb898a4b5841009491fbc9d24e46f0cdcd5252b694b2e426f"
  let secret = "cce2077b319979bfe8f50c584da2e6e4390df39f2719b64a515be4771d1f78cd"
  
  // ImageViewer #2
//  let applicationID = "5c725a427ad999bdb3c9e5d5fbe2bbbf318cf067d4df4fbd0903dc3f81c990dc"
//  let secret = "475b40297f851137dc52cdf797d978a2da3bcfa257cc12052d49c64636d15597"
  
  var token: String? {
    get {
      return UserDefaults.standard.object(forKey: "imageViewer.unsplash.token") as? String
    }
    
    set {
      UserDefaults.standard.set(newValue, forKey: "imageViewer.unsplash.token")
    }
  }
  
  let apiEndpointURL = "https://api.unsplash.com"
  let authorizationURL = "https://unsplash.com/oauth/authorize"
  let tokenURL = "https://unsplash.com/oauth/token"
  let redirectURL = "https://zs-imageviewer.com/unsplash/callback"
}
