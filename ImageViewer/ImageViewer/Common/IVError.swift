//
//  IVError.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-08.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit

class IVError: Error {
  let description: String
  
  init(desc: String) {
    self.description = desc
  }
}
