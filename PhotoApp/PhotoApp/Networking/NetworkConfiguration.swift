//
//  NetworkConfiguration.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation

import Foundation

protocol NetworkEndPoint {
    var baseUrl: String { get }
    var path: String { get }
    var urlQueryItems: [URLQueryItem] { get }
}

extension NetworkEndPoint {
    var urlComponents: URLComponents {
        var components = URLComponents(string: baseUrl)
        components?.path = path
        components?.queryItems = urlQueryItems
        return components!
    }
    
    var request: URLRequest {
        return URLRequest(url: urlComponents.url!)
    }
}

enum NetworkConfiguration: NetworkEndPoint {
    case curatedPhotos(page: Int, sortedBy: SortedPhoto)
    
    static var photosPerPage = 28
    
    var baseUrl: String {
        return "https://api.unsplash.com"
    }
    
    var apiKey: String {
        return "f92a7e8c58fcd64f17093ef038ad28548cbca5971d84b60c0fd8401ae9619004"
    }
    
    var path: String {
        switch self {
        case .curatedPhotos:
            return "/photos/curated"
        }
    }
    
    var urlQueryItems: [URLQueryItem] {
        switch self {
        case .curatedPhotos(let page, let sorted):
            return [URLQueryItem(name: ParameterKeys.clientId.rawValue, value: apiKey),
                    URLQueryItem(name: ParameterKeys.page.rawValue, value: String(page)),
                    URLQueryItem(name: ParameterKeys.orderBy.rawValue, value: sorted.rawValue),
                    URLQueryItem(name: ParameterKeys.perPage.rawValue, value: String(NetworkConfiguration.photosPerPage))
            ]
        }
    }
}

extension NetworkConfiguration {
    private enum ParameterKeys: String {
        case clientId = "client_id"
        case page = "page"
        case orderBy = "order_by"
        case perPage = "per_page"
    }
    
    enum SortedPhoto: String {
        case latest = "latest"
        case oldest = "oldest"
        case popular = "popular"
    }
}
