//
//  NetworkTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-19.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class NetworkTests: XCTestCase {
    
    var sut: PhotoApiNetworkService!
    let mockSession = MockUrlSession()
    
    override func setUp() {
        super.setUp()
        sut = PhotoApiNetworkService(session: mockSession)
    }
    
    func test_networkServiceCallsResume_whenFetching() {
        let dataTask = MockUrlSessionDataTask()
        mockSession.sessionDataTask = dataTask
        sut.fetchPhotos(with: makeNetworkConfiguration()) { _ in }
        XCTAssertTrue(dataTask.resumeCalled)
    }
    
    func test_networkServicePhotoFetch_invokesCompletion() {
        let dataTask = MockUrlSessionDataTask()
        mockSession.sessionDataTask = dataTask
        mockSession.sessionDataTask.resume()
        let expectation = XCTestExpectation(description: "Completion Handler Invoked")
        sut.fetchPhotos(with: makeNetworkConfiguration()) { (result) in
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 5)
    }

    func test_networkService_fetchesPhoto() {
        var photos: [Photo] = []

        guard let url = URL(string: "https://api.unsplash.com/photos/?client_id=f92a7e8c58fcd64f17093ef038ad28548cbca5971d84b60c0fd8401ae9619004") else {
            XCTFail("Invalid url provided")
            return
        }

        guard let data = try? Data(contentsOf: url) else {
            XCTFail("Invalid data provided")
            return
        }
        mockSession.data = data

        let expectation = XCTestExpectation(description: "Photo fetch")
        var _error: Error?

        sut.fetchPhotos(with: makeNetworkConfiguration()) { (result) in
            switch result {
            case .success(let fetchedPhotos):
                photos = fetchedPhotos
            case .failure(let error):
                _error = error
            }
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 5)
        XCTAssertNil(_error)
        XCTAssertTrue(photos.count > 0)
    }
    
    
    func makeNetworkConfiguration(_ page: Int = 1, _ sortedBy: NetworkConfiguration.SortedPhoto = .latest) -> NetworkConfiguration {
        return NetworkConfiguration.curatedPhotos(page: page, sortedBy: sortedBy)
    }
    
    //MARK:- Mocks 
    class MockUrlSession: URLSessionProtocol {
        var sessionDataTask = MockUrlSessionDataTask()
        var data: Data?
        var response: URLResponse?
        var error: Error?
        private(set) var assignedUrl: URL?
        
        func dataTask(with: URLRequest, completionHandler: @escaping URLSessionProtocol.DataTaskCompletion) -> URLSessionDataTaskProtocol {
            assignedUrl = with.url!
            let response = fakeHttpResponse(request: with)
            completionHandler(data, response, error)
            return sessionDataTask
        }
        
    }
    
    class MockUrlSessionDataTask: URLSessionDataTaskProtocol {
        private(set) var resumeCalled = false
        func resume() {
            resumeCalled = true
        }
    }
    
    // Source Apple Documentation
    class func fakeHttpResponse(request: URLRequest) -> URLResponse? {
        return HTTPURLResponse(url: request.url!, statusCode: 200, httpVersion: "HTTP/1.1", headerFields: nil)
    }
    
}


