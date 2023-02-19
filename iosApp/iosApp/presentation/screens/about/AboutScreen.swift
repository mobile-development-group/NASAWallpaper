//
//  AboutScreenView.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 29.03.2022.
//  Copyright © 2022 mdgroup. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    
    @State
    private var isShowShareAppSheet = false
    
    var body: some View {
        let label = "\(Bundle.main.displayName) - \("version".localized()): \(Bundle.main.version) (\(Bundle.main.versionCode))"
        
        Form {
            VStack(alignment: .center) {
                Image("AppIconAbout")
                    .resizable()
                    .frame(width: 120.0, height: 120.0)
                    .cornerRadius(16)
                    .padding(.top, 20)
                Text(label)
                    .padding(.top)
                Text("about_app_description".localized())
                    .foregroundColor(.secondary)
                    .font(.system(size: 16))
                    .padding(.vertical)
            }
            .padding(.horizontal, -16)
            .listRowBackground(Color.ui.background)
            
            Section(header: Text("legal_information".localized())) {
                FormText(title: "privacy_policy".localized())
                    .onTapGesture { openUrl(string: "privacy_policy_url".localized()) }
                
                FormText(title: "terms_of_use".localized())
                    .onTapGesture { openUrl(string: "terms_of_use_url".localized()) }
                
                
                NavigationLink(destination: LicensesScreen()) {
                    Text("licenses".localized())
                }
            }
            Section {
                FormText(title: "open_on_github".localized())
                    .onTapGesture { openUrl(string: "github_url".localized()) }
            }
            Section {
                FormText(title: "share_app".localized())
                    .onTapGesture { self.isShowShareAppSheet.toggle() }
            }
        }
        .navigationTitle("about_app".localized())
        .navigationBarTitleDisplayMode(.inline)
        .background(Color.ui.background)
        .navigationBarBackground{
            Color.ui.background
        }
        .sheet(isPresented: $isShowShareAppSheet) {
            let items: [String] = [
                "\("share_app".localized()) \"\(Bundle.main.displayName)\": https://apps.apple.com/app/\(Constants.APP_ID)"
            ]
            TransactionActivityView(activityItems: items)
        }
    }
    
    func openUrl(string: String) {
        if let url = URL(string: string) {
            UIApplication.shared.open(url)
        }
    }
}

struct AboutScreenView_Previews: PreviewProvider {
    static var previews: some View {
        AboutScreen()
    }
}
