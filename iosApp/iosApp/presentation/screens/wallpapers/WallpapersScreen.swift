//
//  WallpapersScreen.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI

struct WallpapersScreen: View {
    
    @StateObject
    var viewModel = getWallpapersViewModel()
    
    @Binding
    var tabSelection: Tab
    
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    var body: some View {
        NavigationView {
            ZStack {
                ScrollView {
                    LazyVGrid(columns: columns, spacing: 16) {
                        ForEach(viewModel.wallpapers.indices, id: \.self) { index in
                            NavigationLink(destination: WallpaperScreen(item: viewModel.wallpapers[index])) {
                                WallpaperItem(item: viewModel.wallpapers[index])
                            }
                            .onAppear(perform: {
                                viewModel.refresh(index: index)
                            })
                        }
                    }
                }
                
                if viewModel.isLoading {
                    ProgressView()
                }
            }
            .padding(.horizontal)
            .navigationBarHidden(true)
        }
    }
}

struct WallpapersScreen_Previews: PreviewProvider {
    static var previews: some View {
        WallpapersScreen(tabSelection: .constant(Tab.wallpapers))
    }
}
