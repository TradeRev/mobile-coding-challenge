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
        didSet { layoutGrid() }
    }
    
    var dataSource: PhotoDataSource? {
        didSet { reloadData() }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        createdConstraints()
        loadDataSource()
    }
    
    fileprivate func createdConstraints() {
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            collectionView.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            collectionView.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            collectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            collectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            collectionView.heightAnchor.constraint(equalToConstant: Device.screenHeight / 2)
            ])
    }
    
    fileprivate func loadDataSource() {
        UnsplashPhotoDataSource.load(onSuccess: { (photoDataSource) in
            self.dataSource = photoDataSource
        }) { (error) in
            self.popAlertWith(message: error.localizedDescription)
        }
    }
    
    fileprivate func layoutGrid() {
        let layout = collectionView.collectionViewLayout as? UICollectionViewFlowLayout
        layout?.itemSize = CGSize(width: Device.screenWidth, height: floor(Device.screenHeight / 2))
        layout?.minimumLineSpacing = 0
    }
    
    fileprivate func reloadData() {
        collectionView.reloadData()
    }
    
    fileprivate func popAlertWith(message: String?) {
        let alertController = UIAlertController(title: "Sorry.....", message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: "Ok", style: .cancel)
        alertController.addAction(okAction)
        
        present(alertController, animated: true)
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
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: GridCollectionViewCell.identifier, for: indexPath) as? GridCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        let imageUrl = dataSource?.photos[indexPath.item].urls?.regular
        cell.update(with: imageUrl)
        
        return cell
    }
}
