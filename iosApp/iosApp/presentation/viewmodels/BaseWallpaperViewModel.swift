//
//  BaseWallpaperViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 18.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared

class BaseWallpaperViewModel: BaseViewModel {
    
    @Published
    var wallpaper: WallpaperIdentifiable? = nil
    
    var interactor: WallpaperInteractor
    
    init(interactor: WallpaperInteractor) {
        self.interactor = interactor
        super.init()
    }
 
    func toBookmark() {
        guard let wallpaper = self.wallpaper else { return }
        
        if wallpaper.uri == nil {
            download(url: wallpaper.hdurl) { error, uri in
                
                guard let uri = uri else {
                    Logger.e(error)
                    return
                }
                
                let model = WallpaperModel(
                    copyright: wallpaper.copyright,
                    date: wallpaper.date,
                    explanation: wallpaper.explanation,
                    hdurl: wallpaper.hdurl,
                    mediaType: wallpaper.mediaType,
                    serviceVersion: wallpaper.serviceVersion,
                    title: wallpaper.title,
                    url: wallpaper.url,
                    uri: uri.lastPathComponent
                )
                
                self.interactor.save(model: model)
                
                // Update UI
                DispatchQueue.main.async {
                    self.wallpaper = try? WallpaperIdentifiable.fromModel(model: model)
                    NotificationCenter.default.post(name: .BookmarkNotification, object: nil)
                }
            }
        } else {
            if let path = wallpaper.uri {
                try? FileManager.default.removeItem(atPath: path)
            }
            
            self.interactor.delete(date: dateToDateModel(string: wallpaper.date))
            
            // Update UI
            self.wallpaper?.uri = nil
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .BookmarkNotification, object: nil)
            }
        }
    }
    
    func dateToDateModel(string: String) -> DateModel {
        let date = string.components(separatedBy: "-")
        return DateModel(year: Int32(date[0])!, month: Int32(date[1])!, day: Int32(date[2])!)
    }
    
    private func download(url: String, completion: @escaping (Error?, URL?) -> Void) {
        guard let url = URL(string: url) else { return }
        
        let task = URLSession.shared.downloadTask(with: url) { (tempLocalUrl, response, error) in
            if let tempLocalUrl = tempLocalUrl, error == nil {
                if let statusCode = (response as? HTTPURLResponse)?.statusCode {
                    Logger.d("Successfully downloaded image. Status code: \(statusCode)")
                }
                do {
                    let documentDirectory = try FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: true)
                    let destinationUrl = documentDirectory.appendingPathComponent(url.lastPathComponent)
                    try FileManager.default.moveItem(at: tempLocalUrl, to: destinationUrl)
                    
                    Logger.d("Image saved to: \(destinationUrl)")
                    completion(nil, destinationUrl)
                } catch (let writeError) {
                    completion(writeError, nil)
                }
            } else {
                completion(error, nil)
            }
        }
        
        task.resume()
    }
    
}
