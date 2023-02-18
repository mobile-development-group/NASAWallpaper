//
//  Logger.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 18.02.2023.
//  Copyright © 2022 mdgroup. All rights reserved.
//

import Foundation
import shared

/**
 🐛 - Debug
 ℹ️ - Info
 📖 - Notice
 ⚠️ - Warning
 ⚡ - Critical
 🔥 - Error
 */
class Logger {
    static func d(_ msg: String?, file: String = #fileID, function: String = #function, line: Int = #line) {
        if let message = msg {
            print("🐛 \(makeTitle(file, function, line)): \(message)\n")
        }
    }
    
    static func e(_ error: String?, file: String = #fileID, function: String = #function, line: Int = #line) {
        if let error = error {
            print("🔥 \(makeTitle(file, function, line)) Error: \(error)\n")
        } else {
            print("🔥 \(makeTitle(file, function, line)) Error\n")
        }
    }
    
    static func e(_ error: Error?, file: String = #fileID, function: String = #function, line: Int = #line) {
        e(error.debugDescription, file: file, function: function, line: line)
    }
    
    static func e(_ error: KotlinException?, file: String = #fileID, function: String = #function, line: Int = #line) {
        e(error.debugDescription, file: file, function: function, line: line)
    }
    
    private static func makeTitle(_ file: String, _ function: String, _ line: Int) -> String{
        return "\(file.replacingOccurrences(of: ".swift", with: "")).\(function.replacingOccurrences(of: "()", with: "")):\(line)"
    }
}
