//
//  Reusable.swift
//  TradeRev-coding-challenge
//
//  Created by ZHITAO TIAN on 2018-03-21.
//  Copyright © 2018 ZHITAO TIAN. All rights reserved.
//

import Foundation

protocol Reusable {
    
    static var reuseIdentifier: String { get }
}

extension Reusable {
    
    static var reuseIdentifier: String {
        
        return String(describing: self)
    }
}

