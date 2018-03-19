//
//  UnsplashAPI.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-19.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import Alamofire
import AlamofireObjectMapper

typealias UnsplashAPIOnSuccessCompletion = (UnsplashResponse) -> Void
typealias UnsplashAPIOnFailureCompletion = (Error) -> Void

class UnsplashAPI {
    
    fileprivate struct APIConsole {
        static let baseUrl = "https://api.unsplash.com"
        static let clientId = "5757e4791b1573bd316ebcf32e69f2942fb6d44809b62ac34d8bc8c151de6bbd"
        
        struct Path {
            static let photos = "/photos/curated"
        }
    }
    
    static func fetchPhotos(onSuccess: UnsplashAPIOnSuccessCompletion?, onfailure: UnsplashAPIOnFailureCompletion?) {
        
        let photoUrl = APIConsole.baseUrl + APIConsole.Path.photos
        let params = ["client_id": APIConsole.clientId]
        
        Alamofire.request(photoUrl, parameters: params).responseObject { (response: DataResponse<UnsplashResponse>) in
            switch response.result {
            case .success(let value):
                onSuccess?(value)
            case .failure(let error):
                onfailure?(error)
            }
        }
    }
}

