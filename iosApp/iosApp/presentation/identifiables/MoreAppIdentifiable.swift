//
//  MoreAppIdentifiable.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 01.04.2022.
//  Copyright © 2022 mdgroup. All rights reserved.
//

import Foundation

struct MoreAppIdentifiable: Identifiable {
    var name: String
    var description: String
    var appId: String
    var imageRes: String
    
    let id = UUID()
}
