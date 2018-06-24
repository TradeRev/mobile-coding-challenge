//
//  PhotoTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-19.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class PhotoTests: XCTestCase {
    
    fileprivate var validFields: ValidFields!
    fileprivate var invalidFields: InvalidFields!
    
    override func setUp() {
        super.setUp()
        
        validFields = ValidFields()
        invalidFields = InvalidFields()
    }
    
    func test_photoModelCorrectly_decodesData() {
        let json = ValidFields.completeFields
        let photo = PhotoStubGenerator.createPhoto(with: json)!
        XCTAssertEqual(photo.id, "12345")
        XCTAssertEqual(photo.height, 200)
        XCTAssertEqual(photo.width, 300)
        XCTAssertEqual(photo.likes, 50)
    }
    
    func test_photoModelDecodes_withIncompleteFields() {
        let incompleteJson = InvalidFields.incompleteFields
        let photo = PhotoStubGenerator.createPhoto(with: incompleteJson)
        XCTAssertNotNil(photo)
    }
    
    func test_photoModel_doesnotAddDuplicatePhotos() {
        var photos: [Photo] = []
        
        let json1 = ValidFields.completeFields
        let json2 = ValidFields.completeFields
        let photo1 = PhotoStubGenerator.createPhoto(with: json1)!
        let photo2 = PhotoStubGenerator.createPhoto(with: json2)!
        
        XCTAssertEqual(photo1, photo2)
        photos.append(photo1)
        if !photos.contains(photo2) {
            photos.append(photo2)
        }
        
        XCTAssertEqual(photos.count, 1)
    }
    
    //Mark:- Stubs
    struct PhotoStubGenerator {
        static func createPhoto(with json: [String: Any]) -> Photo? {
            guard let data = try? JSONSerialization.data(withJSONObject: json, options: .prettyPrinted) else {
                XCTFail("expected valid data")
                return nil
            }
            guard let photo = try? JSONDecoder().decode(Photo.self, from: data) else {
                return nil
            }
            return photo
        }
    }
    
}

private extension PhotoTests {
    struct ValidFields {
        static let completeFields: [String: Any]  = [
            "id": "12345",
            "height": 200,
            "width": 300,
            "updated_at": "2018-06-17T20:28:11-04:00",
            "created_at": "2018-06-17T20:28:11-04:00",
            "urls": [
                "raw": "https://www.myImage.com/raw",
                "full": "https://www.myImage.com/full",
                "regular": "https://www.myImage.com/regular",
                "small": "https://www.myImage.com/small",
                "thumb": "https://www.myImage.com/thumb"
            ],
            "likes": 50
        ]
    }
    struct InvalidFields {
        static let incompleteFields: [String: Any]  = [
            "id": "12345",
            "height": 200,
            "width": 300,
            "creation_date": "2018-06-17T20:28:11-04:00"
        ]
        
        static let missingFields: [String: Any] = [:]
    }
}




