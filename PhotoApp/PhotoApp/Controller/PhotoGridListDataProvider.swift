//
//  PhotoGridListDataProvider.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation
import UIKit

private let cellIdentifier = "GridCollectionCell"

class PhotoGridListDataProvider: NSObject {
    var photos: [Photo] = []
}

extension PhotoGridListDataProvider: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return photos.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        switch Section.init(rawValue: indexPath.section)! {
        case .allPhotos:
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: cellIdentifier, for: indexPath) as! PhotoGridCollectionCell
            let photo = photos[indexPath.item]
            let photoViewModel = PhotoGridCellViewModel(photo)
            cell.displayPhoto(with: photoViewModel)
            return cell
        }
    }
}

extension PhotoGridListDataProvider {
    enum Section: Int {
        case allPhotos = 0
    }
}




