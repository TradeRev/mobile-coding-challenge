//
//  UIView+Animation.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-21.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import UIKit

let cAnimationDuration = 0.35

extension UIView {
    
    func fadeIn(with duration: TimeInterval = cAnimationDuration, completion: (() -> Void)? = nil) {
        if alpha >= 1.0 {
            return
        }
        
        alpha = 0.0
        isHidden = false
        
        UIView.animate(withDuration: duration, delay: 0.0, options: .curveEaseIn, animations: { [weak self] in
            self?.alpha = 1.0
        }) { (_) in
            completion?()
        }
    }
    
    func fadeOut(with duration: TimeInterval = cAnimationDuration, completion: (() -> Void)? = nil) {
        if alpha <= 0 {
            return
        }
        
        alpha = 1.0
        isHidden = false
        
        UIView.animate(withDuration: duration, delay: 0.0, options: .curveEaseOut, animations: { [weak self] in
            self?.alpha = 0.0
        }) { (_) in
            completion?()
        }
    }
    
    func addSubviewAndFadeIn(_ subview: UIView, with duration: TimeInterval = cAnimationDuration, completion: (() -> Void)? = nil) {
        addSubview(subview)
        subview.alpha = 0.0
        subview.fadeIn(with: duration, completion: completion)
    }
    
    func fadeOutAndRemove(subview: UIView, with duration: TimeInterval = cAnimationDuration, completion: (() -> Void)?) {
        subview.fadeOut(with: duration, completion: completion)
        subview.removeFromSuperview()
    }
}
