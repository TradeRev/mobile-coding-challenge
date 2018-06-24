//
//  PhotoGridViewModelTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class PhotoGridViewModelTests: XCTestCase {
    
    let mockPhotoGridViewModel = MockPhotoGridViewModel()
    
    override func setUp() {
        super.setUp()
    }
    
    func test_networkServiceProvider_isPhotoApiNetworkService() {
        XCTAssertTrue(mockPhotoGridViewModel.networkService is PhotoApiNetworkService, "Expected PhotoApiNetworkService to be the network provider")
    }
    
    func test_fetchPhotos_gotCalled() {
        mockPhotoGridViewModel.fetchPhotos(forPage: 1)
        XCTAssertTrue(mockPhotoGridViewModel.fetchPhotosCalled)
    }
    
    func test_didFinishFetch_returnsFetchedPhotos() {
        let json: [String : Any] = ["id": "adbc123", "likes": 1]
        let fakeData = try! JSONSerialization.data(withJSONObject: json, options: .sortedKeys)
        let fakePhoto = try! JSONDecoder().decode(Photo.self, from: fakeData)
        let photos = [Photo](repeating: fakePhoto, count: 5)
        
        mockPhotoGridViewModel.didFinishFetch?(photos)
        
        mockPhotoGridViewModel.didFinishFetch = { photos in
            XCTAssertTrue(photos.count > 0)
        }
    }
    
    func test_fetchingError_returnsError() {
        let error = NetworkError.requestFailed
        var fetchedError: NetworkError?
        mockPhotoGridViewModel.fetchingError?(error)
        mockPhotoGridViewModel.fetchingError = { error in
            fetchedError = error
            XCTAssertEqual(fetchedError?.localizedDescription, error.localizedDescription)
        }
    }
    
    class MockPhotoGridViewModel: PhotoGridViewModelProtocol {
        var fetchingError: ((NetworkError) -> Void)?
        var fetchingPhotos: (() -> Void)?
        var didFinishFetch: (([Photo]) -> Void)?
        private(set) var fetchPhotosCalled = false
        
        let networkService: NetworkService
        
        init(networkService: NetworkService = PhotoApiNetworkService()) {
            self.networkService = networkService
        }
        
        func fetchPhotos(forPage page: Int) {
            fetchPhotosCalled = true
        }
    }
    
}
