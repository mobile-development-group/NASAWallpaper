//
//  WallpapersModule.swift
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

/// For make ViewModel with initial value from Screen
func getWallpaperViewModel(item: WallpaperIdentifiable) -> StateObject<WallpaperViewModel> {
    
    let interactor = koin.get(protocol: WallpaperInteractor.self) as! WallpaperInteractor
    
    let viewModel = WallpaperViewModel(interactor: interactor, data: item)
    
    return StateObject(wrappedValue: viewModel)
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
