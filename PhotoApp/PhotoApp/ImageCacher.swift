//
//  ImageCacher.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-23.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import UIKit

public protocol ImageCachable {}

extension UIImageView: ImageCachable {}

public let imageCache = NSCache<NSString, UIImage>()

extension ImageCachable where Self: UIImageView {
    typealias completionHandler = ((Bool) -> ())
    
    func loadImageCache(with urlString: String, completion: completionHandler?) {
        let group = DispatchGroup()
        self.image = nil
        if let cachedImage = imageCache.object(forKey: NSString(string: urlString)) {
            DispatchQueue.main.async {
                self.image = cachedImage
                completion?(true)
            }
            return
        }
        if let url = URL.init(string: urlString) {
            group.enter()
            DispatchQueue.global(qos: .background).async {
                if let imageData = try? Data.init(contentsOf: url) {
                    if let image = UIImage(data: imageData) {
                        imageCache.setObject(image, forKey: NSString.init(string: urlString))
                        DispatchQueue.main.async {
                            self.image = image
                            group.leave()
                        }
                    }
                }
            }
            group.notify(queue: .main, execute: {
                completion?(true)
            })
        }
    }
}
