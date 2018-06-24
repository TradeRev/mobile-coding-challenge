//
//  PhotoGridListDataProviderTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class PhotoGridListDataProviderTests: XCTestCase {
    
    var sut: PhotoGridListDataProvider!
    var collectionView: UICollectionView!
    var controller: PhotoGridListViewController!
    
    override func setUp() {
        super.setUp()
        sut = PhotoGridListDataProvider()
        
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        controller = storyboard.instantiateViewController(withIdentifier: "PhotoGridListViewController") as! PhotoGridListViewController
        _ = controller.view
        
        collectionView = controller.collectionView
        collectionView.dataSource = sut

    }
    
    func test_numberOfSections_isOne() {
        let numberOfSection = collectionView.numberOfSections
        XCTAssertEqual(numberOfSection, 1)
    }
    
    func test_numberOfItems_isInitiallyZero() {
        let numberOfRows = collectionView.numberOfItems(inSection: 0)
        XCTAssertEqual(numberOfRows, 0)
    }
    
    func test_numberOfItemsIncreasesByOne_whenPhotoIsAdded() {
        let photo = createFakePhoto()
        sut.photos.append(photo)
        let numberOfRows = collectionView.numberOfItems(inSection: 0)
        XCTAssertEqual(numberOfRows, 1)
    }
    
    func test_numberOfItemsIncreasesBy_amountPhotosAdded() {
        let photo1 = createFakePhoto(id: "a")
        let photo2 = createFakePhoto(id: "b")
      
        sut.photos.append(photo1)
        sut.photos.append(photo2)

        XCTAssertEqual(collectionView.numberOfItems(inSection: 0), 2)
        
        let photo3 = createFakePhoto(id: "c")
        let photo4 = createFakePhoto(id: "d")
        
        sut.photos.append(photo3)
        sut.photos.append(photo4)
        collectionView.reloadData()
       
        XCTAssertEqual(collectionView.numberOfItems(inSection: 0), 4)
        
    }
    
    func test_cellForRow_returnsPhotoGridCollectionCell() {
        let photo = createFakePhoto()
        sut.photos.append(photo)
        collectionView.reloadData()
        XCTAssertEqual(sut.photos.count, 1)
     
    let cell = collectionView.dataSource?.collectionView(collectionView, cellForItemAt: IndexPath.init(item: 0, section: 0))
        
        XCTAssertTrue(cell is PhotoGridCollectionCell)
       
    }
    

    // MARK:- Fakes
    func createFakePhoto(id: String = "", width: Int = 0, height: Int = 0, likes: Int = 0) -> Photo {
        let json: [String: Any] = ["id": id, "width": width,"height": height, "likes": likes]
        let fakeData = try! JSONSerialization.data(withJSONObject: json, options: .sortedKeys)
        let photo = try! JSONDecoder().decode(Photo.self, from: fakeData)
        return photo
    }
    
}













