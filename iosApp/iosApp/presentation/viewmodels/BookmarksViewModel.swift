//
//  BookmarksViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared

class BookmarksViewModel : BaseViewModel {
    
    var interactor: WallpaperInteractor
    
    @Published
    var wallpapers: [WallpaperIdentifiable] = []
    
    init(interactor: WallpaperInteractor) {
        self.interactor = interactor
        
        super.init()
                
        fetch()
    }
    
    func fetch() {
        wallpapers = interactor.getAll().map({ model in
            WallpaperIdentifiable(
                copyright: model.copyright,
                date: model.date,
                explanation: model.explanation,
                hdurl: model.hdurl,
                mediaType: model.mediaType,
                serviceVersion: model.serviceVersion,
                title: model.title,
                url: model.url
            )
        })
        hideLoading()
    }
}
