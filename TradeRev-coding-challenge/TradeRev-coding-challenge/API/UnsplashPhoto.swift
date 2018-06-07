//
//  UnsplashPhoto.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-19.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import ObjectMapper

struct UnsplashPhoto: Mappable {
    var id: String?
    var createdAt: String?
    var width: Double?
    var height: Double?
    var description: String?
    var urls: PhotoUrl?
    var sponsored = false
    
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        id          <- map["id"]
        createdAt   <- map["created_at"]
        width       <- map["width"]
        height      <- map["height"]
        description <- map["description"]
        urls        <- map["urls"]
        sponsored   <- map["sponsored"]
    }
}

struct PhotoUrl: Mappable {
    var raw: String?
    var full: String?
    var regular: String?
    var small: String?
    var thumb: String?
 
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        raw     <- map["raw"]
        full    <- map["full"]
        regular <- map["regular"]
        small   <- map["small"]
        thumb   <- map["thumb"]
    }
}
