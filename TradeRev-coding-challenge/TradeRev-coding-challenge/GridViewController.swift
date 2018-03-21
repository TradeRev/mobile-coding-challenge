//
//  GridViewController.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-19.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import UIKit

class GridViewController: UIViewController {

    @IBOutlet weak var collectionView: UICollectionView! {
        didSet {
            collectionView.register(GridCollectionViewCell.self, forCellWithReuseIdentifier: GridCollectionViewCell.reuseIdentifier)
        }
    }
    
    @IBOutlet weak var loadingIndicator: UIActivityIndicatorView!
    
    var dataSource: PhotoDataSource? {
        didSet { reloadData() }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loadDataSource()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        reloadData()
    }
    
    fileprivate func loadDataSource() {
        loadingIndicator.startAnimating()
        UnsplashPhotoDataSource.load(onSuccess: { (photoDataSource) in
            self.loadingIndicator.stopAnimating()
            self.dataSource = photoDataSource
        }) { (error) in
            self.loadingIndicator.stopAnimating()
            self.popAlertWith(message: error.localizedDescription)
        }
    }
    
    fileprivate func reloadData() {
        
        guard let dataSource = dataSource else {
            return
        }
        
        collectionView.reloadData()
        
        let currentItem = dataSource.currentItem
        let currentIndexPath = IndexPath(item: currentItem, section: 0)
        
        collectionView.scrollToItem(at: currentIndexPath, at: .centeredHorizontally, animated: false)
    }
    
    fileprivate func popAlertWith(message: String?) {
        let alertController = UIAlertController(title: "Sorry.....", message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: "Ok", style: .cancel)
        alertController.addAction(okAction)
        
        present(alertController, animated: true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let fullImageViewController = segue.destination as? FullImageViewController
        
        fullImageViewController?.dataSource             = dataSource
        fullImageViewController?.transitioningDelegate  = self
    }
    
    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        collectionView.reloadData()
    }
}

extension GridViewController: UICollectionViewDataSource, UICollectionViewDelegate {
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
        
        let imageUrl = dataSource?.photos[indexPath.item].urls?.regular
        cell.update(with: imageUrl)
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        dataSource?.currentItem = indexPath.item
        
        let segueIdentifier = "fullImageSegue"
        performSegue(withIdentifier: segueIdentifier, sender: nil)
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
        let width = Device.isPortrait ? Device.screenWidth : Device.screenWidth / 2
        
        if dataSource?.currentItem != Int(offsetX / width) {
            dataSource?.currentItem = Int(offsetX / width)
        }
    }
}

extension GridViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let itemSize = Device.isPortrait ? CGSize(width: Device.screenWidth, height: floor(Device.screenHeight / 2)) : CGSize(width: Device.screenWidth / 2, height: floor(Device.screenHeight))
        return itemSize
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0.0
    }
}

extension GridViewController: UIViewControllerTransitioningDelegate {
    func animationController(forPresented presented: UIViewController, presenting: UIViewController, source: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        return CustomAnimator()
    }
    
    func animationController(forDismissed dismissed: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        return CustomAnimator()
    }
}
