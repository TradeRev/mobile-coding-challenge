//
//  PhotoGridListViewController.swift
//  PhotoApp
//
//  Created by Michael A on 2018-06-21.
//  Copyright © 2018 AI Labs. All rights reserved.
//

import Foundation
import UIKit

class PhotoGridListViewController: UIViewController {
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    private let datasource = PhotoGridListDataProvider()
    private let delegateSource = PhotoGridListDelegateSource()
    private let viewModel = PhotoGridViewModel()
    private var page = 1
    private let photosPerPage = NetworkConfiguration.photosPerPage

    override func viewDidLoad() {
        super.viewDidLoad()
        setupCollectionView()
        
        viewModel.fetchPhotos(forPage: page)
        viewModel.didFinishFetch = { [weak self] photos in
            guard let `self` = self else { return }
            photos.forEach { if !self.datasource.photos.contains($0) { self.datasource.photos.append($0) } }
            self.collectionViewReloading()
        }
        
        viewModel.fetchingPhotos = { print("fetching photos...") }
        addObservers()
    }
    
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
    
    @objc private func loadMorePhotos() {
        page += 1
        viewModel.fetchPhotos(forPage: page)
    }
    
    private func addObservers() {
        NotificationCenter.default.addObserver(self, selector: #selector(loadMorePhotos), name: .fetchPhotos, object: nil)
    }
    
    private func setupCollectionView() {
        collectionView.dataSource = datasource
        collectionView.delegate = delegateSource
    }
    
}

extension PhotoGridListViewController {
    private func collectionViewReloading() {
        let lastIndex = datasource.photos.count - 1
        let previousIndexStart = lastIndex - (photosPerPage - 1)
        var indexPaths: [IndexPath] = []
        
        for index in previousIndexStart...lastIndex {
            let indexPath = IndexPath.init(item: index, section: 0)
            indexPaths.append(indexPath)
        }
        
        collectionView.insertItems(at: indexPaths)
        collectionView.reloadItems(at: indexPaths)
        collectionView.scrollToItem(at: IndexPath.init(item: datasource.photos.count - 1, section: 0), at: UICollectionViewScrollPosition.top, animated: true)
    }
}









