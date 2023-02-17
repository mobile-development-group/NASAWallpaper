//
//  WallpapersModule.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared

func getWallpapersViewModel() -> WallpapersViewModel {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    return WallpapersViewModel(interactor: interactor)
}

func getWallpaperViewModel() -> WallpaperViewModel {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    return WallpaperViewModel(interactor: interactor)
}

func getBookmarksViewModel() -> BookmarksViewModel {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    return BookmarksViewModel(interactor: interactor)
}
