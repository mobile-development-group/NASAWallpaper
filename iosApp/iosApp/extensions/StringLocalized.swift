//
//  StringLocalized.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation

extension String {
        
    func localized() -> String {
        return NSLocalizedString(self, comment: "Localized string by id: \(self).")
    }
}
