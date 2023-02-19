//
//  Bundle.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 29.03.2022.
//  Copyright © 2022 mdgroup. All rights reserved.
//

import Foundation

extension Bundle {
    var displayName: String {
        return object(forInfoDictionaryKey: "CFBundleDisplayName") as? String ?? ""
    }
    
    var version: String {
        return object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String ?? ""
    }
    
    var versionCode: String {
        return object(forInfoDictionaryKey: "CFBundleVersion") as? String ?? ""
    }
    
    var isDebug: Bool {
        #if DEBUG
            return true
        #else
            return false
        #endif
    }
}
