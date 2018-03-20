//
//  GridCollectionViewCell.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-20.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import UIKit

class GridCollectionViewCell: UICollectionViewCell {
    
    static let identifier = "gridCell"
    
    @IBOutlet weak var gridImageView: UIImageView!
    
    func update(with imageUrl: String?) {
        gridImageView.loadImage(url: imageUrl)
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        gridImageView.af_cancelImageRequest()
        gridImageView.image = nil
    }
}
