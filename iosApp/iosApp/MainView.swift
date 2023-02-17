//
//  MainView.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI
import shared

struct MainView: View {
    
    @State
    private var selection = 0
    
    var body: some View {
        TabView(selection: $selection) {
            WallpapersScreen(tabSelection: $selection)
                .tabItem{
                    Label("wallpapers".localized(), systemImage: selection == 0 ? "globe.americas.fill" : "globe.americas")
                }
                .tag(0)
            CalendarScreen(tabSelection: $selection)
                .tabItem{
                    Label("calendar".localized(), systemImage: "calendar")
                }
                .tag(1)
            BookmarksScreen(tabSelection: $selection)
                .tabItem{
                    Label("bookmarks".localized(), systemImage: selection == 2 ? "bookmark.fill" : "bookmark")
                }
                .tag(2)
            SettingsScreen(tabSelection: $selection)
                .tabItem{
                    Label("settings".localized(), systemImage: "gear")
                }
                .tag(3)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
