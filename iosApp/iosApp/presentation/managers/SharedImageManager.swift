//
//  WallpaperDetailsViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 18.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import SwiftUI

class SharedImageManager : ObservableObject {
    
    private var data: WallpaperIdentifiable
    
    @Published
    var image: Image? = nil
    
    init(data: WallpaperIdentifiable) {
        self.data = data
        
        self.fetch()
    }
    
    func fetch() {
        if let path = data.uri, let image = UIImage(contentsOfFile: path) {
            self.image = Image(uiImage: image)
        }
    }
}
