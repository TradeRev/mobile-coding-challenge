//
//  NetworkConfigurationTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class NetworkConfigurationTests: XCTestCase {
    
    func test_apiEndPoint_correctlySetsBaseUrl_whenGivenBaseUrl() {
        let baseURL = "https://test.com"
        XCTAssertEqual(createMockEndPoint(baseURL: baseURL).baseUrl, baseURL)
    }
    
    func test_apiEndPoint_correctlySetsUrlPath_whenGivenUrlPath () {
        let path = "/testPath"
        XCTAssertEqual(createMockEndPoint(path: path).path, path)
    }
    
    func test_apiEndPoint_correctlySetsQueryItems_whenGivenQueryItems() {
        let queryItems: [URLQueryItem] = [
            URLQueryItem(name: "qKey", value: "qValue"),
            URLQueryItem(name: "q2Key", value: "q2Value"),
            ]
        XCTAssertEqual(createMockEndPoint(queryItems: queryItems).urlQueryItems.count, 2)
    }
    
    func test_apiEndPoint_returnsCorrectComponents_whenGivenURLComponents() {
        let baseURL = "https://test.com"
        let path = "/testPath"
        let queryItems: [URLQueryItem] = [
            URLQueryItem(name: "qKey", value: "qValue"),
            ]
        
        let mockEndPoint = createMockEndPoint(baseURL: baseURL, path: path, queryItems: queryItems)
        
        let queryItem = "\(queryItems.first!.name)=\(queryItems.first!.value!)"
        let urlString = "\(baseURL)\(path)?\(queryItem)"
        let apiURL = URL(string: urlString)
        
        XCTAssertEqual(mockEndPoint.urlComponents.queryItems!, queryItems)
        XCTAssertEqual(mockEndPoint.urlComponents.path, path)
        XCTAssertEqual(mockEndPoint.urlComponents.url, apiURL)
    }
    
    func createMockEndPoint(baseURL: String = "", path: String = "", queryItems: [URLQueryItem] = []) -> MockApiEndPoint {
        return MockApiEndPoint(baseUrl: baseURL, path: path, urlQueryItems: queryItems)
    }
    
    struct MockApiEndPoint: NetworkEndPoint {
        var baseUrl: String
        var path: String
        var urlQueryItems: [URLQueryItem]
    }
}
