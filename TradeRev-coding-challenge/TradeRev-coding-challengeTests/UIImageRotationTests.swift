//
//  UIImageRotationTests.swift
//  TradeRev-coding-challengeTests
//
//  Created by ZHITAO TIAN on 2018-03-21.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import XCTest
@testable import TradeRev_coding_challenge

class UIImageRotationTests: XCTestCase {
    
    override func setUp() {
        super.setUp()
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testImageDoesNeedRotate() {
        let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 2, height: 3))
        let needsRotate = imageView.needRotateImage(originalImageSize: CGSize(width: 3, height: 2))
        
        XCTAssertTrue(needsRotate)
    }
    
    func testImageDoesNotNeedRotate() {
        let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 2, height: 3))
        let needsRotate = imageView.needRotateImage(originalImageSize: CGSize(width: 2, height: 3))
        
        XCTAssertFalse(needsRotate)
    }
    
}
