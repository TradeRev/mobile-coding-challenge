//
//  Photo.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-19.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation

struct Photo: Codable {
    let id: String
    let createDate: String?
    let updatedDate: String?
    let width: Int?
    let height: Int?
    let imageUrls: URLS?
    let likes: Int?
    
    enum CodingKeys: String, CodingKey {
        case createDate = "created_at"
        case updatedDate = "updated_at"
        case imageUrls = "urls"
        case likes
        case id
        case width
        case height
    }
}

extension Photo {
    struct URLS: Codable {
        let raw: URL
        let full: URL
        let regular: URL
        let small: URL
        let thumb: URL
    }
}


extension Photo: Equatable {
    static func ==(lhs: Photo, rhs: Photo) -> Bool {
        return lhs.id == rhs.id
    }
}
