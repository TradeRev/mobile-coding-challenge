//
//  PhotoCell.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-09.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

class PhotoCell: UICollectionViewCell {
    
  @IBOutlet weak var photoImage: UIImageView!
  
  func configureCell(with URLString: String, placeholderImage: UIImage) {
    
    backgroundColor = UIColor.white
    
    let size = photoImage.frame.size
    
    photoImage.af_setImage(withURL: URL(string: URLString)!, placeholderImage: placeholderImage, filter: AspectScaledToFillSizeWithRoundedCornersFilter(size: size, radius: 20.0), imageTransition: .crossDissolve(0.2)
    )
  }
  
  override func prepareForReuse() {
    super.prepareForReuse()
    
    photoImage.af_cancelImageRequest()
    photoImage.layer.removeAllAnimations()
    photoImage.image = nil
  }
}
