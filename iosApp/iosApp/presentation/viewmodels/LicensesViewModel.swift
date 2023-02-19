//
//  LicensesViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 19.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared

class LicensesViewModel: BaseViewModel {
    
    private var interactor: LicenseInteractor
    
    @Published
    var licenseIdentifiables: [LicenseIdentifiable] = []
    
    init(interactor: LicenseInteractor) {
        self.interactor = interactor
        
        super.init()
        
        self.fetch()
    }
    
    private func fetch() {
        let fileName = "licenses.json"
        do {
            if let url = Bundle.main.path(forAuxiliaryExecutable: fileName),
               let data = try String(contentsOfFile: url).data(using: .utf8),
               let json = String(data: data, encoding: .utf8) {
                licenseIdentifiables = interactor.fetch(json: json).map({ model in
                    LicenseIdentifiable(title: model.title, url: model.url)
                })
            }
        } catch {
            Logger.e(error)
        }
    }
    
}
