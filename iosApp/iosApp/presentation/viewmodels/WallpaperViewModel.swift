//
//  WallpaperViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared

class WallpaperViewModel : BaseViewModel {
    
    var interactor: WallpaperInteractor
    var wallpaper: WallpaperIdentifiable? = nil
    
    init(interactor: WallpaperInteractor) {
        self.interactor = interactor
    }
    
    func save(wallpaper: WallpaperIdentifiable) {
        interactor.save(
            model: WallpaperModel(
                copyright: wallpaper.copyright,
                date: wallpaper.date,
                explanation: wallpaper.explanation,
                hdurl: wallpaper.hdurl,
                mediaType: wallpaper.mediaType,
                serviceVersion: wallpaper.serviceVersion,
                title: wallpaper.title,
                url: wallpaper.url,
                uri: wallpaper.uri
            )
        )
        
        NotificationCenter.default.post(name: .BookmarkNotification, object: nil)
    }
    
    func share(wallpaper: WallpaperIdentifiable) {
        
    }
}
