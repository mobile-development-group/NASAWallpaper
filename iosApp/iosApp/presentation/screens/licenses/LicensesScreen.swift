//
//  LicensesScreen.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 19.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI

struct LicensesScreen: View {
    
    @StateObject
    var viewModel: LicensesViewModel = getLicensesViewModel()
    
    var body: some View {
        let licenses = viewModel.licenseIdentifiables
        
        VStack(alignment: .leading) {
            List(licenses.indices, id: \.self) { index in
                VStack(alignment: .leading) {
                    Text(licenses[index].title).padding()
                }
                .onTapGesture { openUrl(string: licenses[index].url) }
                .frame(
                    maxWidth: .infinity,
                    maxHeight: 44,
                    alignment: .topLeading
                )
                .background(Color.ui.primary)
                .clipShape(RoundedRectangle(cornerRadius:10))
            }
            .navigationTitle("licenses".localized())
            .navigationBarTitleDisplayMode(.inline)
            .listStyle(SidebarListStyle())
        }
    }
    
    func openUrl(string: String) {
        if let url = URL(string: string) {
            UIApplication.shared.open(url)
        }
    }
}

struct LicensesScreen_Previews: PreviewProvider {
    static var previews: some View {
        LicensesScreen()
    }
}
