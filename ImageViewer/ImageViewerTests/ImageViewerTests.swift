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
  
  func testPhotoGrid() {
    let fulfill = expectation("test")
    class TestPresenter: PhotoGridPresentationLogic {
      
      let fulfill:()->Void
      
      init(_ fulfill: @escaping (()->Void)) {
        self.fulfill = fulfill
      }
      
      func presentRefreshPhotos(_ indexes: CountableRange<Int>) {
        fulfill()
      }
      
      func presentError(error: PhotoGrid.Error) {
        XCTFail()
      }
    }
    
    let presenter = TestPresenter(fulfill)
    let interactor = PhotoGridInteractor()
    interactor.presenter = presenter
    
    interactor.doWithdrawPhotos(request: PhotoGrid.Request())
    
    XCTAssert(wait() == nil)
  }
}

