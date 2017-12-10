//
//  PhotoDataStorage.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-10.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import Foundation
import UnsplashKit
import PagedArray

protocol PhotoDataStorage
{
  var photos: PagedArray<Photo>? { get }
  
  func loadPhotosIfNeededForIndex(_ idx: Int)
}

