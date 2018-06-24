//
//  ResultTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-20.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class ResultTests: XCTestCase {
    
    func test_networkResultErrorCases_returnsCorrectErrorOutputs() {
        XCTAssertEqual(generateDecodingError().localizedDescription, "Unable to decode JSON from data")
        XCTAssertEqual(generateInvalidDataError().localizedDescription, "Unable to get data from url")
        XCTAssertEqual(generateRequestFailedError().localizedDescription, "Invalid http response")
        XCTAssertEqual(generateInvalidStatusCodeError(404).localizedDescription, "Invalid response code, status code 404")

    }
    
    func generateInvalidDataError() -> NetworkError {
        return .invalidData
    }
    
    func generateDecodingError() -> NetworkError {
        return .decodingError
    }
    
    func generateRequestFailedError() -> NetworkError {
        return .requestFailed
    }
    
    func generateInvalidStatusCodeError(_ code: Int) -> NetworkError {
        return .invalidStatusCode(code)
    }
    
}
