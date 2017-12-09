//
//  PhotoGridRouter.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-09.
//  Copyright (c) 2017 Zakhar Sukhanov. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

@objc protocol PhotoGridRoutingLogic
{
  //func routeToSomewhere(segue: UIStoryboardSegue?)
}

protocol PhotoGridDataPassing
{
  var dataStore: PhotoGridDataStore? { get }
}

class PhotoGridRouter: NSObject, PhotoGridRoutingLogic, PhotoGridDataPassing
{
  weak var viewController: PhotoGridViewController?
  var dataStore: PhotoGridDataStore?
  
  // MARK: Routing
  
  //func routeToSomewhere(segue: UIStoryboardSegue?)
  //{
  //  if let segue = segue {
  //    let destinationVC = segue.destination as! SomewhereViewController
  //    var destinationDS = destinationVC.router!.dataStore!
  //    passDataToSomewhere(source: dataStore!, destination: &destinationDS)
  //  } else {
  //    let storyboard = UIStoryboard(name: "Main", bundle: nil)
  //    let destinationVC = storyboard.instantiateViewController(withIdentifier: "SomewhereViewController") as! SomewhereViewController
  //    var destinationDS = destinationVC.router!.dataStore!
  //    passDataToSomewhere(source: dataStore!, destination: &destinationDS)
  //    navigateToSomewhere(source: viewController!, destination: destinationVC)
  //  }
  //}

  // MARK: Navigation
  
  //func navigateToSomewhere(source: PhotoGridViewController, destination: SomewhereViewController)
  //{
  //  source.show(destination, sender: nil)
  //}
  
  // MARK: Passing data
  
  //func passDataToSomewhere(source: PhotoGridDataStore, destination: inout SomewhereDataStore)
  //{
  //  destination.name = source.name
  //}
}
