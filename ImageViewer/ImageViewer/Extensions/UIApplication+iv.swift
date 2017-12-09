//
//  UIApplication+iv.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-09.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit

extension UIApplication {
  func instantiateAndShow<T: UIViewController>(controllerID: String, animated: Bool = true, initializer:((_ vc: T) -> Void)? = nil) -> T?
  {
    guard let rootController = (UIApplication.shared.delegate as? AppDelegate)?.window?.rootViewController as? UINavigationController else {
      Log.error("FAILED TO LOAD ROOT CONTROLLER")
      return nil
    }
    
    if let vc = R.storyboard.main().instantiateViewController(withIdentifier: controllerID) as? T {
      initializer?(vc)
      rootController.setViewControllers([vc], animated: true)
      return vc
    }
    
    return nil
  }
}
