//
//  CalendarViewmodel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 18.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared

class CalendarViewModel: BaseWallpaperViewModel {
    
    @Published
    var startDate: Date
    
    @Published
    var endDate: Date
    
    private var calendar = Calendar.current
    
    override init(interactor: WallpaperInteractor) {
        calendar.timeZone = TimeZone(identifier: "America/New_York")!
        let components = calendar.dateComponents([.year, .month, .day], from: Date())
        
        startDate = calendar.date(from: DateComponents(year: 1995, month: 6, day: 16))!
        endDate = calendar.startOfDay(for: Date())
        
        super.init(interactor: interactor)
        
        self.fetch(date: DateModel(year: Int32(components.year ?? 1995), month: Int32(components.month ?? 6), day: Int32(components.day ?? 16)))
    }
    
    func random() {
        let components = calendar.dateComponents([.year, .month, .day], from: Date())
        
        let year = Int.random(in: 1995...Int(components.year ?? 1995))
        
        var month: Int = 1
        switch year {
        case components.year:
            month = Int.random(in: 1...Int(components.month ?? 1))
        case 1995:
            month = Int.random(in: 6...12)
        default:
            month = Int.random(in: 1...12)
        }
        
        var day: Int = 1
        switch year {
        case components.year:
            if components.month == month {
                day = Int.random(in: 1...Int(components.day ?? 1))
                break
            }
        case 1995:
            if 6 == month {
                day = Int.random(in: 16...30)
                break
            }
        default:
            day = Int.random(in: 1...28)
        }
        
        self.fetch(date: DateModel(year: Int32(year), month: Int32(month), day: Int32(day)))
    }
    
    func fetch(date: Date) {
        let components = calendar.dateComponents([.year, .month, .day], from: date)
        
        fetch(date: DateModel(year: Int32(components.year!), month: Int32(components.month!), day: Int32(components.day!)))
    }
    
    private func fetch(date: DateModel) {
        showLoading()
        interactor.fetch(date: date) { result, error in
            if let result = result as? Result, let model = result.data {
                do {
                    self.wallpaper = try WallpaperIdentifiable.fromModel(model: model)
                } catch {
                    Logger.e(error)
                }
                self.hideLoading()
            }
        }
    }
}
