//
//  WallpaperViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import shared
import UIKit

class WallpaperViewModel : BaseViewModel {
    
    private var interactor: WallpaperInteractor
    
    private var data: WallpaperIdentifiable
    
    @Published
    var wallpaper: WallpaperIdentifiable? = nil
    
    init(interactor: WallpaperInteractor, data: WallpaperIdentifiable) {
        self.interactor = interactor
        self.data = data
        
        super.init()
        
        self.fetch()
    }
    
    func fetch() {
        let date = data.date.components(separatedBy: "-")
        
        // Check cache
        interactor.fetch(date: DateModel(year: Int32(date[0])!, month: Int32(date[1])!, day: Int32(date[2])!)) { result, error in
            if let result = result as? Result, let model = result.data {
                do {
                    self.wallpaper = try WallpaperIdentifiable.fromModel(model: model)
                } catch {
                    Logger.e(error)
                    self.wallpaper = self.data
                }
            } else {
                self.wallpaper = self.data
            }
        }
    }
    
    func save() {
        guard let wallpaper = self.wallpaper else { return }
        
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
            
            DispatchQueue.main.async {
                // Update UI
                self.wallpaper = try? WallpaperIdentifiable.fromModel(model: model)
                NotificationCenter.default.post(name: .BookmarkNotification, object: nil)
            }
        }
    }
    
    func share() {
        guard let wallpaper = self.wallpaper else { return }
        
        download(url: wallpaper.hdurl) { error, uri in
            
            guard let uri = uri else {
                Logger.e(error)
                return
            }
            
            
            
        }
    }
    
    func download(url: String, completion: @escaping (Error?, URL?) -> Void) {
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
