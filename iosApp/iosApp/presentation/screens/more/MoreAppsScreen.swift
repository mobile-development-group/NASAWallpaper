//
//  MoreAppsScreenView.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 29.03.2022.
//  Copyright © 2022 mdgroup. All rights reserved.
//

import SwiftUI

struct MoreAppsScreen: View {
    
    @StateObject
    var viewModel: MoreAppsViewModel = getMoreAppsViewModel()
    
    var body: some View {
        let apps = viewModel.appIdentifiables
        
        VStack(alignment: .leading) {
            List(apps.indices, id: \.self) { index in
                MoreAppItem(item: apps[index])
                    .onTapGesture { viewModel.selectItem(index: index) }
            }
            .navigationTitle("more_apps".localized())
            .navigationBarTitleDisplayMode(.inline)
            .listStyle(SidebarListStyle())
        }
    }
}

struct MoreAppItem: View {
    
    var item: MoreAppIdentifiable
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack(alignment: .center) {
                Image(item.imageRes)
                    .resizable()
                    .frame(width: 64, height: 64)
                    .scaledToFill()
                    .padding(.top)
                    .padding(.horizontal)
                VStack(alignment: .leading) {
                    Text(item.name)
                        .font(.system(size: 18))
                        .fontWeight(.medium)
                    Text("open_in_store".localized())
                        .font(.system(size: 16))
                        .foregroundColor(Color.accentColor)
                        .padding(.top, 2)
                }
            }
            Text(item.description)
                .lineLimit(nil)
                .fixedSize(horizontal: false, vertical: true)
                .padding()
        }
        .frame(
            minWidth: 0,
            maxWidth: .infinity,
            minHeight: 0,
            maxHeight: .none,
            alignment: .topLeading
        )
        .background(Color.ui.primary)
        .clipShape(RoundedRectangle(cornerRadius:10))
    }
}

struct MoreAppsScreenView_Previews: PreviewProvider {
    static var previews: some View {
        return MoreAppsScreen()
    }
}
