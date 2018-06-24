//
//  PhotoGridCellViewModel.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-24.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation

struct PhotoGridCellViewModel {
    let id: String
    let createDate: String?
    let updatedDate: String?
    let width: Int?
    let height: Int?
    let imageUrls: Photo.URLS?
    let likes: Int?
    
    init(_ model: Photo) {
        self.id = model.id
        self.createDate = model.createDate
        self.updatedDate = model.updatedDate
        self.width = model.width
        self.height = model.height
        self.likes = model.likes
        self.imageUrls = model.imageUrls
        
    }
}
