//
//  Logger.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 18.02.2023.
//  Copyright ÂŠ 2022 mdgroup. All rights reserved.
//

import Foundation
import shared

/**
 đ - Debug
 âšī¸ - Info
 đ - Notice
 â ī¸ - Warning
 âĄ - Critical
 đĨ - Error
 */
class Logger {
    static func d(_ msg: String?, file: String = #fileID, function: String = #function, line: Int = #line) {
        if let message = msg {
            print("đ \(makeTitle(file, function, line)): \(message)\n")
        }
    }
    
    static func e(_ error: String?, file: String = #fileID, function: String = #function, line: Int = #line) {
        if let error = error {
            print("đĨ \(makeTitle(file, function, line)) Error: \(error)\n")
        } else {
            print("đĨ \(makeTitle(file, function, line)) Error\n")
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
