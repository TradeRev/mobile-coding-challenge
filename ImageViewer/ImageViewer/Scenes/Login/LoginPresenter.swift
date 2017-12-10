//
//  LoginPresenter.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-08.
//  Copyright (c) 2017 Zakhar Sukhanov. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

protocol LoginPresentationLogic
{
  func presentPhotGrid()
  func presentError(error: Login.LoginError)
}

class LoginPresenter: LoginPresentationLogic
{
  weak var viewController: LoginDisplayLogic?
  
  // MARK: Do something
  
  func presentPhotGrid() {
    DispatchQueue.main.async {
      self.viewController?.displayPhotoGrid()
    }
  }
  
  func presentError(error: Login.LoginError) {
    DispatchQueue.main.async {
      self.viewController?.displayError(error)
    }
  }
}
