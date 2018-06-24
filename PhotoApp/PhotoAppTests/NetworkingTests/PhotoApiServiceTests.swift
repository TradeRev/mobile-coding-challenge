//
//  PhotoApiServiceTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-19.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class PhotoApiServiceTests: XCTestCase {
    
    func test_photoApiCalls_fetchPhotos() {
        let service = MockPhotoApiNetworkService()
        let configuration = NetworkConfiguration.curatedPhotos(page: 1, sortedBy: .latest)
        service.fetchPhotos(with: configuration) { _ in }
        XCTAssertTrue(service.fetchGotCalled)
    }
    
    class MockPhotoApiNetworkService: PhotoApiNetworkService {
        private(set) var fetchGotCalled = false
        
        override func fetchPhotos(with configuration: NetworkConfiguration, _ completion: @escaping (Result<Photo>) -> Void) {
            fetchGotCalled = true
        }
    }
}

