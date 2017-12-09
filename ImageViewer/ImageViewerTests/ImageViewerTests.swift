//
//  ImageViewerTests.swift
//  ImageViewerTests
//
//  Created by Zakhar Sukhanov on 2017-12-08.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import XCTest
@testable import ImageViewer

class ImageViewerTests: XCTestCase {
    
    override func setUp() {
        super.setUp()
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testAuthentication() {
      let authServ = UnsplashAuthenticationService()
      
      let fulfill = expectation("test")
      authServ.authorize(applicationID: "bfd715955275ab1cb898a4b5841009491fbc9d24e46f0cdcd5252b694b2e426f").then { (Void) -> Void in
          fulfill()
        }.catch { (err) in
          Log.error("ERROR: \((err as? IVError)?.description)")
          return XCTFail()
      }
      
      XCTAssert(wait() == nil)
    }
}
