//
//  UIImageView+Loader.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-20.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

extension UIImageView {
    func loadImage(url: String?, rotateIfNeeded: Bool = true) {
        guard let urlString = url else {
            self.image = nil
            return
        }
        
        guard let URL = URL(string: urlString) else {
            self.image = nil
            return
        }
        
        Alamofire.request(URL).responseImage { (response) in
            
            switch response.result {
            case .success(let value):
                if rotateIfNeeded && self.needRotateImage(originalImageSize: value.size) {
                    self.image = UIImage(cgImage: value.cgImage!, scale: 1.0, orientation: .left)
                } else {
                    self.image = value
                }
            case .failure(_):
                self.image = nil
            }
        }
    }
    
    func needRotateImage(originalImageSize: CGSize) -> Bool {
        
        let frameAspectRatio = frame.width / frame.height
        let imageAspectRatio = originalImageSize.width / originalImageSize.height
        
        return (frameAspectRatio < 1 && imageAspectRatio > 1) || (frameAspectRatio > 1 && imageAspectRatio < 1)
    }
}
