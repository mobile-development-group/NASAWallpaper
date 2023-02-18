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
    
    private var interactor: WallpaperInteractor
    
    @Published
    var wallpapers: [WallpaperIdentifiable] = []
    
    init(interactor: WallpaperInteractor) {
        self.interactor = interactor
        
        super.init()
                
        self.fetch()
    }
    
    func fetch() {
        showLoading()
        do {
            wallpapers = try interactor.getAll().map({ model in
                return try WallpaperIdentifiable.fromModel(model: model)
            })
        } catch {
            Logger.e(error)
        }
        hideLoading()
    }
}
