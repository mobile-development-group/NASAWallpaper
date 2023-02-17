//
//  WallpapersViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared

class WallpapersViewModel : BaseViewModel {
    
    var interactor: WallpaperInteractor
    
    @Published
    var wallpapers: [WallpaperIdentifiable] = []
    
    var pageSize: Int = 10    
    var currentPage: Int = 1
    
    init(interactor: WallpaperInteractor) {
        self.interactor = interactor
        
        super.init()
        
        hideLoading()
        fetch()
    }
    
    func fetch() {
        if isLoading { return }
        
        let calendar = Calendar.current
        
        if let startDate = Calendar.current.date(byAdding: DateComponents(day: -1 * currentPage * (pageSize - 1)), to: Date()),
           let endDate = Calendar.current.date(byAdding: DateComponents(day: -1 * (currentPage - 1) * pageSize), to: Date()) {
            
            let startDateComponents = calendar.dateComponents([.year, .month, .day], from: startDate)
            let endDateComponents = calendar.dateComponents([.year, .month, .day], from: endDate)
            
            showLoading()
            interactor.fetch(
                from: DateModel(year: Int32(startDateComponents.year ?? 1970), month: Int32(startDateComponents.month ?? 1), day: Int32(startDateComponents.day ?? 1)),
                to: DateModel(year: Int32(endDateComponents.year ?? 1970), month: Int32(endDateComponents.month ?? 1), day: Int32(endDateComponents.day ?? 1))
            ) { result, error in
                if let result = result as? Result {
                    if (result.isSuccess){
                        let list = (result.data as? [WallpaperModel] ?? [])
                            .map({ model in
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
                            .filter { item in
                                !self.wallpapers.contains(item)
                            }
                        self.wallpapers.append(contentsOf: list)
                        
                        self.currentPage += 1
                        
                        self.hideLoading()
                    } else {
                        Logger.shared.e(msg: result.ex?.message)
                        //                    Logger.e(result.ex?.message)
                        self.hideLoading()
                    }
                }
                if (error != nil){
                    //                Logger.e(error)
                    self.hideLoading()
                }
            }
        }
    }
}
