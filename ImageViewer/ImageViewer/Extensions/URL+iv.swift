//
//  URL+iv.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-09.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import Foundation

extension URL {
  public var queryItems: [String: String] {
    var params = [String: String]()
    return URLComponents(url: self, resolvingAgainstBaseURL: false)?
      .queryItems?
      .reduce([:], { (_, item) -> [String: String] in
        params[item.name] = item.value
        return params
      }) ?? [:]
  }
}
