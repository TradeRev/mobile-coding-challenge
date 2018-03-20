//
//  UnsplashPhotoDataSource.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-20.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import Foundation

struct UnsplashPhotoDataSource: PhotoDataSource {
    var photos: [UnsplashPhoto] = [UnsplashPhoto]()
    
    static func load(onSuccess: PhotoOnSuccess?, onFailure: PhotoOnFailure?) {
        UnsplashAPI.fetchPhotos(onSuccess: { (photos) in
            let dataSource = UnsplashPhotoDataSource(photos: photos)
            onSuccess?(dataSource)
        }, onfailure: onFailure)
    }
    
    func loadNextPage(onSuccess: PhotoOnSuccess?, onFailure: PhotoOnFailure?) {
        
    }
}
