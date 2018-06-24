//
//  PhotoGridCellViewModelTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-24.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class PhotoGridCellViewModelTests: XCTestCase {
    
    func test_initSetsID_whenGivenID() {
        let photo = Photo(id: "abc123", createDate: nil, updatedDate: nil, width: nil, height: nil, imageUrls: nil, likes: nil)
        let viewModel = PhotoGridCellViewModel(photo)
        XCTAssertEqual(viewModel.id, "abc123")
    }
    
    func test_initSetsCreateData_whenGivenCreateDate() {
        let photo = createPhoto(createDate: "2018")
        let viewModel = PhotoGridCellViewModel(photo)
        XCTAssertEqual(viewModel.createDate, "2018")
    }
    
    func test_initSetsUpdatedDate_whenGivenUpdatedDate() {
        let photo = createPhoto(updatedDate: "2018")
        let viewModel = PhotoGridCellViewModel(photo)
        XCTAssertEqual(viewModel.updatedDate, "2018")
    }
    
    func test_initSetsWidth_whenGivenWidth() {
        let photo = createPhoto(width: 100)
        let viewModel = PhotoGridCellViewModel(photo)
        XCTAssertEqual(viewModel.width, 100)
    }
    
    func test_initSetsHeight_whenGivenHeight() {
        let photo = createPhoto(height: 200)
        let viewModel = PhotoGridCellViewModel(photo)
        XCTAssertEqual(viewModel.height, 200)
    }
    
    func test_initSetsLikes_whenGivenLikes() {
        let photo = createPhoto(likes: 50)
        let viewModel = PhotoGridCellViewModel(photo)
        XCTAssertEqual(viewModel.likes, 50)
    }
    
    func createPhoto(withId id: String = "", createDate: String = "", updatedDate: String = "", width: Int = 0, height: Int = 0, likes: Int = 0) -> Photo {
        return Photo(id: id, createDate: createDate, updatedDate: updatedDate, width: width, height: height, imageUrls: nil, likes: likes)
    }
    
}




