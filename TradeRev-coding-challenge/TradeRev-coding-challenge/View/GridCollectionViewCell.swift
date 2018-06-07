//
//  GridCollectionViewCell.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-20.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import UIKit

class GridCollectionViewCell: UICollectionViewCell, Reusable {
    
    lazy var gridImageView: UIImageView = {
        let imageView                                       = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.contentMode                               = .scaleAspectFit
        
        return imageView
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        contentView.addSubview(gridImageView)
        createConstraints()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func update(with imageUrl: String?) {
        gridImageView.loadImage(url: imageUrl)
    }
    
    fileprivate func createConstraints() {
        NSLayoutConstraint.activate([
            gridImageView.topAnchor.constraint(equalTo: contentView.topAnchor),
            gridImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            gridImageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor),
            gridImageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor)
        ])
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        gridImageView.af_cancelImageRequest()
        gridImageView.image = nil
    }
}
