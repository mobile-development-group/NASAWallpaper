//
//  LicenseIdentifiable.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 19.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation

struct LicenseIdentifiable: Identifiable {
    var title: String
    var url: String
    
    let id = UUID()
}
