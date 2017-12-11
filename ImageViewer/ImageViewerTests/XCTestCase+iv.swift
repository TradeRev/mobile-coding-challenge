//
//  XCTestCase+iv.swift
//  ImageViewerTests
//
//  Created by Zakhar Sukhanov on 2017-12-08.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit
import XCTest

extension XCTestCase {
  // 30 seconds isn't always enough on old phones to generate an RSA key...
  func wait(_ seconds: TimeInterval = 60) -> Error? {
    var err: Error? = nil
    waitForExpectations(timeout: seconds) { (error) -> Void in
      err = error
    }
    return err
  }
  
  func expectation(_ description: String) -> (()->Void) {
    weak var exp = expectation(description: description)
    return {
      let strongExp = exp
      exp = nil
      strongExp?.fulfill()
    }
  }
}
