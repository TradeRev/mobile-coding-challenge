//
//  Result.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-19.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation

enum Result<T> {
    case success([T])
    case failure(NetworkError)
}

enum NetworkError: Error {
    case invalidStatusCode(Int)
    case requestFailed
    case invalidData
    case decodingError
    
    var localizedDescription: String {
        switch self {
        case .requestFailed:
            return NSLocalizedString("Invalid http response", comment: "Describing http response error")
        case .invalidData:
            return NSLocalizedString("Unable to get data from url", comment: "Describing nil data provided from url session")
        case .decodingError:
            return NSLocalizedString("Unable to decode JSON from data", comment: "Describing JSON decoding error")
        case .invalidStatusCode(let statusCode):
            return NSLocalizedString("Invalid response code, status code \(statusCode)", comment: "Describing http response error")
        }
    }
}
