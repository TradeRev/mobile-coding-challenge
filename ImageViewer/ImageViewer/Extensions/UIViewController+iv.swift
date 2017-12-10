//
//  UIViewController+iv.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-10.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit

extension UIViewController {
  func displayError(_ errorMessage: String) {
    let alert = UIAlertController(title: "ERROR", message: errorMessage, preferredStyle: .alert)
    alert.addAction(UIAlertAction(title: "OK", style: .cancel))
    self.present(alert, animated: true)
  }
}
