//
//  ViewModelsModule.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared
import SwiftUI

func getWallpapersViewModel() -> WallpapersViewModel {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    return WallpapersViewModel(interactor: interactor)
}

func getWallpaperViewModel(item: WallpaperIdentifiable) -> WallpaperViewModel {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    let viewModel = WallpaperViewModel(interactor: interactor, data: item)
    
    return viewModel
}

func getCalendarViewModel() -> CalendarViewModel {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    return CalendarViewModel(interactor: interactor)
}

func getBookmarksViewModel() -> BookmarksViewModel {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    return BookmarksViewModel(interactor: interactor)
}

func getMoreAppsViewModel() -> MoreAppsViewModel {
    return MoreAppsViewModel()
}

func getLicensesViewModel() -> LicensesViewModel {
    
    let interactor = koin.get(protocol: LicenseInteractor.self) as! LicenseInteractor
    
    return LicensesViewModel(interactor: interactor)
}
