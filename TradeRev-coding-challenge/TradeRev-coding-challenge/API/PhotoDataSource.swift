//
//  PhotoDataSource.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-20.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import Foundation

typealias PhotoOnSuccess = (PhotoDataSource) -> Void
typealias PhotoOnFailure = (Error) -> Void

protocol PhotoDataSource: class {
    
    var photos: [UnsplashPhoto] { get }
    var currentItem: Int { get set }
    
    static func load(onSuccess: PhotoOnSuccess?, onFailure: PhotoOnFailure?)
    
    func loadNextPage(onCompletion: (() -> Void)?)
}
