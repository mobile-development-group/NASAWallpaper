//
//  WallpaperViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared
import UIKit

class WallpaperViewModel : BaseWallpaperViewModel {
    
    private var data: WallpaperIdentifiable
    
    init(interactor: WallpaperInteractor, data: WallpaperIdentifiable) {
        self.data = data
        
        super.init(interactor: interactor)
        
        self.fetch()
    }
    
    func fetch() {
        showLoading()
        interactor.fetch(date: dateToDateModel(string: self.data.date)) { result, error in
            if let result = result as? Result, let model = result.data {
                do {
                    self.wallpaper = try WallpaperIdentifiable.fromModel(model: model)
                } catch {
                    Logger.e(error)
                    self.wallpaper = self.data
                }
            } else {
                self.wallpaper = self.data
            }
            self.hideLoading()
        }
    }
    
    
}
