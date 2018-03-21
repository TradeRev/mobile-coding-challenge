//
//  FullImageViewController.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-21.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import UIKit

class FullImageViewController: UIViewController {

    var dataSource: PhotoDataSource?
    
    @IBOutlet weak var collectionView: UICollectionView! {
        didSet {
            collectionView.register(GridCollectionViewCell.self, forCellWithReuseIdentifier: GridCollectionViewCell.reuseIdentifier)
            layoutGrid()
        }
    }
    
    @IBOutlet weak var descriptionLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        reloadData()
    }
    
    fileprivate func reloadData() {
        collectionView.reloadData()
        
        let currentItem         = dataSource?.currentItem ?? 0
        let currentIndexPath    = IndexPath(item: currentItem, section: 0)
        
        reloadDescription()
        
        collectionView.scrollToItem(at: currentIndexPath, at: .centeredHorizontally, animated: false)
    }
    
    fileprivate func reloadDescription() {
        let currentItem         = dataSource?.currentItem ?? 0
        let currentPhoto        = dataSource?.photos[currentItem]
        descriptionLabel.text   = photoDescription(from: currentPhoto)
    }
    
    fileprivate func layoutGrid() {
        let layout = collectionView.collectionViewLayout as? UICollectionViewFlowLayout
        layout?.itemSize = CGSize(width: Device.screenWidth, height: Device.screenHeight)
        layout?.minimumLineSpacing = 0
    }
    
    fileprivate func photoDescription(from photo: UnsplashPhoto?) -> String {
        var description: String = ""
        if let des = photo?.description {
            description = des
        }
        let appending = description.isEmpty ? photo?.createdAt ?? "" : "\n\(photo?.createdAt ?? "")"
    
        description.append(appending)
        
        return description
    }
    
    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        collectionView.reloadData()
    }
}

extension FullImageViewController: UICollectionViewDataSource, UICollectionViewDelegate {
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return dataSource?.photos.count ?? 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: GridCollectionViewCell.reuseIdentifier, for: indexPath) as? GridCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        let imageUrl = dataSource?.photos[indexPath.item].urls?.full
        cell.update(with: imageUrl)
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        dataSource?.currentItem = indexPath.item
        presentingViewController?.dismiss(animated: true)
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if scrollView.bounds.maxX >= scrollView.contentSize.width {
            dataSource?.loadNextPage(onCompletion: {
                self.reloadData()
            })
        }
    }
    
    func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
        let offsetX = scrollView.contentOffset.x
        let width = Device.screenWidth
        
        if dataSource?.currentItem != Int(offsetX / width) {
            dataSource?.currentItem = Int(offsetX / width)
            reloadDescription()
        }
    }
}
