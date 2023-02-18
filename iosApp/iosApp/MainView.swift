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
    private var selection: Tab = .wallpapers
    
    var body: some View {
        TabView(selection: $selection) {
            WallpapersScreen(tabSelection: $selection)
                .tag(Tab.wallpapers)
                .tabItem{
                    Label("wallpapers".localized(), systemImage: selection == Tab.wallpapers ? "globe.americas.fill" : "globe.americas")
                }
            CalendarScreen(tabSelection: $selection)
                .tag(Tab.calendar)
                .tabItem{
                    Label("calendar".localized(), systemImage: "calendar")
                }
            BookmarksScreen(tabSelection: $selection)
                .tag(Tab.bookmarks)
                .tabItem{
                    Label("bookmarks".localized(), systemImage: selection == Tab.bookmarks ? "bookmark.fill" : "bookmark")
                }
            SettingsScreen(tabSelection: $selection)
                .tag(Tab.settings)
                .tabItem{
                    Label("settings".localized(), systemImage: "gear")
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
