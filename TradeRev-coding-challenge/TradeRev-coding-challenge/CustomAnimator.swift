//
//  CustomAnimator.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-21.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import UIKit

class CustomAnimator: NSObject, UIViewControllerAnimatedTransitioning {
    func transitionDuration(using transitionContext: UIViewControllerContextTransitioning?) -> TimeInterval {
        return cAnimationDuration
    }
    func animateTransition(using transitionContext: UIViewControllerContextTransitioning) {
        guard let toController = transitionContext.viewController(forKey: .to) else {
            return
        }
        
        let containerView = transitionContext.containerView
        let duration = transitionDuration(using: transitionContext)
        
        containerView.addSubviewAndFadeIn(toController.view, with: duration) {
            transitionContext.completeTransition(!transitionContext.transitionWasCancelled)
        }
    }
}

