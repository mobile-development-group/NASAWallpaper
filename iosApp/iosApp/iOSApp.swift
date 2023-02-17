//
//  iOSApp.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI
import shared

var koin: Koin_coreKoin!

@main
struct iOSApp: App {
    
    init() {
        koin = KoinKt.doInitKoin().koin
    }
    
	var body: some Scene {
		WindowGroup {
            MainView()
		}
	}
}
