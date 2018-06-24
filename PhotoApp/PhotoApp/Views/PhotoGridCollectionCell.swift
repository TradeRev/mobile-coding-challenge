//
//  PhotoGridCollectionCell.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import UIKit

class PhotoGridCollectionCell: UICollectionViewCell {
    
    @IBOutlet weak var imageView: UIImageView!
    
    func displayPhoto(with viewModel: PhotoGridCellViewModel) {
        imageView.loadImageCache(with: viewModel.imageUrls?.thumb.absoluteString ?? "", completion: nil)
    }
}
