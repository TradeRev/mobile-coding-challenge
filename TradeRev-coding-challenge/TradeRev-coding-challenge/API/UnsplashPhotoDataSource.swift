//
//  UnsplashPhotoDataSource.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-20.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import Foundation

class UnsplashPhotoDataSource: PhotoDataSource {
    
    var photos: [UnsplashPhoto] = [UnsplashPhoto]()
    var currentItem: Int = 0
    
    private var currentAPIPage: Int = 1
    private var isLoadingNextPage = false
    
    static func load(onSuccess: PhotoOnSuccess?, onFailure: PhotoOnFailure?) {
        UnsplashAPI.fetchPhotos(onSuccess: { (photos) in
            let dataSource = UnsplashPhotoDataSource()
            dataSource.photos = photos
            onSuccess?(dataSource)
        }, onfailure: onFailure)
    }
    
    func loadNextPage(onCompletion: (() -> Void)?) {
        if isLoadingNextPage {
            return
        }
        
        isLoadingNextPage = true
        
        UnsplashAPI.fetchPhotos(page: currentAPIPage + 1, onSuccess: { (photos) in
            self.photos.append(contentsOf: photos)
            self.currentAPIPage += 1
            self.isLoadingNextPage = false
            onCompletion?()
        }) { (_) in
            self.isLoadingNextPage = false
            onCompletion?()
        }
    }
}
