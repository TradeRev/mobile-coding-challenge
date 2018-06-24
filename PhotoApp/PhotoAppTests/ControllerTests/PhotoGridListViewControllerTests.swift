//
//  PhotoGridListViewControllerTests.swift
//  PhotoAppTests
//
//  Created by Michael A on 2018-06-23.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import XCTest
@testable import PhotoApp

class PhotoGridListViewControllerTests: XCTestCase {
    
    var sut: PhotoGridListViewController!
    
    override func setUp() {
        super.setUp()
        
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: "PhotoGridListViewController") as! PhotoGridListViewController
        sut = viewController
        _ = sut.view
    }
    
    func test_afterViewLoads_collectionViewIsNotNil() {
        XCTAssertNotNil(sut.collectionView)
    }
    
    func test_afterViewLoads_setsDatasource() {
        XCTAssertTrue(sut.collectionView.dataSource is PhotoGridListDataProvider)
    }
    
    func test_afterViewLoads_setsDelegateSource() {
        XCTAssertTrue(sut.collectionView.delegate is PhotoGridListDelegateSource)
    }
    
}
