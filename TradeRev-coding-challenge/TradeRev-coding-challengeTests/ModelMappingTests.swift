//
//  TradeRev_coding_challengeTests.swift
//  TradeRev-coding-challengeTests
//
//  Created by ZHITAO TIAN on 2018-03-19.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import XCTest
import ObjectMapper
@testable import TradeRev_coding_challenge

class TradeRev_coding_challengeTests: XCTestCase {
    
    override func setUp() {
        super.setUp()
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testPhotoMapping() {
        let json: [String: Any] = [
            "id": "1111",
            "created_at": "2018-09-18",
            "width": "100",
            "height": "100",
            "description": "image",
            "urls": "fake urls",
            "sponsored": true]
        
        let response = Mapper<UnsplashPhoto>().map(JSON: json)
        
        XCTAssertNotNil(response?.createdAt)
        XCTAssertNotNil(response?.description)
        XCTAssertEqual(response?.sponsored, true)
    }
    
    func testUrlMapping() {
        let json: [String: Any] = [
            "raw": "raw url",
            "full": "full url",
            "regular": "regular url",
            "small": "small url",
            "thumb": "thumb url"]
        
        let response = Mapper<PhotoUrl>().map(JSON: json)
        
        XCTAssertNotNil(response?.full)
        XCTAssertNotNil(response?.regular)
    }
    
}
