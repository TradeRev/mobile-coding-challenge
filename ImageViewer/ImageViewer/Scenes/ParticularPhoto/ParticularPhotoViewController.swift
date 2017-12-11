//
//  ParticularPhotoViewController.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-10.
//  Copyright (c) 2017 Zakhar Sukhanov. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit
import UnsplashKit
import AlamofireImage

protocol ParticularPhotoDisplayLogic: class
{
}

class ParticularPhotoViewController: UIViewController, ParticularPhotoDisplayLogic
{
  var interactor: ParticularPhotoBusinessLogic?
  var router: (NSObjectProtocol & ParticularPhotoRoutingLogic & ParticularPhotoDataPassing)?

  @IBOutlet weak var photoImage: UIImageView!
  
  fileprivate var activityIndicator: UIActivityIndicatorView?
  
  // MARK: Object lifecycle
  
  override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?)
  {
    super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    setup()
  }
  
  required init?(coder aDecoder: NSCoder)
  {
    super.init(coder: aDecoder)
    setup()
  }
  
  // MARK: Setup
  
  private func setup()
  {
    let viewController = self
    let interactor = ParticularPhotoInteractor()
    let presenter = ParticularPhotoPresenter()
    let router = ParticularPhotoRouter()
    viewController.interactor = interactor
    viewController.router = router
    interactor.presenter = presenter
    presenter.viewController = viewController
    router.viewController = viewController
    router.dataStore = interactor
  }
    
  // MARK: View lifecycle
  
  override func viewDidLoad()
  {
    super.viewDidLoad()
    
    let swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(handleGesture))
    swipeLeft.direction = .left
    self.view.addGestureRecognizer(swipeLeft)
    
    let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(handleGesture))
    swipeRight.direction = .right
    self.view.addGestureRecognizer(swipeRight)
    
    if let photo = interactor?.doGetCurrentPhoto() {
      showPhoto(photo)
    }
  }
  
  
  override func willMove(toParentViewController parent: UIViewController?) {
    super.willMove(toParentViewController: parent)
    
    if parent == nil{
      router?.routeToPhotGrid()
    }
  }
  
  // MARK: Private
  
  @objc fileprivate func handleGesture(gesture: UISwipeGestureRecognizer) -> Void {
    if gesture.direction == UISwipeGestureRecognizerDirection.right {
      if let photo = interactor?.doGetPreviousPhoto() {
        self.photoImage.swipeAnimation(toRight: false)
        showPhoto(photo)
      }
    }
    else if gesture.direction == UISwipeGestureRecognizerDirection.left {
      if let photo = interactor?.doGetNextPhoto() {
        self.photoImage.swipeAnimation(toRight: true)
        showPhoto(photo)
      }
    }
  }
  
  fileprivate func showPhoto(_ photo: Photo) {
    if let fullURL = photo.urls?.full {
      displayProgress()
      photoImage.af_setImage(withURL: URL(string: fullURL)!, placeholderImage: UIImage(), imageTransition: .crossDissolve(0.2)
        , completion: { image in
          self.stopProgress()
      })
    }
  }

  
  fileprivate func displayProgress() {
    let activityIndicator = UIActivityIndicatorView(activityIndicatorStyle: .whiteLarge)
    activityIndicator.color = .gray
    activityIndicator.frame = (photoImage?.bounds)!
    
    photoImage?.addSubview(activityIndicator)
    activityIndicator.startAnimating()
    
    self.activityIndicator = activityIndicator // To stop it somewhere
  }

  
  fileprivate func stopProgress() {
    if let ai = self.activityIndicator {
      ai.removeFromSuperview()
    }
  }
}

extension UIView {
  func swipeAnimation(toRight: Bool, duration: TimeInterval = 0.5, completionDelegate: AnyObject? = nil) {
    
    let swipeTransition = CATransition()
    
    if let delegate: AnyObject = completionDelegate {
      swipeTransition.delegate = delegate as? CAAnimationDelegate
    }
    
    swipeTransition.type = kCATransitionPush
    swipeTransition.subtype = toRight ? kCATransitionFromRight : kCATransitionFromLeft
    swipeTransition.duration = duration
    swipeTransition.timingFunction = CAMediaTimingFunction(name: kCAMediaTimingFunctionEaseInEaseOut)
    swipeTransition.fillMode = kCAFillModeRemoved
    
    self.layer.add(swipeTransition, forKey: "swipeTransition")
  }
}
