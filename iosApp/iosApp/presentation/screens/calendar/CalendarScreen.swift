//
//  CalendarScreen.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI

struct CalendarScreen: View {
    
    @Binding
    var tabSelection: Tab
    
    @StateObject
    private var viewModel: CalendarViewModel = getCalendarViewModel()
    
    var body: some View {
        ZStack(alignment: .topTrailing) {
            if let wallpaper = viewModel.wallpaper {
                WallpaperDetailsView(
                    item: wallpaper,
                    onClickBookmark: { viewModel.toBookmark() }
                )
            }
            
            // For not use NavigationView on this screen
            HStack {
                Button(
                    action: {
                        viewModel.random()
                    },
                    label: {
                        Image(systemName: "questionmark")
                    }
                )
                Button(
                    action: {
                        viewModel.random()
                    },
                    label: {
                        Image(systemName: "calendar")
                    }
                )
                .padding(.leading, 8)
            }
            .font(.title2)
            .padding(.top, 54)
            .padding(.trailing, 16)
            
            if viewModel.isLoading {
                ProgressView()
            }
        }
        .ignoresSafeArea()
    }
}

struct CalendarScreen_Previews: PreviewProvider {
    static var previews: some View {
        CalendarScreen(tabSelection: .constant(Tab.calendar))
    }
}
