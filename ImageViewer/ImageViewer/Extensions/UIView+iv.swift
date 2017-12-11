//
//  UIView+iv.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-11.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit

extension UIView {
  func installSubview(_ subview: UIView) {
    subview.translatesAutoresizingMaskIntoConstraints = false
    self.addSubview(subview)
    NSLayoutConstraint.activate([
      subview.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 0),
      subview.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: 0),
      subview.topAnchor.constraint(equalTo: self.topAnchor, constant: 0),
      subview.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: 0)])
  }
}
