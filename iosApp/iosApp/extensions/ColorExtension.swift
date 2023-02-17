//
//  ColorExtension.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI

extension Color {
    static let ui = Color.UI()
    
    struct UI {
        let background = Color("background")
        
        let primary = Color("primary")
        let icon = Color("icon")
        
        let error = Color("error")
        let green = Color("green")
        
        let surface = Color("surface")
    }
}

extension UIColor {
    static func random() -> UIColor {
        return UIColor(
           red: CGFloat(arc4random()) / CGFloat(UInt32.max),
           green: CGFloat(arc4random()) / CGFloat(UInt32.max),
           blue:  CGFloat(arc4random()) / CGFloat(UInt32.max),
           alpha: 1.0
        )
    }
}
