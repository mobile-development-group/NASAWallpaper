//
//  WallpaperMapper.swift
//  iosApp
//
//  Created by Pavel Kravchenko on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension WallpaperIdentifiable {
    
    static func fromModel(model: WallpaperModel) throws -> WallpaperIdentifiable {
        let documentDirectory = try FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: true)
        
        var path: URL?
        if let uri = model.uri {
            if #available(iOS 16.0, *) {
                path = documentDirectory.appending(path: uri)
            } else {
                path = documentDirectory.appendingPathComponent(uri, conformingTo: .image)
            }
        }
        
        return WallpaperIdentifiable(
            copyright: model.copyright,
            date: model.date,
            explanation: model.explanation,
            hdurl: model.hdurl,
            mediaType: model.mediaType,
            serviceVersion: model.serviceVersion,
            title: model.title,
            url: model.url,
            uri: path?.path ?? nil
        )
    }
}
