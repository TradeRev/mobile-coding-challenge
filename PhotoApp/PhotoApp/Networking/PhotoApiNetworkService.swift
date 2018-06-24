//
//  PhotoApiNetworkService.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-19.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation

class PhotoApiNetworkService: NetworkService {
    let session: URLSessionProtocol
    
    init(session: URLSessionProtocol = URLSession.shared) {
        self.session = session
    }
    
    func fetchPhotos(with configuration: NetworkConfiguration, _ completion: @escaping (Result<Photo>) -> Void) {
        let request = configuration.request
        fetch(with: request, completion: completion)
    }
}
