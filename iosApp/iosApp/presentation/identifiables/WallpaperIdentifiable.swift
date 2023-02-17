//
//  WallpaperIdentifiable.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation

struct WallpaperIdentifiable: Identifiable, Hashable {
    var copyright: String?
    var date: String
    var explanation: String?
    var hdurl: String
    var mediaType: String?
    var serviceVersion: String?
    var title: String
    var url: String?
    
    var uri: String?
    
    let id = UUID()    
}
