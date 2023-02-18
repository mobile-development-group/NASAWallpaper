//
//  ShareSheet.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 18.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import SwiftUI

/// Sheet for show ShareActivityItemSource
struct ShareSheet: UIViewControllerRepresentable {
    
    let text: String
    let image: UIImage
          
    func makeUIViewController(context: Context) -> UIActivityViewController {
        let itemSource = ShareActivityItemSource(shareText: text, shareImage: image)
        
        let activityItems: [Any] = [text, image, itemSource]
        
        let controller = UIActivityViewController(
            activityItems: activityItems,
            applicationActivities: nil)
        
        return controller
    }
      
    func updateUIViewController(_ vc: UIActivityViewController, context: Context) {
    }
}
