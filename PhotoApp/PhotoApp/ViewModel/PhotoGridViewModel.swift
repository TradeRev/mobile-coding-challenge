//
//  PhotoGridViewModel.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation
import UIKit

protocol PhotoGridViewModelProtocol {
    var networkService: NetworkService { get }
    var fetchingError: ((NetworkError) -> Void)? { get }
    var fetchingPhotos: (() -> Void)? { get }
    var didFinishFetch: ((_ photos: [Photo]) -> Void)? { get }
    func fetchPhotos(forPage page: Int)
}

class PhotoGridViewModel: PhotoGridViewModelProtocol {
    
    private(set) var photosSortType: NetworkConfiguration.SortedPhoto = .latest
    
    private var photos: [Photo] = []
    
    private let downloadGroup = DispatchGroup()
    
    var fetchingError: ((NetworkError) -> Void)?
    var fetchingPhotos: (() -> Void)?
    var didFinishFetch: ((_ photos: [Photo]) -> Void)?
    
    fileprivate(set) var state: State = .idle {
        didSet {
            DispatchQueue.main.async { 
                switch self.state {
                case .idle:
                    break
                case .fetching:
                    self.fetchingPhotos?()
                case .didFinishFetching(let photos):
                    self.didFinishFetch?(photos)
                    DispatchQueue.main.asyncAfter(deadline: .now() + 1, execute: {
                       self.photos = []
                    })
                case .fetchingError(let error):
                    self.fetchingError?(error)
                }
            }
        }
    }
    
    let networkService: NetworkService
    
    init(networkService: NetworkService = PhotoApiNetworkService()) {
        self.networkService = networkService
    }
    
}
extension PhotoGridViewModel {
    func fetchPhotos(forPage page: Int) {
        state = .fetching
        let photoApiService = networkService as! PhotoApiNetworkService
        let configuration = NetworkConfiguration.curatedPhotos(page: page, sortedBy: photosSortType)
        downloadGroup.enter()
        print(configuration.request.url!)
        photoApiService.fetchPhotos(with: configuration) { [weak self] (result) in
            guard let `self` = self else { return }
            switch result {
            case .success(let retrievedPhotos):
                self.photos = retrievedPhotos
                self.downloadGroup.leave()
            case .failure(let error):
                DispatchQueue.main.async {
                    self.state = .fetchingError(error)
                }
            }
        }
        downloadGroup.notify(queue: .main) {
            self.state = .didFinishFetching(self.photos)
        }
    }
    
}

extension PhotoGridViewModel {
    enum State {
        case idle
        case fetching
        case didFinishFetching([Photo])
        case fetchingError(NetworkError)
    }
}



